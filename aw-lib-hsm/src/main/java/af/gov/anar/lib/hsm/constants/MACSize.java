package af.gov.anar.lib.hsm.constants;

public enum MACSize {

	EIGHT_HEX_DIGITS("0"), SIXTEEN_HEX_DIGITS("1");

	private final String size;

	private MACSize(final String size) {
		this.size = size;
	}

	public final String toString() {
		return size;
	}
}
