
package com.wandrell.example.mule.wss.endpoint;

/**
 * Constants for the {@link ExampleEntityEndpoint}.
 * <p>
 * These define such things as the namespace of the SOAP action used, and match
 * the data on the generated WSDL.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public class ExampleEntityEndpointConstants {

    /**
     * The action for acquiring the entities.
     * <p>
     * When sending requests to the web service this action should be used if
     * the authentication systems modifies the message.
     */
    public static final String ACTION    = "http://wandrell.com/example/ws/entity/getEntity";
    /**
     * Namespace for the example entities.
     */
    public static final String ENTITY_NS = "http://wandrell.com/example/ws/entity";
    /**
     * Name for the operation used to acquire an entity.
     */
    public static final String REQUEST   = "getEntityRequest";
    /**
     * Name for the service used to acquire an entity.
     */
    public static final String SERVICE   = "EntityEndpointService";

    /**
     * Private constructor to avoid initialization.
     */
    private ExampleEntityEndpointConstants() {
        super();
    }

}
