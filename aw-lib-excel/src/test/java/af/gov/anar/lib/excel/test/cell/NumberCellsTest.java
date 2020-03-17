
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.ECell;
import af.gov.anar.lib.excel.cells.NumberCell;
import af.gov.anar.lib.excel.cells.NumberCells;
import com.jcabi.immutable.Array;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link NumberCells}.
 */
public final class NumberCellsTest {

    @Test
    public void createsMultipleNumberCells() {
        final int expected = 3;
        final Double[] numbers = {1.0, 2.0, 3.0};
        final Array<ECell> cells = new NumberCells(numbers).asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(NumberCell.class)
        );
    }

    @Test
    public void createsMultipleNumberCellsInPosition() {
        final int expected = 1;
        final int column = 2;
        final double number = 5.0;
        final Array<ECell> cells = new NumberCells(column, number).asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(NumberCell.class)
        );
    }
}
