
package af.gov.anar.lib.excel.test.prop.row;

import af.gov.anar.lib.excel.props.row.Height;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test cases for {@link Height}.
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class HeightTest {

    /**
     * Set row height.
     * @throws IOException If fails
     */
    @Test
    public void setsRowHeight() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final short height = (short) 10;
            final Row row = wbook.createSheet().createRow(0);
            new Height(height).accept(row);
            MatcherAssert.assertThat(
                row.getHeight(),
                Matchers.equalTo(height)
            );
        }
    }
}
