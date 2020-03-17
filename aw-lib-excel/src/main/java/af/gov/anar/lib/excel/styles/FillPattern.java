
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

/**
 * Fill pattern style.
 */
public final class FillPattern implements Props<CellStyle> {

    /**
     * Type of fill pattern.
     */
    private final FillPatternType pattern;

    /**
     * Ctor.
     * @param type Pattern type
     */
    public FillPattern(final FillPatternType type) {
        this.pattern = type;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setFillPattern(this.pattern);
    }
}
