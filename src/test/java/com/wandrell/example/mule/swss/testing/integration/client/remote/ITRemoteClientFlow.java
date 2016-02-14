package com.wandrell.example.mule.swss.testing.integration.client.remote;

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
@ContextConfiguration(TestContextConfig.CLIENT_EXTERNAL)
public final class ITRemoteClientFlow extends FunctionalTestCase {

	@Value("${client.remote.consumer.flow}")
	private String consumerFlow;
	private final String expectedResult;
	@Resource(name = "configFiles")
	private String[] files;
	private final String payload;
	@Value("${client.remote.wsdlFirst.flow}")
	private String wsdlFirstFlow;

	{
		expectedResult = "<city>Brooklyn</city>";
		payload = "11222";
	}

	public ITRemoteClientFlow() {
		super();
	}

	@Override
	protected String getConfigResources() {
		return StringUtils.join(files, ", ");
	}

	@Test
	public final void testClient_Consumer() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(consumerFlow, payload);

		result = event.getMessageAsString();

		Assert.assertTrue(result.toLowerCase().contains(
				expectedResult.toLowerCase()));
	}

	@Test
	public final void testClient_WSDLFirst() throws Exception {
		final MuleEvent event;
		final String result;

		event = runFlow(wsdlFirstFlow, payload);

		result = event.getMessageAsString();

		Assert.assertTrue(result.toLowerCase().contains(
				expectedResult.toLowerCase()));
	}

}
