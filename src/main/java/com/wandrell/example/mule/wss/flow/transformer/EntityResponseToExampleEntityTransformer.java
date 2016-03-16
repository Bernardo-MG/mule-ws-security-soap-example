
package com.wandrell.example.mule.wss.flow.transformer;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.wandrell.example.mule.wss.model.ExampleEntity;
import com.wandrell.example.mule.wss.model.jaxb.XmlExampleEntity;

public final class EntityResponseToExampleEntityTransformer extends
        AbstractTransformer {

    public EntityResponseToExampleEntityTransformer() {
        super();
    }

    private final ExampleEntity buildEntity(final Object src)
            throws JDOMException, IOException {
        final SAXBuilder saxBuilder;
        final Element root;
        final Element docRoot;
        final XmlExampleEntity entity;

        saxBuilder = new SAXBuilder();
        org.jdom.Document doc = saxBuilder.build(new StringReader(src
                .toString()));

        docRoot = doc.getRootElement();
        if (docRoot.getChildren().size() == 1) {
            root = (Element) docRoot.getChildren().iterator().next();
        } else {
            root = docRoot;
        }

        entity = new XmlExampleEntity();
        entity.setId(Integer.parseInt(root.getChild("id").getText()));
        entity.setName(root.getChild("name").getText());

        return entity;
    }

    @Override
    protected final ExampleEntity doTransform(final Object src,
            final String enc) throws TransformerException {
        final ExampleEntity sample;

        checkNotNull(src, "Received a null pointer as source");

        try {
            sample = buildEntity(src);
        } catch (JDOMException | IOException e) {
            throw new TransformerException(this, e);
        }

        return sample;
    }

}
