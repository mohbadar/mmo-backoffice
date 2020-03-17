
package af.gov.anar.lib.excel.test;

import af.gov.anar.lib.excel.row.XsRow;
import af.gov.anar.lib.excel.sheet.XsSheet;
import af.gov.anar.lib.excel.styles.XsStyle;

import af.gov.anar.lib.excel.cells.TextCell;
import af.gov.anar.lib.excel.styles.ForegroundColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link XsSheet}.
 */
public final class XsSheetTest {

    /**
     * Add sheet to a workbook.
     * @throws IOException IOException
     */
    @Test
    public void addsSheetToWorkbook() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int expected = 1;
            final String title = "testTitle";
            final Sheet sheet = new XsSheet(title)
                .with(new XsRow())
                .attachTo(workbook);
            MatcherAssert.assertThat(
                sheet.getLastRowNum(),
                Matchers.equalTo(expected)
            );
            MatcherAssert.assertThat(
                sheet.getSheetName(),
                Matchers.equalTo(title)
            );
        }
    }

    /**
     * Add styled sheet to a workbook.
     * @throws IOException IOException
     */
    @Test
    public void addsSheetWithStyleToWorkbook() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Sheet sheet = new XsSheet(new XsRow(new TextCell("text")))
                .with(
                    new XsStyle(
                        new ForegroundColor(
                            IndexedColors.GOLD.getIndex()
                        )
                    )
                )
                .attachTo(workbook);
            MatcherAssert.assertThat(
                sheet.getRow(0).getCell(0)
                    .getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GOLD.getIndex())
            );
        }
    }
}
