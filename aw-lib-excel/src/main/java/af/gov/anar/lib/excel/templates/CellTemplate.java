
package af.gov.anar.lib.excel.templates;


import af.gov.anar.lib.excel.cells.ECell;
import af.gov.anar.lib.excel.styles.Style;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Template to build custom cells.
 *
 * <p>This is how you can use it:</p>
 *
 * <pre> class MyGoldCell extends CellTemplate {
 *      public MyGoldCell(final ECell cell) {
 *          super(cell.with(
 *              new XsStyle(
 *                  new ForegroundColor(IndexedColors.GOLD.getIndex()),
 *                  new FillPattern(FillPatternType.SOLID_FOREGROUND)
 *              )
 *          ));
 *      }
 *  }
 * </pre>
 */
public class CellTemplate implements ECell {

    /**
     * Origin cell.
     */
    private final ECell origin;

    /**
     * Ctor.
     * @param cell Cell
     */
    public CellTemplate(final ECell cell) {
        this.origin = cell;
    }

    @Override
    public final Cell attachTo(final Row row) {
        return this.origin.attachTo(row);
    }

    @Override
    public final ECell with(final Style style) {
        return this.origin.with(style);
    }
}
