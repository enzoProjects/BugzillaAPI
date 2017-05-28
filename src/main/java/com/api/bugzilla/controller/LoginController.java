package com.api.bugzilla.controller;

import com.api.bugzilla.controller.exception.APIException;
import com.api.bugzilla.model.LoginDTO;
import com.api.bugzilla.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by springfield-home on 5/26/17.
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public void login(@RequestParam(name = "login", required = false) String login,
                      @RequestParam(name = "password", required = false) String password,
                      HttpServletRequest request) throws APIException {
        if (!ObjectUtils.isEmpty(login) && !ObjectUtils.isEmpty(password)) {
            LoginDTO dto = new LoginDTO();
            dto.login = login;
            dto.password = password;
            try {
                loginService.login(dto, request);
            } catch (Exception e) {
                throw new APIException(e.getMessage(), e);
            }

        }
    }

    @RequestMapping("/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public void logout(HttpServletRequest request) throws APIException {
        try {
            loginService.logout(request);
        } catch (Exception e) {
            throw new APIException(e.getMessage(), e);
        }
    }
}
