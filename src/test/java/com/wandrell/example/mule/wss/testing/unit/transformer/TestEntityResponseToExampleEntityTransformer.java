
package com.wandrell.example.mule.wss.testing.unit.transformer;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.example.mule.wss.flow.transformer.EntityResponseToExampleEntityTransformer;
import com.wandrell.example.mule.wss.model.ExampleEntity;
import com.wandrell.example.mule.wss.testing.util.config.context.TestContextPaths;
import com.wandrell.example.mule.wss.testing.util.config.properties.SOAPPropertiesPaths;

@ContextConfiguration(locations = { TestContextPaths.DEFAULT })
@TestPropertySource({ SOAPPropertiesPaths.TEST_SOAP })
public final class TestEntityResponseToExampleEntityTransformer extends
        AbstractTestNGSpringContextTests {

    @Value("${soap.unsecure.response.codeFirst.payload.path}")
    private String codeFirstPath;
    @Value("${soap.unsecure.response.wsdlFirst.payload.path}")
    private String wsdlFirstPath;

    public TestEntityResponseToExampleEntityTransformer() {
        super();
    }

    @Test
    public final void testTransform_CodeFirst() throws TransformerException,
            IOException {
        final ExampleEntity sample;
        final Transformer transformer;
        final String sourceCodeFirst;

        sourceCodeFirst = IOUtils.toString(
                new ClassPathResource(codeFirstPath).getInputStream(), "UTF-8");

        transformer = new EntityResponseToExampleEntityTransformer();

        sample = (ExampleEntity) transformer
                .transform(sourceCodeFirst, "UTF-8");

        Assert.assertEquals(sample.getId(), new Integer(1));
        Assert.assertEquals(sample.getName(), "name_1");
    }

    @Test
    public final void testTransform_WSDLFirst() throws TransformerException,
            IOException {
        final ExampleEntity sample;
        final Transformer transformer;
        final String sourceWSDLFirst;

        sourceWSDLFirst = IOUtils.toString(
                new ClassPathResource(wsdlFirstPath).getInputStream(), "UTF-8");

        transformer = new EntityResponseToExampleEntityTransformer();

        sample = (ExampleEntity) transformer
                .transform(sourceWSDLFirst, "UTF-8");

        Assert.assertEquals(sample.getId(), new Integer(1));
        Assert.assertEquals(sample.getName(), "name_1");
    }

}
