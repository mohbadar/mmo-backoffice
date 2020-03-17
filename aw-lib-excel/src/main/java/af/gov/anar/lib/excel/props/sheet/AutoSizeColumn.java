
package af.gov.anar.lib.excel.props.sheet;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Auto size column.
 */
public final class AutoSizeColumn implements Props<Sheet> {

    /**
     * Column.
     */
    private final int value;

    /**
     * Are cells to be merged.
     */
    private final boolean ismerged;

    /**
     * Ctor.
     * @param column Column
     */
    public AutoSizeColumn(final int column) {
        this(column, false);
    }

    /**
     * Ctor.
     * @param column Column
     * @param merged Is cells to be merged
     */
    public AutoSizeColumn(final int column, final boolean merged) {
        this.value = column;
        this.ismerged = merged;
    }

    @Override
    public void accept(final Sheet sheet) {
        sheet.autoSizeColumn(this.value, this.ismerged);
    }
}
