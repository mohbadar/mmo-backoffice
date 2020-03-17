
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Hidden cell.
 */
public final class Hidden implements Props<CellStyle> {

    /**
     * Is hidden value.
     */
    private final boolean value;

    /**
     * Ctor.
     */
    public Hidden() {
        this(true);
    }

    /**
     * Ctor.
     * @param hidden Is hidden
     */
    public Hidden(final boolean hidden) {
        this.value = hidden;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setHidden(this.value);
    }
}
