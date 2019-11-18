package af.asr.identity.service.internal.repository;


import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.extras.codecs.enums.EnumNameCodec;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum AllowedOperationType {
    READ, //GET, TRACE
    CHANGE, //POST, PUT
    DELETE; //DELETE

    public static final Set<AllowedOperationType> ALL = Collections.unmodifiableSet(
            new HashSet<AllowedOperationType>() {{add(READ); add(CHANGE); add(DELETE);}});

    static TypeCodec<AllowedOperationType> getCodec()
    {
        return new EnumNameCodec<>(AllowedOperationType.class);
    }

    static public AllowedOperationType fromHttpMethod(final String httpMethod)
    {
        switch (httpMethod) {
            case "GET":
            case "TRACE":
                return READ;
            case "POST":
            case "PUT":
                return CHANGE;
            case "DELETE":
                return DELETE;
            default:
                return null;
        }
    }

}