package af.asr.permittedfeignclient.service.config;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@SuppressWarnings("WeakerAccess")
public class PermittedFeignClientBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(
            final AnnotationMetadata importingClassMetadata,
            final BeanDefinitionRegistry registry) {

        final Object clients = importingClassMetadata.getAnnotationAttributes(
                EnablePermissionRequestingFeignClient.class.getTypeName()).get("feignClasses");

        final AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(ApplicationPermissionRequirementsService.class)
                .addConstructorArgValue(clients)
                .setScope(SCOPE_SINGLETON)
                .getBeanDefinition();

        registry.registerBeanDefinition("applicationPermissionRequirementsService", beanDefinition);
    }
}