
package af.gov.anar.lib.excel.cells;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Calendar;

/**
 * Cell that holds calendar value.
 */
public final class CalendarCell extends AbstractStyleableCell {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Calendar value.
     */
    private final Calendar value;

    /**
     * Ctor.
     * @param calendar Calendar
     */
    public CalendarCell(final Calendar calendar) {
        this(-1, calendar);
    }

    /**
     * Ctor.
     * @param column Cell position
     * @param calendar Calendar
     */
    public CalendarCell(final int column, final Calendar calendar) {
        super();
        this.position = column;
        this.value = calendar;
    }

    @Override
    public Cell attachTo(final Row row) {
        final Cell cell = new EmptyCell(this.position).attachTo(row);
        cell.setCellValue(this.value);
        return cell;
    }
}
