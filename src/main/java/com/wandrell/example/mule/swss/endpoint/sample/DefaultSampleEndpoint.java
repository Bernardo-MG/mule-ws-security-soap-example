package com.wandrell.example.mule.swss.endpoint.sample;

import javax.inject.Singleton;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.example.mule.swss.data.model.sample.JPASample;
import com.wandrell.example.mule.swss.data.repository.JPASampleRepository;
import com.wandrell.example.mule.swss.model.sample.XMLSample;

@Service
@Singleton
@WebService(endpointInterface = "com.wandrell.example.mule.swss.endpoint.sample.SampleEndpoint", serviceName = "SampleEndpointService", targetNamespace="http://sample.endpoint.swss.mule.example.wandrell.com/")
public final class DefaultSampleEndpoint implements SampleEndpoint {

	@Autowired
	private JPASampleRepository repository;

	public DefaultSampleEndpoint() {
		super();
	}

	private final JPASampleRepository getRepository() {
		return repository;
	}

	@Override
	public final XMLSample getSample(final Float cod1, final Float cod2) {
		final JPASample dbSample;
		final XMLSample result;

		dbSample = getRepository().findByCoordinates(cod1, cod2);

		result = new XMLSample();
		result.setCod1(dbSample.getCod1());
		result.setCod2(dbSample.getCod2());
		result.setDescription(dbSample.getDescription());
		result.setExtra(dbSample.getExtra());

		return result;
	}

	public final void setRepository(final JPASampleRepository repo) {
		this.repository = repo;
	}

}
