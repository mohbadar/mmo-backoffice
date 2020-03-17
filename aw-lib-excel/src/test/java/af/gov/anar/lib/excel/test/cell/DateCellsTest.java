
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.ECell;
import af.gov.anar.lib.excel.cells.DateCell;
import af.gov.anar.lib.excel.cells.DateCells;
import com.jcabi.immutable.Array;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import java.util.Date;

/**
 * Test case for {@link DateCells}.
 */
public final class DateCellsTest {

    @Test
    public void createsMultipleDateCells() {
        final int expected = 3;
        final Date[] dates = {new Date(), new Date(), new Date()};
        final Array<ECell> cells = new DateCells(dates).asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(DateCell.class)
        );
    }

    @Test
    public void createsMultipleDateCellsInPosition() {
        final int expected = 1;
        final Array<ECell> cells = new DateCells(2, new Date()).asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(DateCell.class)
        );
    }
}
