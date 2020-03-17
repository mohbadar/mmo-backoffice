
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Cell indention (Number of spaces to indent the text in the cell).
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class Indention implements Props<CellStyle> {

    /**
     * Indention.
     */
    private final short value;

    /**
     * Ctor.
     * @param indention Indention
     */
    public Indention(final short indention) {
        this.value = indention;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setIndention(this.value);
    }
}
