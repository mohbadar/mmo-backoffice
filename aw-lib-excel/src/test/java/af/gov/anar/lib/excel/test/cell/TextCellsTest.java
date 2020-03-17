
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.ECell;
import af.gov.anar.lib.excel.styles.XsStyle;
import af.gov.anar.lib.excel.cells.TextCell;
import af.gov.anar.lib.excel.cells.TextCells;
import af.gov.anar.lib.excel.styles.FillPattern;
import com.jcabi.immutable.Array;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for {@link TextCells}.
 */
public final class TextCellsTest {

    @Test
    public void createsMultipleTextCells() {
        final int expected = 3;
        final Array<ECell> cells = new TextCells("a", "b", "c")
            .asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(TextCell.class)
        );
    }

    @Test
    public void createsMultipleTextCellsInPosition() {
        final int expected = 1;
        final Array<ECell> cells = new TextCells(2, "d")
            .asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(TextCell.class)
        );
    }

    @Test
    public void createsMultipleTextCellsWithStyle() throws IOException {
        final Array<ECell> cells = new TextCells("a", "b", "c")
            .with(
                new XsStyle(
                    new FillPattern(FillPatternType.SOLID_FOREGROUND)
                )
            ).asArray();
        try (final Workbook wbook = new XSSFWorkbook()) {
            final Row row = wbook.createSheet().createRow(0);
            for (final ECell cell : cells) {
                cell.attachTo(row);
            }
            MatcherAssert.assertThat(
                row.getCell(0).getCellStyle().getFillPatternEnum(),
                Matchers.equalTo(FillPatternType.SOLID_FOREGROUND)
            );
        }
    }
}
