
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.AlignVertical;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link AlignVerticalTest}.
 */
public final class AlignVerticalTest {

    /**
     * Adds cell alignment.
     * @throws IOException If fails
     */
    @Test
    public void addsCellAlignment() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final CellStyle style = wbook.createCellStyle();
            new AlignVertical(VerticalAlignment.CENTER).accept(style);
            MatcherAssert.assertThat(
                style.getVerticalAlignmentEnum(),
                Matchers.equalTo(VerticalAlignment.CENTER)
            );
        }
    }
}
