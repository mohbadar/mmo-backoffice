package af.gov.anar.lib.hsm.constants;

public enum InputFormat {

	BINARY("0"), HEX_ENCODED_BINARY("1"), TEXT("2");

	private final String format;

	private InputFormat(final String format) {
		this.format = format;
	}

	public final String toString() {
		return format;
	}
}
