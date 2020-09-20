package com.github.syed.bugtracker.project;

import com.github.syed.bugtracker.issue.Issue;
import com.github.syed.bugtracker.issue.IssueRepository;
import com.github.syed.bugtracker.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class ProjectDAO {

    @Autowired
    ProjectRepository repository;

    @Autowired
    IssueRepository issueRepository;

    //TODO is there a way to automatically fetch issues?
    @Transactional
    public List<Project> getAllProjects(User user) {
        List<Project> projects = repository.findAllByUser(user);

        for(Project project: projects){
            Set<Issue> issues = issueRepository.findByProject(project);
            project.setIssues(issues);
        }

        return projects;
    }

    public Optional<Project> findByUserAndName(User user, String projectName) {
        return repository.findByUserAndName(user, projectName);
    }

    public void delete(Project project) {
        repository.delete(project);
    }

    public Project save(Project project) {
        return repository.save(project);
    }
}
