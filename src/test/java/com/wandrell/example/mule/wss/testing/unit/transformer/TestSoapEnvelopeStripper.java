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

package com.wandrell.example.mule.wss.testing.unit.transformer;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.wandrell.example.mule.wss.testing.util.config.context.TestContextPaths;
import com.wandrell.example.mule.wss.testing.util.config.properties.SoapPropertiesPaths;
import com.wandrell.example.mule.wss.transformer.SoapEnvelopeStripper;

/**
 * Unit tests for {@link SoapEnvelopeStripper} checking that the transformer
 * correctly parses SOAP messages.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>The transformer correctly strips SOAP 1.1 envelopes.</li>
 * <li>The transformer correctly strips SOAP 1.2 envelopes.</li>
 * <li>The transformer returns the XML message if it is not an envelope.</li>
 * </ol>
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@ContextConfiguration(locations = { TestContextPaths.DEFAULT })
@TestPropertySource({ SoapPropertiesPaths.UNSECURE_TRANSFORMER })
public final class TestSoapEnvelopeStripper extends
        AbstractTestNGSpringContextTests {

    /**
     * Path to a valid SOAP 1.1 envelope.
     */
    @Value("${soap.request.envelope.11.path}")
    private String envelopePath11;

    /**
     * Path to a valid SOAP 1.2 envelope.
     */
    @Value("${soap.request.envelope.12.path}")
    private String envelopePath12;

    /**
     * Path to a valid SOAP payload.
     */
    @Value("${soap.request.payload.path}")
    private String payloadPath;

    /**
     * Default constructor.
     */
    public TestSoapEnvelopeStripper() {
        super();
    }

    /**
     * Tests that the transformer correctly strips SOAP 1.1 envelopes.
     * 
     * @throws TransformerException
     *             never, this is a required declaration
     * @throws SAXException
     *             never, this is a required declaration
     * @throws IOException
     *             never, this is a required declaration
     */
    @Test
    public final void testTransform_Envelope_11() throws TransformerException,
            SAXException, IOException {
        final Transformer transformer; // Tested transformer
        final String source;           // Message being transformed
        final String expected;         // Expected result
        final String result;           // Result from the transformation

        expected = IOUtils.toString(
                new ClassPathResource(payloadPath).getInputStream(), "UTF-8");

        source = IOUtils
                .toString(
                        new ClassPathResource(envelopePath11).getInputStream(),
                        "UTF-8");

        transformer = new SoapEnvelopeStripper();

        result = (String) transformer.transform(source, "UTF-8");

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expected, result);
    }

    /**
     * Tests that the transformer correctly strips SOAP 1.2 envelopes.
     * 
     * @throws TransformerException
     *             never, this is a required declaration
     * @throws SAXException
     *             never, this is a required declaration
     * @throws IOException
     *             never, this is a required declaration
     */
    @Test
    public final void testTransform_Envelope_12() throws TransformerException,
            SAXException, IOException {
        final Transformer transformer; // Tested transformer
        final String source;           // Message being transformed
        final String expected;         // Expected result
        final String result;           // Result from the transformation

        expected = IOUtils.toString(
                new ClassPathResource(payloadPath).getInputStream(), "UTF-8");

        source = IOUtils
                .toString(
                        new ClassPathResource(envelopePath12).getInputStream(),
                        "UTF-8");

        transformer = new SoapEnvelopeStripper();

        result = (String) transformer.transform(source, "UTF-8");

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expected, result);
    }

    /**
     * Tests that the transformer returns the XML message if it is not an
     * envelope.
     * 
     * @throws TransformerException
     *             never, this is a required declaration
     * @throws SAXException
     *             never, this is a required declaration
     * @throws IOException
     *             never, this is a required declaration
     */
    @Test
    public final void testTransform_Payload() throws TransformerException,
            SAXException, IOException {
        final Transformer transformer; // Tested transformer
        final String source;           // Message being transformed
        final String expected;         // Expected result
        final String result;           // Result from the transformation

        source = IOUtils.toString(
                new ClassPathResource(payloadPath).getInputStream(), "UTF-8");
        expected = source;

        transformer = new SoapEnvelopeStripper();

        result = (String) transformer.transform(source, "UTF-8");

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expected, result);
    }

}
