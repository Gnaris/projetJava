package com.projetjava.main.request.HomeworkRequest;

import com.projetjava.main.entity.HomeworkType;

import java.util.Date;
import java.util.List;

public class CreateHomeworkRequest {

    private HomeworkType homeworkType;
    private Date date;
    private float coefficient;
    private Long subject_id;
    private Long classe_id;


    public HomeworkType getHomeworkType() {
        return homeworkType;
    }

    public Date getDate() {
        return date;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public Long getClasse_id() {
        return classe_id;
    }

}
