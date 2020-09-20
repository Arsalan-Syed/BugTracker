package com.github.syed.bugtracker.issue;

import com.github.syed.bugtracker.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    Set<Issue> findByProject(Project projectForIssue);
    Optional<Issue> findByIssueId(String issueId);
}