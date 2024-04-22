package com.projetjava.main.models;

import jakarta.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student_id;

    @ManyToOne
    @JoinColumn(name = "homework_id")
    private Homework homework_id;

    private float note;


    public Long getId() {
        return id;
    }

    public Student getStudent_id() {
        return student_id;
    }

    public float getNote() {
        return note;
    }
}
