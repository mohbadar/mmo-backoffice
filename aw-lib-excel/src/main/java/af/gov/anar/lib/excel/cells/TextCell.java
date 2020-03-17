
package af.gov.anar.lib.excel.cells;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * TextCell.
 */
public final class TextCell extends AbstractStyleableCell {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Textual value.
     */
    private final String value;

    /**
     * Ctor.
     * @param text Text
     */
    public TextCell(final String text) {
        this(-1, text);
    }

    /**
     * Ctor.
     * @param column Column
     * @param text Text
     */
    public TextCell(final int column, final String text) {
        super();
        this.position = column;
        this.value = text;
    }

    @Override
    public Cell attachTo(final Row row) {
        final Cell cell =  new EmptyCell(this.position).attachTo(row);
        cell.setCellValue(this.value);
        return cell;
    }
}
