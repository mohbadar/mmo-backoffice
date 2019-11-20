package af.asr.permittedfeignclient.service.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EndpointSet {
    String identifier() default "";
}