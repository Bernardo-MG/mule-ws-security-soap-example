
package com.wandrell.example.mule.wss.security;

import static com.google.common.base.Preconditions.checkNotNull;

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

        checkNotNull(callbacks, "Received a null pointer as callbacks");

        pc = (WSPasswordCallback) callbacks[0];

        if (pc.getIdentifier().equals("name")) {
            pc.setPassword("password");
        } else if (pc.getIdentifier().equals("joe")) {
            pc.setPassword("secret");
        }
    }

}
