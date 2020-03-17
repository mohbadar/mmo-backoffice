
package af.gov.anar.lib.excel.test.prop.row;


import af.gov.anar.lib.excel.props.XsProps;
import af.gov.anar.lib.excel.row.XsRow;
import af.gov.anar.lib.excel.props.row.HeightInPoints;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for row properties.
 */
public final class RowPropsTest {

    /**
     * Add properties to row.
     * @throws IOException If fails
     */
    @Test
    public void addRowWithPropertiesToSheet() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final float points = 10.0F;
            final Row row = new XsRow().with(
                new XsProps<>(
                    new HeightInPoints(points)
                )
            ).attachTo(wbook.createSheet());
            MatcherAssert.assertThat(
                row.getHeightInPoints(),
                Matchers.equalTo(points)
            );
        }
    }
}
