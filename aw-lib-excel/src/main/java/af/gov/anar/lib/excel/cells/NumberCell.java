
package af.gov.anar.lib.excel.cells;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Cell that holds numeric value.
 */
public final class NumberCell extends AbstractStyleableCell {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Numeric value.
     */
    private final double value;

    /**
     * Ctor.
     * @param number Number
     */
    public NumberCell(final double number) {
        this(-1, number);
    }

    /**
     * Ctor.
     * @param column Column
     * @param number Number
     */
    public NumberCell(final int column, final double number) {
        super();
        this.position = column;
        this.value = number;
    }

    @Override
    public Cell attachTo(final Row row) {
        final Cell cell = new EmptyCell(this.position).attachTo(row);
        cell.setCellValue(this.value);
        return cell;
    }
}
