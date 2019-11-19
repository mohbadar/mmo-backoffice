package af.asr.vault.service.service;


import static af.asr.vault.service.config.AnubisConstants.LOGGER_NAME;

import af.asr.ApplicationName;
import af.asr.vault.api.domain.AllowedOperation;
import af.asr.vault.api.domain.PermittableEndpoint;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import af.asr.vault.service.config.AnubisProperties;
import af.asr.vault.service.security.ApplicationPermission;
import io.jsonwebtoken.lang.Assert;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.web.servlet.ControllerEndpointHandlerMapping;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@Component
public class PermittableService {
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private final ControllerEndpointHandlerMapping endpointHandlerMapping;
    private final ApplicationName applicationName;
    private final Permittable defaultPermittable;

    @Autowired
    public PermittableService(final RequestMappingHandlerMapping requestMappingHandlerMapping,
                              final ControllerEndpointHandlerMapping endpointHandlerMapping,
                              final ApplicationName applicationName,
                              final AnubisProperties anubisProperties,
                              final @Qualifier(LOGGER_NAME) Logger logger) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        this.endpointHandlerMapping = endpointHandlerMapping;
        this.applicationName = applicationName;
        if (anubisProperties.getAcceptGuestTokensForSystemEndpoints()) {
            logger.error("The service property anubis.tokenTypeRequiredForSystemEndpoints is set to GUEST. This " +
                    "feature is intended for use only in test environments. Turning it on in a production environment " +
                    "could be a serious security vulnerability.");
            this.defaultPermittable = guestPermittable();}
        else {
            this.defaultPermittable = systemPermittable();
        }
    }

    public Set<ApplicationPermission> getPermittableEndpointsAsPermissions(
            final AcceptedTokenType... acceptedTokenType) {
        final Set<PermittableEndpoint> permittableEndpoints
                = getPermittableEndpointsHelper(Arrays.asList(acceptedTokenType), false);

        return Collections.unmodifiableSet(
                permittableEndpoints.stream()
                        .map(x -> new ApplicationPermission(x.getPath(), mapHttpMethod(x.getMethod()), x.isAcceptTokenIntendedForForeignApplication()))
                        .collect(Collectors.toSet()));
    }

    private static AllowedOperation mapHttpMethod(final String httpMethod) {
        switch (httpMethod) {
            case "GET":
                return AllowedOperation.READ;
            case "HEAD":
                return AllowedOperation.READ;
            case "POST":
                return AllowedOperation.CHANGE;
            case "PUT":
                return AllowedOperation.CHANGE;
            case "DELETE":
                return AllowedOperation.DELETE;
            default:
                throw new IllegalArgumentException("Unsupported HTTP Method " + httpMethod);
        }
    }

    public Set<PermittableEndpoint> getPermittableEndpoints(final Collection<AcceptedTokenType> acceptedTokenTypes) {
        return getPermittableEndpointsHelper(acceptedTokenTypes, true);
    }

    private Set<PermittableEndpoint> getPermittableEndpointsHelper(
            final Collection<AcceptedTokenType> acceptedTokenTypes, boolean withAppName) {
        Assert.notEmpty(acceptedTokenTypes);

        final Set<PermittableEndpoint> permittableEndpoints = new LinkedHashSet<>();

        fillPermittableEndpointsFromHandlerMethods(acceptedTokenTypes, withAppName, this.requestMappingHandlerMapping.getHandlerMethods(), permittableEndpoints);
        fillPermittableEndpointsFromHandlerMethods(acceptedTokenTypes, withAppName, this.endpointHandlerMapping.getHandlerMethods(), permittableEndpoints);

        if (acceptedTokenTypes.contains(AcceptedTokenType.SYSTEM)) {
            final PermittableEndpoint permittableEndpoint = new PermittableEndpoint();
            if (withAppName)
                permittableEndpoint.setPath(applicationName + "/initialize");
            else
                permittableEndpoint.setPath("/initialize");

            permittableEndpoint.setMethod("POST");

            permittableEndpoint.setAcceptTokenIntendedForForeignApplication(false);

            permittableEndpoints.add(permittableEndpoint);
        }

        return permittableEndpoints;
    }

    private static class WhatINeedToBuildAPermittableEndpoint
    {
        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        final Permittable annotation;
        final Set<String> patterns;
        final Set<RequestMethod> methods;

        WhatINeedToBuildAPermittableEndpoint(final @Nonnull Permittable annotation,
                                             final @Nonnull Set<String> patterns,
                                             final @Nonnull Set<RequestMethod> methods) {
            this.annotation = annotation;
            this.patterns = patterns;
            this.methods = methods;
        }
    }

    private void fillPermittableEndpointsFromHandlerMethods(
            final Collection<AcceptedTokenType> acceptedTokenTypes,
            final boolean withAppName,
            final Map<RequestMappingInfo, HandlerMethod> handlerMethods,
            final @Nonnull Set<PermittableEndpoint> permittableEndpoints) {
        handlerMethods.entrySet()
                .stream().flatMap(handlerMethod -> PermittableService.whatINeedToBuildAPermittableEndpoint(handlerMethod, defaultPermittable))
                .filter(whatINeedToBuildAPermittableEndpoint -> acceptedTokenTypes.contains(getAcceptedTokenType(whatINeedToBuildAPermittableEndpoint)))
                .forEachOrdered(whatINeedToBuildAPermittableEndpoint ->
                        whatINeedToBuildAPermittableEndpoint.patterns
                                .forEach(pattern -> whatINeedToBuildAPermittableEndpoint.methods
                                        .forEach(method -> {
                                            final PermittableEndpoint permittableEndpoint = new PermittableEndpoint();
                                            permittableEndpoint.setPath(getPath(
                                                    withAppName ? applicationName.toString() : "",
                                                    pattern,
                                                    whatINeedToBuildAPermittableEndpoint));
                                            permittableEndpoint.setMethod(method.name());
                                            permittableEndpoint.setGroupId(whatINeedToBuildAPermittableEndpoint.annotation.groupId());
                                            permittableEndpoint.setAcceptTokenIntendedForForeignApplication(whatINeedToBuildAPermittableEndpoint.annotation.acceptTokenIntendedForForeignApplication());
                                            permittableEndpoints.add(permittableEndpoint);
                                        })
                                ));
    }

    static private AcceptedTokenType getAcceptedTokenType(final @Nonnull WhatINeedToBuildAPermittableEndpoint whatINeedToBuildAPermittableEndpoint) {
        return whatINeedToBuildAPermittableEndpoint.annotation.value();
    }

    static private String getPath(final @Nonnull String applicationName,
                                  final @Nonnull String pattern,
                                  final @Nonnull WhatINeedToBuildAPermittableEndpoint whatINeedToBuildAPermittableEndpoint) {
        final String programmerSpecifiedEndpoint = whatINeedToBuildAPermittableEndpoint.annotation.permittedEndpoint();
        if (!programmerSpecifiedEndpoint.isEmpty())
            return applicationName + programmerSpecifiedEndpoint;

        final StringBuilder ret = new StringBuilder(applicationName);

        PermissionSegmentMatcher.getServletPathSegmentMatchers(pattern).stream()   //parse the pattern into segments
                .map(x -> x.isParameterSegment() ? "*" : x.getPermissionSegment()) //replace the parameter segments with stars.
                .filter(x -> !x.isEmpty())                                         //remove any empty segments
                .forEachOrdered(x -> ret.append("/").append(x));                   //reassemble into a string.

        return ret.toString();
    }

    static private Stream<WhatINeedToBuildAPermittableEndpoint> whatINeedToBuildAPermittableEndpoint(
            final Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethod,
            final Permittable defaultPermittable) {
        final Set<Permittable> annotations = getPermittableAnnotations(handlerMethod, defaultPermittable);
        final Set<String> patterns = handlerMethod.getKey().getPatternsCondition().getPatterns();
        final Set<RequestMethod> methods = handlerMethod.getKey().getMethodsCondition().getMethods();
        return annotations.stream()
                .map(annotation -> new WhatINeedToBuildAPermittableEndpoint(annotation, patterns, methods));
    }

    static private Set<Permittable> getPermittableAnnotations(
            final Map.Entry<RequestMappingInfo, HandlerMethod> handlerMethod,
            final Permittable defaultPermittable) {
        final Method method = handlerMethod.getValue().getMethod();
        final Set<Permittable> ret = AnnotationUtils.getRepeatableAnnotations(method, Permittable.class);
        if (ret.isEmpty())
            return Collections.singleton(defaultPermittable);
        else
            return ret;
    }

    static private Permittable guestPermittable() {
        return new Permittable() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return Permittable.class;
            }

            @Override
            public AcceptedTokenType value() {
                return AcceptedTokenType.GUEST;
            }

            @Override
            public String groupId() {
                return "";
            }

            @Override
            public String permittedEndpoint() {
                return "";
            }

            @Override
            public boolean acceptTokenIntendedForForeignApplication() {
                return false;
            }
        };
    }

    static private Permittable systemPermittable() {
        return new Permittable() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return Permittable.class;
            }

            @Override
            public AcceptedTokenType value() {
                return AcceptedTokenType.SYSTEM;
            }

            @Override
            public String groupId() {
                return "";
            }

            @Override
            public String permittedEndpoint() {
                return "";
            }

            @Override
            public boolean acceptTokenIntendedForForeignApplication() {
                return false;
            }
        };
    }
}