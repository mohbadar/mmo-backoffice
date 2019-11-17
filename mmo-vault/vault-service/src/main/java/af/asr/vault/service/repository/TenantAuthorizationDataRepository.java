package af.asr.vault.service.repository;


import af.asr.ApplicationName;
import af.asr.cassandra.core.CassandraSessionProvider;
import af.asr.security.RsaKeyPairFactory;
import af.asr.security.RsaPrivateKeyBuilder;
import af.asr.security.RsaPublicKeyBuilder;
import af.asr.vault.api.domain.ApplicationSignatureSet;
import af.asr.vault.api.domain.Signature;
import af.asr.vault.service.config.AnubisConstants;
import af.asr.vault.service.config.TenantSignatureRepository;
import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This repository saves identity manager public keys and application private keys for use in authentication
 * of tokens. The data is saved in a separate cassandra table for each service. Keys are timestamped so that
 * they can be rotated without stopping the services.
 *
 * Operations which change the key data are synchronized for several reasons:
 *
 * <ul>
 * <li> Multiple parallel calls to create a signature can cause one signature to be saved for an application, but
 * a different one to have been returned for the same application, which in turn can lead to authentication
 * failures in isis.</li>
 * <li>Multiple parallel calls to create a table in cassandra can lead to
 * an exception of the form "org.apache.cassandra.exceptions.ConfigurationException: Column family ID mismatch"
 * However, synchronizing this will only work within the context of one service and is inadequate if multiple
 * services are running. More protection against multiple calls to initialize are still needed and should be
 * implemented within the provisioner.</li>
 * </ul>
 */
//
@Component
public class TenantAuthorizationDataRepository implements TenantSignatureRepository {
    private static final String AUTHORIZATION_TABLE_SUFFIX = "_authorization_v1_data";
    private static final String AUTHORIZATION_INDEX_SUFFIX = "_authorization_v1_valid_index";
    private static final String TIMESTAMP_COLUMN = "timestamp";
    private static final String VALID_COLUMN = "valid";
    private static final String IDENTITY_MANAGER_PUBLIC_KEY_MOD_COLUMN = "identity_manager_public_key_mod";
    private static final String IDENTITY_MANAGER_PUBLIC_KEY_EXP_COLUMN = "identity_manager_public_key_exp";
    private static final String APPLICATION_PRIVATE_KEY_MOD_COLUMN = "application_private_key_mod";
    private static final String APPLICATION_PRIVATE_KEY_EXP_COLUMN = "application_private_key_exp";
    private static final String APPLICATION_PUBLIC_KEY_MOD_COLUMN = "application_public_key_mod";
    private static final String APPLICATION_PUBLIC_KEY_EXP_COLUMN = "application_public_key_exp";

    private final String tableName;
    private final String indexName;
    private final CassandraSessionProvider cassandraSessionProvider;

    //So that the query only has to be prepared once and the Cassandra driver stops writing warnings into my logfiles.
    private final Map<String, Select.Where> timestampToSignatureQueryMap = new HashMap<>();
    private final Logger logger;

    @Autowired
    public TenantAuthorizationDataRepository(
            final ApplicationName applicationName,
            final CassandraSessionProvider cassandraSessionProvider,
            final @Qualifier(AnubisConstants.LOGGER_NAME) Logger logger)
    {
        tableName = applicationName.getServiceName() + AUTHORIZATION_TABLE_SUFFIX;
        indexName = applicationName.getServiceName() + AUTHORIZATION_INDEX_SUFFIX;
        this.cassandraSessionProvider = cassandraSessionProvider;
        this.logger = logger;
    }

    /**
     *
     * @param timestamp The timestamp to save the signatures for.  When rotating keys, this will be used to delete keys
     *                  which are being rotated out.
     *
     * @param identityManagerSignature The public keys of the identity manager.  These keys will be used to authenticate
     *                                 the user via the token provided in most requests.
     *
     * @return The signature containing the public keys of the application.  This is *not* the signature passed in
     * for the identity manager.
     */
    public synchronized Signature createSignatureSet(final String timestamp, final Signature identityManagerSignature) {
        Assert.notNull(timestamp);
        Assert.notNull(identityManagerSignature);

        // getSignatureSet (below) queries the table, so make sure it's created first.
        final Session session = cassandraSessionProvider.getTenantSession();
        createTable(session);

        // if there is already a signature set for the identity manager then return it rather than create a new one.
        // Having multiple signature sets floating around for the same application, can cause problems because the
        // application may sign it's tokens with one signature, only to have identity check those tokens with a different
        // signature.
        final Optional<ApplicationSignatureSet> signatureSet = getSignatureSet(timestamp);
        if (signatureSet.isPresent() &&
                signatureSet.map(x -> x.getIdentityManagerSignature().equals(identityManagerSignature)).orElse(false))
            return signatureSet.get().getApplicationSignature();


        //TODO: add validation to make sure this timestamp is more recent than any already stored.
        logger.info("Creating application signature set for timestamp '" + timestamp +
                "'. Identity manager signature is: " + identityManagerSignature);

        final RsaKeyPairFactory.KeyPairHolder applicationSignature = RsaKeyPairFactory.createKeyPair();


        createEntry(session,
                timestamp,
                identityManagerSignature.getPublicKeyMod(),
                identityManagerSignature.getPublicKeyExp(),
                applicationSignature.getPrivateKeyMod(),
                applicationSignature.getPrivateKeyExp(),
                applicationSignature.getPublicKeyMod(),
                applicationSignature.getPublicKeyExp());

        return new Signature(applicationSignature.getPublicKeyMod(), applicationSignature.getPublicKeyExp());
    }

    public synchronized void deleteSignatureSet(final String timestamp) {
        Assert.notNull(timestamp);
        //Don't actually delete, just invalidate, so that if someone starts coming at me with an older keyset, I'll
        //know what's happening.
        logger.info("Invalidationg signature set for timestamp '" + timestamp + "'.");
        final Session session = cassandraSessionProvider.getTenantSession();
        invalidateEntry(session, timestamp);
    }

    public Optional<Signature> getApplicationSignature(final String timestamp) {
        Assert.notNull(timestamp);

        return getRow(timestamp).map(TenantAuthorizationDataRepository::mapRowToApplicationSignature);
    }

    private void createTable(final @Nonnull Session tenantSession) {

        final String createTenantsTable = SchemaBuilder
                .createTable(tableName)
                .ifNotExists()
                .addPartitionKey(TIMESTAMP_COLUMN, DataType.text())
                .addColumn(VALID_COLUMN, DataType.cboolean())
                .addColumn(IDENTITY_MANAGER_PUBLIC_KEY_MOD_COLUMN, DataType.varint())
                .addColumn(IDENTITY_MANAGER_PUBLIC_KEY_EXP_COLUMN, DataType.varint())
                .addColumn(APPLICATION_PRIVATE_KEY_MOD_COLUMN, DataType.varint())
                .addColumn(APPLICATION_PRIVATE_KEY_EXP_COLUMN, DataType.varint())
                .addColumn(APPLICATION_PUBLIC_KEY_MOD_COLUMN, DataType.varint())
                .addColumn(APPLICATION_PUBLIC_KEY_EXP_COLUMN, DataType.varint())
                .buildInternal();

        tenantSession.execute(createTenantsTable);

        final String createValidIndex = SchemaBuilder.createIndex(indexName)
                .ifNotExists()
                .onTable(tableName)
                .andColumn(VALID_COLUMN)
                .toString();

        tenantSession.execute(createValidIndex);
    }

    private void createEntry(final @Nonnull Session tenantSession,
                             final @Nonnull String timestamp,
                             final @Nonnull BigInteger identityManagerPublicKeyModulus,
                             final @Nonnull BigInteger identityManagerPublicKeyExponent,
                             final @Nonnull BigInteger applicationPrivateKeyModulus,
                             final @Nonnull BigInteger applicationPrivateKeyExponent,
                             final @Nonnull BigInteger applicationPublicKeyModulus,
                             final @Nonnull BigInteger applicationPublicKeyExponent)
    {

        final ResultSet timestampCount =
                tenantSession.execute("SELECT count(*) FROM " + this.tableName + " WHERE " + TIMESTAMP_COLUMN + " = '" + timestamp + "'");
        final Long value = timestampCount.one().get(0, Long.class);
        if (value == 0L) {
            //There will only be one entry in this table per version.
            final BoundStatement tenantCreationStatement =
                    tenantSession.prepare("INSERT INTO " + tableName + " ("
                            + TIMESTAMP_COLUMN + ", "
                            + VALID_COLUMN + ", "
                            + IDENTITY_MANAGER_PUBLIC_KEY_MOD_COLUMN + ", "
                            + IDENTITY_MANAGER_PUBLIC_KEY_EXP_COLUMN + ", "
                            + APPLICATION_PRIVATE_KEY_MOD_COLUMN + ", "
                            + APPLICATION_PRIVATE_KEY_EXP_COLUMN + ", "
                            + APPLICATION_PUBLIC_KEY_MOD_COLUMN + ", "
                            + APPLICATION_PUBLIC_KEY_EXP_COLUMN + ")"
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)").bind();
            completeBoundStatement(tenantCreationStatement,
                    timestamp,
                    true,
                    identityManagerPublicKeyModulus,
                    identityManagerPublicKeyExponent,
                    applicationPrivateKeyModulus,
                    applicationPrivateKeyExponent,
                    applicationPublicKeyModulus,
                    applicationPublicKeyExponent);

            tenantSession.execute(tenantCreationStatement);
        } else {
            //TODO: Make sure existing entry hasn't been invalidated, or just don't allow an update.
            final BoundStatement tenantUpdateStatement =
                    tenantSession.prepare("UPDATE " + tableName + " SET "
                            + VALID_COLUMN + " = ?, "
                            + IDENTITY_MANAGER_PUBLIC_KEY_MOD_COLUMN + " = ?, "
                            + IDENTITY_MANAGER_PUBLIC_KEY_EXP_COLUMN + " = ?, "
                            + APPLICATION_PRIVATE_KEY_MOD_COLUMN + " = ?, "
                            + APPLICATION_PRIVATE_KEY_EXP_COLUMN + " = ?, "
                            + APPLICATION_PUBLIC_KEY_MOD_COLUMN + " = ?, "
                            + APPLICATION_PUBLIC_KEY_EXP_COLUMN + " = ? "
                            + "WHERE " + TIMESTAMP_COLUMN + " = ?").bind();
            completeBoundStatement(tenantUpdateStatement,
                    timestamp,
                    true,
                    identityManagerPublicKeyModulus,
                    identityManagerPublicKeyExponent,
                    applicationPrivateKeyModulus,
                    applicationPrivateKeyExponent,
                    applicationPublicKeyModulus,
                    applicationPublicKeyExponent);

            tenantSession.execute(tenantUpdateStatement);
        }
    }

    private void invalidateEntry(final @Nonnull Session tenantSession, final @Nonnull String timestamp) {
        final Update.Assignments updateQuery = QueryBuilder.update(tableName).where(QueryBuilder.eq(TIMESTAMP_COLUMN, timestamp)).with(QueryBuilder.set(VALID_COLUMN, false));
        tenantSession.execute(updateQuery);
    }

    private void completeBoundStatement(
            final @Nonnull BoundStatement boundStatement,
            final @Nonnull String timestamp,
            final boolean valid,
            final @Nonnull BigInteger identityManagerPublicKeyModulus,
            final @Nonnull BigInteger identityManagerPublicKeyExponent,
            final @Nonnull BigInteger applicationPrivateKeyModulus,
            final @Nonnull BigInteger applicationPrivateKeyExponent,
            final @Nonnull BigInteger applicationPublicKeyModulus,
            final @Nonnull BigInteger applicationPublicKeyExponent) {
        boundStatement.setString(TIMESTAMP_COLUMN, timestamp);
        boundStatement.setBool(VALID_COLUMN, valid);
        boundStatement.setVarint(IDENTITY_MANAGER_PUBLIC_KEY_MOD_COLUMN, identityManagerPublicKeyModulus);
        boundStatement.setVarint(IDENTITY_MANAGER_PUBLIC_KEY_EXP_COLUMN, identityManagerPublicKeyExponent);
        boundStatement.setVarint(APPLICATION_PRIVATE_KEY_MOD_COLUMN, applicationPrivateKeyModulus);
        boundStatement.setVarint(APPLICATION_PRIVATE_KEY_EXP_COLUMN, applicationPrivateKeyExponent);
        boundStatement.setVarint(APPLICATION_PUBLIC_KEY_MOD_COLUMN, applicationPublicKeyModulus);
        boundStatement.setVarint(APPLICATION_PUBLIC_KEY_EXP_COLUMN, applicationPublicKeyExponent);
    }

    @Override
    public Optional<Signature> getIdentityManagerSignature(final String timestamp)
    {
        Assert.notNull(timestamp);
        return getRow(timestamp).map(TenantAuthorizationDataRepository::mapRowToIdentityManagerSignature);
    }

    private Optional<Row> getRow(final @Nonnull String timestamp) {
        final Session tenantSession = cassandraSessionProvider.getTenantSession();
        final Select.Where query = timestampToSignatureQueryMap.computeIfAbsent(timestamp, timestampKey ->
                QueryBuilder.select().from(tableName).where(QueryBuilder.eq(TIMESTAMP_COLUMN, timestampKey)));
        try {
            final Row row = tenantSession.execute(query).one();
            final Optional<Row> ret = Optional.ofNullable(row);
            ret.map(TenantAuthorizationDataRepository::mapRowToValid).ifPresent(valid -> {
                if (!valid)
                    logger.warn("Invalidated keyset for timestamp '" + timestamp + "' requested. Pretending no keyset exists.");
            });
            return ret.filter(TenantAuthorizationDataRepository::mapRowToValid);
        }
        catch (final InvalidQueryException authorizationDataTableProbablyIsntConfiguredYet) {
            throw new IllegalArgumentException("Tenant not found.");
        }
    }

    private static Boolean mapRowToValid(final @Nonnull Row row) {
        return row.get(VALID_COLUMN, Boolean.class);
    }

    private static Signature getSignature(final @Nonnull Row row,
                                          final @Nonnull String publicKeyModColumnName,
                                          final @Nonnull String publicKeyExpColumnName) {
        final BigInteger publicKeyModulus = row.get(publicKeyModColumnName, BigInteger.class);
        final BigInteger publicKeyExponent = row.get(publicKeyExpColumnName, BigInteger.class);

        Assert.notNull(publicKeyModulus);
        Assert.notNull(publicKeyExponent);

        return new Signature(publicKeyModulus, publicKeyExponent);
    }

    private static Signature mapRowToIdentityManagerSignature(final @Nonnull Row row) {
        return getSignature(row, IDENTITY_MANAGER_PUBLIC_KEY_MOD_COLUMN, IDENTITY_MANAGER_PUBLIC_KEY_EXP_COLUMN);
    }

    private static Signature mapRowToApplicationSignature(final @Nonnull Row row) {
        return getSignature(row, APPLICATION_PUBLIC_KEY_MOD_COLUMN, APPLICATION_PUBLIC_KEY_EXP_COLUMN);
    }

    private static RsaKeyPairFactory.KeyPairHolder mapRowToKeyPairHolder(final @Nonnull Row row) {
        final BigInteger publicKeyModulus = row.get(APPLICATION_PUBLIC_KEY_MOD_COLUMN, BigInteger.class);
        final BigInteger publicKeyExponent = row.get(APPLICATION_PUBLIC_KEY_EXP_COLUMN, BigInteger.class);
        final BigInteger privateKeyModulus = row.get(APPLICATION_PRIVATE_KEY_MOD_COLUMN, BigInteger.class);
        final BigInteger privateKeyExponent = row.get(APPLICATION_PRIVATE_KEY_EXP_COLUMN, BigInteger.class);

        final PublicKey publicKey = new RsaPublicKeyBuilder()
                .setPublicKeyMod(publicKeyModulus)
                .setPublicKeyExp(publicKeyExponent)
                .build();
        final PrivateKey privateKey = new RsaPrivateKeyBuilder()
                .setPrivateKeyMod(privateKeyModulus)
                .setPrivateKeyExp(privateKeyExponent)
                .build();
        final String timestamp = row.get(TIMESTAMP_COLUMN, String.class);
        return new RsaKeyPairFactory.KeyPairHolder(timestamp, (RSAPublicKey)publicKey, (RSAPrivateKey)privateKey);
    }

    private static ApplicationSignatureSet mapRowToSignatureSet(final @Nonnull Row row) {
        final String timestamp = row.get(TIMESTAMP_COLUMN, String.class);
        final Signature identityManagerSignature = mapRowToIdentityManagerSignature(row);
        final Signature applicationSignature = mapRowToApplicationSignature(row);

        return new ApplicationSignatureSet(timestamp, applicationSignature, identityManagerSignature);
    }

    public List<String> getAllSignatureSetKeyTimestamps() {
        final Select.Where selectValid = QueryBuilder.select(TIMESTAMP_COLUMN).from(tableName).where(QueryBuilder.eq(VALID_COLUMN, true));
        final ResultSet result = cassandraSessionProvider.getTenantSession().execute(selectValid);
        return StreamSupport.stream(result.spliterator(), false)
                .map(x -> x.get(TIMESTAMP_COLUMN, String.class))
                .collect(Collectors.toList());
    }

    public Optional<ApplicationSignatureSet> getSignatureSet(final String timestamp) {
        Assert.notNull(timestamp);
        return getRow(timestamp).map(TenantAuthorizationDataRepository::mapRowToSignatureSet);
    }

    @Override
    public Optional<ApplicationSignatureSet> getLatestSignatureSet() {
        Optional<String> timestamp = getMostRecentTimestamp();
        return timestamp.flatMap(this::getSignatureSet);
    }

    @Override
    public Optional<Signature> getLatestApplicationSignature() {
        Optional<String> timestamp = getMostRecentTimestamp();
        return timestamp.flatMap(this::getApplicationSignature);
    }

    @Override
    public Optional<RsaKeyPairFactory.KeyPairHolder> getLatestApplicationSigningKeyPair() {
        Optional<String> timestamp = getMostRecentTimestamp();
        return timestamp.flatMap(this::getRow).map(TenantAuthorizationDataRepository::mapRowToKeyPairHolder);
    }

    private Optional<String> getMostRecentTimestamp() {
        return getAllSignatureSetKeyTimestamps().stream()
                .max(String::compareTo);
    }
}