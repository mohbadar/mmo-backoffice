package af.gov.anar.lib.hsm.test;

import af.asr.lib.hsm.constants.PinBlockFormat;
import af.asr.lib.hsm.service.HSMConfig;
import af.asr.lib.hsm.service.HSMService;
import af.asr.lib.hsm.service.thales.ThalesHSMService;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.UnknownHostException;

public class TranslationTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		HSMConfig  hsmConfig  = new HSMConfig("127.0.0.1", 6046);
		HSMService hsmService = new ThalesHSMService();
		hsmService.translator().fromZPKToZPK(hsmConfig, "368320008173", "B58ADECA2C972098", PinBlockFormat.ISOFORMAT0, "UB27EC3FAB16D5D4D0DFCC5C3246776E3",
				PinBlockFormat.ISOFORMAT0, "U401770057601955CD7797A450474C4E1",  LoggerFactory.getLogger("HSM"));
	}
}
