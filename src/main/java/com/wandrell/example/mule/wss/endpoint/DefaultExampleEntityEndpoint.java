
package com.wandrell.example.mule.wss.endpoint;

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

    private final ExampleEntityService entityService;

    @Autowired
    public DefaultExampleEntityEndpoint(final ExampleEntityService service) {
        super();

        entityService = service;
    }

    @Override
    public XmlExampleEntity getEntity(final Integer id) {
        final ExampleEntity dbSample;
        final XmlExampleEntity result;

        dbSample = getExampleEntityService().findById(id);

        result = new XmlExampleEntity();
        result.setId(dbSample.getId());
        result.setName(dbSample.getName());

        return result;
    }

    private final ExampleEntityService getExampleEntityService() {
        return entityService;
    }

}
