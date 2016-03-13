package com.wandrell.example.mule.swss.endpoint;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.example.mule.swss.model.jpa.JpaExampleEntity;
import com.wandrell.example.mule.swss.repository.ExampleEntityRepository;
import com.wandrell.example.ws.entity.EntityEndpoint;
import com.wandrell.example.ws.entity.GetEntityResponse;

@Service
@Singleton
public final class WSDLFirstExampleEntityEndpoint implements EntityEndpoint {

	@Autowired
	private ExampleEntityRepository repository;

	public WSDLFirstExampleEntityEndpoint() {
		super();
	}

	@Override
	public final GetEntityResponse.Return getEntity(final int id) {
		final JpaExampleEntity dbSample;
		final GetEntityResponse.Return result;

		dbSample = getRepository().findOne(id);

		result = new GetEntityResponse.Return();
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
