
package af.gov.anar.lib.excel.props.sheet;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Column width.
 */
public final class ColumnWidth implements Props<Sheet> {

    /**
     * Column.
     */
    private final int col;

    /**
     * Width in pixels.
     */
    private final int width;

    /**
     * Ctor.
     * @param column Column
     * @param pixels Pixels
     */
    public ColumnWidth(final int column, final int pixels) {
        this.col = column;
        this.width = pixels;
    }

    @Override
    public void accept(final Sheet sheet) {
        sheet.setColumnWidth(this.col, this.width);
    }
}
