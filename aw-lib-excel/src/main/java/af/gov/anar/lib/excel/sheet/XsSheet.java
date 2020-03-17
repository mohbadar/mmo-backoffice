
package af.gov.anar.lib.excel.sheet;

import af.gov.anar.lib.excel.props.Props;
import af.gov.anar.lib.excel.styles.Style;
import af.gov.anar.lib.excel.row.ERow;
import com.jcabi.immutable.Array;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.cactoos.Func;
import org.cactoos.func.IoCheckedFunc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Sheet in an excel table.
 *
 * <p>This is how you should use it:</p>
 *
 *     new XsSheet(
 *        new XsRow()
 *           .with(new TextCells("name", "email"))
 *        )
 *     )

 */
public final class XsSheet implements ESheet {

    /**
     * Array of rows.
     */
    private final Array<ERow> rows;

    /**
     * Sheet name.
     */
    private final String name;

    /**
     * Ctor.
     * @param title Title
     */
    public XsSheet(final String title) {
        this(title, new Array<>());
    }

    /**
     * Ctor.
     * @param elements Elements
     */
    public XsSheet(final ERow... elements) {
        this("", new Array<>(elements));
    }

    /**
     * Ctor.
     * @param title Title
     * @param elements Elements
     */
    public XsSheet(final String title, final ERow... elements) {
        this(title, new Array<>(elements));
    }

    /**
     * Ctor.
     * @param title Title
     * @param elements Elements
     */
    public XsSheet(final String title, final Iterable<ERow> elements) {
        this.name = title;
        this.rows = new Array<>(elements);
    }

    @Override
    public Sheet attachTo(final Workbook workbook) {
        final Sheet sheet;
        if (this.name.isEmpty()) {
            sheet = workbook.createSheet();
        } else {
            sheet = workbook.createSheet(this.name);
        }
        for (final ERow row : this.rows) {
            row.attachTo(sheet);
        }
        return sheet;
    }

    @Override
    public ESheet with(final ERow element) {
        return new XsSheet(this.name, this.rows.with(element));
    }

    @Override
    public ESheet with(final Style style) {
        final List<ERow> elements = new ArrayList<>(this.rows.size());
        for (final ERow row : this.rows) {
            elements.add(row.with(style));
        }
        return new XsSheet(this.name, elements);
    }

    /**
     * Sheet with additional properties.
     * @param props Properties
     * @return WithProps WithProps
     */
    public XsSheet.WithProps with(final Props<Sheet> props) {
        return new XsSheet.WithProps(this, props);
    }

    /**
     * Sheet with additional properties.
     */
    public static final class WithProps implements ESheet {

        /**
         * Sheet origin.
         */
        private final ESheet origin;

        /**
         * Properties.
         */
        private final Props<Sheet> props;

        /**
         * Ctor.
         * @param sheet Sheet
         * @param properties Properties
         */
        public WithProps(final ESheet sheet, final Props<Sheet> properties) {
            this.origin = sheet;
            this.props = properties;
        }

        @Override
        public Sheet attachTo(final Workbook workbook) throws IOException {
            final Sheet sheet = this.origin.attachTo(workbook);
            this.props.accept(sheet);
            return sheet;
        }

        @Override
        public ESheet with(final ERow row) {
            return this.origin.with(row);
        }

        @Override
        public ESheet with(final Style style) {
            return this.origin.with(style);
        }
    }

    /**
     * Read sheet from existing one by title or index.
     */
    public static final class ReadFrom implements ESheet {

        /**
         * Func.
         */
        private final Func<Workbook, Sheet> func;

        /**
         * Array of rows.
         */
        private final Array<ERow> rows;

        /**
         * Ctor.
         * @param title Title
         */
        public ReadFrom(final String title) {
            this(workbook -> workbook.getSheet(title), new Array<>());
        }

        /**
         * Ctor.
         * @param index Index
         */
        public ReadFrom(final int index) {
            this(workbook -> workbook.getSheetAt(index), new Array<>());
        }

        /**
         * Ctor.
         * @param function Function
         * @param elements Elements
         */
        public ReadFrom(final Func<Workbook, Sheet> function,
            final Iterable<ERow> elements) {
            this.func = function;
            this.rows = new Array<>(elements);
        }

        @Override
        public Sheet attachTo(final Workbook workbook) throws IOException {
            final Sheet sheet = new IoCheckedFunc<>(this.func).apply(workbook);
            if (sheet == null) {
                throw new IOException("Sheet not found on specified index");
            }
            for (final ERow row : this.rows) {
                row.attachTo(sheet);
            }
            return sheet;
        }

        @Override
        public ESheet with(final ERow row) {
            return new XsSheet.ReadFrom(this.func, this.rows.with(row));
        }

        @Override
        public ESheet with(final Style style) {
            final List<ERow> elements = new ArrayList<>(this.rows.size());
            for (final ERow row : this.rows) {
                elements.add(row.with(style));
            }
            return new XsSheet.ReadFrom(this.func, elements);
        }
    }
}
