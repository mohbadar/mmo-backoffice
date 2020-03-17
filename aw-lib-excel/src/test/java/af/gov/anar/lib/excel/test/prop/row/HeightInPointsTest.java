
package af.gov.anar.lib.excel.props.row;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link HeightInPoints}.
 */
public final class HeightInPointsTest {

    /**
     * Set row height in points.
     * @throws IOException If fails
     */
    @Test
    public void setsRowHightInPoints() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final float points = 10.0F;
            final Row row = wbook.createSheet().createRow(0);
            new HeightInPoints(points).accept(row);
            MatcherAssert.assertThat(
                row.getHeightInPoints(),
                Matchers.equalTo(points)
            );
        }
    }
}
