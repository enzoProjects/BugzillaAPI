package com.api.bugzilla.controller.exception;

import com.api.bugzilla.service.exception.LoginServiceException;

/**
 * Created by springfield-home on 5/27/17.
 */
public class APIException extends Exception {
    public APIException(String message, Exception e) {
        super(message, e);
    }

    public APIException(String s) {
        super(s);
    }
}
