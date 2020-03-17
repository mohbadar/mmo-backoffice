
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.BorderLeft;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link BorderLeft}.

 */
public final class BorderLeftTest {

    /**
     * Add left border to cell.
     * @throws IOException If fails
     */
    @Test
    public void addsLeftBorderToCell() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final CellStyle style = wbook.createCellStyle();
            new BorderLeft(BorderStyle.DASH_DOT).accept(style);
            MatcherAssert.assertThat(
                style.getBorderLeftEnum(),
                Matchers.equalTo(BorderStyle.DASH_DOT)
            );
        }
    }
}
