package eu.europa.ec.markt.dss.extension.asic.asics;

import eu.europa.ec.markt.dss.extension.asic.AbstractTestASiCwithCAdESExtension;
import eu.europa.ec.markt.dss.signature.SignatureLevel;

public class ASiCsExtensionWithCAdESBToLTTest extends AbstractTestASiCwithCAdESExtension {

	@Override
	protected SignatureLevel getOriginalSignatureLevel() {
		return SignatureLevel.ASiC_S_BASELINE_B;
	}

	@Override
	protected SignatureLevel getOriginalUnderlyingSignatureLevel() {
		return SignatureLevel.CAdES_BASELINE_B;
	}

	@Override
	protected SignatureLevel getFinalSignatureLevel() {
		return SignatureLevel.ASiC_S_BASELINE_LT;
	}

	@Override
	protected SignatureLevel getFinalUnderlyingSignatureLevel() {
		return SignatureLevel.CAdES_BASELINE_LT;
	}
}
