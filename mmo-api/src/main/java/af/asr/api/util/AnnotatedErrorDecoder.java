package af.asr.api.util;


import af.asr.api.annotation.ThrowsException;
import af.asr.api.annotation.ThrowsExceptions;
import feign.Feign;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;


@SuppressWarnings("WeakerAccess")
public class AnnotatedErrorDecoder implements ErrorDecoder {

    private final Class feignClientClass;
    private final Logger logger;

    public AnnotatedErrorDecoder(final Logger logger, final Class feignClientClass) {
        this.logger = logger;
        this.feignClientClass = feignClientClass;
    }

    @Override
    public Exception decode(
            final String methodKey,
            final Response response) {
        final Optional<Exception> ret =
                Arrays.stream(feignClientClass.getMethods())
                        .filter(method -> Feign.configKey(feignClientClass, method).equals(methodKey))
                        .map(method -> {
                            final Optional<ThrowsException> annotation = getMatchingAnnotation(response, method);
                            return annotation.flatMap(a -> constructException(response, a));
                        })
                        .findAny().flatMap(x -> x);

        return ret.orElse(getAlternative(methodKey, response));
    }

    private RuntimeException getAlternative(final String methodKey, final Response response) {
        final String bodyText = stringifyBody(response);

        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            return new IllegalArgumentException(bodyText);
        } else if (response.status() == HttpStatus.FORBIDDEN.value()) {
            return new InvalidTokenException(bodyText);
        } else if (response.status() == HttpStatus.NOT_FOUND.value()) {
            return new NotFoundException(bodyText);
        } else if (response.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return new InternalServerError(bodyText);
        } else {
            return FeignException.errorStatus(methodKey, response);
        }
    }

    private String stringifyBody(final Response response) {
        try {
            if (response.body() != null)
                return Util.toString(response.body().asReader());
        } catch (IOException ignored) {
        }
        return null;
    }

    private Optional<ThrowsException> getMatchingAnnotation(
            final Response response,
            final Method method) {

        final ThrowsExceptions throwsExceptionsAnnotation =
                method.getAnnotation(ThrowsExceptions.class);
        if (throwsExceptionsAnnotation == null) {
            final ThrowsException throwsExceptionAnnotation =
                    method.getAnnotation(ThrowsException.class);
            if ((throwsExceptionAnnotation != null) &&
                    statusMatches(response, throwsExceptionAnnotation)) {
                return Optional.of(throwsExceptionAnnotation);
            }
        } else {
            return Arrays.stream(throwsExceptionsAnnotation.value())
                    .filter(throwsExceptionAnnotation -> statusMatches(response,
                            throwsExceptionAnnotation))
                    .findAny();
        }

        return Optional.empty();
    }

    private boolean statusMatches(final Response response,
                                  final ThrowsException throwsExceptionAnnotation) {
        return throwsExceptionAnnotation.status().value() == response.status();
    }

    private Optional<Exception> constructException(
            final Response response,
            final ThrowsException throwsExceptionAnnotations) {
        try {
            try {
                final Constructor<? extends RuntimeException> oneResponseArgumentConstructor =
                        throwsExceptionAnnotations.exception().getConstructor(Response.class);

                return Optional.of(oneResponseArgumentConstructor.newInstance(response));
            } catch (final NoSuchMethodException e) {
                try {
                    final Constructor<? extends RuntimeException> noArgumentConstructor =
                            throwsExceptionAnnotations.exception().getConstructor();

                    return Optional.of(noArgumentConstructor.newInstance());
                }
                catch (final NoSuchMethodException e2) {
                    final Constructor<? extends RuntimeException> noStringArgumentConstructor =
                            throwsExceptionAnnotations.exception().getConstructor(String.class);

                    return Optional.of(noStringArgumentConstructor.newInstance(stringifyBody(response)));
                }
            }
        } catch (final InvocationTargetException
                | IllegalAccessException
                | InstantiationException
                | NoSuchMethodException e) {
            logger.error("Instantiating exception {}, in for status {} failed with an exception",
                    throwsExceptionAnnotations.exception(), throwsExceptionAnnotations.status(), e);

            return Optional.empty();
        }
    }
}