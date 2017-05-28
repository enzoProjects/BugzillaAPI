package com.api.bugzilla.fetcher;

import com.j2bugzilla.base.BugzillaConnector;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by springfield-home on 5/28/17.
 */
@Service
public interface BugFetch {
    @Async
    void fetch(BugzillaConnector conn) throws InterruptedException;
}
