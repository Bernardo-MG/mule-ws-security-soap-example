
package com.wandrell.example.mule.wss.testing.unit.transformer;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.springframework.core.io.ClassPathResource;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.example.mule.wss.flow.transformer.EntityResponseToExampleEntityTransformer;
import com.wandrell.example.mule.wss.model.ExampleEntity;

public final class TestEntityResponseToExampleEntityTransformer {

    private final String sourceCodeFirst;
    private final String sourceWSDLFirst;

    public TestEntityResponseToExampleEntityTransformer() throws IOException {
        super();

        sourceCodeFirst = IOUtils.toString(new ClassPathResource(
                "soap/response/response-unsecure-code-first-payload.xml")
                .getInputStream(), "UTF-8");
        sourceWSDLFirst = IOUtils.toString(new ClassPathResource(
                "soap/response/response-unsecure-wsdl-first-payload.xml")
                .getInputStream(), "UTF-8");
    }

    @Test
    public final void testTransform_CodeFirst() throws TransformerException {
        final ExampleEntity sample;
        final Transformer transformer;

        transformer = new EntityResponseToExampleEntityTransformer();

        sample = (ExampleEntity) transformer
                .transform(sourceCodeFirst, "UTF-8");

        Assert.assertEquals(sample.getId(), new Integer(1));
        Assert.assertEquals(sample.getName(), "name_1");
    }

    @Test
    public final void testTransform_WSDLFirst() throws TransformerException {
        final ExampleEntity sample;
        final Transformer transformer;

        transformer = new EntityResponseToExampleEntityTransformer();

        sample = (ExampleEntity) transformer
                .transform(sourceWSDLFirst, "UTF-8");

        Assert.assertEquals(sample.getId(), new Integer(1));
        Assert.assertEquals(sample.getName(), "name_1");
    }

}
