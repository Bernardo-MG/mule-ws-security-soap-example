package com.wandrell.example.mule.wss.endpoint;

import javax.inject.Singleton;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.example.mule.wss.model.jaxb.XmlExampleEntity;
import com.wandrell.example.mule.wss.model.jpa.JpaExampleEntity;
import com.wandrell.example.mule.wss.repository.ExampleEntityRepository;

@Service
@Singleton
@WebService(endpointInterface = "com.wandrell.example.mule.wss.endpoint.ExampleEntityEndpoint", serviceName = ExampleEntityEndpointConstants.SERVICE, targetNamespace = ExampleEntityEndpointConstants.ENTITY_NS)
public final class DefaultExampleEntityEndpoint implements
		ExampleEntityEndpoint {

	@Autowired
	private ExampleEntityRepository repository;

	public DefaultExampleEntityEndpoint() {
		super();
	}

	@Override
	public XmlExampleEntity getEntity(final Integer id) {
		final JpaExampleEntity dbSample;
		final XmlExampleEntity result;

		dbSample = getRepository().findOne(id);

		result = new XmlExampleEntity();
		result.setId(dbSample.getId());
		result.setName(dbSample.getName());

		return result;
	}

	private final ExampleEntityRepository getRepository() {
		return repository;
	}

	public final void setRepository(final ExampleEntityRepository repo) {
		this.repository = repo;
	}

}
