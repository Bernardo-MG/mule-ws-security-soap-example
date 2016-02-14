package com.wandrell.example.mule.swss.flow.transformer.sample;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.wandrell.example.mule.swss.model.sample.XMLSample;

public final class SampleResponseToSampleTransformer extends
		AbstractTransformer {

	public SampleResponseToSampleTransformer() {
		super();
	}

	private final XMLSample buildSample(final Object src) throws JDOMException,
			IOException {
		final SAXBuilder saxBuilder;
		final Element root;
		final Element docRoot;
		final XMLSample sample;

		saxBuilder = new SAXBuilder();
		org.jdom.Document doc = saxBuilder.build(new StringReader(src
				.toString()));

		docRoot = doc.getRootElement();
		if (docRoot.getChildren().size() == 1) {
			root = (Element) docRoot.getChildren().iterator().next();
		} else {
			root = docRoot;
		}

		sample = new XMLSample();
		sample.setCod1(Float.parseFloat(root.getChild("cod1").getText()));
		sample.setCod2(Float.parseFloat(root.getChild("cod2").getText()));
		sample.setDescription(root.getChild("description").getText());
		sample.setExtra(root.getChild("extra").getText());

		return sample;
	}

	@Override
	protected final XMLSample doTransform(final Object src, final String enc)
			throws TransformerException {
		XMLSample sample = null;

		try {
			sample = buildSample(src);
		} catch (JDOMException | IOException e) {
			throw new TransformerException(this, e);
		}

		return sample;
	}

}
