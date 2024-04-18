package com.school.main.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String photo;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe_student;

    @OneToMany(mappedBy = "student_id", cascade = CascadeType.REMOVE)
    private List<Note> notes;

    public String getFirstName() {
        return this.firstname;
    }

    public String getLastName(){
        return this.lastname;
    }

    public Long getId()
    {
        return this.id;
    }

    public void setClass(Classe classe)
    {
        this.classe_student = classe;
    }


    public List<Note> getNotes() {
        return notes;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
