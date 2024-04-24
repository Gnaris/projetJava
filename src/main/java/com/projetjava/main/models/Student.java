package com.projetjava.main.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(length = 65535)
    private String photo;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = true)
    private Classe classe;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Note> notes;

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public String getPhoto(){ return this.photo; }

    public Long getId()
    {
        return this.id;
    }

    public void setClass(Classe classe)
    {
        this.classe = classe;
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

    public void setPhoto(String photo){
        this.photo = photo;
    }
}
