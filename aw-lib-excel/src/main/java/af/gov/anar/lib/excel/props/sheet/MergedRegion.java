
package af.gov.anar.lib.excel.props.sheet;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Merged region.
 */
public final class MergedRegion implements Props<Sheet> {

    /**
     * CellRangeAddress.
     */
    private final CellRangeAddress value;

    /**
     * Ctor.
     * @param region Region
     */
    public MergedRegion(final CellRangeAddress region) {
        this.value = region;
    }

    @Override
    public void accept(final Sheet sheet) {
        sheet.addMergedRegion(this.value);
    }
}
