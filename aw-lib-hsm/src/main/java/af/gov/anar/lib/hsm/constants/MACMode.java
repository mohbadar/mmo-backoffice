package af.gov.anar.lib.hsm.constants;

public enum MACMode {

	//formattter:off
	ONLY_BLOCK_OF_SINGLE_BLOCK_MESSAGE("0"), 
	FIRST_BLOCK_OF_MULTI_BLOCK_MESSAGE("1"), 
	MIDDLE_BLOCK_OF_MULTI_BLOCK_MESSAGE("2"),
	FINAL_BLOCK_OF_MULTI_BLOCK_MESSAGE("3");

	private final String mode;

	private MACMode(final String mode) {
		this.mode = mode;
	}

	public final String toString() {
		return mode;
	}
}
