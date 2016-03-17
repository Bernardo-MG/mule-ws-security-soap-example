/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.wandrell.example.mule.wss.endpoint;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.example.mule.wss.generated.EntityEndpoint;
import com.wandrell.example.mule.wss.generated.GetEntityResponse;
import com.wandrell.example.mule.wss.model.ExampleEntity;
import com.wandrell.example.mule.wss.service.data.ExampleEntityService;

@Service
@Singleton
public final class WSDLFirstExampleEntityEndpoint implements EntityEndpoint {

    private static final Logger        LOGGER = LoggerFactory
            .getLogger(WSDLFirstExampleEntityEndpoint.class);
    private final ExampleEntityService entityService;

    @Autowired
    public WSDLFirstExampleEntityEndpoint(final ExampleEntityService service) {
        super();

        entityService = checkNotNull(service,
                "Received a null pointer as service");
    }

    @Override
    public final GetEntityResponse.Return getEntity(final int id) {
        final ExampleEntity entity;
        final GetEntityResponse.Return response;

        checkNotNull(id, "Received a null pointer as id");

        LOGGER.debug(
                String.format("Received request for id %d", id));

        entity = getExampleEntityService().findById(id);

        response = new GetEntityResponse.Return();
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
