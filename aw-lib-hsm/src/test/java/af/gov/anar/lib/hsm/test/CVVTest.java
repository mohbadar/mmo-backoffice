package af.gov.anar.lib.hsm.test;

import af.asr.lib.hsm.service.HSMConfig;
import af.asr.lib.hsm.service.HSMService;
import af.asr.lib.hsm.service.thales.ThalesHSMService;
import org.slf4j.LoggerFactory;


public class CVVTest {

	public static HSMConfig  hsmConfig  = new HSMConfig("LoggerFactory.getLogger("HSM")", 6046);
	public static HSMService hsmService = new ThalesHSMService();


	public static void main(String[] args) {
		hsmService.cvv().calculateCVV(hsmConfig, "6077990020000011", "000", "2005", "150D8C0DF3348295", "B75E6BCE8B0A1D07",  LoggerFactory.getLogger("HSM"));
		hsmService.cvv().calculateCVV(hsmConfig, "6077990020000011", "620", "2005", "150D8C0DF3348295", "B75E6BCE8B0A1D07",  LoggerFactory.getLogger("HSM"));
		hsmService.cvv().calculateCVV(hsmConfig, "6077990020000011", "999", "2005", "150D8C0DF3348295", "B75E6BCE8B0A1D07",  LoggerFactory.getLogger("HSM"));
		
		hsmService.cvv().calculateCVV(hsmConfig, "6071029990433864", "000", "2401", "150D8C0DF3348295", "B75E6BCE8B0A1D07",  LoggerFactory.getLogger("HSM"));
		hsmService.cvv().calculateCVV(hsmConfig, "6071029990433864", "620", "2401", "150D8C0DF3348295", "B75E6BCE8B0A1D07",  LoggerFactory.getLogger("HSM"));
		hsmService.cvv().calculateCVV(hsmConfig, "6071029990433864", "999", "2401", "150D8C0DF3348295", "B75E6BCE8B0A1D07",  LoggerFactory.getLogger("HSM"));
	}
}
