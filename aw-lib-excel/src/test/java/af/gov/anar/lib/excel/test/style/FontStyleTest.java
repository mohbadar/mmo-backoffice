
package af.gov.anar.lib.excel.test.style;

import af.gov.anar.lib.excel.styles.FontStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.IOException;

@SuppressWarnings("PMD.AvoidUsingShortType")
public final class FontStyleTest {

    /**
     * Add new font style.
     * @throws IOException If fails
     */
    @Test
    public void addNewFont() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final String name = "TimesNewRoman";
            final short height = 11;
            final short color = 128;
            final short offset = 0;
            final int ansi = 0;
            final CellStyle style = wbook.createCellStyle();
            new FontStyle()
                .withName(name)
                .withHeight(height)
                .withItalic()
                .withStrikeOut()
                .withColor(color)
                .withTypeOffset(offset)
                .withUnderline(FontUnderline.SINGLE.getByteValue())
                .withCharset(Font.ANSI_CHARSET)
                .withCharset(ansi)
                .withBold()
                .accept(style);
            final Font font = wbook.getFontAt(style.getFontIndex());
            MatcherAssert.assertThat(
                font.getFontName(),
                Matchers.equalTo(name)
            );
            MatcherAssert.assertThat(
                font.getFontHeight(),
                Matchers.equalTo(height)
            );
            MatcherAssert.assertThat(
                font.getItalic(),
                Matchers.equalTo(true)
            );
            MatcherAssert.assertThat(
                font.getStrikeout(),
                Matchers.equalTo(true)
            );
            MatcherAssert.assertThat(
                font.getColor(),
                Matchers.equalTo(color)
            );
            MatcherAssert.assertThat(
                font.getTypeOffset(),
                Matchers.equalTo(offset)
            );
            MatcherAssert.assertThat(
                font.getUnderline(),
                Matchers.equalTo(FontUnderline.SINGLE.getByteValue())
            );
            MatcherAssert.assertThat(
                font.getCharSet(),
                Matchers.equalTo(ansi)
            );
            MatcherAssert.assertThat(
                font.getBold(),
                Matchers.equalTo(true)
            );
        }
    }
}
