
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
