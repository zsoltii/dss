package eu.europa.esig.dss.cades.validation.dss2011;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import eu.europa.esig.dss.cades.validation.AbstractCAdESTestValidation;
import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.diagnostic.TimestampWrapper;
import eu.europa.esig.dss.enumerations.ArchiveTimestampType;
import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.DigestDocument;
import eu.europa.esig.dss.model.FileDocument;
import eu.europa.esig.dss.model.InMemoryDocument;

public class CAdESDetachedTstV2WithDigestDocTest extends AbstractCAdESTestValidation {

	@Override
	protected DSSDocument getSignedDocument() {
		return new FileDocument("src/test/resources/validation/dss-2011/cades-tstv2-detached.p7s");
	}
	
	@Override
	protected List<DSSDocument> getDetachedContents() {
		DigestDocument digestDocument = new DigestDocument();
		digestDocument.addDigest(DigestAlgorithm.SHA256, new InMemoryDocument("aaa".getBytes(), "data.txt").getDigest(DigestAlgorithm.SHA256));
		return Arrays.asList(digestDocument);
	}
	
	@Override
	protected void checkSignatureLevel(DiagnosticData diagnosticData) {
		assertTrue(diagnosticData.isTLevelTechnicallyValid(diagnosticData.getFirstSignatureId()));
		assertFalse(diagnosticData.isALevelTechnicallyValid(diagnosticData.getFirstSignatureId()));
	}
	
	@Override
	protected void checkTimestamps(DiagnosticData diagnosticData) {
		// not able to compute message imprint for ATSTv2 without original binaries
				int v2ArchiveTsts = 0;
				for (TimestampWrapper timestamp : diagnosticData.getTimestampList()) {
					if (ArchiveTimestampType.CAdES_V2.equals(timestamp.getArchiveTimestampType())) {
						assertFalse(timestamp.isMessageImprintDataFound());
						assertFalse(timestamp.isMessageImprintDataIntact());
						++v2ArchiveTsts;
					}
				}
				assertEquals(1, v2ArchiveTsts);
	}

}