
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.Indention;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link Indention}.

 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class IndentionTest {

    /**
     * Adds indention to cell.
     * @throws IOException If fails
     */
    @Test
    public void addIndentionToCell() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final short expected = (short) 10;
            final CellStyle style = wbook.createCellStyle();
            new Indention(expected).accept(style);
            MatcherAssert.assertThat(
                style.getIndention(),
                Matchers.equalTo(expected)
            );
        }
    }
}
