package com.school.main.request.ClasseRequest;

import com.school.main.models.Classe;

import java.util.List;

public class CreateClasseRequest {

    private String name;
    private List<Long> students_id;

    public List<Long> getStudents_id()
    {
        return this.students_id;
    }
    public String getName() {
        return this.name;
    }
}
