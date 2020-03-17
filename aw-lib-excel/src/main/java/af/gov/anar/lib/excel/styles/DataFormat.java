
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Cell data format. See apache POI class BuiltinFormats
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class DataFormat implements Props<CellStyle> {

    /**
     * Format.
     */
    private final short value;

    /**
     * Ctor.
     * @param format Format
     */
    public DataFormat(final short format) {
        this.value = format;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setDataFormat(this.value);
    }
}
