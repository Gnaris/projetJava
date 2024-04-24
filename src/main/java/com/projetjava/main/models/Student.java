package com.projetjava.main.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(length = 65535)
    private String photo;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = true)
    private Classe classe;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Note> notes;

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
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
        this.lastName = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }
}
