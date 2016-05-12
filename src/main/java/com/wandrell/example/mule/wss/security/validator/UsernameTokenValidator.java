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

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.message.token.UsernameToken;
import org.apache.ws.security.validate.Credential;
import org.apache.ws.security.validate.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validator for username token security.
 * 
 * @author Bernardo Martínez Garrido
 */
public final class UsernameTokenValidator implements Validator {

	/**
	 * The logger used for logging the validator.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SamlCustomValidator.class);

	/**
	 * Default constructor.
	 */
	public UsernameTokenValidator() {
		super();
	}

	@Override
	public final Credential validate(final Credential credential,
			final RequestData data) throws WSSecurityException {
		final UsernameToken usernameToken;

		checkNotNull(credential, "Received a null pointer as credential");
		checkNotNull(data, "Received a null pointer as data");

		usernameToken = credential.getUsernametoken();

		// TODO: Users and passwords may be injected as dependencies
		if (!"myPassword".equalsIgnoreCase(usernameToken.getPassword())) {
			throw new WSSecurityException(
					WSSecurityException.FAILED_AUTHENTICATION);
		} else {
			LOGGER.debug(String.format("Rejected password %2$s for user %1$s",
					usernameToken.getName(), usernameToken.getPassword()));
		}

		return credential;
	}

}
