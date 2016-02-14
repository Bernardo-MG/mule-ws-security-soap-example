package com.wandrell.example.mule.swss.endpoint.sample;

import javax.inject.Singleton;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import com.wandrell.example.mule.swss.data.model.sample.JPASample;
import com.wandrell.example.mule.swss.data.repository.JPASampleRepository;
import com.wandrell.example.mule.swss.model.sample.XMLSample;

@Service
@Singleton
public final class SimpleSampleEndpoint {
		
	@Autowired
	private JPASampleRepository repository;

	public SimpleSampleEndpoint() {
		super();
	}

	private final JPASampleRepository getRepository() {
		return repository;
	}

	public final XMLSample getSample(
			@XmlElement(required = true, nillable = false) final Float cod1,
			@XmlElement(required = true, nillable = false) final Float cod2) {
		final JPASample dbSample;
		final XMLSample sample;

		dbSample = getRepository().findByCoordinates(cod1, cod2);

		sample = new XMLSample();
		sample.setCod1(dbSample.getCod1());
		sample.setCod2(dbSample.getCod2());
		sample.setDescription(dbSample.getDescription());
		sample.setExtra(dbSample.getExtra());

		return sample;
	}

}
