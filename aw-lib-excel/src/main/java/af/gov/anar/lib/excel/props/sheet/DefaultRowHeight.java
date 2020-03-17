
package af.gov.anar.lib.excel.props.sheet;

import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Default row height.
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class DefaultRowHeight implements Props<Sheet> {

    /**
     * Row height.
     */
    private final short value;

    /**
     * Ctor.
     * @param height Height.
     */
    public DefaultRowHeight(final short height) {
        this.value = height;
    }

    @Override
    public void accept(final Sheet sheet) {
        sheet.setDefaultRowHeight(this.value);
    }

    /**
     * Default row height in points.
     */
    public static final class InPoints implements Props<Sheet> {

        /**
         * Heith in points.
         */
        private final float value;

        /**
         * Ctor.
         * @param height Height in points
         */
        public InPoints(final float height) {
            this.value = height;
        }

        @Override
        public void accept(final Sheet sheet) {
            sheet.setDefaultRowHeightInPoints(this.value);
        }
    }
}
