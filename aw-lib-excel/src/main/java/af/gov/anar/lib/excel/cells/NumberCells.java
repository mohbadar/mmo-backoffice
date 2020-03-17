
package af.gov.anar.lib.excel.cells;

import com.jcabi.immutable.Array;

import java.util.stream.Collectors;

/**
 * Multiple cells that hold numeric values.
 */
public final class NumberCells extends AbstractStyleableCells {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Array of numbers.
     */
    private final Array<Double> numbers;

    /**
     * Ctor.
     * @param values Values
     */
    public NumberCells(final Double... values) {
        this(new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Column
     * @param values Values
     */
    public NumberCells(final int column, final Double... values) {
        this(column, new Array<>(values));
    }

    /**
     * Ctor.
     * @param values Values
     */
    public NumberCells(final Iterable<Double> values) {
        this(-1, new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Column
     * @param values Values
     */
    public NumberCells(final int column, final Iterable<Double> values) {
        super();
        this.position = column;
        this.numbers = new Array<>(values);
    }

    @Override
    public Array<ECell> asArray() {
        return new Array<>(this.numbers.stream()
            .map(number -> new NumberCell(this.position, number))
            .collect(Collectors.toList())
        );
    }
}
