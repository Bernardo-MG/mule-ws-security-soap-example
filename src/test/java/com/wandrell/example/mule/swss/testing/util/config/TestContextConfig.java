package com.wandrell.example.mule.swss.testing.util.config;

public final class TestContextConfig {

	public static final String CLIENT_EXTERNAL = "classpath:spring/client/test-client-remote.xml";
	public static final String CLIENT_SECURE = "classpath:spring/client/test-client-security.xml";
	public static final String CLIENT_SECURE_CONSUMER = "classpath:spring/client/test-client-security-consumer.xml";
	public static final String CLIENT_UNSECURE = "classpath:spring/client/test-client-unsecure.xml";
	public static final String SERVICE_EXTERNAL = "classpath:spring/service/test-service-remote.xml";
	public static final String SERVICE_UNSECURE = "classpath:spring/service/test-service-unsecure.xml";

	private TestContextConfig() {
		super();
	}

}
