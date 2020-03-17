
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Cell bottom border style.
 */
public final class BorderBottom implements Props<CellStyle> {

    /**
     * Border.
     */
    private final BorderStyle value;

    /**
     * Ctor.
     * @param style Style
     */
    public BorderBottom(final BorderStyle style) {
        this.value = style;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setBorderBottom(this.value);
    }
}
