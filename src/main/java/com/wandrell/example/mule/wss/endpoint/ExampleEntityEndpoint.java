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

package com.wandrell.example.mule.wss.endpoint;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.wandrell.example.mule.wss.model.jaxb.XmlExampleEntity;

/**
 * Interface for an endpoint supporting {@link XmlExampleEntity}.
 * <p>
 * It just receives an integer id, and then returns the data for the entity with
 * that same id inside a JAXB annotated bean.
 * <p>
 * The implementations of this interface should take care of querying the actual
 * entity by using the received id.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@WebService(serviceName = ExampleEntityEndpointConstants.SERVICE,
        targetNamespace = ExampleEntityEndpointConstants.ENTITY_NS)
public interface ExampleEntityEndpoint {

    /**
     * Returns a {@link XmlExampleEntity} containing the data for the id
     * received.
     * <p>
     * The {@code id} parameter is taken from the Mule flow as a web parameter,
     * and the returned bean is a JAXB annotated class.
     * <p>
     * Implementations are expected to take the data contained in the returned
     * bean from the persistence layer.
     * 
     * @param id
     *            id of the entity being queried
     * @return the queried entity
     */
    public XmlExampleEntity getEntity(@WebParam(name = "id") @XmlElement(
            required = true, nillable = false) final Integer id);

}
