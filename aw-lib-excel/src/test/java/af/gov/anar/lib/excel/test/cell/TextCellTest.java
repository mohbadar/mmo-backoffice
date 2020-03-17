
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.styles.XsStyle;
import af.gov.anar.lib.excel.cells.TextCell;
import af.gov.anar.lib.excel.styles.FillPattern;
import af.gov.anar.lib.excel.styles.FontStyle;
import af.gov.anar.lib.excel.styles.ForegroundColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link af.gov.anar.lib.excel.cells.TextCell}.
 */
public final class TextCellTest {

    @Test
    public void addsCellContainingTextToRow() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final String text = "someText";
            final Cell cell = new TextCell(text).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getStringCellValue(),
                Matchers.equalTo(text)
            );
        }
    }

    @Test
    public void addsCellContainingTextInPosition() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int column = 2;
            final int expected = 1;
            final String text = "txt";
            final Cell cell = new TextCell(column, text).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getColumnIndex(),
                Matchers.equalTo(expected)
            );
        }
    }

    @Test
    public void addsStyleToCell() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final String name = "TimesNewRoman";
            final Cell cell = new TextCell("text").with(
                new XsStyle()
                    .with(
                        new ForegroundColor(
                            IndexedColors.GREY_25_PERCENT.getIndex()
                        )
                    )
                    .with(new FillPattern(FillPatternType.SOLID_FOREGROUND))
                    .with(new FontStyle().withName(name))
            )
                .attachTo(
                    workbook.createSheet().createRow(0)
                );
            MatcherAssert.assertThat(
                cell.getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GREY_25_PERCENT.getIndex())
            );
            MatcherAssert.assertThat(
                cell.getCellStyle().getFillPatternEnum(),
                Matchers.equalTo(FillPatternType.SOLID_FOREGROUND)
            );
            MatcherAssert.assertThat(
                workbook.getFontAt(
                    cell.getCellStyle().getFontIndex()
                ).getFontName(),
                Matchers.equalTo(name)
            );
        }
    }
}
