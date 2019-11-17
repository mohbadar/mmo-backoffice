package af.asr.vault.service.config;



import af.asr.vault.service.controller.EmptyInitializeResourcesRestController;
import af.asr.vault.service.controller.PermittableRestController;
import af.asr.vault.service.controller.SignatureCreatorRestController;
import af.asr.vault.service.controller.SignatureRestController;
import af.asr.vault.service.provider.SystemRsaKeyProvider;
import af.asr.vault.service.provider.TenantRsaKeyProvider;
import af.asr.vault.service.repository.TenantAuthorizationDataRepository;
import af.asr.vault.service.security.GuestAuthenticator;
import af.asr.vault.service.security.IsisAuthenticatedAuthenticationProvider;
import af.asr.vault.service.security.SystemAuthenticator;
import af.asr.vault.service.security.TenantAuthenticator;
import af.asr.vault.service.service.PermittableService;
import af.asr.vault.service.token.SystemAccessTokenSerializer;
import af.asr.vault.service.token.TenantAccessTokenSerializer;
import af.asr.vault.service.token.TenantRefreshTokenSerializer;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashSet;
import java.util.Set;


class AnubisImportSelector implements ImportSelector {
    AnubisImportSelector() { }

    @Override public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        final Set<Class> classesToImport = new HashSet<>();
        classesToImport.add(TenantRsaKeyProvider.class);
        classesToImport.add(SystemRsaKeyProvider.class);

        classesToImport.add(SystemAccessTokenSerializer.class);
        classesToImport.add(TenantAccessTokenSerializer.class);
        classesToImport.add(TenantRefreshTokenSerializer.class);

        classesToImport.add(IsisAuthenticatedAuthenticationProvider.class);
        classesToImport.add(TenantAuthenticator.class);
        classesToImport.add(SystemAuthenticator.class);
        classesToImport.add(GuestAuthenticator.class);

        classesToImport.add(PermittableRestController.class);
        classesToImport.add(PermittableService.class);

        final boolean provideSignatureRestController = (boolean)importingClassMetadata
                .getAnnotationAttributes(EnableAnubis.class.getTypeName())
                .get("provideSignatureRestController");
        final boolean provideSignatureStorage = (boolean) importingClassMetadata
                .getAnnotationAttributes(EnableAnubis.class.getTypeName())
                .get("provideSignatureStorage");
        final boolean generateEmptyInitializeEndpoint = (boolean)importingClassMetadata
                .getAnnotationAttributes(EnableAnubis.class.getTypeName())
                .get("generateEmptyInitializeEndpoint");

        if (provideSignatureRestController) {
            classesToImport.add(SignatureRestController.class);

            if (provideSignatureStorage)
                classesToImport.add(SignatureCreatorRestController.class);
        }

        if (provideSignatureStorage)
            classesToImport.add(TenantAuthorizationDataRepository.class);

        if (generateEmptyInitializeEndpoint)
            classesToImport.add(EmptyInitializeResourcesRestController.class);


        return classesToImport.stream().map(Class::getCanonicalName).toArray(String[]::new);
    }
}