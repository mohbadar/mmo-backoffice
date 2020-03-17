
package af.gov.anar.lib.excel.test.prop.row;

import af.gov.anar.lib.excel.props.row.ZeroHeight;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link ZeroHeight}.
 */
public final class ZeroHeightTest {

    /**
     * Set row zero height.
     * @throws IOException If fails
     */
    @Test
    public void setsZeroHeight() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final boolean zero = true;
            final Row row = wbook.createSheet().createRow(0);
            new ZeroHeight(zero).accept(row);
            MatcherAssert.assertThat(
                row.getZeroHeight(),
                Matchers.equalTo(zero)
            );
        }
    }
}
