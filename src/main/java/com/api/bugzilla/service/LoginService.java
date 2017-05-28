package com.api.bugzilla.service;

import com.api.bugzilla.model.LoginDTO;
import com.api.bugzilla.service.exception.LoginServiceException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by springfield-home on 5/26/17.
 */
@Service
public interface LoginService {

    void logout(HttpServletRequest request) throws LoginServiceException;

    void login(LoginDTO dto, HttpServletRequest request) throws LoginServiceException;
}
