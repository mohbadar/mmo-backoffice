package af.asr.provisioner.service.config;


import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@KeysValid
@ConfigurationProperties(prefix = "system")
public class SystemProperties {
    @NotEmpty
    private String domain = "asr.af";

    @Valid
    private final Token token = new Token();

    @Valid
    private final PublicKey publicKey = new PublicKey();

    @Valid
    private final PrivateKey privateKey = new PrivateKey();

    public static class Token {
        @Range(min = 1)
        private int ttl = 60;

        public int getTtl() {
            return ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }
    }

    public static class PublicKey {
        @NotEmpty
        private String timestamp;

        @NotNull
        private BigInteger modulus;

        @NotNull
        private BigInteger exponent;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public BigInteger getModulus() {
            return modulus;
        }

        public void setModulus(BigInteger modulus) {
            this.modulus = modulus;
        }

        public BigInteger getExponent() {
            return exponent;
        }

        public void setExponent(BigInteger exponent) {
            this.exponent = exponent;
        }
    }

    public static class PrivateKey {
        @NotNull
        private BigInteger modulus;

        @NotNull
        private BigInteger exponent;

        public BigInteger getModulus() {
            return modulus;
        }

        public void setModulus(BigInteger modulus) {
            this.modulus = modulus;
        }

        public BigInteger getExponent() {
            return exponent;
        }

        public void setExponent(BigInteger exponent) {
            this.exponent = exponent;
        }
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Token getToken() {
        return token;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}