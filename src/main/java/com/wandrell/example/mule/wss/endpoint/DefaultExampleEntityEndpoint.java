
package com.wandrell.example.mule.wss.endpoint;

import static com.google.common.base.Preconditions.checkNotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Singleton;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.example.mule.wss.model.ExampleEntity;
import com.wandrell.example.mule.wss.model.jaxb.XmlExampleEntity;
import com.wandrell.example.mule.wss.service.data.ExampleEntityService;

@Service
@Singleton
@WebService(
        endpointInterface = "com.wandrell.example.mule.wss.endpoint.ExampleEntityEndpoint",
        serviceName = ExampleEntityEndpointConstants.SERVICE,
        targetNamespace = ExampleEntityEndpointConstants.ENTITY_NS)
public final class DefaultExampleEntityEndpoint implements
        ExampleEntityEndpoint {

    private static final Logger        LOGGER = LoggerFactory
            .getLogger(DefaultExampleEntityEndpoint.class);
    private final ExampleEntityService entityService;

    @Autowired
    public DefaultExampleEntityEndpoint(final ExampleEntityService service) {
        super();

        entityService = checkNotNull(service,
                "Received a null pointer as service");
    }

    @Override
    public XmlExampleEntity getEntity(final Integer id) {
        final ExampleEntity entity;
        final XmlExampleEntity response;

        checkNotNull(id, "Received a null pointer as id");

        LOGGER.debug(
                String.format("Received request for id %d", id));
        
        entity = getExampleEntityService().findById(id);
        
        response = new XmlExampleEntity();
        if (entity == null) {
            LOGGER.debug("Entity not found");
        } else {
            // The entity is transformed from the persistence model to the XML
            // one
            response.setId(entity.getId());
            response.setName(entity.getName());

            LOGGER.debug(
                    String.format("Found entity with id %1$d and name %2$s",
                            entity.getId(), entity.getName()));
        }

        return response;
    }

    private final ExampleEntityService getExampleEntityService() {
        return entityService;
    }

}
