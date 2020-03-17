package af.gov.anar.lib.logger.util;

import lombok.Getter;

/**
 * Log level for logger contains {@link #DEBUG} {@link #ERROR} {@link #TRACE}
 * {@link #INFO} {@link #WARN}
 * @since 1.0.0
 */
public enum LogLevel {

    /**
     * Debug log level for logger
     */
    DEBUG("DEBUG"),
    /**
     * Trace log level for logger
     */
    TRACE("TRACE"),
    /**
     * Error log level for logger
     */
    ERROR("ERROR"),
    /**
     * Warn log level for logger
     */
    WARN("WARN"),
    /**
     * Info log level for logger
     */
    INFO("INFO");

    @Getter
    private final String level;

    /**
     * Constructor for this class
     *
     * @param level set {@link Enum} level
     */
    private LogLevel(final String level) {
        this.level = level;
    }

}