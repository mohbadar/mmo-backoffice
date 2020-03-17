
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Cell right border style.
 */
public final class BorderRight implements Props<CellStyle> {

    /**
     * Border.
     */
    private final BorderStyle value;

    /**
     * Ctor.
     * @param style Style
     */
    public BorderRight(final BorderStyle style) {
        this.value = style;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setBorderRight(this.value);
    }
}
