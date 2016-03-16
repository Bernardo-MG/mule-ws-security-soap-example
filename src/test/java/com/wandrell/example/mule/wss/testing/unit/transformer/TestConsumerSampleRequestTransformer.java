
package com.wandrell.example.mule.wss.testing.unit.transformer;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.wandrell.example.mule.wss.flow.transformer.ConsumerSOAPRequestTransformer;

public final class TestConsumerSampleRequestTransformer {

    private final String result;
    private final String sourceEnvelop;
    private final String sourcePayload;

    public TestConsumerSampleRequestTransformer() throws IOException {
        super();

        sourceEnvelop = IOUtils.toString(new ClassPathResource(
                "soap/request/request-unsecure.xml").getInputStream(), "UTF-8");
        sourcePayload = IOUtils.toString(new ClassPathResource(
                "soap/request/request-unsecure-payload.xml").getInputStream(),
                "UTF-8");
        result = sourcePayload;
    }

    @Test
    public final void testTransform_Envelope() throws TransformerException,
            SAXException, IOException {
        final String body;
        final Transformer transformer;

        transformer = new ConsumerSOAPRequestTransformer();

        body = (String) transformer.transform(sourceEnvelop, "UTF-8");

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(result, body);
    }

    @Test
    public final void testTransform_Payload() throws TransformerException,
            SAXException, IOException {
        final String body;
        final Transformer transformer;

        transformer = new ConsumerSOAPRequestTransformer();

        body = (String) transformer.transform(sourcePayload, "UTF-8");

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(result, body);
    }

}
