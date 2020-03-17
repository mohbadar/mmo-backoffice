
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.FormulaCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link FormulaCell}.
 */
public final class FormulaCellTest {

    @Test
    public void addsCellWithFormulaValueToRow() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final String formula = "A1+A2";
            final Cell cell = new FormulaCell(formula).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getCellFormula(),
                Matchers.containsString(formula)
            );
        }
    }

    @Test
    public void addsCellWithFormulaValueInPosition() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int column = 2;
            final int expected = 1;
            final String formula = "B1+B2";
            final Cell cell = new FormulaCell(column, formula).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getColumnIndex(),
                Matchers.equalTo(expected)
            );
        }
    }
}
