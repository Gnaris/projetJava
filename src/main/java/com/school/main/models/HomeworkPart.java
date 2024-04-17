package com.school.main.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class HomeworkPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "homework_id")
    private Homework homework;

    @OneToMany(mappedBy = "homework_part_id", cascade = CascadeType.REMOVE)
    private List<Note> notes;

    private float point;


    public Long getId() {
        return id;
    }

    public Homework getHomework() {
        return homework;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public float getPoint() {
        return point;
    }
}
