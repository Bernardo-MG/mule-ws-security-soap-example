/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 the original author or authors.
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

package com.wandrell.example.mule.wss.testing.util.config.context;

/**
 * Configuration class for the test client context files paths.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class ClientContextPaths {

    /**
     * Encrypted.
     */
    public static final String ENCRYPTION = "classpath:context/client/test-client-encryption.xml";

    /**
     * Password protected.
     */
    public static final String PASSWORD   = "classpath:context/client/test-client-password.xml";

    /**
     * Signed.
     */
    public static final String SIGNATURE  = "classpath:context/client/test-client-signature.xml";

    /**
     * Unsecure.
     */
    public static final String UNSECURE   = "classpath:context/client/test-client-unsecure.xml";

    /**
     * Private constructor to avoid initialization.
     */
    private ClientContextPaths() {
        super();
    }

}
