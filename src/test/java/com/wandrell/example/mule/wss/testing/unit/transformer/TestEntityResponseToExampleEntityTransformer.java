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
