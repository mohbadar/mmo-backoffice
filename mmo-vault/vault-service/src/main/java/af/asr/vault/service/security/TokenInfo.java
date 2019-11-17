package af.asr.vault.service.security;


import af.asr.vault.service.token.TokenType;

class TokenInfo {
    final private TokenType type;
    final private String keyTimestamp;

    TokenInfo(final TokenType type, final String keyTimestamp) {
        this.type = type;
        this.keyTimestamp = keyTimestamp;
    }

    TokenType getType() {
        return type;
    }

    String getKeyTimestamp() {
        return keyTimestamp;
    }
}