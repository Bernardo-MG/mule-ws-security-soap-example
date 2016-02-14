package com.wandrell.example.mule.swss.testing.integration.client.local.unsecure;

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
@ContextConfiguration(TestContextConfig.CLIENT_UNSECURE)
public final class ITUnsecureLocalClientFlow extends FunctionalTestCase {

	@Value("${client.unsecure.codeFirst.flow}")
	private String codeFirstFlow;
	@Value("${client.unsecure.consumer.flow}")
	private String consumerFlow;
	private final String expectedResult;
	@Resource(name = "configFiles")
	private String[] files;
	@Value("${client.unsecure.simple.flow}")
	private String simpleFlow;
	@Value("${client.unsecure.wsdlFirst.flow}")
	private String wsdlFirstFlow;

	{
		expectedResult = "<com.wandrell.example.mule.swss.model.sample.XMLSample>\n  <cod1>1.0</cod1>\n  <cod2>2.1</cod2>\n  <description>desc</description>\n  <extra>extra</extra>\n</com.wandrell.example.mule.swss.model.sample.XMLSample>";
	}

	public ITUnsecureLocalClientFlow() {
		super();
	}

	@Override
	protected String getConfigResources() {
		return StringUtils.join(files, ", ");
	}

	@Test
	public void testClient_CodeFirst() throws Exception {
		final Float[] payload;
		final MuleEvent event;
		final String result;

		payload = new Float[] { new Float(1), new Float(2.1) };

		event = runFlow(codeFirstFlow, payload);

		result = event.getMessageAsString();

		Assert.assertEquals(result, expectedResult);
	}

	@Test
	public void testClient_Consumer() throws Exception {
		final Float[] payload;
		final MuleEvent event;
		final String result;

		payload = new Float[] { new Float(1), new Float(2.1) };

		event = runFlow(consumerFlow, payload);

		result = event.getMessageAsString();

		Assert.assertEquals(result, expectedResult);
	}

	@Test
	public void testClient_Simple() throws Exception {
		final Float[] payload;
		final MuleEvent event;
		final String result;

		payload = new Float[] { new Float(1), new Float(2.1) };

		event = runFlow(simpleFlow, payload);

		result = event.getMessageAsString();

		Assert.assertEquals(result, expectedResult);
	}

	@Test
	public void testClient_WSDLFirst() throws Exception {
		final Float[] payload;
		final MuleEvent event;
		final String result;

		payload = new Float[] { new Float(1), new Float(2.1) };

		event = runFlow(wsdlFirstFlow, payload);

		result = event.getMessageAsString();

		Assert.assertEquals(result, expectedResult);
	}

}
