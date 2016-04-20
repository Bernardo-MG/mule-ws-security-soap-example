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

package com.wandrell.example.mule.wss.testing.util.test.integration.client;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mule.tck.junit4.FunctionalTestCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wandrell.example.mule.wss.testing.util.config.properties.SoapPropertiesPaths;

/**
 * Abstract integration tests for an unsecure client flow testing that it
 * handles messages correctly.
 * <p>
 * A client expects the id of the queried entity, as an integer, for the
 * message. It returns the entity as a XML.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>A valid payload is processed and a valid response returned.</li>
 * </ol>
 * <p>
 * Pay attention to the fact that it requires the WS to be running, and a Spring
 * context to populate the test data.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource({ SoapPropertiesPaths.JAXB })
public class AbstractITClientFlow extends FunctionalTestCase {

    /**
     * Name of the flow being tested.
     */
    @Value("${client.flow}")
    private String   clientFlow;
    /**
     * Configuration files to be loaded to build the Mule context.
     */
    @Resource(name = "configFiles")
    private String[] files;
    /**
     * Path to the SOAP payload for the response.
     */
    @Value("${soap.response.payload.path}")
    private String   responsePath;

    /**
     * Default constructor.
     */
    public AbstractITClientFlow() {
        super();
    }

    /**
     * Tests that a valid payload is processed and a valid response returned.
     * 
     * @throws Exception
     *             never, this is a required declaration
     */
    @Test
    public void testClient() throws Exception {
        final Integer[] payload; // Payload sent to the flow
        final String result;     // Response from the endpoint
        final String encoding;   // Files encoding
        final String response;   // SOAP response

        // Loads the messages
        encoding = "UTF-8";
        response = IOUtils.toString(
                new ClassPathResource(responsePath).getInputStream(), encoding);

        // Prepares the payload
        payload = new Integer[] { new Integer(1) };

        // Sends the request to the flow
        result = runFlow(clientFlow, payload).getMessageAsString();

        // Verifies results
        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(response, result);
    }

    @Override
    protected String getConfigResources() {
        return StringUtils.join(files, ", ");
    }

}
