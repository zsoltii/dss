package eu.europa.ec.markt.dss.cookbook.example.sign;

import java.io.IOException;

import eu.europa.ec.markt.dss.DSSUtils;
import eu.europa.ec.markt.dss.DigestAlgorithm;
import eu.europa.ec.markt.dss.cookbook.example.Cookbook;
import eu.europa.ec.markt.dss.exception.DSSException;
import eu.europa.ec.markt.dss.parameter.SignatureParameters;
import eu.europa.ec.markt.dss.signature.DSSDocument;
import eu.europa.ec.markt.dss.signature.SignatureLevel;
import eu.europa.ec.markt.dss.signature.SignaturePackaging;
import eu.europa.ec.markt.dss.signature.xades.XAdESService;
import eu.europa.ec.markt.dss.validation102853.CommonCertificateVerifier;
import eu.europa.ec.markt.dss.validation102853.crl.OnlineCRLSource;
import eu.europa.ec.markt.dss.validation102853.https.CommonsDataLoader;
import eu.europa.ec.markt.dss.validation102853.ocsp.OnlineOCSPSource;
import eu.europa.ec.markt.dss.validation102853.tsl.TSLRefreshPolicy;
import eu.europa.ec.markt.dss.validation102853.tsl.TrustedListsCertificateSource;

/**
 * How to sign with XAdES-BASELINE-LT
 */
public class SignXmlXadesLT extends Cookbook {

	public static void main(String[] args) throws DSSException, IOException {

		// GET document to be signed -
		// Return DSSDocument toSignDocument
		prepareXmlDoc();

		// Get a token connection based on a pkcs12 file commonly used to store private
		// keys with accompanying public key certificates, protected with a password-based
		// symmetric key -
		// Return AbstractSignatureTokenConnection signingToken
		// and it's first private key entry from the PKCS12 store
		// Return DSSPrivateKeyEntry privateKey *****
		preparePKCS12TokenAndKey();

		// Preparing parameters for the XAdES signature
		SignatureParameters parameters = new SignatureParameters();
		// We choose the level of the signature (-B, -T, -LT, -LTA).
		parameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_LT);
		// We choose the type of the signature packaging (ENVELOPED, ENVELOPING, DETACHED).
		parameters.setSignaturePackaging(SignaturePackaging.ENVELOPED);
		// We set the digest algorithm to use with the signature algorithm. You must use the
		// same parameter when you invoke the method sign on the token. The default value is SHA256
		parameters.setDigestAlgorithm(DigestAlgorithm.SHA256);
		// We choose the private key with the certificate and corresponding certificate chain.
		parameters.setPrivateKeyEntry(privateKey);

		// Create common certificate verifier
		CommonCertificateVerifier commonCertificateVerifier = new CommonCertificateVerifier();

		CommonsDataLoader commonsHttpDataLoader = new CommonsDataLoader();

		String lotlUrl = "https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-mp.xml";
		TrustedListsCertificateSource tslCertificateSource = new TrustedListsCertificateSource();
		tslCertificateSource.setLotlUrl(lotlUrl);
		tslCertificateSource.setCheckSignature(false);
		tslCertificateSource.setDataLoader(commonsHttpDataLoader);
		tslCertificateSource.setTslRefreshPolicy(TSLRefreshPolicy.NEVER);
		tslCertificateSource.init();
		commonCertificateVerifier.setTrustedCertSource(tslCertificateSource);

		OnlineCRLSource onlineCRLSource = new OnlineCRLSource();
		onlineCRLSource.setDataLoader(commonsHttpDataLoader);
		commonCertificateVerifier.setCrlSource(onlineCRLSource);

		OnlineOCSPSource onlineOCSPSource = new OnlineOCSPSource();
		onlineCRLSource.setDataLoader(commonsHttpDataLoader);
		commonCertificateVerifier.setOcspSource(onlineOCSPSource);

		// Create XAdES service for signature
		XAdESService service = new XAdESService(commonCertificateVerifier);
		service.setTspSource(getMockTSPSource());

		// Get the SignedInfo XML segment that need to be signed.
		byte[] dataToSign = service.getDataToSign(toSignDocument, parameters);

		// This function obtains the signature value for signed information using the
		// private key and specified algorithm
		byte[] signatureValue = signingToken.sign(dataToSign, parameters.getDigestAlgorithm(), privateKey);

		// We invoke the service to sign the document with the signature value obtained in
		// the previous step.
		DSSDocument signedDocument = service.signDocument(toSignDocument, parameters, signatureValue);

		//DSSUtils.copy(signedDocument.openStream(), System.out);
		DSSUtils.saveToFile(signedDocument.openStream(), "signedXmlXadesLT.xml");
	}
}
