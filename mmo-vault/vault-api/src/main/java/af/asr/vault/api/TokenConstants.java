package af.asr.vault.api;

@SuppressWarnings("unused")
public interface TokenConstants {
    String NO_AUTHENTICATION = "N/A";
    String PREFIX = "Bearer ";

    String JWT_SIGNATURE_TIMESTAMP_CLAIM = "/asr.af/s";
    String JWT_ENDPOINT_SET_CLAIM = "/asr.af/e";
    String JWT_CONTENT_CLAIM = "/asr.af/c";
    String JWT_SOURCE_APPLICATION_CLAIM = "/asr.af/a";

    String REFRESH_TOKEN_COOKIE_NAME = "asr.af.refreshToken";
}