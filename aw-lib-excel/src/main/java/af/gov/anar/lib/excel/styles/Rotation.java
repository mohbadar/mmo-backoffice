
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Cell rotation.
 *  Available values: 0 to 180 degrees
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class Rotation implements Props<CellStyle> {

    /**
     * Rotation in degrees.
     */
    private final short value;

    /**
     * Ctor.
     * @param rotation Rotation
     */
    public Rotation(final short rotation) {
        this.value = rotation;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setRotation(this.value);
    }
}
