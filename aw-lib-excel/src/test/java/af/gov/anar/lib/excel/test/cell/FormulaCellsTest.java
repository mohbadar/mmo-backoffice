
package af.gov.anar.lib.excel.test.cell;

import af.gov.anar.lib.excel.cells.ECell;
import af.gov.anar.lib.excel.cells.FormulaCell;
import af.gov.anar.lib.excel.cells.FormulaCells;
import com.jcabi.immutable.Array;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link FormulaCells}.
 */
public final class FormulaCellsTest {

    @Test
    public void createsMultipleNumberCells() {
        final int expected = 3;
        final String[] formulas = {"A+B", "A*B", "A-B"};
        final Array<ECell> cells = new FormulaCells(formulas).asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(FormulaCell.class)
        );
    }

    @Test
    public void createsMultipleNumberCellsInPosition() {
        final int expected = 1;
        final Array<ECell> cells = new FormulaCells(2, "C+D").asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(FormulaCell.class)
        );
    }
}
