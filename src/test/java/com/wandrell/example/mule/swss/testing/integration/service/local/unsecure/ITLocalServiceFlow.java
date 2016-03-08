package com.wandrell.example.mule.swss.testing.integration.service.local.unsecure;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;

import com.wandrell.example.mule.swss.testing.util.config.TestContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(TestContextConfig.SERVICE_UNSECURE)
public final class ITLocalServiceFlow extends FunctionalTestCase {

	@Value("${service.unsecure.codeFirst.flow}")
	private String codeFirstFlow;
	@Value("${service.unsecure.consumer.flow}")
	private String consumerFlow;
	@Resource(name = "configFiles")
	private String[] files;
	@Value("${service.unsecure.proxy.flow}")
	private String proxyFlow;
	@Value("${service.unsecure.simple.flow}")
	private String simpleFlow;
	private final String soapRequestFull;
	private final String soapRequestShort;
	private final String soapResponseFull;
	private final String soapResponseFullNamespaced;
	private final String soapResponseShort;
	private final String soapTemplateFull;
	private final String soapTemplateShort;
	@Value("${service.unsecure.wsdlFirst.flow}")
	private String wsdlFirstFlow;

	{
		soapTemplateFull = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sam=\"http://wandrell.com/example/ws/entity\">\n   <soapenv:Header/>\n   <soapenv:Body>\n      <sam:getSample>\n         <cod1>%s</cod1>\n         <cod2>%s</cod2>\n      </sam:getSample>\n   </soapenv:Body>\n</soapenv:Envelope>";
		soapTemplateShort = "<sam:getSample xmlns:sam=\"http://wandrell.com/example/ws/entity\">\n   <cod1>%s</cod1>\n   <cod2>%s</cod2>\n</sam:getSample>";

		soapRequestFull = String.format(soapTemplateFull, "1", "2.1");
		soapRequestShort = String.format(soapTemplateShort, "1", "2.1");

		soapResponseFull = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:getSampleResponse xmlns:ns2=\"http://wandrell.com/example/ws/entity\"><return><cod1>1.0</cod1><cod2>2.1</cod2><description>desc</description><extra>extra</extra></return></ns2:getSampleResponse></soap:Body></soap:Envelope>";
		soapResponseFullNamespaced = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns1:getSampleResponse xmlns:ns1=\"http://wandrell.com/example/ws/entity\"><ns1:return><ns2:cod1 xmlns:ns2=\"http://wandrell.com/example/ws/entity\">0.0</ns2:cod1><ns2:cod2 xmlns:ns2=\"http://wandrell.com/example/ws/entity\">0.0</ns2:cod2><ns2:description xmlns:ns2=\"http://wandrell.com/example/ws/entity\"></ns2:description><ns2:extra xmlns:ns2=\"http://wandrell.com/example/ws/entity\"></ns2:extra></ns1:return></ns1:getSampleResponse></soap:Body></soap:Envelope>";
		soapResponseShort = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:getSampleResponse xmlns:ns2=\"http://wandrell.com/example/ws/entity\"><return><cod1>1.0</cod1><cod2>2.1</cod2><description>desc</description><extra>extra</extra></return></ns2:getSampleResponse>";
	}

	public ITLocalServiceFlow() {
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

		event = runFlow(codeFirstFlow, soapRequestFull);

		result = event.getMessageAsString();

		Assert.assertEquals(result, soapResponseFull);
	}

	@Test
	public final void testEndpoint_Consumer_Full() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(consumerFlow, soapRequestFull);

		result = event.getMessageAsString();

		Assert.assertEquals(result, soapResponseShort);
	}

	@Test
	public final void testEndpoint_Consumer_Short() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(consumerFlow, soapRequestShort);

		result = event.getMessageAsString();

		Assert.assertEquals(result, soapResponseShort);
	}

	@Test
	public final void testEndpoint_Proxy() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(proxyFlow, soapRequestFull);

		result = event.getMessageAsString();

		Assert.assertEquals(result, soapResponseFull);
	}

	@Test
	public final void testEndpoint_Simple() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(simpleFlow, soapRequestFull);

		result = event.getMessageAsString();

		Assert.assertEquals(result, soapResponseFullNamespaced);
	}

	@Test
	public final void testEndpoint_WSDLFirst() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(wsdlFirstFlow, soapRequestFull);

		result = event.getMessageAsString();

		Assert.assertEquals(result, soapResponseFull);
	}

}
