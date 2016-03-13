package com.wandrell.example.mule.swss.endpoint;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.wandrell.example.mule.swss.model.ExampleEntity;
import com.wandrell.example.mule.swss.model.jaxb.XmlExampleEntity;

/**
 * Web service endpoint for {@link ExampleEntity}.
 * <p>
 * It is as simple as it can be, having a single service which just receives a
 * {@link GetEntityRequest} asking for an entity with a specific id, and then
 * returns a {@code GetEntityResponse} which said id.
 *
 * @author Bernardo Mart&uacute;nez Garrido
 */
@WebService(serviceName = ExampleEntityEndpointConstants.SERVICE, targetNamespace = ExampleEntityEndpointConstants.ENTITY_NS)
public interface ExampleEntityEndpoint {

	public XmlExampleEntity getEntity(
			@WebParam(name = "id") @XmlElement(required = true, nillable = false) final Integer id);

}
