
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.QuotePrefixed;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link QuotePrefixed}.

 */
public final class QuotePrefixedTest {

    /**
     * Add quote prefixed style to cell.
     * @throws IOException If fails
     */
    @Test
    public void addsQuotePrefixedStyleToCell() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final CellStyle style = wbook.createCellStyle();
            new QuotePrefixed().accept(style);
            MatcherAssert.assertThat(
                style.getQuotePrefixed(),
                Matchers.equalTo(true)
            );
        }
    }
}
