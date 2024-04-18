package com.school.main.request.HomeworkRequest;

import com.school.main.entity.HomeworkType;

import java.util.Date;
import java.util.List;

public class UpdateHomeworkRequest {

    private Long id;
    private HomeworkType homeworkType;
    private Date date;
    private float coefficient;
    private Long subject_id;
    private Long classe_id;
    private List<Integer> points;

    public Long getId()
    {
        return this.id;
    }

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

    public List<Integer> getPoints() {
        return points;
    }
}
