package com.github.syed.bugtracker.issue;

import com.github.syed.bugtracker.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByProject(Project projectForIssue);
    Optional<Issue> findByIssueId(String issueId);
}