package com.api.bugzilla.service;

import com.api.bugzilla.model.Bug;
import com.j2bugzilla.base.BugzillaConnector;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by springfield-home on 5/27/17.
 */
@Service
public interface BugService {

    Bug getBugByID(Integer id, BugzillaConnector conn);

    List<Bug> getBugs(BugzillaConnector conn);
}
