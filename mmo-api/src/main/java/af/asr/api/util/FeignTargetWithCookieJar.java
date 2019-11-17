package af.asr.api.util;

@SuppressWarnings({"WeakerAccess", "unused"})
public class FeignTargetWithCookieJar<T> {
    private final T feignTarget;
    private final CookieInterceptingClient cookieInterceptor;

    FeignTargetWithCookieJar(final T feignTarget, final CookieInterceptingClient cookieInterceptor) {
        this.feignTarget = feignTarget;
        this.cookieInterceptor = cookieInterceptor;
    }

    public void putCookie(final String relativeUrl, final String cookieName, final String cookieValue) {
        this.cookieInterceptor.putCookie(relativeUrl, cookieName, cookieValue);
    }

    public T getFeignTarget() {
        return feignTarget;
    }
}