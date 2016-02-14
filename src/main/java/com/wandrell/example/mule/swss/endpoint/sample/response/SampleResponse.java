package com.wandrell.example.mule.swss.endpoint.sample.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wandrell.example.mule.swss.model.sample.XMLSample;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "return" })
@XmlRootElement(name = "getSampleResponse")
public class SampleResponse {

	@XmlElement(name = "return", required = true)
	private XMLSample sample = new XMLSample();

	public SampleResponse() {
		super();
	}

	public final XMLSample getReturn() {
		return sample;
	}

	public final void setReturn(final XMLSample value) {
		this.sample = value;
	}

}
