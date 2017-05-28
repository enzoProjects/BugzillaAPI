package com.api.bugzilla.controller;

import com.api.bugzilla.controller.exception.APIException;
import com.api.bugzilla.model.Bug;
import com.api.bugzilla.service.BugService;
import com.api.bugzilla.utils.SessionUtils;
import com.j2bugzilla.base.BugzillaConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by springfield-home on 5/27/17.
 */
@RestController
public class BugController {

    @Autowired
    private BugService bugService;

    @RequestMapping("/bugs")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Bug> login(HttpServletRequest request) throws APIException {
        BugzillaConnector conn = SessionUtils.extractBugzillaConnector(request);
        if (conn == null) {
            throw new APIException("No are not login yet");
        }
        try {
            return bugService.getBugs(conn);
        } catch (Exception e) {
            throw new APIException(e.getMessage(), e);
        }

    }


}
