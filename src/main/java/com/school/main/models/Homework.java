package com.school.main.models;

import com.school.main.entity.HomeworkType;
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
    private Classe classe_homework;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "homework", cascade = CascadeType.REMOVE)
    private List<HomeworkPart> homeworksParts;

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

    public Classe getClasse_homework() {
        return classe_homework;
    }

    public Subject getSubject() {
        return subject;
    }

    public List<HomeworkPart> getHomeworksParts() {
        return homeworksParts;
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

    public void setClasse(Classe classe_homework) {
        this.classe_homework = classe_homework;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setHomeworksParts(List<HomeworkPart> homeworksParts) {
        this.homeworksParts = homeworksParts;
    }
}
