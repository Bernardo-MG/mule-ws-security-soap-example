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
