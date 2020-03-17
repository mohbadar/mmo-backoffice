
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.BorderBottom;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link BorderBottom}.

 */
public final class BorderBottomTest {

    /**
     * Add bottom border to cell.
     * @throws IOException If fails
     */
    @Test
    public void addsBottomBorderToCell() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final CellStyle style = wbook.createCellStyle();
            new BorderBottom(BorderStyle.DASH_DOT).accept(style);
            MatcherAssert.assertThat(
                style.getBorderBottomEnum(),
                Matchers.equalTo(BorderStyle.DASH_DOT)
            );
        }
    }
}
