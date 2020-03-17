package af.gov.anar.lib.hsm.constants;

public enum KSNDescriptor {
	KSNDES009("009"),
	KSNDES609("609"),
	KSNDES809("809");
	
	private String ksnDescriptor;
	
	private KSNDescriptor(String ksnDescriptor) {
		this.ksnDescriptor = ksnDescriptor;
	}
	
	public String toString() {
		return ksnDescriptor;
	}
}
