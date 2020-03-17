
package af.gov.anar.lib.excel.cells;

import com.jcabi.immutable.Array;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * Multiple cells with date values.
 */
public final class DateCells extends AbstractStyleableCells {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Array of date values.
     */
    private final Array<Date> dates;

    /**
     * Ctor.
     * @param values Values
     */
    public DateCells(final Date... values) {
        this(new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Position of cells
     * @param values Values
     */
    public DateCells(final int column, final Date... values) {
        this(column, new Array<>(values));
    }

    /**
     * Ctor.
     * @param values Values
     */
    public DateCells(final Iterable<Date> values) {
        this(-1, new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Position of cells
     * @param values Values
     */
    public DateCells(final int column, final Iterable<Date> values) {
        super();
        this.position = column;
        this.dates = new Array<>(values);
    }

    @Override
    public Array<ECell> asArray() {
        return new Array<>(this.dates.stream()
            .map(date -> new DateCell(this.position, date))
            .collect(Collectors.toList())
        );
    }
}
