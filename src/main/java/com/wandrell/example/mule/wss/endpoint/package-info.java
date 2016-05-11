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
/**
 * Web service endpoints.
 * <p>
 * The various final endpoints will extend over the ones in this package by
 * adding the required authentication protocols.
 * <p>
 * There is different endpoint for each variant.
 * <p>
 * For simple endpoints the
 * {@link com.wandrell.example.mule.wss.endpoint.SimpleExampleEntityEndpoint
 * SimpleExampleEntityEndpoint} is all that is needed.
 * <p>
 * A WSDL-first endpoint will use the
 * {@link com.wandrell.example.mule.wss.endpoint.WSDLFirstExampleEntityEndpoint
 * WSDLFirstExampleEntityEndpoint}, and the generated
 * {@link com.wandrell.example.mule.wss.generated.EntityEndpoint EntityEndpoint}
 * interface.
 * <p>
 * For the code-first endpoint the
 * {@link com.wandrell.example.mule.wss.endpoint.CodeFirstExampleEntityEndpoint
 * CodeFirstExampleEntityEndpoint}, and its interface the
 * {@link com.wandrell.example.mule.wss.endpoint.ExampleEntityEndpoint
 * ExampleEntityEndpoint}, are used.
 */

package com.wandrell.example.mule.wss.endpoint;

