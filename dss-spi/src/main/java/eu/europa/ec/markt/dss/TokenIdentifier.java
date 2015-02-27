/*
 * DSS - Digital Signature Services
 *
 * Copyright (C) 2013 European Commission, Directorate-General Internal Market and Services (DG MARKT), B-1049 Bruxelles/Brussel
 *
 * Developed by: 2013 ARHS Developments S.A. (rue Nicolas Bové 2B, L-1253 Luxembourg) http://www.arhs-developments.com
 *
 * This file is part of the "DSS - Digital Signature Services" project.
 *
 * "DSS - Digital Signature Services" is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * DSS is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * "DSS - Digital Signature Services".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.europa.ec.markt.dss;

import java.security.MessageDigest;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.binary.Hex;

import eu.europa.ec.markt.dss.exception.DSSException;
import eu.europa.ec.markt.dss.validation102853.Token;

/**
 * This class is used to obtain a unique id for Token
 */
public final class TokenIdentifier {

	private Digest tokenDigest;

	private TokenIdentifier(byte[] encodedToken) {
		if (encodedToken == null) {
			throw new DSSException("The encodedToken cannot be null!");
		}
		try {
			DigestAlgorithm algo = DigestAlgorithm.SHA256;
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digestValue = md.digest(encodedToken);

			Digest digest = new Digest(algo, digestValue);
			this.tokenDigest = digest;
		} catch (Exception e) {
			throw new DSSException(e);
		}
	}

	public TokenIdentifier(final Token token) {
		this(token.getEncoded());
	}
	
	/**
	 * Return an ID conformant to XML Id
	 */
	public String asXmlId() {
		return Hex.encodeHexString(tokenDigest.getValue());
	}

	/**
	 * Return the DSS certificate's unique id for a given
	 * {@link X509Certificate}.
	 *
	 * @param cert
	 * @return
	 * @deprecated Use constructor instead
	 */
	@Deprecated
	public static TokenIdentifier getId(final X509Certificate cert) {
		try {
			return new TokenIdentifier(cert.getEncoded());
		} catch (CertificateEncodingException e) {
			throw new DSSException(e);
		}
	}

	@Override
	public String toString() {
		return "{id:" + tokenDigest + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tokenDigest == null) ? 0 : tokenDigest.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenIdentifier other = (TokenIdentifier) obj;
		if (tokenDigest == null) {
			if (other.tokenDigest != null)
				return false;
		} else if (!tokenDigest.equals(other.tokenDigest))
			return false;
		return true;
	}

}
