package af.gov.anar.lib.hsm.util;

public class Strings {

	public static boolean isNullOrEmptyOrSpace(String string) {
		return string == null || string.trim().length() == 0;
	}


	public static boolean isNullOrEmpty(String string) {
		return string == null || string.length() == 0;
	}


	public static boolean isNull(String string) {
		return string == null;
	}


	public static boolean isEmpty(String string) {
		return string.length() == 0;
	}


	public static String padRight(String string, char padChar, int len) {
		if (string == null || string.length() >= len) return string;
		StringBuilder sb = new StringBuilder(len);
		sb.append(string);
		while (sb.length() != len) sb.append(padChar);
		return sb.toString();
	}
	
	public static String padRight(StringBuilder sb, char padChar, int len) {
		if (sb == null || sb.length() >= len) return sb.toString();
		while (sb.length() != len) sb.append(padChar);
		return sb.toString();
	}

	public static String padLeft(String string, char padChar, int len) {
		if (string == null || string.length() >= len) return string;
		StringBuilder sb = new StringBuilder(len);
		while (sb.length() != (len - string.length())) sb.append(padChar);
		sb.append(string);
		return sb.toString();
	}
	
	

}
