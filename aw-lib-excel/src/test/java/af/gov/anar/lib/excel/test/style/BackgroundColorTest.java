
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.BackgroundColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link BackgroundColor}.

 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class BackgroundColorTest {

    /**
     * Add background color to cell.
     * @throws IOException If fails
     */
    @Test
    public void addsBackgroundColorToCell() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final short expected = (short) 50;
            final CellStyle style = wbook.createCellStyle();
            new BackgroundColor(expected).accept(style);
            MatcherAssert.assertThat(
                style.getFillBackgroundColor(),
                Matchers.equalTo(expected)
            );
        }
    }
}
