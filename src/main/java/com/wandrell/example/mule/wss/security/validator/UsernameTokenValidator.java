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

package com.wandrell.example.mule.wss.security.validator;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.message.token.UsernameToken;
import org.apache.ws.security.validate.Credential;
import org.apache.ws.security.validate.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for username tokens.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class UsernameTokenValidator implements Validator {

    /**
     * The logger used for logging the password callback handler usage.
     */
    private static final Logger LOGGER = LoggerFactory
                                               .getLogger(UsernameTokenValidator.class);

    /**
     * Default constructor.
     */
    public UsernameTokenValidator() {
        super();
    }

    @Override
    public final Credential validate(final Credential credential,
            final RequestData data) throws WSSecurityException {
        final UsernameToken usernameToken; // Username token to validate

        usernameToken = credential.getUsernametoken();
        if (!"myUser".equals(usernameToken.getName())) {
            LOGGER.debug(String.format("Username %s not valid",
                    usernameToken.getName()));
            throw new WSSecurityException(
                    WSSecurityException.FAILED_AUTHENTICATION);
        } else if (!"myPassword".equals(usernameToken.getPassword())) {
            LOGGER.debug(String.format("Password for username %s not valid",
                    usernameToken.getName()));
            throw new WSSecurityException(
                    WSSecurityException.FAILED_AUTHENTICATION);
        }

        return credential;
    }

}
