
package af.gov.anar.lib.excel.styles;

import af.gov.anar.lib.excel.props.Props;
import com.jcabi.immutable.Array;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.function.Consumer;

/**
 * Font.
 */
@SuppressWarnings({"PMD.AvoidUsingShortType", "PMD.TooManyMethods"})
public final class FontStyle implements Props<CellStyle> {

    /**
     * Font properties.
     */
    private final Array<Consumer<Font>> props;

    /**
     * Ctor.
     */
    public FontStyle() {
        this(new Array<>());
    }

    /**
     * Ctor.
     * @param consumers Font properties
     */
    public FontStyle(final Iterable<Consumer<Font>> consumers) {
        this.props = new Array<>(consumers);
    }

    @Override
    public void accept(final CellStyle style) {
        final Font font = ((XSSFCellStyle) style).getFont();
        this.props.forEach(
            prop -> prop.accept(font)
        );
        style.setFont(font);
    }

    /**
     * Font name.
     * @param name Name
     * @return FontStyle Font style
     */
    public FontStyle withName(final String name) {
        return new FontStyle(this.props.with(font -> font.setFontName(name)));
    }

    /**
     * Font height.
     * @param height Height
     * @return FontStyle Font style
     */
    public FontStyle withHeight(final short height) {
        return new FontStyle(
            this.props.with(font -> font.setFontHeight(height))
        );
    }

    /**
     * Font height in points.
     * @param height Height
     * @return FontStyle Font style
     */
    public FontStyle withHeightInPoints(final short height) {
        return new FontStyle(
            this.props.with(font -> font.setFontHeightInPoints(height))
        );
    }

    /**
     * Italic font.
     * @return FontStyle Font style
     */
    public FontStyle withItalic() {
        return new FontStyle(
            this.props.with(font -> font.setItalic(true))
        );
    }

    /**
     * Strike out.
     * @return FontStyle Font style
     */
    public FontStyle withStrikeOut() {
        return new FontStyle(
            this.props.with(font -> font.setStrikeout(true))
        );
    }

    /**
     * Font color.
     * Usage example:
     * withColor(XSSFColor.RED.index)
     * @param color Color
     * @return FontStyle Font style
     */
    public FontStyle withColor(final short color) {
        return new FontStyle(
            this.props.with(font -> font.setColor(color))
        );
    }

    /**
     * Font offset.
     * Available values:
     * 0: BASELINE
     * 1: SUPERSCRIPT
     * 2: SUBSCRIPT
     * @param offset Offset
     * @return FontStyle Font style
     */
    public FontStyle withTypeOffset(final short offset) {
        return new FontStyle(
            this.props.with(font -> font.setTypeOffset(offset))
        );
    }

    /**
     * Font underline.
     * Usage example:
     * withUnderline(FontUnderline.SINGLE.getByteValue())
     * @param underline Underline
     * @return FontStyle Font style
     */
    public FontStyle withUnderline(final byte underline) {
        return new FontStyle(
            this.props.with(font -> font.setUnderline(underline))
        );
    }

    /**
     * Charset.
     * Usage example:
     * withCharset(Font.ANSI_CHARSET)
     * @param charset Cahrset
     * @return FontStyle Font style
     */
    public FontStyle withCharset(final byte charset) {
        return new FontStyle(
            this.props.with(font -> font.setCharSet(charset))
        );
    }

    /**
     * Charset.
     * Usage example:
     * withCharset(0) -> ANSI
     *
     * See {@link org.apache.poi.common.usermodel.fonts.FontCharset}
     * for more examples
     * @param charset Cahrset
     * @return FontStyle Font style
     */
    public FontStyle withCharset(final int charset) {
        return new FontStyle(
            this.props.with(font -> font.setCharSet(charset))
        );
    }

    /**
     * Bold font.
     * @return FontStyle Font style
     */
    public FontStyle withBold() {
        return new FontStyle(
            this.props.with(font -> font.setBold(true))
        );
    }
}
