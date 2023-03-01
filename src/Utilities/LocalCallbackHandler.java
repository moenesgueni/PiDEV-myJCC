/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
/**
 *
 * @author moene
 */
public class LocalCallbackHandler implements CallbackHandler {
    private String username;
    private String password;

    public LocalCallbackHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void handle(Callback[] callbacks) {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                // provide the username to the login module
                NameCallback nc = (NameCallback) callback;
                nc.setName(username);
            } else if (callback instanceof PasswordCallback) {
                // provide the password to the login module
                PasswordCallback pc = (PasswordCallback) callback;
                pc.setPassword(password.toCharArray());
            }
        }
    }
    
}
