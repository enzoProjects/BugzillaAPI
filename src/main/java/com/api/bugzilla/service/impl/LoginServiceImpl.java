package com.api.bugzilla.service.impl;

import com.api.bugzilla.config.Constants;
import com.api.bugzilla.fetcher.BugFetch;
import com.api.bugzilla.model.LoginDTO;
import com.api.bugzilla.service.LoginService;
import com.api.bugzilla.service.exception.LoginServiceException;
import com.api.bugzilla.utils.SessionUtils;
import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.BugzillaMethod;
import com.j2bugzilla.base.ConnectionException;
import com.j2bugzilla.rpc.LogIn;
import com.j2bugzilla.rpc.LogOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by springfield-home on 5/26/17.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    BugFetch bugFetch;

    @Override
    public void logout(HttpServletRequest request) throws LoginServiceException {
        try {
            BugzillaConnector conn = SessionUtils.extractBugzillaConnector(request);
            if (conn == null) {
                throw new Exception("Failed to retrieved conection");
            }
            conn.executeMethod(new LogOut());
            request.getSession().removeAttribute(Constants.BUGZILLA_CONNECTOR);
        } catch (Exception e) {
            throw new LoginServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void login(LoginDTO dto, HttpServletRequest request) throws LoginServiceException {
        BugzillaConnector conn = SessionUtils.extractBugzillaConnector(request);
        if (conn != null) {
            throw new LoginServiceException("You are already login");
        }
        conn = new BugzillaConnector();
        try {
            conn.connectTo("https://bugzilla.eng.vmware.com/");
            LogIn logIn = new LogIn(dto.login, dto.password);
            conn.executeMethod(logIn);
            if (logIn.getUserID() == -1) {
                throw new ConnectionException("Unable to login ID returned is -1", new Exception());
            }
            request.getSession().setAttribute(Constants.BUGZILLA_CONNECTOR, conn);
            try {
                bugFetch.fetch(conn);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (ConnectionException e) {
            throw new LoginServiceException(e.getMessage(), e);
        } catch (BugzillaException e) {
            throw new LoginServiceException(e.getMessage(), e);
        }
    }
}
