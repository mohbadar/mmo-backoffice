package af.gov.anar.lib.hsm.model;

public class GenKeyResponse {

	public static final GenKeyResponse IO = new GenKeyResponse("IO");

	public final String  responseCode;
	public final boolean isSuccess;
	public String        keyUnderLMK;
	public String        keyUnderMasterKey;
	public String        kcv;

	public GenKeyResponse(String responseCode) {
		this.responseCode = responseCode;
		this.isSuccess    = "00".equals(responseCode);
	}

	@Override
	public String toString() {
		return "GenKeyResponse [responseCode=" + responseCode + ", isSuccess=" + isSuccess + ", keyUnderLMK=" + keyUnderLMK + ", keyUnderMasterKey="
				+ keyUnderMasterKey + ", kcv=" + kcv + "]";
	}

}
