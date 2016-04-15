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

package com.wandrell.example.mule.wss.testing.integration.client.password;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.wandrell.example.mule.wss.testing.util.config.context.ClientContextPaths;
import com.wandrell.example.mule.wss.testing.util.config.properties.ClientProxyPropertiesPaths;
import com.wandrell.example.mule.wss.testing.util.test.integration.client.AbstractITClientFlow;

/**
 * Implementation of {@code AbstractITClientFlow} for the password protected Code-First
 * client flow.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@ContextConfiguration(ClientContextPaths.PASSWORD)
@TestPropertySource({ ClientProxyPropertiesPaths.PASSWORD })
public final class ITPasswordClientProxy extends AbstractITClientFlow {

    /**
     * Default constructor.
     */
    public ITPasswordClientProxy() {
        super();
    }

}
