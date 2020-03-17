
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.ECell;
import af.gov.anar.lib.excel.cells.CalendarCell;
import af.gov.anar.lib.excel.cells.CalendarCells;
import com.jcabi.immutable.Array;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Calendar;

/**
 * Test case for {@link CalendarCells}.
 */
public final class CalendarCellsTest {

    @Test
    public void createsMultipleCalendarCells() {
        final int expected = 3;
        final Calendar calendar = Calendar.getInstance();
        final Calendar[] dates = {calendar, calendar, calendar};
        final Array<ECell> cells = new CalendarCells(dates).asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(CalendarCell.class)
        );
    }

    @Test
    public void createsMultipleCalendarCellsInPosition() {
        final int expected = 1;
        final Array<ECell> cells =
            new CalendarCells(2, Calendar.getInstance()).asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(CalendarCell.class)
        );
    }
}
