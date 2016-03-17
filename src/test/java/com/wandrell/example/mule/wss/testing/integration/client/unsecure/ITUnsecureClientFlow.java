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

package com.wandrell.example.mule.wss.testing.integration.client.unsecure;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wandrell.example.mule.wss.testing.util.config.context.ClientContextPaths;
import com.wandrell.example.mule.wss.testing.util.config.properties.SOAPPropertiesPaths;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(ClientContextPaths.CLIENT_UNSECURE)
@TestPropertySource({ SOAPPropertiesPaths.TEST_SOAP })
public final class ITUnsecureClientFlow extends FunctionalTestCase {

    @Value("${client.unsecure.codeFirst.flow}")
    private String   codeFirstFlow;
    @Value("${client.unsecure.consumer.flow}")
    private String   consumerFlow;
    @Resource(name = "configFiles")
    private String[] files;
    @Value("${client.unsecure.simple.flow}")
    private String   simpleFlow;
    @Value("${client.unsecure.wsdlFirst.flow}")
    private String   wsdlFirstFlow;
    @Value("${soap.unsecure.response.jaxb.payload.path}")
    private String   respPayloadPath;

    public ITUnsecureClientFlow() throws IOException {
        super();
    }

    @Override
    protected String getConfigResources() {
        return StringUtils.join(files, ", ");
    }

    @Test
    public void testClient_CodeFirst() throws Exception {
        final Integer[] payload;
        final MuleEvent event;
        final String result;
        final String expectedResult;

        expectedResult = IOUtils.toString(
                new ClassPathResource(respPayloadPath).getInputStream(),
                "UTF-8");

        payload = new Integer[] { new Integer(1) };

        event = runFlow(codeFirstFlow, payload);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedResult, result);
    }

    @Test
    public void testClient_Consumer() throws Exception {
        final Integer[] payload;
        final MuleEvent event;
        final String result;
        final String expectedResult;

        expectedResult = IOUtils.toString(
                new ClassPathResource(respPayloadPath).getInputStream(),
                "UTF-8");

        payload = new Integer[] { new Integer(1) };

        event = runFlow(consumerFlow, payload);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedResult, result);
    }

    @Test
    public void testClient_Simple() throws Exception {
        final Integer[] payload;
        final MuleEvent event;
        final String result;
        final String expectedResult;

        expectedResult = IOUtils.toString(
                new ClassPathResource(respPayloadPath).getInputStream(),
                "UTF-8");

        payload = new Integer[] { new Integer(1) };

        event = runFlow(simpleFlow, payload);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedResult, result);
    }

    @Test
    public void testClient_WSDLFirst() throws Exception {
        final Integer[] payload;
        final MuleEvent event;
        final String result;
        final String expectedResult;

        expectedResult = IOUtils.toString(
                new ClassPathResource(respPayloadPath).getInputStream(),
                "UTF-8");

        payload = new Integer[] { new Integer(1) };

        event = runFlow(wsdlFirstFlow, payload);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(expectedResult, result);
    }

}
