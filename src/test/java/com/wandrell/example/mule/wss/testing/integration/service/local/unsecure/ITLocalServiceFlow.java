package com.wandrell.example.mule.wss.testing.integration.service.local.unsecure;

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

import com.wandrell.example.mule.wss.testing.util.config.TestContextConfig;

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
	private final String soapResponseFullNamespacedSimple;
	private final String soapResponseShort;
	private final String soapTemplateFull;
	private final String soapTemplateShort;
	@Value("${service.unsecure.wsdlFirst.flow}")
	private String wsdlFirstFlow;

	{
		soapTemplateFull = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ent=\"http://wandrell.com/example/ws/entity\">\n   <soapenv:Header/>\n   <soapenv:Body>\n      <ent:getEntity>\n         <id>%s</id>\n      </ent:getEntity>\n   </soapenv:Body>\n</soapenv:Envelope>";
		soapTemplateShort = "<ent:getEntity xmlns:ent=\"http://wandrell.com/example/ws/entity\">\n   <id>%s</id>\n</ent:getEntity>";

		soapRequestFull = String.format(soapTemplateFull, "1");
		soapRequestShort = String.format(soapTemplateShort, "1");

		soapResponseFull = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:getEntityResponse xmlns:ns2=\"http://wandrell.com/example/ws/entity\"><return><id>1</id><name>entity_1</name></return></ns2:getEntityResponse></soap:Body></soap:Envelope>";
		soapResponseFullNamespaced = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns1:getEntityResponse xmlns:ns1=\"http://wandrell.com/example/ws/entity\"><ns1:return><ns2:id xmlns:ns2=\"http://wandrell.com/example/ws/entity\">1</ns2:id><ns2:name xmlns:ns2=\"http://wandrell.com/example/ws/entity\">name_1</ns2:name></ns1:return></ns1:getEntityResponse></soap:Body></soap:Envelope>";
		soapResponseFullNamespacedSimple = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns1:getEntityResponse xmlns:ns1=\"http://endpoint.swss.mule.example.wandrell.com/\"><ns1:return><ns2:id xmlns:ns2=\"http://jaxb.model.swss.mule.example.wandrell.com\">0</ns2:id><ns2:name xmlns:ns2=\"http://jaxb.model.swss.mule.example.wandrell.com\"></ns2:name></ns1:return></ns1:getEntityResponse></soap:Body></soap:Envelope>";
		soapResponseShort = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:getEntityResponse xmlns:ns2=\"http://wandrell.com/example/ws/entity\"><return><id>1</id><name>entity_1</name></return></ns2:getEntityResponse>";
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

		Assert.assertEquals(result, soapResponseFullNamespacedSimple);
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
