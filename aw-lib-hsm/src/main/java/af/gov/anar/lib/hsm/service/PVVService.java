package af.gov.anar.lib.hsm.service;

import af.asr.lib.hsm.constants.KSNDescriptor;
import af.asr.lib.hsm.constants.PinBlockFormat;
import af.asr.lib.hsm.constants.PinKeyType;
import af.asr.lib.hsm.model.HSMResponse;
import org.slf4j.Logger;

public interface PVVService {

	public abstract HSMResponse calculatePVVUsingPin(final HSMConfig hsmConfig, final String pan12, final String pin, final String pvk, final String pvki,
                                                     final Logger logger);

	public abstract HSMResponse calculatePVVUsingPinblock(final HSMConfig hsmConfig, final String pan12, final String pinblock, final PinBlockFormat format,
                                                          final String pvk, final String pvki, final PinKeyType pinKeyType, final String pinKey, final Logger logger);

	public abstract HSMResponse validateDUKPTPin(final HSMConfig hsmConfig, final String pan12, final String pvv, final String pinBlock,
                                                 final PinBlockFormat format, final String ksn, final KSNDescriptor ksnDescriptor, final String pvk, final String pvki, final String bdk,
                                                 final Logger logger);

	public abstract HSMResponse validateInterchangePin(final HSMConfig hsmConfig, final String pan12, final String pvv, final String pinBlock, final PinBlockFormat format,
                                                       final String pvk, final String pvki, final String zpk, final Logger logger);

	public abstract HSMResponse validateTerminalPin(final HSMConfig hsmConfig, final String pan12, final String pvv, final String pinBlock,
                                                    final PinBlockFormat format, final String pvk, final String pvki, final String tpk, final Logger logger);

	public abstract HSMResponse changePVV(final HSMConfig hsmConfig, final String pan12, final String pvv, final String pinBlock, final String newPinBlock,
                                          final String pvk, final PinBlockFormat format, final String pvki, final PinKeyType pinKeyType, final String pinKey, final Logger logger);

}
