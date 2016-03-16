
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

@ContextConfiguration(locations = { "classpath:context/test-default.xml" })
@TestPropertySource({ "classpath:config/test-soap.properties" })
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
