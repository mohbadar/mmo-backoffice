
package af.gov.anar.lib.excel.workbook;

import af.gov.anar.lib.excel.sheet.ESheet;
import af.gov.anar.lib.excel.styles.Style;
import com.jcabi.immutable.Array;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoCheckedScalar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel workbook.
 *
 * <p>This is how you can use it:</p>
 *
 * new XsWorkbook(
 * new XsSheet(
 * new XsRow().with(new TextCells("name", "email"))
 * )
 * )
 * ).saveTo("Test.xlsx");
 */
public final class XsWorkbook implements EWorkbook {

    /**
     * Array of sheets.
     */
    private final Array<ESheet> sheets;

    /**
     * Workbook.
     */
    private final IoCheckedScalar<Workbook> workbook;

    /**
     * Ctor.
     * @param elements Sheets
     */
    public XsWorkbook(final Iterable<ESheet> elements) {
        this(elements, XSSFWorkbook::new);
    }

    /**
     * Ctor.
     * @param elements Sheets
     */
    public XsWorkbook(final ESheet... elements) {
        this(new Array<>(elements), XSSFWorkbook::new);
    }

    /**
     * Ctor.
     * @param path File path
     */
    public XsWorkbook(final String path) {
        this(new File(path));
    }

    /**
     * Ctor.
     * @param file File
     */
    public XsWorkbook(final File file) {
        this(new Array<>(), () -> new XSSFWorkbook(new FileInputStream(file)));
    }

    /**
     * Ctor.
     * @param stream InputStream
     */
    public XsWorkbook(final InputStream stream) {
        this(new Array<>(), () -> new XSSFWorkbook(stream));
    }

    /**
     * Ctor.
     * @param elements Sheets
     * @param scalar Scalar
     */
    public XsWorkbook(final Iterable<ESheet> elements,
        final Scalar<Workbook> scalar) {
        this.sheets = new Array<>(elements);
        this.workbook = new IoCheckedScalar<>(scalar);
    }

    @Override
    public ByteArrayOutputStream asStream() throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try (final Workbook wbook = this.workbook.value()) {
            this.attachSheets(wbook);
            wbook.write(stream);
        }
        return stream;
    }

    @Override
    public Workbook asWorkbook() throws IOException {
        final Workbook wbook = this.workbook.value();
        this.attachSheets(wbook);
        return wbook;
    }

    @Override
    public void saveTo(final String path) throws IOException {
        try (final FileOutputStream file = new FileOutputStream(path);
            final Workbook wbook = this.workbook.value()) {
            this.attachSheets(wbook);
            wbook.write(file);
            file.flush();
        }
    }

    @Override
    public EWorkbook with(final ESheet sheet) {
        return new XsWorkbook(this.sheets.with(sheet), this.workbook);
    }

    @Override
    public EWorkbook with(final Style style) {
        final List<ESheet> elements = new ArrayList<>(this.sheets.size());
        for (final ESheet sheet : this.sheets) {
            elements.add(sheet.with(style));
        }
        return new XsWorkbook(elements, this.workbook);
    }

    /**
     * Attach sheets.
     * @param wbook Workbook
     * @throws IOException If fails
     */
    private void attachSheets(final Workbook wbook) throws IOException {
        for (final ESheet sheet : this.sheets) {
            sheet.attachTo(wbook);
        }
    }



}
