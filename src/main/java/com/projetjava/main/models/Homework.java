package com.projetjava.main.models;

import com.projetjava.main.entity.HomeworkType;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HomeworkType type;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private float coefficient;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @OneToMany(mappedBy = "homework")
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Long getId() {
        return id;
    }

    public HomeworkType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public Classe getClasse() {
        return classe;
    }

    public Subject getSubject() {
        return subject;
    }


    public void setType(HomeworkType type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
