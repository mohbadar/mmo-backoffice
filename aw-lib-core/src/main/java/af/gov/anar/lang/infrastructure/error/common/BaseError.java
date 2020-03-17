package af.gov.anar.lang.infrastructure.error.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("WeakerAccess")
@ToString
@EqualsAndHashCode
public final class BaseError {

    private final int code;
    private final String message;

    private BaseError(final int code, final String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public static Builder create(final int code) {
        return new Builder(code);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static final class Builder {

        private final int code;
        private String message;

        public Builder(final int code) {
            super();
            this.code = code;
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public BaseError build() {
            return new BaseError(this.code, this.message);
        }
    }

}