package com.api.bugzilla.utils;

import com.api.bugzilla.config.Constants;
import com.j2bugzilla.base.BugzillaConnector;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by springfield-home on 5/27/17.
 */
public class SessionUtils {
    public static BugzillaConnector extractBugzillaConnector(HttpServletRequest req) {
        return (BugzillaConnector) req.getSession().getAttribute(Constants.BUGZILLA_CONNECTOR);
    }

    public static boolean checkIfConnected(HttpServletRequest req) {
        return extractBugzillaConnector(req) != null;
    }
}
