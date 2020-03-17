
package af.gov.anar.lib.excel.styles;


import af.gov.anar.lib.excel.props.Props;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Wrapped text in cell.
 */
public final class WrappedText implements Props<CellStyle> {

    /**
     * Is text wrapped.
     */
    private final boolean value;

    /**
     * Ctor.
     */
    public WrappedText() {
        this(true);
    }

    /**
     * Ctor.
     * @param wrapped Is text wrapped
     */
    public WrappedText(final boolean wrapped) {
        this.value = wrapped;
    }

    @Override
    public void accept(final CellStyle style) {
        style.setWrapText(this.value);
    }
}
