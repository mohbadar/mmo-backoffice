
package af.gov.anar.lib.excel.test.template;

import af.gov.anar.lib.excel.styles.XsStyle;
import af.gov.anar.lib.excel.styles.FillPattern;
import af.gov.anar.lib.excel.styles.ForegroundColor;
import af.gov.anar.lib.excel.templates.StyleTemplate;
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
 * Test case for {@link StyleTemplate}.
 */
public final class StyleTemplateTest {

    /**
     * Create custom cell style.
     * @throws IOException If fails
     */
    @Test
    public void createsCustomStyle() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final Cell cell = wbook.createSheet()
                .createRow(0).createCell(0);
            new StyleTemplateTest.GrayBackground().attachTo(cell);
            MatcherAssert.assertThat(
                cell.getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GREY_25_PERCENT.getIndex())
            );
        }
    }

    /**
     * Create custom cell style with additional property.
     * @throws IOException If fails
     */
    @Test
    public void createsCustomStyleWithProperty() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final Cell cell = wbook.createSheet()
                .createRow(0).createCell(0);
            new StyleTemplateTest.GrayBackground()
                .with(new FillPattern(FillPatternType.SOLID_FOREGROUND))
                .attachTo(cell);
            MatcherAssert.assertThat(
                cell.getCellStyle().getFillPatternEnum(),
                Matchers.equalTo(FillPatternType.SOLID_FOREGROUND)
            );
        }
    }

    /**
     * Custom cell style.
     */
    private static final class GrayBackground extends StyleTemplate {

        /**
         * Ctor.
         */
        GrayBackground() {
            super(new XsStyle(
                new ForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
            ));
        }
    }
}
