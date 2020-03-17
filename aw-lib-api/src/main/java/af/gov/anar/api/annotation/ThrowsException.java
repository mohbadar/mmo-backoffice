
package af.gov.anar.api.annotation;

import org.springframework.http.HttpStatus;

import java.lang.annotation.*;

@Repeatable(value = ThrowsExceptions.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ThrowsException {

  HttpStatus status();

  Class<? extends RuntimeException> exception();
}
