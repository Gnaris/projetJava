package com.school.main.models;

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
    @JoinColumn(name = "homework_part_id")
    private HomeworkPart homework_part_id;

    private int note;


    public Long getId() {
        return id;
    }

    public Student getStudent_id() {
        return student_id;
    }

    public HomeworkPart getHomework_part_id() {
        return homework_part_id;
    }

    public int getNote() {
        return note;
    }
}
