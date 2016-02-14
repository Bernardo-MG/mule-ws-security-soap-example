package com.wandrell.example.mule.swss.endpoint.sample.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "cod1", "cod2" })
@XmlRootElement(name = "getSample")
public class SampleRequest {

	@XmlElement(required = true)
	private Float cod1;
	@XmlElement(required = true)
	private Float cod2;

	public SampleRequest() {
		super();
	}

	public final Float getCod1() {
		return cod1;
	}

	public final Float getCod2() {
		return cod2;
	}

	public final void setCod1(final Float value) {
		this.cod1 = value;
	}

	public final void setCod2(final Float value) {
		this.cod2 = value;
	}

}
