
package af.gov.anar.lib.excel.cells;

import af.gov.anar.lib.excel.styles.Style;
import com.jcabi.immutable.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Cells.
 */
public interface ECells {

    /**
     * Describe object as array of cells.
     * @return Array of cells
     */
    Array<ECell> asArray();

    /**
     * Add style to cells.
     * @param style Style
     * @return Cell
     */
    ECells with(Style style);

    /**
     * Cells with specific style.
     */
    final class WithStyle implements ECells {

        /**
         * Origin cell.
         */
        private final ECells origin;

        /**
         * List of styles.
         */
        private final Array<Style> styles;

        /**
         * Ctor.
         * @param cells Cells
         * @param style Style
         */
        public WithStyle(final ECells cells, final Style style) {
            this(cells, new Array<>(style));
        }

        /**
         * Ctor.
         * @param cells Cells
         * @param styls Style
         */
        public WithStyle(final ECells cells, final Iterable<Style> styls) {
            this.origin = cells;
            this.styles = new Array<>(styls);
        }

        @Override
        public Array<ECell> asArray() {
            final List<ECell> cells = new ArrayList<>(0);
            for (final ECell cell: this.origin.asArray()) {
                ECell scell = cell;
                for (final Style style: this.styles) {
                    scell = cell.with(style);
                }
                cells.add(scell);
            }
            return new Array<>(cells);
        }

        @Override
        public ECells with(final Style style) {
            return new ECells.WithStyle(this.origin, this.styles.with(style));
        }
    }
}
