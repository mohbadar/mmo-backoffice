package af.gov.anar.lib.hsm.constants;

public enum MACPadding {

	NO_PADDING("0"), ISO9797_PADDING1("1"), ISO9797_PADDING2("2"), ISO9797_PADDING3("3");
	
	private final String padding;

	private MACPadding(final String padding) {
		this.padding = padding;
	}

	public final String toString() {
		return padding;
	}
}
