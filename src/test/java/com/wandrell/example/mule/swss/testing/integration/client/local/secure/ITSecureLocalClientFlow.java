package com.wandrell.example.mule.swss.testing.integration.client.local.secure;

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
@ContextConfiguration(TestContextConfig.CLIENT_SECURE)
public final class ITSecureLocalClientFlow extends FunctionalTestCase {

	@Value("${client.secure.encrypted.flow}")
	private String encryptedFlow;
	private final String expectedResult;
	@Resource(name = "configFiles")
	private String[] files;
	@Value("${client.secure.signed.flow}")
	private String signedFlow;
	@Value("${client.secure.username.flow}")
	private String usernameFlow;

	{
		expectedResult = "<com.wandrell.example.mule.swss.model.sample.XMLSample>\n  <cod1>1.0</cod1>\n  <cod2>2.1</cod2>\n  <description>desc</description>\n  <extra>extra</extra>\n</com.wandrell.example.mule.swss.model.sample.XMLSample>";
	}

	public ITSecureLocalClientFlow() {
		super();
	}

	@Override
	protected String getConfigResources() {
		return StringUtils.join(files, ", ");
	}

	@Test
	public void testClient_Encrypted() throws Exception {
		final Float[] payload;
		final MuleEvent event;
		final String result;

		payload = new Float[] { new Float(1), new Float(2.1) };

		event = runFlow(encryptedFlow, payload);

		result = event.getMessageAsString();

		Assert.assertEquals(result, expectedResult);
	}

	@Test
	public void testClient_Signed() throws Exception {
		final Float[] payload;
		final MuleEvent event;
		final String result;

		payload = new Float[] { new Float(1), new Float(2.1) };

		event = runFlow(signedFlow, payload);

		result = event.getMessageAsString();

		Assert.assertEquals(result, expectedResult);
	}

	@Test
	public void testClient_UsernameToken() throws Exception {
		final Float[] payload;
		final MuleEvent event;
		final String result;

		payload = new Float[] { new Float(1), new Float(2.1) };

		event = runFlow(usernameFlow, payload);

		result = event.getMessageAsString();

		Assert.assertEquals(result, expectedResult);
	}

}
