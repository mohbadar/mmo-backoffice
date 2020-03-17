
package af.gov.anar.lib.excel.test.prop.sheet;


import af.gov.anar.lib.excel.props.XsProps;
import af.gov.anar.lib.excel.sheet.XsSheet;
import af.gov.anar.lib.excel.props.sheet.DefaultColumnWidth;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;


public final class SheetPropsTest {

    /**
     * Add new sheet with properties to workbook.
     * @throws IOException If fails
     */
    @Test
    public void addsSheetWithPropertiesToWorkbook() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int width = 100;
            final Sheet sheet = new XsSheet()
                .with(
                    new XsProps<>(
                        new DefaultColumnWidth(width)
                    )
                )
                .attachTo(workbook);
            MatcherAssert.assertThat(
                sheet.getDefaultColumnWidth(),
                Matchers.equalTo(width)
            );
        }
    }
}
