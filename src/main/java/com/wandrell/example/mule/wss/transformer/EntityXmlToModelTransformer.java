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

package com.wandrell.example.mule.wss.transformer;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.wandrell.example.mule.wss.model.jaxb.XmlExampleEntity;

/**
 * Transformer to create an {@code XmlExampleEntity} from a XML representation
 * of a {@code ExampleEntity}.
 * <p>
 * There are two valid XML formats: one is the SOAP response, another is a XML
 * structure containing the entity data, which is just an id and a name.
 * <p>
 * For this reason instead of using a marshaller the values are acquired
 * directly from the XML, taking advantage of the fact the received message is
 * so simple.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class EntityXmlToModelTransformer extends AbstractTransformer {

    /**
     * Constructs a {@code EntityXmlToModelTransformer}.
     */
    public EntityXmlToModelTransformer() {
        super();
    }

    /**
     * Parses a {@code XmlExampleEntity} from the received XML.
     * 
     * @param xml
     *            the XML to parse
     * @return a {@code XmlExampleEntity} parsed from the XML
     * @throws JDOMException
     *             if a JDOM error occurs during parsing
     * @throws IOException
     *             if a I/O error occurs during parsing
     */
    private final XmlExampleEntity parseEntity(final String xml)
            throws JDOMException, IOException {
        final SAXBuilder saxBuilder; // SAX builder to parse the SOAP message
        final Element entityNode; // Node with the entity data
        final Element root; // Root of the parsed XML
        final XmlExampleEntity entity; // Entity with the parsed data
        final Document doc; // Document created from the XML

        // Parses the XML
        saxBuilder = new SAXBuilder();
        doc = saxBuilder.build(new StringReader(xml));

        // Acquires the root and the entity node
        root = doc.getRootElement();
        if (root.getChildren().size() == 1) {
            // The root is wrapped
            // This is a SOAP response
            entityNode = (Element) root.getChildren().iterator().next();
        } else {
            entityNode = root;
        }

        // Creates the resulting entity
        entity = new XmlExampleEntity();
        entity.setId(Integer.parseInt(entityNode.getChild("id").getText()));
        entity.setName(entityNode.getChild("name").getText());

        return entity;
    }

    @Override
    protected final XmlExampleEntity doTransform(final Object src,
            final String enc) throws TransformerException {
        final XmlExampleEntity sample; // Parsed entity

        checkNotNull(src, "Received a null pointer as source");

        try {
            sample = parseEntity(src.toString());
        } catch (JDOMException | IOException e) {
            throw new TransformerException(this, e);
        }

        return sample;
    }

}
