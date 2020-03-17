
package af.gov.anar.lib.excel.props.row;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.Row;

/**
 * Row zero height.
 */
public final class ZeroHeight implements Props<Row> {

    /**
     * Zero height.
     */
    private final boolean value;

    /**
     * Ctor.
     * @param zero Zero
     */
    public ZeroHeight(final boolean zero) {
        this.value = zero;
    }

    @Override
    public void accept(final Row row) {
        row.setZeroHeight(this.value);
    }
}
