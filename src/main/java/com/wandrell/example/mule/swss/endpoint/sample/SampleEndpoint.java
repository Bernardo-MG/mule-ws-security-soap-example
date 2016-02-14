package com.wandrell.example.mule.swss.endpoint.sample;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.wandrell.example.mule.swss.model.sample.XMLSample;

@WebService
public interface SampleEndpoint {

	public XMLSample getSample(
			@WebParam(name = "cod1") @XmlElement(required = true, nillable = false) final Float cod1,
			@WebParam(name = "cod2") @XmlElement(required = true, nillable = false) final Float cod2);

}
