package com.projetjava.main.request.ClasseRequest;

import java.util.List;

public class UpdateClasseRequest {

    private Long id;
    private String name;
    private List<Long> students;

    public List<Long> getStudentsIdToAdd() {
        return this.students;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
