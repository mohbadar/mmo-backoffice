package af.gov.anar.lib.hsm.util;

public class Utils {

	public final static String getPAN12(final String pan) {
		return pan.substring(pan.length() - 13, pan.length() - 1);
	}
	
	public final static String getValidationData(final String pan) {
		return pan.substring(0, 10)+"N"+pan.substring(pan.length()-1);
	}
}