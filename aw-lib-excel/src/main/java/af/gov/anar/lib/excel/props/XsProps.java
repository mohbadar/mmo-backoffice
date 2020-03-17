
package af.gov.anar.lib.excel.props;

import com.jcabi.immutable.Array;

/**
 * Properties.
 */
public final class XsProps<T> implements Props<T> {

    /**
     * List of properties.
     */
    private final Array<Props<T>> props;

    /**
     * Ctor.
     */
    public XsProps() {
        this(new Array<>());
    }

    /**
     * Ctor.
     * @param properties Properties
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public XsProps(final Props<T>... properties) {
        this(new Array<>(properties));
    }

    /**
     * Ctor.
     * @param properties Properties
     */
    public XsProps(final Iterable<Props<T>> properties) {
        this.props = new Array<>(properties);
    }

    @Override
    public void accept(final T element) {
        this.props.forEach(prop -> prop.accept(element));
    }
}
