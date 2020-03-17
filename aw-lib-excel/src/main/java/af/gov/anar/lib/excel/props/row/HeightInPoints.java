
package af.gov.anar.lib.excel.props.row;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.Row;

/**
 * Row height in points.
 */
public final class HeightInPoints implements Props<Row> {

    /**
     * Height in points.
     */
    private final float value;

    /**
     * Ctor.
     * @param points Points
     */
    public HeightInPoints(final float points) {
        this.value = points;
    }

    @Override
    public void accept(final Row row) {
        row.setHeightInPoints(this.value);
    }
}
