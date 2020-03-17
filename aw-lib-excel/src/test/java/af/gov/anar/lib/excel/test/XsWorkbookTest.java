
package af.gov.anar.lib.excel.test;

import af.gov.anar.lib.excel.row.XsRow;
import af.gov.anar.lib.excel.sheet.XsSheet;
import af.gov.anar.lib.excel.styles.XsStyle;
import af.gov.anar.lib.excel.workbook.XsWorkbook;
import af.gov.anar.lib.excel.cells.TextCell;
import af.gov.anar.lib.excel.styles.ForegroundColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link XsWorkbook}.
 */
public final class XsWorkbookTest {

    /**
     * Create workbook containing rows and cells.
     * @throws IOException If fails
     */
    @Test
    public void createsWorkbookWithRowsAndCells() throws IOException {
        final String text = "someText";
        final Workbook workbook = new XsWorkbook(
            new XsSheet(
                new XsRow(
                    new TextCell(text)
                )
            )
        ).asWorkbook();
        MatcherAssert.assertThat(
            workbook.getSheetAt(0).getRow(0)
                .getCell(0).getStringCellValue(),
            Matchers.containsString(text)
        );
    }

    /**
     * Create styled workbook.
     * @throws IOException If fails
     */
    @Test
    public void createsWorkbookWithStyles() throws IOException {
        final Workbook workbook = new XsWorkbook(
            new XsSheet(
                new XsRow(
                    new TextCell("text")
                )
            )
        ).with(
            new XsStyle(
                new ForegroundColor(
                    IndexedColors.GOLD.getIndex()
                )
            )
        ).asWorkbook();
        MatcherAssert.assertThat(
            workbook.getSheetAt(0).getRow(0)
                .getCell(0).getCellStyle().getFillForegroundColor(),
            Matchers.equalTo(IndexedColors.GOLD.getIndex())
        );
    }

    /**
     * Creates workbook with multiple sheets.
     * @throws IOException If fails
     */
    @Test
    public void createsWorkbookWithMultipleSheets() throws IOException {
        final String fsheet = "sheet1";
        final String ssheet = "sheet2";
        final Workbook wbook = new XsWorkbook()
            .with(new XsSheet(new XsRow(new TextCell(fsheet))))
            .with(new XsSheet(new XsRow(new TextCell(ssheet))))
            .asWorkbook();
        MatcherAssert.assertThat(
            wbook.getSheetAt(0).getRow(0).getCell(0)
                .getStringCellValue(),
            Matchers.equalTo(fsheet)
        );
        MatcherAssert.assertThat(
            wbook.getSheetAt(1).getRow(0).getCell(0)
                .getStringCellValue(),
            Matchers.equalTo(ssheet)
        );
    }
}
