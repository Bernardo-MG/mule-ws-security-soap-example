
package com.wandrell.example.mule.wss.testing.util.config.context;

public final class ClientContextPaths {

    public static final String CLIENT_EXTERNAL        = "classpath:context/client/test-client-remote.xml";
    public static final String CLIENT_SECURE          = "classpath:context/client/test-client-security.xml";
    public static final String CLIENT_SECURE_CONSUMER = "classpath:context/client/test-client-security-consumer.xml";
    public static final String CLIENT_UNSECURE        = "classpath:context/client/test-client-unsecure.xml";
    
    private ClientContextPaths() {
        super();
    }

}
