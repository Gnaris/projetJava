package com.projetjava.main.models;

import jakarta.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "homework_id")
    private Homework homework;

    private float note;


    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Homework getHomework(){return this.homework;}

    public float getNote() {
        return note;
    }
}
