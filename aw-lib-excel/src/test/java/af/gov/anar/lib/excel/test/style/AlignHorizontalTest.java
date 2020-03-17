
package af.gov.anar.lib.excel.test.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import  af.gov.anar.lib.excel.styles.AlignHorizontal;
import java.io.IOException;

/**
 * Test case for {@link AlignHorizontal}.
 */
public final class AlignHorizontalTest {

    /**
     * Adds cell alignment.
     * @throws IOException If fails
     */
    @Test
    public void addsCellAlignment() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final CellStyle style = wbook.createCellStyle();
            new AlignHorizontal(HorizontalAlignment.CENTER).accept(style);
            MatcherAssert.assertThat(
                style.getAlignmentEnum(),
                Matchers.equalTo(HorizontalAlignment.CENTER)
            );
        }
    }
}
