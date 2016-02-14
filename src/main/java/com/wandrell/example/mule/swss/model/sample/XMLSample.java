package com.wandrell.example.mule.swss.model.sample;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "cod1", "cod2", "description", "extra" })
@XmlRootElement(name = "sample")
public class XMLSample implements Sample {

	@XmlElement
	private Float cod1 = new Float(0);
	@XmlElement
	private Float cod2 = new Float(0);
	@XmlElement(defaultValue = "")
	private String description = "";
	@XmlElement(defaultValue = "")
	private String extra = "";

	public XMLSample() {
		super();
	}

	public XMLSample(final Float cod1, final Float cod2) {
		super();

		this.cod1 = cod1;
		this.cod2 = cod2;
	}

	@Override
	public final Float getCod1() {
		return cod1;
	}

	@Override
	public final Float getCod2() {
		return cod2;
	}

	@Override
	public final String getDescription() {
		return description;
	}

	@Override
	public final String getExtra() {
		return extra;
	}

	public final void setCod1(final Float value) {
		this.cod1 = value;
	}

	public final void setCod2(final Float value) {
		this.cod2 = value;
	}

	public final void setDescription(final String value) {
		this.description = value;
	}

	public final void setExtra(final String value) {
		this.extra = value;
	}

	@Override
	public final String toString() {
		return "XMLSample [cod1=" + cod1 + ", cod2=" + cod2 + ", description="
				+ description + ", extra=" + extra + "]";
	}

}
