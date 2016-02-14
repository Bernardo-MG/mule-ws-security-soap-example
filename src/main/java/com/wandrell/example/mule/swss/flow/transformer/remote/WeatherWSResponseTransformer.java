package com.wandrell.example.mule.swss.flow.transformer.remote;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public final class WeatherWSResponseTransformer extends AbstractTransformer {

	public WeatherWSResponseTransformer() {
		super();
	}

	private final String buildSample(final Object src) throws JDOMException,
			IOException {
		final SAXBuilder saxBuilder;
		final Element root;
		final Element docRoot;
		final XMLOutputter xmlOutput;

		saxBuilder = new SAXBuilder();
		org.jdom.Document doc = saxBuilder.build(new StringReader(src
				.toString()));

		docRoot = doc.getRootElement();
		if (docRoot.getChildren().size() == 1) {
			root = (Element) docRoot.getChildren().iterator().next();
		} else {
			root = docRoot;
		}

		xmlOutput = new XMLOutputter();

		return xmlOutput.outputString(root);
	}

	@Override
	protected final String doTransform(final Object src, final String enc)
			throws TransformerException {
		String result = null;

		try {
			result = buildSample(src);
		} catch (JDOMException | IOException e) {
			throw new TransformerException(this, e);
		}

		return result;
	}

}
