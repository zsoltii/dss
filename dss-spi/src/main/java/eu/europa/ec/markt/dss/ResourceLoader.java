/*
 * DSS - Digital Signature Services
 *
 * Copyright (C) 2011 European Commission, Directorate-General Internal Market and Services (DG MARKT), B-1049 Bruxelles/Brussel
 *
 * Developed by: 2011 ARHS Developments S.A. (rue Nicolas Bové 2B, L-1253 Luxembourg) http://www.arhs-developments.com
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

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import eu.europa.ec.markt.dss.exception.DSSException;
import eu.europa.ec.markt.dss.signature.DSSDocument;
import eu.europa.ec.markt.dss.signature.FileDocument;

/**
 * TODO
 * <p/>
 * <p> DISCLAIMER: Project owner DG-MARKT.
 *
 * @author <a href="mailto:dgmarkt.Project-DSS@arhs-developments.com">ARHS Developments</a>
 * @version $Revision: 1016 $ - $Date: 2011-06-17 15:30:45 +0200 (Fri, 17 Jun 2011) $
 */
public class ResourceLoader {

	protected Class<?> anyClass = ResourceLoader.class;

	public ResourceLoader() {
	}

	/**
	 * It can be used when there is a need to change the class loader.
	 *
	 * @param anyClass
	 */
	public ResourceLoader(Class<?> anyClass) {
		this.anyClass = anyClass;
	}

	public static String getNormalizedFileName(final String fileName) {

		final String normalizedFileName = fileName.replace('/', '_').replace(':', '_').replace('?', '_');
		return normalizedFileName;
	}

	/**
	 * This method converts the resource path to the absolute root resource path in source folder.
	 *
	 * @param resourcePath resource path
	 * @return converted absolute resource source folder
	 */
	public String getAbsoluteSourceResourceFolder(final String resourcePath) throws DSSException {

		final URL uri = anyClass.getResource(resourcePath);
		if (uri == null) {
			return null;
		}
		final String absolutePath = uri.getPath();
		try {
			final String decodedAbsoluteFolder = URLDecoder.decode(absolutePath, "UTF-8");
			String finalFolder = DSSUtils.replaceStrStr(decodedAbsoluteFolder, "target/test-classes", "src/test/resources");
			finalFolder = DSSUtils.replaceStrStr(finalFolder, "target/classes", "src/main/resources");
			if (finalFolder.endsWith("/")) {
				finalFolder = finalFolder.substring(0, finalFolder.length() - 1);
			}
			return finalFolder;
		} catch (UnsupportedEncodingException e) {
			throw new DSSException(e);
		}
	}

	/**
	 * This method converts the resource path to the absolute root resource path in main source folder.
	 *
	 * @param resourcePath resource path
	 * @return converted absolute resource main source folder
	 */
	public String getAbsoluteSourceMainResourceFolder(final String resourcePath) throws DSSException {

		final URL uri = anyClass.getResource(resourcePath);
		if (uri == null) {
			return null;
		}
		final String absolutePath = uri.getPath();
		try {
			final String decodedAbsoluteFolder = URLDecoder.decode(absolutePath, "UTF-8");
			String finalFolder = DSSUtils.replaceStrStr(decodedAbsoluteFolder, "target/test-classes", "src/main/resources");
			finalFolder = DSSUtils.replaceStrStr(finalFolder, "target/classes", "src/main/resources");
			if (finalFolder.endsWith("/")) {
				finalFolder = finalFolder.substring(0, finalFolder.length() - 1);
			}
			return finalFolder;
		} catch (UnsupportedEncodingException e) {
			throw new DSSException(e);
		}
	}

	/**
	 * This method converts the resource path to the absolute path in target folder.
	 *
	 * @param resourcePath resource path
	 * @return
	 */
	public String getAbsoluteResourceFolder(final String resourcePath) throws DSSException {

		final URL uri = anyClass.getResource(resourcePath);
		if (uri == null) {
			return null;
		}
		final String absolutePath = uri.getPath();
		try {
			final String decodedAbsolutePath = URLDecoder.decode(absolutePath, "UTF-8");
			return decodedAbsolutePath;
		} catch (UnsupportedEncodingException e) {
			throw new DSSException(e);
		}
	}

	/**
	 * This method converts the resource path to the input stream.
	 *
	 * @param resourcePath
	 * @return
	 */
	public InputStream getResource(final String resourcePath) {

		final InputStream resourceAsStream = anyClass.getResourceAsStream(resourcePath);
		return resourceAsStream;
	}

	/**
	 * This method returns the DSSDocument (FileDocument) created from the resource path.
	 *
	 * @param resourcePath
	 * @return
	 */
	public DSSDocument getDSSDocumentFromResource(final String resourcePath) {

		final String absolutePath = getAbsoluteResourceFolder(resourcePath);
		final DSSDocument dssDocument = new FileDocument(absolutePath);
		return dssDocument;
	}
}
