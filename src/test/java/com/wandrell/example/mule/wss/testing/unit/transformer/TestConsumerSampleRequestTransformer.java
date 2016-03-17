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

import com.wandrell.example.mule.wss.flow.transformer.ConsumerSOAPRequestTransformer;
import com.wandrell.example.mule.wss.testing.util.config.context.TestContextPaths;
import com.wandrell.example.mule.wss.testing.util.config.properties.SOAPPropertiesPaths;

@ContextConfiguration(locations = { TestContextPaths.DEFAULT })
@TestPropertySource({ SOAPPropertiesPaths.TEST_SOAP })
public final class TestConsumerSampleRequestTransformer extends
        AbstractTestNGSpringContextTests {

    @Value("${soap.unsecure.request.payload.path}")
    private String payloadPath;
    @Value("${soap.unsecure.request.envelope.path}")
    private String envelopePath;

    public TestConsumerSampleRequestTransformer() {
        super();
    }

    @Test
    public final void testTransform_Envelope() throws TransformerException,
            SAXException, IOException {
        final String body;
        final Transformer transformer;
        final String sourceEnvelope;
        final String result;

        result = IOUtils.toString(
                new ClassPathResource(payloadPath).getInputStream(), "UTF-8");

        sourceEnvelope = IOUtils.toString(
                new ClassPathResource(envelopePath).getInputStream(), "UTF-8");

        transformer = new ConsumerSOAPRequestTransformer();

        body = (String) transformer.transform(sourceEnvelope, "UTF-8");

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(result, body);
    }

    @Test
    public final void testTransform_Payload() throws TransformerException,
            SAXException, IOException {
        final String body;
        final Transformer transformer;
        final String result;
        final String sourcePayload;

        sourcePayload = IOUtils.toString(
                new ClassPathResource(payloadPath).getInputStream(), "UTF-8");
        result = sourcePayload;

        transformer = new ConsumerSOAPRequestTransformer();

        body = (String) transformer.transform(sourcePayload, "UTF-8");

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(result, body);
    }

}
