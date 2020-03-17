package af.gov.anar.lib.hsm.constants;


public class ResponseCode {
	
	private static final String[] responseCodeDesc = new String[100];
	
	static {
		responseCodeDesc[0] 	= "Success";
		responseCodeDesc[1] 	= "PIN verification failure";
		responseCodeDesc[10]	= "TPK parity error";
		responseCodeDesc[11]	= "PVK parity error";
		
	}
}
