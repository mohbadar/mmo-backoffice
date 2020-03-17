package af.gov.anar.lib.hsm.constants;


public enum MasterKeyType {
	ZMK("000"),
	TMK("002");
	
	private String keyVariantType;
	
	MasterKeyType(String keyVariantType) {
		this.keyVariantType = keyVariantType;
	}
	
	public String toString() {
		return keyVariantType;
	}
}
