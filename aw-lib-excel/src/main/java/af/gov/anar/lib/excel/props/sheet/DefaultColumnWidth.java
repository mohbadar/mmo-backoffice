
package af.gov.anar.lib.excel.props.sheet;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Default column width.
 */
public final class DefaultColumnWidth implements Props<Sheet> {

    /**
     * Width in pixels.
     */
    private final int width;

    /**
     * Ctor.
     * @param pixels Pixels
     */
    public DefaultColumnWidth(final int pixels) {
        this.width = pixels;
    }

    @Override
    public void accept(final Sheet sheet) {
        sheet.setDefaultColumnWidth(this.width);
    }
}
