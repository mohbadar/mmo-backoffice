
package af.gov.anar.lib.excel.cells;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Cell that holds formula value.
 */
public final class FormulaCell extends AbstractStyleableCell {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Formula value.
     */
    private final String value;

    /**
     * Ctor.
     * @param formula Formula
     */
    public FormulaCell(final String formula) {
        this(-1, formula);
    }

    /**
     * Ctor.
     * @param column Column
     * @param formula Formula
     */
    public FormulaCell(final int column, final String formula) {
        super();
        this.position = column;
        this.value = formula;
    }

    @Override
    public Cell attachTo(final Row row) {
        final Cell cell = new EmptyCell(this.position).attachTo(row);
        cell.setCellFormula(this.value);
        return cell;
    }
}
