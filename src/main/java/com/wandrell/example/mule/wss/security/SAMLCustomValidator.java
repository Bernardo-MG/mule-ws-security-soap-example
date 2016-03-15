
package com.wandrell.example.mule.wss.security;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.saml.ext.AssertionWrapper;
import org.apache.ws.security.saml.ext.OpenSAMLUtil;
import org.apache.ws.security.validate.Credential;
import org.apache.ws.security.validate.SamlAssertionValidator;

public final class SAMLCustomValidator extends SamlAssertionValidator {

    @Override
    public final Credential validate(final Credential credential,
            final RequestData data) throws WSSecurityException {
        final Credential returnedCredential;

        returnedCredential = super.validate(credential, data);

        //
        // Do some custom validation on the assertion
        //
        AssertionWrapper assertion = credential.getAssertion();
        if (!"self".equals(assertion.getIssuerString())) {
            throw new WSSecurityException(WSSecurityException.FAILURE,
                    "invalidSAMLsecurity");
        }

        if (assertion.getSaml2() == null) {
            throw new WSSecurityException(WSSecurityException.FAILURE,
                    "invalidSAMLsecurity");
        }

        String confirmationMethod = assertion.getConfirmationMethods().get(0);
        if (confirmationMethod == null) {
            throw new WSSecurityException(WSSecurityException.FAILURE,
                    "invalidSAMLsecurity");
        }
        if (!OpenSAMLUtil.isMethodSenderVouches(confirmationMethod)) {
            throw new WSSecurityException(WSSecurityException.FAILURE,
                    "invalidSAMLsecurity");
        }

        if (!"AllowGreetingServices".equals(assertion.getSaml2().getSubject()
                .getNameID().getValue())) {
            throw new WSSecurityException(WSSecurityException.FAILURE,
                    "invalidSAMLsecurity");
        }

        return returnedCredential;
    }

}
