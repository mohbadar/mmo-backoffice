package af.asr.permittedfeignclient.service.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashSet;
import java.util.Set;

class PermittedFeignClientImportSelector implements ImportSelector {
    PermittedFeignClientImportSelector() { }

    @Override public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        final Set<Class> classesToImport = new HashSet<>();
        classesToImport.add(ApplicationPermissionRequirementsRestController.class);
        classesToImport.add(ApplicationAccessTokenService.class);

        return classesToImport.stream().map(Class::getCanonicalName).toArray(String[]::new);
    }
}