package com.school.main.entity;

import com.school.main.models.Homework;
import com.school.main.models.Student;

public class Bulletin {

    private Student student;

    private Homework homeworks;

    private float note;


    public Bulletin(Student student, Homework homeworks, float note) {
        this.student = student;
        this.homeworks = homeworks;
        this.note = note;
    }


    public Student getStudent() {
        return student;
    }

    public Homework getHomeworks() {
        return homeworks;
    }

    public float getNote() {
        return note;
    }
}
