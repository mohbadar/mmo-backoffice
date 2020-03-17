
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.Locked;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link Locked}.

 */
public final class LockedTest {

    /**
     * Locks cell.
     * @throws IOException If fails
     */
    @Test
    public void addsLockedStyleToCell() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final CellStyle style = wbook.createCellStyle();
            new Locked().accept(style);
            MatcherAssert.assertThat(
                style.getLocked(),
                Matchers.equalTo(true)
            );
        }
    }
}
