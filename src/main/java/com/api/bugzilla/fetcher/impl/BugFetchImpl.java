package com.api.bugzilla.fetcher.impl;

import com.api.bugzilla.fetcher.BugFetch;
import com.api.bugzilla.model.Bug;
import com.api.bugzilla.repository.BugRepository;
import com.api.bugzilla.utils.SessionUtils;
import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.HttpBug;
import com.j2bugzilla.http.BugSearchParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by springfield-home on 5/28/17.
 */
@Service
public class BugFetchImpl implements BugFetch {

    public static final String EXACT = "exact";
    public static final String TRUE = "1";
    public static final String BUG_STATUS_NEW = "new";

    @Autowired
    BugRepository bugRepository;

    @Override
    @Async
    public void fetch(BugzillaConnector conn) throws InterruptedException {
        while (conn.getConnectionStatus()) {
            try {
                BugSearchParser.SearchQuery email = new BugSearchParser.SearchQuery(BugSearchParser.SearchLimiter.ASSIGNED_TO, conn.getUser());
                BugSearchParser.SearchQuery emilaType = new BugSearchParser.SearchQuery(BugSearchParser.SearchLimiter.EMAIL_TYPE, EXACT);
                BugSearchParser.SearchQuery assigned = new BugSearchParser.SearchQuery(BugSearchParser.SearchLimiter.EMAIL_ASSIGNED, TRUE);
                BugSearchParser.SearchQuery newBugs = new BugSearchParser.SearchQuery(BugSearchParser.SearchLimiter.BUG_STATUS, BUG_STATUS_NEW);
                BugSearchParser bugSearch = new BugSearchParser(email, emilaType, assigned, newBugs);
                bugSearch.setSearchColumns(
                        BugSearchParser.SearchColumn.ASSIGNED,
                        BugSearchParser.SearchColumn.LAST_UPDATE,
                        BugSearchParser.SearchColumn.CASE_COUNT,
                        BugSearchParser.SearchColumn.SUMMARY);
                conn.executeHttpRequest(bugSearch);

                List<HttpBug> bugs = bugSearch.getResults();
                List<Bug> dbBugs = bugRepository.findByAssignedTo(conn.getUser());
                for (HttpBug bug: bugs) {
                    int index = dbBugs.indexOf(bug);
                    if(index != -1) {
                        Date lastChange = bug.getDeltaTs();
                        Date lastChangeDb = dbBugs.get(index).getDeltaTs();
                        if (lastChange.equals(lastChangeDb)) {
                            continue;
                        }
                    }
                    saveBug(bug);

                }
                Thread.sleep(1_000L);
            } catch (BugzillaException e) {
                e.printStackTrace();
            }
        }

    }

    private void saveBug(HttpBug bug) {
        Bug bugToSave = new Bug();
        bugToSave.buildBug(bug);
        bugRepository.save(bugToSave);
    }
}
