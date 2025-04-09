package com.javarush.restonspring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_writer")
@Getter
@Setter
public class Writer extends BaseEntity {

    @Column(name = "login", nullable = false, length = 64, unique = true)
    private String login;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "firstname", nullable = false, length = 64)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 64)
    private String lastname;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Topic> topics = new ArrayList<>();
}
