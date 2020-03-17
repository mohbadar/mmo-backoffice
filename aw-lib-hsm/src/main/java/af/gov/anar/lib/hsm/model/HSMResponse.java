package af.gov.anar.lib.hsm.model;

public class HSMResponse {

	public static final HSMResponse IO = new HSMResponse("IO");
	
	public final String  responseCode;
	public final boolean isSuccess;
	public String        value;

	public HSMResponse(final String responseCode) {
		this.responseCode = responseCode;
		isSuccess         = "00".equals(responseCode);
	}

	@Override
	public String toString() {
		return "HSMResponse [responseCode=" + responseCode + ", isSuccess=" + isSuccess + ", value=" + value + "]";
	}
}
