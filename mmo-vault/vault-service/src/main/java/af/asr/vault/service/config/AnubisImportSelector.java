//package af.asr.vault.service.config;
//
//
//
//import org.springframework.context.annotation.ImportSelector;
//import org.springframework.core.type.AnnotationMetadata;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author Myrle Krantz
// */
//class AnubisImportSelector implements ImportSelector {
//    AnubisImportSelector() { }
//
//    @Override public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        final Set<Class> classesToImport = new HashSet<>();
//        classesToImport.add(TenantRsaKeyProvider.class);
//        classesToImport.add(SystemRsaKeyProvider.class);
//
//        classesToImport.add(SystemAccessTokenSerializer.class);
//        classesToImport.add(TenantAccessTokenSerializer.class);
//        classesToImport.add(TenantRefreshTokenSerializer.class);
//
//        classesToImport.add(IsisAuthenticatedAuthenticationProvider.class);
//        classesToImport.add(TenantAuthenticator.class);
//        classesToImport.add(SystemAuthenticator.class);
//        classesToImport.add(GuestAuthenticator.class);
//
//        classesToImport.add(PermittableRestController.class);
//        classesToImport.add(PermittableService.class);
//
//        final boolean provideSignatureRestController = (boolean)importingClassMetadata
//                .getAnnotationAttributes(EnableAnubis.class.getTypeName())
//                .get("provideSignatureRestController");
//        final boolean provideSignatureStorage = (boolean) importingClassMetadata
//                .getAnnotationAttributes(EnableAnubis.class.getTypeName())
//                .get("provideSignatureStorage");
//        final boolean generateEmptyInitializeEndpoint = (boolean)importingClassMetadata
//                .getAnnotationAttributes(EnableAnubis.class.getTypeName())
//                .get("generateEmptyInitializeEndpoint");
//
//        if (provideSignatureRestController) {
//            classesToImport.add(SignatureRestController.class);
//
//            if (provideSignatureStorage)
//                classesToImport.add(SignatureCreatorRestController.class);
//        }
//
//        if (provideSignatureStorage)
//            classesToImport.add(TenantAuthorizationDataRepository.class);
//
//        if (generateEmptyInitializeEndpoint)
//            classesToImport.add(EmptyInitializeResourcesRestController.class);
//
//
//        return classesToImport.stream().map(Class::getCanonicalName).toArray(String[]::new);
//    }
//}