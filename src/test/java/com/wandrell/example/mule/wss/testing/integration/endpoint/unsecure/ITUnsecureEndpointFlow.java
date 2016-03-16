
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wandrell.example.mule.wss.testing.util.config.TestContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(TestContextConfig.ENDPOINT_UNSECURE)
public final class ITUnsecureEndpointFlow extends FunctionalTestCase {

    @Value("${endpoint.unsecure.codeFirst.flow}")
    private String       codeFirstFlow;
    @Value("${endpoint.unsecure.consumer.flow}")
    private String       consumerFlow;
    @Resource(name = "configFiles")
    private String[]     files;
    @Value("${endpoint.unsecure.proxy.flow}")
    private String       proxyFlow;
    @Value("${endpoint.unsecure.simple.flow}")
    private String       simpleFlow;
    private final String soapRequest;
    private final String soapRequestPayload;
    private final String soapResponse;
    private final String soapResponseSimple;
    private final String soapResponsePayload;
    @Value("${endpoint.unsecure.wsdlFirst.flow}")
    private String       wsdlFirstFlow;

    public ITUnsecureEndpointFlow() throws IOException {
        super();

        soapRequest = IOUtils.toString(new ClassPathResource(
                "soap/request/request-unsecure.xml").getInputStream(), "UTF-8");
        soapRequestPayload = IOUtils.toString(new ClassPathResource(
                "soap/request/request-unsecure-payload.xml").getInputStream(),
                "UTF-8");

        soapResponse = IOUtils.toString(new ClassPathResource(
                "soap/response/response-unsecure.xml").getInputStream(),
                "UTF-8");
        soapResponseSimple = IOUtils.toString(new ClassPathResource(
                "soap/response/response-unsecure-simple.xml").getInputStream(),
                "UTF-8");
        soapResponsePayload = IOUtils.toString(
                new ClassPathResource(
                        "soap/response/response-unsecure-payload.xml")
                        .getInputStream(), "UTF-8");
    }

    @Override
    protected String getConfigResources() {
        return StringUtils.join(files, ", ");
    }

    @Test
    public final void testEndpoint_CodeFirst() throws Exception {
        final MuleEvent event;
        final String result;

        event = runFlow(codeFirstFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponse, result);
    }

    @Test
    public final void testEndpoint_Consumer_Full() throws Exception {
        final MuleEvent event;
        final String result;

        event = runFlow(consumerFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponsePayload, result);
    }

    @Test
    public final void testEndpoint_Consumer_Short() throws Exception {
        final MuleEvent event;
        final String result;

        event = runFlow(consumerFlow, soapRequestPayload);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponsePayload, result);
    }

    @Test
    public final void testEndpoint_Proxy() throws Exception {
        final MuleEvent event;
        final String result;

        event = runFlow(proxyFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponse, result);
    }

    @Test
    public final void testEndpoint_Simple() throws Exception {
        final MuleEvent event;
        final String result;

        event = runFlow(simpleFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponseSimple, result);
    }

    @Test
    public final void testEndpoint_WSDLFirst() throws Exception {
        final MuleEvent event;
        final String result;

        event = runFlow(wsdlFirstFlow, soapRequest);

        result = event.getMessageAsString();

        XMLUnit.setIgnoreWhitespace(true);
        XMLAssert.assertXMLEqual(soapResponse, result);
    }

}
