
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.Rotation;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for  {@link Rotation}.

 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class RotationTest {

    /**
     * Adds cell rotation style.
     * @throws IOException If fails
     */
    @Test
    public void addCellRotation() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final short expected = (short) 45;
            final CellStyle style = wbook.createCellStyle();
            new Rotation(expected).accept(style);
            MatcherAssert.assertThat(
                style.getRotation(),
                Matchers.equalTo(expected)
            );
        }
    }
}
