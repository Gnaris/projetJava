package com.projetjava.main.request.ClasseRequest;

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