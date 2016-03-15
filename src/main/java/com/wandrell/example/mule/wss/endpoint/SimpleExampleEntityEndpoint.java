
package com.wandrell.example.mule.wss.endpoint;

import javax.inject.Singleton;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.example.mule.wss.model.ExampleEntity;
import com.wandrell.example.mule.wss.model.jaxb.XmlExampleEntity;
import com.wandrell.example.mule.wss.service.data.ExampleEntityService;

@Service
@Singleton
public final class SimpleExampleEntityEndpoint {

    private final ExampleEntityService entityService;

    @Autowired
    public SimpleExampleEntityEndpoint(final ExampleEntityService service) {
        super();

        entityService = service;
    }

    public final XmlExampleEntity getEntity(@XmlElement(required = true,
            nillable = false) final Integer id) {
        final ExampleEntity dbSample;
        final XmlExampleEntity result;

        if (id != null) {
            dbSample = getExampleEntityService().findById(id);

            result = new XmlExampleEntity();
            result.setId(dbSample.getId());
            result.setName(dbSample.getName());
        } else {
            // TODO: This is to fix an error during the integration tests
            result = new XmlExampleEntity();
            result.setId(0);
            result.setName("");
        }

        return result;
    }

    private final ExampleEntityService getExampleEntityService() {
        return entityService;
    }

}
