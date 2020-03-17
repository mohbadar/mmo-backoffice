
package af.gov.anar.lib.excel.test.template;

import af.gov.anar.lib.excel.styles.XsStyle;
import af.gov.anar.lib.excel.cells.TextCell;
import af.gov.anar.lib.excel.styles.FillPattern;
import af.gov.anar.lib.excel.styles.ForegroundColor;
import af.gov.anar.lib.excel.templates.CellTemplate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link CellTemplateTest}.
 */
public final class CellTemplateTest {

    /**
     * Create custom cell.
     * @throws IOException If fails
     */
    @Test
    public void createsCustomCell() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Cell cell = new CellTemplateTest.GrayTextCell()
                .attachTo(workbook.createSheet().createRow(0));
            MatcherAssert.assertThat(
                cell.getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GREY_25_PERCENT.getIndex())
            );
        }
    }

    /**
     * Create custom cell with additional style.
     * @throws IOException If fails
     */
    @Test
    public void createsCustomCellWithStyle() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Cell cell = new CellTemplateTest.GrayTextCell()
                .with(
                    new XsStyle(
                        new FillPattern(FillPatternType.SOLID_FOREGROUND)
                    )
                )
                .attachTo(workbook.createSheet().createRow(0));
            MatcherAssert.assertThat(
                cell.getCellStyle().getFillPatternEnum(),
                Matchers.equalTo(FillPatternType.SOLID_FOREGROUND)
            );
        }
    }

    /**
     * Custom Gray cell.
     */
    private static final class GrayTextCell extends CellTemplate {

        /**
         * Ctor.
         */
        GrayTextCell() {
            super(
                new TextCell("someText")
                    .with(
                        new XsStyle(
                            new ForegroundColor(
                                IndexedColors.GREY_25_PERCENT.getIndex()
                            )
                        )
                    )
            );
        }
    }
}
