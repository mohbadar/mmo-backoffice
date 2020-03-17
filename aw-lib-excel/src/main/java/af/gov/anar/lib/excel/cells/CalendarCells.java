
package af.gov.anar.lib.excel.cells;

import com.jcabi.immutable.Array;

import java.util.Calendar;
import java.util.stream.Collectors;

/**
 * Multiple cells containing Calendar values.
 */
public final class CalendarCells extends AbstractStyleableCells {

    /**
     * Position of cells.
     */
    private final int position;

    /**
     * Array of calendar values.
     */
    private final Array<Calendar> values;

    /**
     * Ctor.
     * @param cvalues Calendar values
     */
    public CalendarCells(final Calendar... cvalues) {
        this(new Array<>(cvalues));
    }

    /**
     * Ctor.
     * @param column Position of cells
     * @param cvalues Values
     */
    public CalendarCells(final int column, final Calendar... cvalues) {
        this(column, new Array<>(cvalues));
    }

    /**
     * Ctor.
     * @param cvalues Values
     */
    public CalendarCells(final Iterable<Calendar> cvalues) {
        this(-1, new Array<>(cvalues));
    }

    /**
     * Ctor.
     * @param column Position of cells
     * @param cvalues Values
     */
    public CalendarCells(final int column, final Iterable<Calendar> cvalues) {
        super();
        this.position = column;
        this.values = new Array<>(cvalues);
    }

    @Override
    public Array<ECell> asArray() {
        return new Array<>(this.values.stream()
            .map(calendar -> new CalendarCell(this.position, calendar))
            .collect(Collectors.toList())
        );
    }
}
