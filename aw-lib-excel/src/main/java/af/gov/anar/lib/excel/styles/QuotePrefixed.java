
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * QuotePrefixed prop on cell.
 */
public final class QuotePrefixed implements Props<CellStyle> {

    /**
     * Is prefixed.
     */
    private final boolean value;

    /**
     * Ctor.
     */
    public QuotePrefixed() {
        this(true);
    }

    /**
     * Ctor.
     * @param prefixed Is prefixed
     */
    public QuotePrefixed(final boolean prefixed) {
        this.value = prefixed;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setQuotePrefixed(this.value);
    }
}
