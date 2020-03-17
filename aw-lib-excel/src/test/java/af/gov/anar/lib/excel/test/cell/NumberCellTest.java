
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.NumberCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link NumberCell}.
 */
public final class NumberCellTest {

    @Test
    public void addsCellContainingNumberToRow() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Double number = 5.0;
            final Cell cell = new NumberCell(number).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getNumericCellValue(),
                Matchers.equalTo(number)
            );
        }
    }

    @Test
    public void addsCellContainingNumberInPosition() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int column = 2;
            final int expected = 1;
            final Double number = 5.0;
            final Cell cell = new NumberCell(column, number).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getColumnIndex(),
                Matchers.equalTo(expected)
            );
        }
    }
}
