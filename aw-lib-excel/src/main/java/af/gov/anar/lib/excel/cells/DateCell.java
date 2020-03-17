
package af.gov.anar.lib.excel.cells;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Date;

/**
 * Cell that represents Date.
 */
public final class DateCell extends AbstractStyleableCell {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Date value.
     */
    private final Date value;

    /**
     * Ctor.
     * @param date Date
     */
    public DateCell(final Date date) {
        this(-1, date);
    }

    /**
     * Ctor.
     * @param column Column
     * @param date Date
     */
    public DateCell(final int column, final Date date) {
        super();
        this.position = column;
        this.value = date;
    }

    @Override
    public Cell attachTo(final Row row) {
        final Cell cell = new EmptyCell(this.position).attachTo(row);
        cell.setCellValue(this.value);
        return cell;
    }
}
