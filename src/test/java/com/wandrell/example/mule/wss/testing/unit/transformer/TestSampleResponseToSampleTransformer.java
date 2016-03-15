
package com.wandrell.example.mule.wss.testing.unit.transformer;

import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.example.mule.wss.flow.transformer.ExampleResponseToExampleEntityTransformer;
import com.wandrell.example.mule.wss.model.ExampleEntity;

public final class TestSampleResponseToSampleTransformer {

    private final String sourceCodeFirst;
    private final String sourceWSDLFirst;

    {
        sourceCodeFirst = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:GetEntityResponse xmlns:ns2=\"http://wandrell.com/example/ws/entity\"><return><id>1</id><name>name_1</name></return></ns2:GetEntityResponse>";
        sourceWSDLFirst = "<com.wandrell.example.ws.entity.GetEntityResponse_-Return><id>1</id><name>name_1</name></com.wandrell.example.ws.entity.GetEntityResponse_-Return>";
    }

    public TestSampleResponseToSampleTransformer() {
        super();
    }

    @Test
    public final void testTransform_CodeFirst() throws TransformerException {
        final ExampleEntity sample;
        final Transformer transformer;

        transformer = new ExampleResponseToExampleEntityTransformer();

        sample = (ExampleEntity) transformer.transform(sourceCodeFirst);

        Assert.assertEquals(sample.getId(), new Integer(1));
        Assert.assertEquals(sample.getName(), "name_1");
    }

    @Test
    public final void testTransform_WSDLFirst() throws TransformerException {
        final ExampleEntity sample;
        final Transformer transformer;

        transformer = new ExampleResponseToExampleEntityTransformer();

        sample = (ExampleEntity) transformer.transform(sourceWSDLFirst);

        Assert.assertEquals(sample.getId(), new Integer(1));
        Assert.assertEquals(sample.getName(), "name_1");
    }

}
