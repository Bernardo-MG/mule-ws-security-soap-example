
package com.wandrell.example.mule.wss.testing.util.config;

public final class TestContextConfig {

    public static final String CLIENT_EXTERNAL        = "classpath:context/client/test-client-remote.xml";
    public static final String CLIENT_SECURE          = "classpath:context/client/test-client-security.xml";
    public static final String CLIENT_SECURE_CONSUMER = "classpath:context/client/test-client-security-consumer.xml";
    public static final String CLIENT_UNSECURE        = "classpath:context/client/test-client-unsecure.xml";
    public static final String SERVICE_EXTERNAL       = "classpath:context/service/test-service-remote.xml";
    public static final String SERVICE_UNSECURE       = "classpath:context/service/test-service-unsecure.xml";

    private TestContextConfig() {
        super();
    }

}
