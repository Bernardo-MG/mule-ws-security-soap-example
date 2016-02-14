package com.wandrell.example.mule.swss.data.model.sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wandrell.example.mule.swss.model.sample.Sample;

@Entity(name = "Sample")
@Table(name = "samples")
public final class JPASample implements Sample {

	public static final int DESCRIPTION_LENGTH = 30;
	public static final int EXTRA_LENGTH = 50;
	@Transient
	@XmlTransient
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	@Column(name = "cod1")
	private Float cod1 = new Float(0);
	@Column(name = "cod2")
	private Float cod2 = new Float(0);
	@Column(name = "description", length = DESCRIPTION_LENGTH)
	private String description = "";
	@Column(name = "extra", length = EXTRA_LENGTH)
	private String extra = "";

	@Id
	private Integer id;

	public JPASample() {
		super();
	}

	public JPASample(final JPASample sample) {
		super();
		BeanUtils.copyProperties(sample, this);
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JPASample other = (JPASample) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
	public String getDescription() {
		return description;
	}

	@Override
	public final String getExtra() {
		return extra;
	}

	public final Integer getId() {
		return id;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public final void setCod1(final Float value) {
		cod1 = value;
	}

	public final void setCod2(final Float value) {
		cod2 = value;
	}

	public final void setDescription(final String decentro) {
		this.description = decentro;
	}

	public final void setExtra(final String dlocalid) {
		this.extra = dlocalid;
	}

	public final void setId(final Integer value) {
		id = value;
	}

}
