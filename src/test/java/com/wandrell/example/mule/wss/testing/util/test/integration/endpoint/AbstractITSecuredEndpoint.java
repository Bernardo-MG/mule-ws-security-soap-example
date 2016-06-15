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

package com.wandrell.example.mule.wss.testing.util.test.integration.endpoint;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.testng.Assert;

/**
 * Abstract extension of {@code AbstractITEndpoint} for secured endpoints.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>An invalid SOAP envelope is rejected.</li>
 * </ol>
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
public class AbstractITSecuredEndpoint extends AbstractITEndpoint {

    /**
     * Name of the flow being tested.
     */
    @Value("${endpoint.flow}")
    private String endpointFlow;

    /**
     * Path to the invalid SOAP envelope for the request.
     */
    @Value("${soap.request.envelope.invalid.path}")
    private String requestInvalidEnvelopePath;

    /**
     * Default constructor.
     */
    public AbstractITSecuredEndpoint() {
        super();
    }

    @Test
    public final void testEndpoint_Envelope_Invalid() throws Exception {
        final String exception; // Exception message from the endpoint
        final String encoding;  // Files encoding
        final String request;   // SOAP request

        // Loads the messages
        encoding = "UTF-8";
        request = IOUtils.toString(new ClassPathResource(
                requestInvalidEnvelopePath).getInputStream(), encoding);
        // response = IOUtils.toString(
        // new ClassPathResource(responseEnvelopePath).getInputStream(),
        // encoding);

        // Sends the request to the flow
        exception = runFlow(endpointFlow, request).getMessage()
                .getExceptionPayload().getMessage();

        // Verifies that an exception message was returned
        Assert.assertTrue(exception != null);
        Assert.assertTrue(!exception.isEmpty());
    }

}
