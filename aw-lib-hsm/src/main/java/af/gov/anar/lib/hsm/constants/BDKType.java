package af.gov.anar.lib.hsm.constants;


public enum BDKType {
	BDK1("*"),
	BDK2("~");
	
	private String bdkFlag;
	
	private BDKType(String bdkFlag) {
		this.bdkFlag = bdkFlag;
	}
	
	public String toString() {
		return bdkFlag;
	}
}
