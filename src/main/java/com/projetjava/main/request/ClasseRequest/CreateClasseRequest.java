package com.projetjava.main.request.ClasseRequest;

import java.util.List;

public class CreateClasseRequest {

    private String name;
    private List<Long> students;

    public List<Long> getStudents_id()
    {
        return this.students;
    }
    public String getName() {
        return this.name;
    }
}