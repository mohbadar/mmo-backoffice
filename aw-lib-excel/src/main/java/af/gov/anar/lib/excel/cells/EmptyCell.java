
package af.gov.anar.lib.excel.cells;

import af.gov.anar.lib.excel.styles.Style;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Empty cell.
 */
public final class EmptyCell implements ECell {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Ctor.
     */
    public EmptyCell() {
        this(-1);
    }

    /**
     * Ctor.
     * @param column Cell position
     */
    public EmptyCell(final int column) {
        this.position = column;
    }

    @Override
    public Cell attachTo(final Row row) {
        Cell cell;
        if (this.position == -1) {
            cell = EmptyCell.createCell((int) row.getLastCellNum(), row);
        } else {
            cell = row.getCell(this.position - 1);
            if (cell == null) {
                cell = EmptyCell.createCell(this.position - 1, row);
            }
        }
        return cell;
    }

    @Override
    public ECell with(final Style style) {
        return new ECell.WithStyle(this, style);
    }

    /**
     * Create cell in given position.
     * @param position Position
     * @param row Row
     * @return Cell cell
     */
    private static Cell createCell(final int position, final Row row) {
        final int index;
        if (position < 0) {
            index = 0;
        } else {
            index = position;
        }
        return row.createCell(index);
    }
}
