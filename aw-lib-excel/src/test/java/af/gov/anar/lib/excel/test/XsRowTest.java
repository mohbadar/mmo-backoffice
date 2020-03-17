
package af.gov.anar.lib.excel.test;
import af.gov.anar.lib.excel.cells.TextCell;
import af.gov.anar.lib.excel.styles.ForegroundColor;
import af.gov.anar.lib.excel.cells.TextCells;
import af.gov.anar.lib.excel.row.XsRow;
import af.gov.anar.lib.excel.styles.XsStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link XsRow}.
 */
public final class XsRowTest {

    /**
     * Add row to a sheet.
     * @throws IOException If fails
     */
    @Test
    @SuppressWarnings("PMD.AvoidUsingShortType")
    public void addsRowToSheet() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final int expected = 4;
            final Row row =
                new XsRow()
                    .with(new TextCell("someText"))
                    .with(new TextCells("a", "b", "c"))
                    .attachTo(wbook.createSheet());
            MatcherAssert.assertThat(
                row.getLastCellNum(),
                Matchers.equalTo((short) expected)
            );
        }
    }

    /**
     * Add styled row to a sheet.
     * @throws IOException If fails
     */
    @Test
    public void addsRowWithStyleToSheet() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final Row row =
                new XsRow()
                    .with(new TextCells("a", "b", "c"))
                    .with(
                        new TextCell("text")
                            .with(
                                new XsStyle(
                                    new ForegroundColor(
                                        IndexedColors.GOLD.getIndex()
                                    )
                                )
                            )
                    )
                    .with(
                        new XsStyle(
                            new ForegroundColor(
                                IndexedColors.GREY_50_PERCENT.getIndex()
                            )
                        )
                    )
                    .attachTo(
                        wbook.createSheet()
                    );
            MatcherAssert.assertThat(
                row.getCell((int) row.getLastCellNum() - 2)
                    .getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GREY_50_PERCENT.getIndex())
            );
            MatcherAssert.assertThat(
                row.getCell((int) row.getLastCellNum() - 1)
                    .getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GOLD.getIndex())
            );
        }
    }

    /**
     * Add row to specific position in sheet.
     * @throws IOException If fails
     */
    @Test
    public void addsRowWithAbsolutePositionToSheet() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final int position = 2;
            final int expected = 1;
            final Row row = new XsRow(
                position,
                new TextCell("textPos")
            ).attachTo(wbook.createSheet());
            MatcherAssert.assertThat(
                row.getRowNum(),
                Matchers.equalTo(expected)
            );
        }
    }
}
