
package af.gov.anar.api.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@SuppressWarnings("unused")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import({ApiConfiguration.class})
public @interface EnableApiFactory {

}
