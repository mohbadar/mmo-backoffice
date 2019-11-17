package af.asr.vault.service.annotation;
import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Permittables.class)
public @interface Permittable {
    AcceptedTokenType value() default AcceptedTokenType.TENANT;
    String groupId() default "";
    String permittedEndpoint() default "";
    boolean acceptTokenIntendedForForeignApplication() default false;
}