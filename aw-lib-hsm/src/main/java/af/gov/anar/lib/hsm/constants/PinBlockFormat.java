package af.gov.anar.lib.hsm.constants;


public enum PinBlockFormat {
	ISOFORMAT0("01"),
	ANSIX98_FORMAT0("01"),
	ISOFORMAT1("05"),
	ISOFORMAT3("47"),
	ANSIX98_FORMAT3("47");
	
	private String value;
	
	PinBlockFormat(String value) {
		this.value = value;
	}
	
	public String toString() {
		return value;
	}
	
}
