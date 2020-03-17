
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import com.jcabi.immutable.Array;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Cell style which can be applied to cell/row/sheet/workbook.
 */
public final class XsStyle implements Style {

    /**
     * Array of style properties.
     */
    private final Array<Props<CellStyle>> properties;

    /**
     * Ctor.
     */
    public XsStyle() {
        this(new Array<>());
    }

    /**
     * Ctor.
     * @param props Properties
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public XsStyle(final Props<CellStyle>... props) {
        this(new Array<>(props));
    }

    /**
     * Ctor.
     * @param props Properties
     */
    public XsStyle(final Iterable<Props<CellStyle>> props) {
        this.properties = new Array<>(props);
    }

    @Override
    public CellStyle attachTo(final Cell cell) {
        final CellStyle style = cell.getCellStyle();
        for (final Props<CellStyle> property : this.properties) {
            property.accept(style);
        }
        return style;
    }

    @Override
    public Style with(final Props<CellStyle> element) {
        return new XsStyle(this.properties.with(element));
    }
}
