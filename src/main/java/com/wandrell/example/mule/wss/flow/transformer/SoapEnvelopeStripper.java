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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transformer to acquire a SOAP payload from a SOAP message.
 * <p>
 * It will take a full SOAP message as a string, parse it and then strip out the
 * envelope, leaving only the first child of the body.
 * <p>
 * If the received object does not have an envelope element as the root then it
 * will be returned back.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class SoapEnvelopeStripper extends AbstractTransformer {

    /**
     * The logger used for logging the transformer.
     */
    private static final Logger        LOGGER = LoggerFactory
            .getLogger(SoapEnvelopeStripper.class);
    
    /**
     * Constructs a {@code SoapEnvelopeStripper}.
     */
    public SoapEnvelopeStripper() {
        super();
    }

    /**
     * Acquires the body data from the received SOAP data.
     * <p>
     * The received element should be the root of a SOAP message, from which the
     * element annotated as the body will be extracted.
     * <p>
     * This SOAP message should be using the base SOAP namespace,
     * {@code http://schemas.xmlsoap.org/soap/envelope/}, or the 2003 revision,
     * {@code http://www.w3.org/2003/05/soap-envelope}. Otherwise the body won't
     * be found.
     * 
     * @param soap
     *            the SOAP message element
     * @return the SOAP body element
     */
    private final Element getBody(final Element soap) {
        final Namespace soapNs;     // Base SOAP namespace
        final Namespace soapNs2003; // 2003 revision SOAP namespace
        Element body;               // Element with the SOAP body

        // Tries to find the body by using the base SOAP namespace
        soapNs = Namespace
                .getNamespace("http://schemas.xmlsoap.org/soap/envelope/");
        body = soap.getChild("Body", soapNs);
        if (body == null) {
            body = soap.getChild("body", soapNs);
        }

        if (body == null) {
            // Body not found
            // Now tries the 2003 revision namespace
            soapNs2003 = Namespace
                    .getNamespace("http://www.w3.org/2003/05/soap-envelope");
            body = soap.getChild("Body", soapNs2003);
            if (body == null) {
                body = soap.getChild("body", soapNs2003);
            }
        }

        return body;
    }

    /**
     * Returns the SOAP operation from the received SOAP body.
     * <p>
     * It will be just the first child in the body.
     * 
     * @param body
     *            SOAP body with the SOAP operation
     * @return the SOAP operation
     */
    private final Element getOperation(final Element body) {
        return (Element) body.getChildren().iterator().next();
    }

    /**
     * Parses a SOAP message and returns its root element.
     * 
     * @param soap
     *            SOAP message to parse
     * @return the SOAP message root element
     * @throws JDOMException
     *             if a JDOM error occurs during parsing
     * @throws IOException
     *             if a I/O error occurs during parsing
     */
    private final Element parseElement(final String soap) throws JDOMException,
            IOException {
        final SAXBuilder saxBuilder; // SAX builder to parse the SOAP message

        // Parses the SOAP message
        saxBuilder = new SAXBuilder();
        org.jdom.Document doc = saxBuilder.build(new StringReader(soap));

        return doc.getRootElement();
    }

    @Override
    protected final String doTransform(final Object src, final String enc)
            throws TransformerException {
        final Element root;           // SOAP message root
        final Element operation;      // SOAP message operation

        checkNotNull(src, "Received a null pointer as source");
        
        LOGGER.debug(String.format("Stripping source: %s", src.toString()));

        try {
            root = parseElement(src.toString());
            if (root.getName().equalsIgnoreCase("envelope")) {
                // The root is a SOAP envelope
                // Acquires the operation
                operation = getOperation(getBody(root));
            } else {
                operation = root;
            }
        } catch (final Exception e) {
            throw new TransformerException(this, e);
        }

        return new XMLOutputter().outputString(operation);
    }

}
