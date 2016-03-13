package com.wandrell.example.mule.swss.endpoint;

import javax.inject.Singleton;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.example.mule.swss.model.jaxb.XmlExampleEntity;
import com.wandrell.example.mule.swss.model.jpa.JpaExampleEntity;
import com.wandrell.example.mule.swss.repository.ExampleEntityRepository;

@Service
@Singleton
public final class SimpleExampleEntityEndpoint {

	@Autowired
	private ExampleEntityRepository repository;

	public SimpleExampleEntityEndpoint() {
		super();
	}

	private final ExampleEntityRepository getRepository() {
		return repository;
	}

	public final XmlExampleEntity getEntity(
			@XmlElement(required = true, nillable = false) final Integer id) {
		final JpaExampleEntity dbSample;
		final XmlExampleEntity result;

		if (id != null) {
			dbSample = getRepository().findOne(id);

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

}
