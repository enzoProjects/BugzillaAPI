package com.api.bugzilla.service.exception;

import com.j2bugzilla.base.ConnectionException;

/**
 * Created by springfield-home on 5/27/17.∫
 */
public class LoginServiceException extends Exception {
    public LoginServiceException(String message, Exception e) {
        super(message, e);
    }

    public LoginServiceException(String s) {
        super(s);
    }
}
