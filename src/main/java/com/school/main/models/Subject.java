package com.school.main.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Homework> homeworks;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }
}
