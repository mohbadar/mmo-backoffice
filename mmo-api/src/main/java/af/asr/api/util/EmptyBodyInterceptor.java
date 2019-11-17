package af.asr.api.util;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.charset.Charset;

/**
 * Sets the content length of a request to zero if the request is of type POST or PUT, and contains
 * no request body.
 */
public class EmptyBodyInterceptor implements RequestInterceptor {

    public EmptyBodyInterceptor() {
        super();
    }

    @Override
    public void apply(final RequestTemplate template) {
        if ((template.method().equalsIgnoreCase(RequestMethod.POST.name())
                || template.method().equalsIgnoreCase(RequestMethod.PUT.name()))
                && template.body() == null) {
            template.body(new byte[0], Charset.defaultCharset());
        }
    }
}