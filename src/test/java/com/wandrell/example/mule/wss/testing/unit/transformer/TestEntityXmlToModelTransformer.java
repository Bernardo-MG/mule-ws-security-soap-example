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

package com.wandrell.example.mule.wss.testing.unit.transformer;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.example.mule.wss.model.ExampleEntity;
import com.wandrell.example.mule.wss.testing.util.config.context.TestContextPaths;
import com.wandrell.example.mule.wss.testing.util.config.properties.SoapPropertiesPaths;
import com.wandrell.example.mule.wss.transformer.EntityXmlToModelTransformer;

/**
 * Unit tests for {@link EntityXmlToModelTransformer} checking that the
 * transformer correctly parses XML messages.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>The transformer correctly parses a XML message from a code-first
 * endpoint.</li>
 * <li>The transformer correctly parses a XML message from a wsdl-first
 * endpoint.</li>
 * </ol>
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@ContextConfiguration(locations = { TestContextPaths.DEFAULT })
@TestPropertySource({ SoapPropertiesPaths.UNSECURE_TRANSFORMER })
public final class TestEntityXmlToModelTransformer extends
		AbstractTestNGSpringContextTests {

	/**
	 * Path to the Code-First endpoint message payload.
	 */
	@Value("${soap.response.codeFirst.payload.path}")
	private String codeFirstPath;

	/**
	 * Path to the WSDL-First endpoint message payload.
	 */
	@Value("${soap.response.wsdlFirst.payload.path}")
	private String wsdlFirstPath;

	/**
	 * Default constructor.
	 */
	public TestEntityXmlToModelTransformer() {
		super();
	}

	/**
	 * Tests that the transformer correctly parses a XML message from a
	 * code-first endpoint.
	 * 
	 * @throws TransformerException
	 *             never, this is a required declaration
	 * @throws IOException
	 *             never, this is a required declaration
	 */
	@Test
	public final void testTransform_CodeFirst() throws TransformerException,
			IOException {
		final Transformer transformer; // Transformer to test
		final ExampleEntity entity; // Parsed entity
		final String source; // Message to parse

		source = IOUtils.toString(
				new ClassPathResource(codeFirstPath).getInputStream(), "UTF-8");

		transformer = new EntityXmlToModelTransformer();

		entity = (ExampleEntity) transformer.transform(source, "UTF-8");

		Assert.assertEquals(entity.getId(), new Integer(1));
		Assert.assertEquals(entity.getName(), "name_1");
	}

	/**
	 * Tests that the transformer correctly parses a XML message from a
	 * WSDL-first endpoint.
	 * 
	 * @throws TransformerException
	 *             never, this is a required declaration
	 * @throws IOException
	 *             never, this is a required declaration
	 */
	@Test
	public final void testTransform_WSDLFirst() throws TransformerException,
			IOException {
		final Transformer transformer; // Transformer to test
		final ExampleEntity entity; // Parsed entity
		final String source; // Message to parse

		source = IOUtils.toString(
				new ClassPathResource(wsdlFirstPath).getInputStream(), "UTF-8");

		transformer = new EntityXmlToModelTransformer();

		entity = (ExampleEntity) transformer.transform(source, "UTF-8");

		Assert.assertEquals(entity.getId(), new Integer(1));
		Assert.assertEquals(entity.getName(), "name_1");
	}

}
