package af.gov.anar.lib.hsm.model;


public class MACResponse {

	public static final MACResponse IO = new MACResponse("IO");
	
	public final String  responseCode;
	public final boolean isSuccess;
	public String        mac;
	public String        iv;

	public MACResponse(final String responseCode) {
		this.responseCode = responseCode;
		isSuccess         = "00".equals(responseCode);
	}

	@Override
	public String toString() {
		return "MACResponse [responseCode=" + responseCode + ", isSuccess=" + isSuccess + ", mac=" + mac + ", iv=" + iv + "]";
	}

}
