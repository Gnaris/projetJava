package com.school.main.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "classe_student", cascade = CascadeType.DETACH)
    private List<Student> students;

    @OneToMany(mappedBy = "classe_homework", cascade = CascadeType.REMOVE)
    private List<Homework> homeworks;


    public Long getId()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }

    public List<Student> getStudents()
    {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Homework> getHomeworks(){return this.homeworks;}

    public void setName(String name)
    {
        this.name = name;
    }

}
