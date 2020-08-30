package com.github.syed.bugtracker.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name="user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    @JsonIgnore
    private String password;

    @NotEmpty
    private String email;

    private Name name;
}
