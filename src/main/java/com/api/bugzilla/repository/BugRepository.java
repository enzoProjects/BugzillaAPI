package com.api.bugzilla.repository;

import com.api.bugzilla.model.Bug;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by springfield-home on 5/28/17.
 */
public interface BugRepository extends MongoRepository<Bug, String> {
    List<Bug> findByAssignedTo(String assignedTo);
}
