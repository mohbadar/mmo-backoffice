
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.CalendarCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;

/**
 * Test case for {@link CalendarCell}.
 */
public final class CalendarCellTest {

    @Test
    public void addsCellContainingCalendarToRow() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Calendar calendar = Calendar.getInstance();
            final Cell cell = new CalendarCell(calendar).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getDateCellValue(),
                Matchers.notNullValue()
            );
        }
    }

    @Test
    public void addsCellContainingCalendarWithPositioning() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int position = 2;
            final int expected = 1;
            final Calendar calendar = Calendar.getInstance();
            final Cell cell = new CalendarCell(position, calendar).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getColumnIndex(),
                Matchers.equalTo(expected)
            );
        }
    }
}
