package af.asr.vault.service.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import({
        AnubisConfiguration.class,
        AnubisImportSelector.class,
        AnubisSecurityConfigurerAdapter.class
})
public @interface EnableAnubis {
    boolean provideSignatureRestController() default true;
    boolean provideSignatureStorage() default true;
    boolean generateEmptyInitializeEndpoint() default false;
}