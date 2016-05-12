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
import javax.xml.bind.annotation.XmlElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.example.mule.wss.model.ExampleEntity;
import com.wandrell.example.mule.wss.model.jaxb.XmlExampleEntity;
import com.wandrell.example.mule.wss.service.domain.ExampleEntityService;

/**
 * Class for a Mule simple endpoint. This kind of endpoint will be built
 * directly from a Java bean, without making use of Java WS annotations.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@Service
@Singleton
public final class SimpleExampleEntityEndpoint {

	/**
	 * The logger used for logging the entity endpoint.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SimpleExampleEntityEndpoint.class);

	/**
	 * Service for accessing the {@code ExampleEntity} instances handled by the
	 * web service.
	 */
	private final ExampleEntityService entityService;

	/**
	 * Constructs a {@code SimpleExampleEntityEndpoint}.
	 * <p>
	 * The constructor is meant to make use of Spring's IOC system.
	 *
	 * @param service
	 *            the service for the {@code ExampleEntity} instances
	 */
	@Autowired
	public SimpleExampleEntityEndpoint(final ExampleEntityService service) {
		super();

		entityService = checkNotNull(service,
				"Received a null pointer as service");
	}

	/**
	 * Returns a {@link XmlExampleEntity} containing the data for the id
	 * received.
	 * <p>
	 * The {@code id} parameter is taken from the Mule flow as a web parameter,
	 * and the returned bean is a JAXB annotated class.
	 * <p>
	 * Implementations are expected to take the data contained in the returned
	 * bean from the persistence layer.
	 * 
	 * @param id
	 *            id of the entity being queried
	 * @return the queried entity
	 */
	public final XmlExampleEntity getEntity(
			@XmlElement(required = true, nillable = false) final Integer id) {
		final XmlExampleEntity response; // XML response with the entity data
		final ExampleEntity entity; // Found entity

		if (id != null) {
			checkNotNull(id, "Received a null pointer as id");

			LOGGER.debug(String.format("Received request for id %d", id));

			// Acquires the entity
			entity = getExampleEntityService().findById(id);

			response = new XmlExampleEntity();
			if (entity == null) {
				LOGGER.debug("Entity not found");
			} else {
				// The entity is transformed from the persistence model to the
				// XML
				// one
				response.setId(entity.getId());
				response.setName(entity.getName());

				LOGGER.debug(String.format(
						"Found entity with id %1$d and name %2$s",
						entity.getId(), entity.getName()));
			}
		} else {
			// TODO: This is to fix an error during the integration tests
			response = new XmlExampleEntity();
			response.setId(0);
			response.setName("");
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
