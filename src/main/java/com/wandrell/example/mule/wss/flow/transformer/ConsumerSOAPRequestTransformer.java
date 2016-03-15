
package com.wandrell.example.mule.wss.flow.transformer;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public final class ConsumerSOAPRequestTransformer extends AbstractTransformer {

    public ConsumerSOAPRequestTransformer() {
        super();
    }

    @Override
    protected final String doTransform(final Object src, final String enc)
            throws TransformerException {
        final Element root;
        final Element result;
        final XMLOutputter xmlOutput;

        try {
            root = getRoot(src.toString());
            if (root.getName().equalsIgnoreCase("envelope")) {
                result = getOperation(getBody(root));
            } else {
                result = root;
            }
        } catch (JDOMException | IOException e) {
            throw new TransformerException(this, e);
        }

        xmlOutput = new XMLOutputter();

        return xmlOutput.outputString(result);
    }

    private final Element getBody(final Element root) throws JDOMException,
            IOException {
        final Namespace soapNs;
        final Namespace soapNs2003;
        Element body;

        soapNs = Namespace
                .getNamespace("http://schemas.xmlsoap.org/soap/envelope/");
        body = root.getChild("Body", soapNs);
        if (body == null) {
            body = root.getChild("body", soapNs);
        }

        if (body == null) {
            soapNs2003 = Namespace
                    .getNamespace("http://www.w3.org/2003/05/soap-envelope");
            body = root.getChild("Body", soapNs2003);
            if (body == null) {
                body = root.getChild("body", soapNs2003);
            }
        }

        return body;
    }

    private final Element getOperation(final Element body) {
        return (Element) body.getChildren().iterator().next();
    }

    private final Element getRoot(final String soap) throws JDOMException,
            IOException {
        final SAXBuilder saxBuilder;
        final Element docRoot;

        saxBuilder = new SAXBuilder();
        org.jdom.Document doc = saxBuilder.build(new StringReader(soap));

        docRoot = doc.getRootElement();

        return docRoot;
    }

}
