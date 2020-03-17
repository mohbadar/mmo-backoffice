package af.gov.anar.lib.hsm.constants;

public interface KeyScheme {

	public int getLength();
	
	public static enum VARIANT implements KeyScheme {
		SINGLE_LEN("Z", 1), DOUBLE_LEN("U", 2), TRIPPLE_LEN("T", 3);

		private String type;
		private int    length;

		private VARIANT(String type, int length) {
			this.type   = type;
			this.length = length;
		}

		public String getType() {
			return type;
		}

		public int getLength() {
			return length;
		}

		public String toString() {
			return type;
		}
	}

	public static enum ANSI implements KeyScheme {
		SINGLE_LEN("Z", 1), DOUBLE_LEN("X", 2), TRIPPLE_LEN("Y", 3);

		private String type;
		private int    length;

		private ANSI(String type, int length) {
			this.type   = type;
			this.length = length;
		}

		public String getType() {
			return type;
		}

		public int getLength() {
			return length;
		}

		public String toString() {
			return type;
		}
	}
}
