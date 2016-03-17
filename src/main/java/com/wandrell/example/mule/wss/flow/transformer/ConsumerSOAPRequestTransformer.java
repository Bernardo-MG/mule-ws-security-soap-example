/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.wandrell.example.mule.wss.flow.transformer;

import static com.google.common.base.Preconditions.checkNotNull;

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
        
        checkNotNull(src, "Received a null pointer as source");

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
