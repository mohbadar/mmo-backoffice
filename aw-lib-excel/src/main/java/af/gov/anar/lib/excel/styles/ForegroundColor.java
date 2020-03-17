
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Foreground cell color.
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class ForegroundColor implements Props<CellStyle> {

    /**
     * Color.
     */
    private final short color;

    /**
     * Ctor.
     * @param rgb Rgb color value
     */
    public ForegroundColor(final short rgb) {
        this.color = rgb;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setFillForegroundColor(this.color);
    }
}
