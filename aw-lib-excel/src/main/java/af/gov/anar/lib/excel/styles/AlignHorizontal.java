
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * Cell alignment.
 */
public final class AlignHorizontal implements Props<CellStyle> {

    /**
     * Aligment.
     */
    private final HorizontalAlignment value;

    /**
     * Ctor.
     * @param alignment Alignment
     */
    public AlignHorizontal(final HorizontalAlignment alignment) {
        this.value = alignment;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setAlignment(this.value);
    }
}
