package com.github.syed.bugtracker.project;

import com.github.syed.bugtracker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByUser(User user);
}