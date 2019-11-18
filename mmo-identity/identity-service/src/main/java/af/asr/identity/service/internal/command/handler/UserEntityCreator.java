package af.asr.identity.service.internal.command.handler;

import af.asr.ServiceException;
import af.asr.crypto.HashGenerator;
import af.asr.crypto.SaltGenerator;
import af.asr.identity.service.internal.repository.PrivateTenantInfoEntity;
import af.asr.identity.service.internal.repository.Tenants;
import af.asr.identity.service.internal.repository.UserEntity;
import af.asr.identity.service.internal.util.IdentityConstants;
import af.asr.identity.service.internal.util.Time;
import com.datastax.driver.core.LocalDate;
import java.nio.ByteBuffer;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.util.EncodingUtils;
import org.springframework.stereotype.Component;

@SuppressWarnings("WeakerAccess")
@Component
public class UserEntityCreator {

    private final SaltGenerator saltGenerator;
    private final HashGenerator hashGenerator;
    private final Tenants tenants;

    @Autowired
    UserEntityCreator(
            final SaltGenerator saltGenerator,
            final HashGenerator hashGenerator,
            final Tenants tenants) {
        this.saltGenerator = saltGenerator;
        this.hashGenerator = hashGenerator;
        this.tenants = tenants;
    }


    UserEntity build(
            final String identifier,
            final String role,
            final String password,
            final boolean passwordMustChange) {

        final Optional<PrivateTenantInfoEntity> tenantInfo = tenants.getPrivateTenantInfo();

        return tenantInfo
                .map(x -> build(identifier, role, password, passwordMustChange,
                        x.getFixedSalt().array(), x.getPasswordExpiresInDays()))
                .orElseThrow(() -> ServiceException.internalError("The tenant is not initialized."));
    }

    public UserEntity build(
            final String identifier,
            final String role,
            final String password,
            final boolean passwordMustChange,
            final byte[] fixedSalt,
            final int passwordExpiresInDays) {
        final UserEntity userEntity = new UserEntity();

        userEntity.setIdentifier(identifier);
        userEntity.setRole(role);

        final byte[] variableSalt = this.saltGenerator.createRandomSalt();
        final byte[] fullSalt = EncodingUtils.concatenate(variableSalt, fixedSalt);

        userEntity.setPassword(ByteBuffer.wrap(this.hashGenerator.hash(password, fullSalt,
                IdentityConstants.ITERATION_COUNT, IdentityConstants.HASH_LENGTH)));

        userEntity.setSalt(ByteBuffer.wrap(variableSalt));
        userEntity.setIterationCount(IdentityConstants.ITERATION_COUNT);
        userEntity.setPasswordExpiresOn(deriveExpiration(passwordMustChange, passwordExpiresInDays));

        return userEntity;
    }

    private LocalDate deriveExpiration(final boolean passwordMustChange, final int passwordExpiresInDays) {
        final LocalDate now = Time.utcNowAsStaxLocalDate();

        if (passwordMustChange)
            return now;
        else {
            final int offset = (passwordExpiresInDays <= 0) ? 93 : passwordExpiresInDays;
            return LocalDate.fromDaysSinceEpoch(now.getDaysSinceEpoch() + offset);
        }
    }

}