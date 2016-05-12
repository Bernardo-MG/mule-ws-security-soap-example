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
import com.wandrell.example.mule.wss.service.domain.ExampleEntityService;

/**
 * Implementation of {@link EntityEndpoint} for a Mule WSDL-first endpoint. This
 * kind of endpoint will be built using Java classes generated from a WSDL file.
 * <p>
 * This is very similar to the {@link CodeFirstExampleEntityEndpoint}, just that
 * both the classes and the interface have been generated from a WSDL file.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see CodeFirstExampleEntityEndpoint
 */
@Service
@Singleton
public final class WSDLFirstExampleEntityEndpoint implements EntityEndpoint {

	/**
	 * The logger used for logging the entity endpoint.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WSDLFirstExampleEntityEndpoint.class);

	/**
	 * Service for accessing the {@code ExampleEntity} instances handled by the
	 * web service.
	 */
	private final ExampleEntityService entityService;

	/**
	 * Constructs a {@code WSDLFirstExampleEntityEndpoint}.
	 * <p>
	 * The constructor is meant to make use of Spring's IOC system.
	 *
	 * @param service
	 *            the service for the {@code ExampleEntity} instances
	 */
	@Autowired
	public WSDLFirstExampleEntityEndpoint(final ExampleEntityService service) {
		super();

		entityService = checkNotNull(service,
				"Received a null pointer as service");
	}

	@Override
	public final GetEntityResponse.Return getEntity(final int id) {
		final GetEntityResponse.Return response; // XML response with the entity
													// data
		final ExampleEntity entity; // Found entity

		checkNotNull(id, "Received a null pointer as id");

		LOGGER.debug(String.format("Received request for id %d", id));

		// Acquires the entity
		entity = getExampleEntityService().findById(id);

		response = new GetEntityResponse.Return();
		if (entity == null) {
			LOGGER.debug("Entity not found");
		} else {
			// The entity is transformed from the persistence model to the XML
			// one
			response.setId(entity.getId());
			response.setName(entity.getName());

			LOGGER.debug(String.format(
					"Found entity with id %1$d and name %2$s", entity.getId(),
					entity.getName()));
		}

		return response;
	}

	/**
	 * Returns the service used to handle the {@code ExampleEntity} instances.
	 *
	 * @return the service used to handle the {@code ExampleEntity} instances
	 */
	private final ExampleEntityService getExampleEntityService() {
		return entityService;
	}

}
