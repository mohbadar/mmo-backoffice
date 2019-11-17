package af.asr.vault.service.annotation;

import java.lang.annotation.*;


@SuppressWarnings({"WeakerAccess", "unused"})
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permittables {
    Permittable[] value();
}