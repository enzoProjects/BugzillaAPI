package com.api.bugzilla.service.impl;

import com.api.bugzilla.model.Bug;
import com.api.bugzilla.repository.BugRepository;
import com.api.bugzilla.service.BugService;
import com.j2bugzilla.base.BugzillaConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by springfield-home on 5/27/17.
 */
@Service
public class BugServiceImpl implements BugService {

    @Autowired
    BugRepository bugRepository;


    @Override
    public Bug getBugByID(Integer id, BugzillaConnector conn) {
        return null;
    }

    @Override
    public List<Bug> getBugs(BugzillaConnector conn) {
        return bugRepository.findByAssignedTo(conn.getUser());
    }
}
