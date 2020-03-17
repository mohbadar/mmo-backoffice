
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * Vertical alignment in cell.
 */
public final class AlignVertical implements Props<CellStyle> {

    /**
     * Vertical alignment.
     */
    private final VerticalAlignment value;

    /**
     * Ctor.
     * @param alignment Vertical align.
     */
    public AlignVertical(final VerticalAlignment alignment) {
        this.value = alignment;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setVerticalAlignment(this.value);
    }
}
