
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Cell Style.
 */
public interface Style {

    /**
     * Attach style to a cell.
     * @param cell Cell
     * @return Cell style
     */
    CellStyle attachTo(Cell cell);

    /**
     * Add property to the style.
     * @param property Property
     * @return Style
     */
    Style with(Props<CellStyle> property);
}
