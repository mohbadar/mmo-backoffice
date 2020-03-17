
package af.gov.anar.lib.excel.props.sheet;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

public final class ColumnWidthTest {

    /**
     * Set column width.
     * @throws IOException If fails
     */
    @Test
    public void setColumnWidth() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final int width = 100;
            final Sheet sheet = wbook.createSheet();
            new ColumnWidth(0, width).accept(sheet);
            MatcherAssert.assertThat(
                sheet.getColumnWidth(0),
                Matchers.equalTo(width)
            );
        }
    }
}
