package af.asr.postgresql.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashSet;
import java.util.Set;


class PostgreSQLJavaConfigurationImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        final boolean forTenantContext = (boolean)importingClassMetadata
                .getAnnotationAttributes(EnablePostgreSQL.class.getTypeName())
                .get("forTenantContext");

        final Set<Class> classesToImport = new HashSet<>();
        final String prop = System.getProperty("postgresql.enabled");
        if (prop == null || "true".equals(prop)) {
            classesToImport.add(PostgreSQLJavaConfiguration.class);
            if (forTenantContext) {
                classesToImport.add(PostgreSQLTenantBasedJavaConfiguration.class);
            }
            else {
                classesToImport.add(PostgreSQLTenantFreeJavaConfiguration.class);
            }
        }
        return classesToImport.stream().map(Class::getCanonicalName).toArray(String[]::new);
    }
}