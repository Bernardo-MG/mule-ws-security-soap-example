/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.wandrell.example.mule.wss.testing.util.config.properties;

/**
 * Paths to the proxy clients configuration properties files.
 * <p>
 * These files contain the data required for setting up a client test context,
 * and mostly indicate which flow to use when testing.
 *
 * @author Bernardo Martínez Garrido
 */
public final class ClientProxyPropertiesPaths {

	/**
	 * Encrypted.
	 */
	public static final String ENCRYPTION = "classpath:config/client/cxf/encryption/test-client-cxf-encryption-proxy.properties";

	/**
	 * Password protected.
	 */
	public static final String PASSWORD = "classpath:config/client/cxf/password/test-client-cxf-password-proxy.properties";

	/**
	 * Password protected with SAML.
	 */
	public static final String PASSWORD_SAML = "classpath:config/client/cxf/password/saml/test-client-cxf-password-saml-proxy.properties";

	/**
	 * Signed.
	 */
	public static final String SIGNATURE = "classpath:config/client/cxf/signature/test-client-cxf-signature-proxy.properties";

	/**
	 * Unsecure.
	 */
	public static final String UNSECURE = "classpath:config/client/cxf/unsecure/test-client-cxf-unsecure-proxy.properties";

	/**
	 * Private constructor to avoid initialization.
	 */
	private ClientProxyPropertiesPaths() {
		super();
	}

}
