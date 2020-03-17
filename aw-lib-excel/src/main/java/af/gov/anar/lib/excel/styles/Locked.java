
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Locked cell.
 */
public final class Locked implements Props<CellStyle> {

    /**
     * Is locked value.
     */
    private final boolean value;

    /**
     * Ctor.
     */
    public Locked() {
        this(true);
    }

    /**
     * Ctor.
     * @param locked Is locked
     */
    public Locked(final boolean locked) {
        this.value = locked;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setLocked(this.value);
    }
}
