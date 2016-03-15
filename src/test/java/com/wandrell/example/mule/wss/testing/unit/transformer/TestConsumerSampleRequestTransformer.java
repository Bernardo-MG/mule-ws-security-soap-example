
package com.wandrell.example.mule.wss.testing.unit.transformer;

import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.example.mule.wss.flow.transformer.ConsumerSOAPRequestTransformer;

public final class TestConsumerSampleRequestTransformer {

    private final String result;
    private final String sourceEnvelop;
    private final String sourceNotEnvelop;

    {
        sourceEnvelop = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sam=\"http://wandrell.com/example/ws/entity\"><soapenv:Header/><soapenv:Body><sam:getSample><cod1>1</cod1><cod2>2.1</cod2></sam:getSample></soapenv:Body>\n</soapenv:Envelope>";
        sourceNotEnvelop = "<sam:getSample xmlns:sam=\"http://wandrell.com/example/ws/entity\"><cod1>1</cod1><cod2>2.1</cod2></sam:getSample>";

        result = "<sam:getSample xmlns:sam=\"http://wandrell.com/example/ws/entity\"><cod1>1</cod1><cod2>2.1</cod2></sam:getSample>";
    }

    public TestConsumerSampleRequestTransformer() {
        super();
    }

    @Test
    public final void testTransform_Enveloped() throws TransformerException {
        final String body;
        final Transformer transformer;

        transformer = new ConsumerSOAPRequestTransformer();

        body = (String) transformer.transform(sourceEnvelop);

        Assert.assertEquals(body, result);
    }

    @Test
    public final void testTransform_NotEnveloped() throws TransformerException {
        final String body;
        final Transformer transformer;

        transformer = new ConsumerSOAPRequestTransformer();

        body = (String) transformer.transform(sourceNotEnvelop);

        Assert.assertEquals(body, result);
    }

}
