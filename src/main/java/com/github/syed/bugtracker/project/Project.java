package com.github.syed.bugtracker.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.syed.bugtracker.color.ColorConverter;
import com.github.syed.bugtracker.color.ColorDeserializer;
import com.github.syed.bugtracker.color.ColorSerializer;
import com.github.syed.bugtracker.issue.Issue;
import com.github.syed.bugtracker.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_generator")
    @SequenceGenerator(name="project_id_generator", sequenceName = "project_id_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;

    @NotEmpty
    private String name;

    @Convert(converter = ColorConverter.class)
    @JsonSerialize(using = ColorSerializer.class)
    @JsonDeserialize(using = ColorDeserializer.class)
    private Color color;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Issue> issues = new HashSet<>();

    @ManyToOne //should be many to many
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private User user;

    //TODO
    public void addIssue(Issue issue){
//        this.issues.add(issue);
        issue.setProject(this);
    }
}
