
package af.gov.anar.lib.excel.cells;

import af.gov.anar.lib.excel.styles.Style;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Cell.

 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public interface ECell {

    /**
     * Attach cell to a row.
     * @param row Row
     * @return Cell
     */
    Cell attachTo(Row row);

    /**
     * Add style to the cell.
     * @param style Style
     * @return Cell
     */
    ECell with(Style style);

    /**
     * Cell that contains specific style.
     */
    @SuppressWarnings("PMD.LooseCoupling")
    final class WithStyle implements ECell {

        /**
         * Cell.
         */
        private final ECell origin;

        /**
         * List of styles.
         */
        private final LinkedList<Style> styles;

        /**
         * Ctor.
         * @param cell Cell
         * @param style Style
         */
        public WithStyle(final ECell cell, final Style style) {
            this(cell, new LinkedList<>(Collections.singletonList(style)));
        }

        /**
         * Ctor.
         * @param cell Cell
         * @param styls Styles
         */
        public WithStyle(final ECell cell, final Collection<Style> styls) {
            this.origin = cell;
            this.styles = new LinkedList<>(styls);
        }

        @Override
        public Cell attachTo(final Row row) {
            final Cell cell = this.origin.attachTo(row);
            cell.setCellStyle(row.getSheet().getWorkbook().createCellStyle());
            for (final Style style : this.styles) {
                style.attachTo(cell);
            }
            return cell;
        }

        @Override
        public ECell with(final Style style) {
            this.styles.addFirst(style);
            return new ECell.WithStyle(this, this.styles);
        }
    }
}
