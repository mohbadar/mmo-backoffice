
package af.gov.anar.lib.excel.props.row;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.Row;

/**
 * Row height.
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class Height implements Props<Row> {

    /**
     * Height.
     */
    private final short value;

    /**
     * Ctor.
     * @param height Height
     */
    public Height(final short height) {
        this.value = height;
    }

    @Override
    public void accept(final Row row) {
        row.setHeight(this.value);
    }
}
