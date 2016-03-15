
package com.wandrell.example.mule.wss.endpoint;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.example.mule.wss.generated.EntityEndpoint;
import com.wandrell.example.mule.wss.generated.GetEntityResponse;
import com.wandrell.example.mule.wss.model.ExampleEntity;
import com.wandrell.example.mule.wss.service.data.ExampleEntityService;

@Service
@Singleton
public final class WSDLFirstExampleEntityEndpoint implements EntityEndpoint {

    private final ExampleEntityService entityService;

    @Autowired
    public WSDLFirstExampleEntityEndpoint(final ExampleEntityService service) {
        super();

        entityService = service;
    }

    @Override
    public final GetEntityResponse.Return getEntity(final int id) {
        final ExampleEntity dbSample;
        final GetEntityResponse.Return result;

        dbSample = getExampleEntityService().findById(id);

        result = new GetEntityResponse.Return();
        result.setId(dbSample.getId());
        result.setName(dbSample.getName());

        return result;
    }

    private final ExampleEntityService getExampleEntityService() {
        return entityService;
    }

}
