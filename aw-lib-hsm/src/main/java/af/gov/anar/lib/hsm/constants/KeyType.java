package af.gov.anar.lib.hsm.constants;


public enum KeyType{
	ZMK("000", ""),
	ZPK("001", "0200"),
	TMK("002", ""),
	TPK("002", ""),
	PVK("002", ""),
	TAK("003", ""),
	WWK("006", ""),
	ZAK("006", "0400"),
	BDK1("009", ""),
	ZEK("00A", ""),
	DEK("00B", ""),
	MK_AC("109", ""),
	MK_SMI("209", ""),
	IPEK("302", ""),
	MK_SMC("309", ""),
	TEK("30B", ""),
	CVK("402", ""),
	CSCK("402", ""),
	MK_DAC("409", ""),
	MK_DN("509", ""),
	BDK2("609", ""),
	MK_CVC3("709", ""),
	BDK3("809", "");
	
	public final String keyType;
	public final String safenetKeyType;
	
	private KeyType(String keyType, String safenetKeyType) {
		this.keyType = keyType;
		this.safenetKeyType = safenetKeyType;
	}
	
	public String toString() {
		return keyType;
	}
}
