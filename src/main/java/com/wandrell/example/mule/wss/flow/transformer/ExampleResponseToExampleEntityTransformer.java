package com.wandrell.example.mule.wss.flow.transformer;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.wandrell.example.mule.wss.model.jaxb.XmlExampleEntity;

public final class ExampleResponseToExampleEntityTransformer extends
		AbstractTransformer {

	public ExampleResponseToExampleEntityTransformer() {
		super();
	}

	private final XmlExampleEntity buildSample(final Object src) throws JDOMException,
			IOException {
		final SAXBuilder saxBuilder;
		final Element root;
		final Element docRoot;
		final XmlExampleEntity sample;

		saxBuilder = new SAXBuilder();
		org.jdom.Document doc = saxBuilder.build(new StringReader(src
				.toString()));

		docRoot = doc.getRootElement();
		if (docRoot.getChildren().size() == 1) {
			root = (Element) docRoot.getChildren().iterator().next();
		} else {
			root = docRoot;
		}

		sample = new XmlExampleEntity();
		sample.setId(Integer.parseInt(root.getChild("id").getText()));
		sample.setName(root.getChild("name").getText());

		return sample;
	}

	@Override
	protected final XmlExampleEntity doTransform(final Object src, final String enc)
			throws TransformerException {
		XmlExampleEntity sample = null;

		try {
			sample = buildSample(src);
		} catch (JDOMException | IOException e) {
			throw new TransformerException(this, e);
		}

		return sample;
	}

}
