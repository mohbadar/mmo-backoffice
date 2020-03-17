
package af.gov.anar.lib.excel.test.prop.sheet;

import af.gov.anar.lib.excel.props.sheet.DefaultColumnWidth;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;


public final class DefaultColumnWidthTest {

    /**
     * Set default column width.
     * @throws IOException If fails
     */
    @Test
    public void setsDefaultColumnWidth() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final int width = 100;
            final Sheet sheet = wbook.createSheet();
            new DefaultColumnWidth(width).accept(sheet);
            MatcherAssert.assertThat(
                sheet.getDefaultColumnWidth(),
                Matchers.equalTo(width)
            );
        }
    }
}
