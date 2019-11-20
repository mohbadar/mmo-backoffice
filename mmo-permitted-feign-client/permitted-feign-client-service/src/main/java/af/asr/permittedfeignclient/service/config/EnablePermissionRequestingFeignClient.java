package af.asr.permittedfeignclient.service.config;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import({
        PermittedFeignClientBeanDefinitionRegistrar.class,
        PermittedFeignClientImportSelector.class,
        PermittedFeignClientConfiguration.class,
})
public @interface EnablePermissionRequestingFeignClient {
    /**
     * @return A list of classes annotated with @EndpointSet and @FeignClient
     */
    Class<?>[] feignClasses() default {};
}