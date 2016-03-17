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

package com.wandrell.example.mule.wss.testing.integration.endpoint.unsecure;

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

import com.wandrell.example.mule.wss.testing.util.config.context.EndpointContextPaths;
import com.wandrell.example.mule.wss.testing.util.config.properties.SOAPPropertiesPaths;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(EndpointContextPaths.ENDPOINT_UNSECURE)
@TestPropertySource({ SOAPPropertiesPaths.TEST_SOAP })
public final class ITUnsecureEndpointFlow extends FunctionalTestCase {

    @Value("${endpoint.unsecure.codeFirst.flow}")
    private String   codeFirstFlow;
    @Value("${endpoint.unsecure.consumer.flow}")
    private String   consumerFlow;
    @Resource(name = "configFiles")
    private String[] files;
    @Value("${endpoint.unsecure.proxy.flow}")
    private String   proxyFlow;
    @Value("${endpoint.unsecure.simple.flow}")
    private String   simpleFlow;
    @Value("${endpoint.unsecure.wsdlFirst.flow}")
    private String   wsdlFirstFlow;
    @Value("${soap.unsecure.request.envelope.path}")
    private String   reqEnvelopePath;
    @Value("${soap.unsecure.response.envelope.path}")
    private String   respEnvelopePath;
    @Value("${soap.unsecure.response.payload.path}")
    private String   respPayloadPath;

    public ITUnsecureEndpointFlow() throws IOException {
        super();
    }

    @Override
    protected String getConfigResources() {
        return StringUtils.join(files, ", ");
    }

    @Test
    public final void testEndpoint_CodeFirst() throws Exception {
        final MuleEvent event;
        final String result;
        final String soapRequest;
        final String soapResponse;

        soapResponse = IOUtils.toString(
                new ClassPathResource(respEnvelopePath).getInputStream(),
                "UTF-8");

        soapRequest = IOUtils.toString(
                new ClassPathResource(reqEnvelopePath).getInputStream(),
                "UTF-8");

        event = runFlow(codeFirstFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponse, result);
    }

    @Test
    public final void testEndpoint_Consumer_Full() throws Exception {
        final MuleEvent event;
        final String result;
        final String soapRequest;
        final String soapResponsePayload;

        soapResponsePayload = IOUtils.toString(
                new ClassPathResource(
                        "soap/response/response-unsecure-payload.xml")
                        .getInputStream(), "UTF-8");

        soapRequest = IOUtils.toString(
                new ClassPathResource(reqEnvelopePath).getInputStream(),
                "UTF-8");

        event = runFlow(consumerFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponsePayload, result);
    }

    @Test
    public final void testEndpoint_Consumer_Short() throws Exception {
        final MuleEvent event;
        final String result;
        final String soapRequestPayload;
        final String soapResponsePayload;

        soapResponsePayload = IOUtils.toString(
                new ClassPathResource(
                        "soap/response/response-unsecure-payload.xml")
                        .getInputStream(), "UTF-8");

        soapRequestPayload = IOUtils.toString(new ClassPathResource(
                "soap/request/request-unsecure-payload.xml").getInputStream(),
                "UTF-8");

        event = runFlow(consumerFlow, soapRequestPayload);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponsePayload, result);
    }

    @Test
    public final void testEndpoint_Proxy() throws Exception {
        final MuleEvent event;
        final String result;
        final String soapRequest;
        final String soapResponse;

        soapResponse = IOUtils.toString(
                new ClassPathResource(respEnvelopePath).getInputStream(),
                "UTF-8");

        soapRequest = IOUtils.toString(
                new ClassPathResource(reqEnvelopePath).getInputStream(),
                "UTF-8");

        event = runFlow(proxyFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponse, result);
    }

    @Test
    public final void testEndpoint_Simple() throws Exception {
        final MuleEvent event;
        final String result;
        final String soapResponseSimple;
        final String soapRequest;

        soapRequest = IOUtils.toString(
                new ClassPathResource(reqEnvelopePath).getInputStream(),
                "UTF-8");

        soapResponseSimple = IOUtils.toString(new ClassPathResource(
                "soap/response/response-unsecure-simple.xml").getInputStream(),
                "UTF-8");

        event = runFlow(simpleFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponseSimple, result);
    }

    @Test
    public final void testEndpoint_WSDLFirst() throws Exception {
        final MuleEvent event;
        final String result;
        final String soapRequest;
        final String soapResponse;

        soapResponse = IOUtils.toString(
                new ClassPathResource(respEnvelopePath).getInputStream(),
                "UTF-8");

        soapRequest = IOUtils.toString(
                new ClassPathResource(reqEnvelopePath).getInputStream(),
                "UTF-8");

        event = runFlow(wsdlFirstFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponse, result);
    }

}
