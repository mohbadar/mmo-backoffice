package af.gov.anar.lib.hsm.test;

import af.asr.lib.hsm.constants.KSNDescriptor;
import af.asr.lib.hsm.constants.PinBlockFormat;
import af.asr.lib.hsm.constants.PinKeyType;
import af.asr.lib.hsm.service.HSMConfig;
import af.asr.lib.hsm.service.HSMService;
import af.asr.lib.hsm.service.thales.ThalesHSMService;
import af.asr.lib.hsm.util.Utils;
import org.slf4j.LoggerFactory;

public class IBMTest {

	public static void main(String[] args) {

		HSMConfig  hsmConfig  = new HSMConfig("LoggerFactory.getLogger("HSM")", 6046);
		HSMService hsmService = new ThalesHSMService();
		hsmService.ibm().calculateOffsetUsingPin(hsmConfig, Utils.getPAN12("6077990020000011"), "9211", Utils.getValidationData("6077990020000011"),
				"4385B5DB5AEAF809",  LoggerFactory.getLogger("HSM"));
		hsmService.ibm().validateTerminalPin(hsmConfig, Utils.getPAN12("4135080060005875"), Utils.getValidationData("4135080060005875"), "E57ECBA24533893C",
				PinBlockFormat.ANSIX98_FORMAT0, "1272", "4385B5DB5AEAF809", "U3DD674BF1A8603473A4A3DD8BE829A2F",  LoggerFactory.getLogger("HSM"));
		hsmService.ibm().validateDUKPTPin(hsmConfig, Utils.getPAN12("0003330089020020"), Utils.getValidationData("0003330089020020"), "1B83AA2E66BA0FCD",
				PinBlockFormat.ISOFORMAT0, "5259", "110000F15CAD880004D6", KSNDescriptor.KSNDES609, "4385B5DB5AEAF809", "UB387DC23B416D398F17E431C3CB72B93",
				 LoggerFactory.getLogger("HSM"));
		hsmService.ibm().validateInterchangePin(hsmConfig, Utils.getPAN12("4135080060005875"), Utils.getValidationData("4135080060005875"), "F8ADC16806B9CDFC",
				PinBlockFormat.ISOFORMAT0, "1395", "4385B5DB5AEAF809", "U0163EE5553AFBEDB2971BD3E1CDED378",  LoggerFactory.getLogger("HSM"));
		
		hsmService.ibm().calculateOffsetUsingPinBlock(hsmConfig, Utils.getPAN12("4135080060005875"), Utils.getValidationData("4135080060005875"),
				"F8ADC16806B9CDFC", PinBlockFormat.ISOFORMAT0, PinKeyType.ZPK, "U0163EE5553AFBEDB2971BD3E1CDED378", "4385B5DB5AEAF809",  LoggerFactory.getLogger("HSM"));
		hsmService.ibm().changePinOffset(hsmConfig, Utils.getPAN12("4135080060005875"), Utils.getValidationData("4135080060005875"), "F8ADC16806B9CDFC",
				PinBlockFormat.ISOFORMAT0, "1395", PinKeyType.ZPK, "U0163EE5553AFBEDB2971BD3E1CDED378", "F8ADC16806B9CDFC", "4385B5DB5AEAF809",  LoggerFactory.getLogger("HSM"));


	}

}
