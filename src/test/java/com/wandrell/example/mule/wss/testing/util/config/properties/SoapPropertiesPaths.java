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
 * Paths to the test SOAP messages properties files.
 * <p>
 * Each file contains the information for finding or generating SOAP messages
 * for the tests.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class SoapPropertiesPaths {

	/**
	 * JAXB created SOAP messages for the client.
	 */
	public static final String JAXB = "classpath:config/soap/test-soap-jaxb.properties";

	/**
	 * Unsecure SOAP messages for the transformer.
	 */
	public static final String UNSECURE_TRANSFORMER = "classpath:config/soap/test-soap-transformer.properties";

	/**
	 * Private constructor to avoid initialization.
	 */
	private SoapPropertiesPaths() {
		super();
	}

}
