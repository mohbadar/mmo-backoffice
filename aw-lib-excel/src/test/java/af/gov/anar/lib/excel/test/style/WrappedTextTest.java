
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.WrappedText;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link WrappedText}.

 */
public final class WrappedTextTest {

    /**
     * Wraps text in a cell.
     * @throws IOException If fails
     */
    @Test
    public void wrapsTextInCell() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final CellStyle style = wbook.createCellStyle();
            new WrappedText().accept(style);
            MatcherAssert.assertThat(
                style.getWrapText(),
                Matchers.equalTo(true)
            );
        }
    }
}
