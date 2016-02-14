package com.wandrell.example.mule.swss.testing.integration.service.remote;

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
@ContextConfiguration(TestContextConfig.SERVICE_EXTERNAL)
public final class ITRemoteServiceFlow extends FunctionalTestCase {

	@Value("${service.remote.consumer.flow}")
	private String consumerFlow;
	private final String expected1;
	private final String expected2;
	@Resource(name = "configFiles")
	private String[] files;
	@Value("${service.remote.proxy.flow}")
	private String proxyFlow;
	private final String soapRequestFull;
	private final String soapRequestShort;
	private final String soapTemplateFull;
	private final String soapTemplateShort;

	{
		soapTemplateFull = "<soapenv:Envelope xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:weat=\"http://ws.cdyne.com/WeatherWS/\">\n   <soapenv:Header/>\n   <soapenv:Body>\n      <weat:GetCityForecastByZIP>\n         <!--Optional:-->\n         <weat:ZIP>%s</weat:ZIP>\n      </weat:GetCityForecastByZIP>\n   </soapenv:Body>\n</soapenv:Envelope>";
		soapTemplateShort = "<weat:GetCityForecastByZIP xmlns:weat=\"http://ws.cdyne.com/WeatherWS/\">\n   <!--Optional:-->\n   <weat:ZIP>%s</weat:ZIP>\n</weat:GetCityForecastByZIP>";

		soapRequestFull = String.format(soapTemplateFull, "11222");
		soapRequestShort = String.format(soapTemplateShort, "11222");

		expected1 = "Brooklyn";
		expected2 = "NY";
	}

	public ITRemoteServiceFlow() {
		super();
	}

	@Override
	protected String getConfigResources() {
		return StringUtils.join(files, ", ");
	}

	@Test
	public final void testEndpoint_Consumer_Full() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(consumerFlow, soapRequestFull);

		result = event.getMessageAsString();

		Assert.assertTrue(result.contains(expected1));
		Assert.assertTrue(result.contains(expected2));
	}

	@Test
	public final void testEndpoint_Consumer_Short() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(consumerFlow, soapRequestShort);

		result = event.getMessageAsString();

		Assert.assertTrue(result.contains(expected1));
		Assert.assertTrue(result.contains(expected2));
	}

	@Test
	public final void testEndpoint_Proxy() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(proxyFlow, soapRequestFull);

		result = event.getMessageAsString();

		Assert.assertTrue(result.contains(expected1));
		Assert.assertTrue(result.contains(expected2));
	}

}
