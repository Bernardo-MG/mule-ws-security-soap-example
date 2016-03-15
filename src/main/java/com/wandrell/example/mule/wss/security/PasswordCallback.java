
package com.wandrell.example.mule.wss.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public final class PasswordCallback implements CallbackHandler {

    public PasswordCallback() {
        super();
    }

    @Override
    public final void handle(final Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
        final WSPasswordCallback pc;

        pc = (WSPasswordCallback) callbacks[0];

        if (pc.getIdentifier().equals("name")) {
            pc.setPassword("password");
        } else if (pc.getIdentifier().equals("joe")) {
            pc.setPassword("secret");
        }
    }

}
