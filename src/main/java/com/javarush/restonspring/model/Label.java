package com.javarush.restonspring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_label")
@Getter
@Setter
public class Label extends BaseEntity {

    @Column(name = "name", nullable = false, length = 32, unique = true)
    private String name;

    @ManyToMany(mappedBy = "labels")
    private Set<Topic> topics = new HashSet<>();
}
