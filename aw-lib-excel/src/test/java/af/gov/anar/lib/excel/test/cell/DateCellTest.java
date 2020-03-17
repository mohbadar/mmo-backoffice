
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.DateCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public final class DateCellTest {

    @Test
    public void addsCellContainingDateToRow() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Date date = new Date();
            final Cell cell = new DateCell(date).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getDateCellValue(),
                Matchers.equalTo(date)
            );
        }
    }

    @Test
    public void addsCellContainingDateWithPosition() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int position = 2;
            final int expected = 1;
            final Date date = new Date();
            final Cell cell = new DateCell(position, date).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getColumnIndex(),
                Matchers.equalTo(expected)
            );
        }
    }
}
