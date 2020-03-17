
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.EmptyCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link af.gov.anar.lib.excel.cells.EmptyCell}.
 */
public final class EmptyCellTest {

    @Test
    public void createsEmptyCell() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int column = 0;
            final Cell cell = new EmptyCell().attachTo(
                workbook.createSheet().createRow(column)
            );
            MatcherAssert.assertThat(
                cell.getColumnIndex(),
                Matchers.equalTo(column)
            );
        }
    }

    @Test
    public void createsEmptyCellWithPositioning() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int column = 2;
            final int expected = 1;
            final Cell cell = new EmptyCell(column).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getColumnIndex(),
                Matchers.equalTo(expected)
            );
        }
    }
}
