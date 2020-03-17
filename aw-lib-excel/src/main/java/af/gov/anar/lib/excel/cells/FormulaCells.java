
package af.gov.anar.lib.excel.cells;

import com.jcabi.immutable.Array;
import java.util.stream.Collectors;

/**
 * Multiples cells that hold formula values.
 */
public final class FormulaCells extends AbstractStyleableCells {

    /**
     * Position of cells.
     */
    private final int position;

    /**
     * Array of formula values.
     */
    private final Array<String> formulas;

    /**
     * Ctor.
     * @param values Values
     */
    public FormulaCells(final String... values) {
        this(new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Column
     * @param values Values
     */
    public FormulaCells(final int column, final String... values) {
        this(column, new Array<>(values));
    }

    /**
     * Ctor.
     * @param values Values
     */
    public FormulaCells(final Iterable<String> values) {
        this(-1, new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Column
     * @param values Values
     */
    public FormulaCells(final int column, final Iterable<String> values) {
        super();
        this.position = column;
        this.formulas = new Array<>(values);
    }

    @Override
    public Array<ECell> asArray() {
        return new Array<>(this.formulas.stream()
            .map(formula -> new FormulaCell(this.position, formula))
            .collect(Collectors.toList())
        );
    }
}
