package com.github.syed.bugtracker.project;

import com.github.syed.bugtracker.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectDAO {

    private final ProjectRepository repository;
    private final EntityManager entityManager;

    public ProjectDAO(ProjectRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public List<Project> getAllProjects(User user) {
        Query query = entityManager.createQuery("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.issues i WHERE p.user = :user");
        query.setParameter("user", user);
        return query.getResultList();
    }

    public Optional<Project> findByUserAndName(User user, String projectName) {
        TypedQuery<Project> query = entityManager.createQuery(
                "SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.issues i " +
                        "WHERE p.user = :user AND p.name = :name",
                Project.class
        );
        query.setParameter("user", user);
        query.setParameter("name", projectName);

        try{
            return Optional.of(query.getSingleResult());
        } catch(NoResultException e){
            return Optional.empty();
        }
    }

    public void delete(Project project) {
        repository.delete(project);
    }

    public Project save(Project project) {
        return repository.save(project);
    }
}
