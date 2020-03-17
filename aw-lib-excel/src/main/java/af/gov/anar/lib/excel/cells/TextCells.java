
package af.gov.anar.lib.excel.cells;

import com.jcabi.immutable.Array;

import java.util.stream.Collectors;

/**
 * TextCells.
 */
public final class TextCells extends AbstractStyleableCells {

    /**
     * Position of cells.
     */
    private final int position;

    /**
     * Array of text values.
     */
    private final Array<String> text;

    /**
     * Ctor.
     * @param values Values
     */
    public TextCells(final String... values) {
        this(new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Column
     * @param values Values
     */
    public TextCells(final int column, final String... values) {
        this(column, new Array<>(values));
    }

    /**
     * Ctor.
     * @param values Values
     */
    public TextCells(final Iterable<String> values) {
        this(-1, new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Column
     * @param values Values
     */
    public TextCells(final int column, final Iterable<String> values) {
        super();
        this.position = column;
        this.text = new Array<>(values);
    }

    @Override
    public Array<ECell> asArray() {
        return new Array<>(this.text.stream()
            .map(txt -> new TextCell(this.position, txt))
            .collect(Collectors.toList())
        );
    }
}
