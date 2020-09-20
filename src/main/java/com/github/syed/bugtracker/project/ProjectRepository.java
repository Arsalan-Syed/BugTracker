package com.github.syed.bugtracker.project;

import com.github.syed.bugtracker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByUser(User user);
    Optional<Project> findByUserAndName(User user, String name);
}