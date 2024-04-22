package com.projetjava.main.request.ClasseRequest;

import java.util.List;

public class UpdateClasseRequest {

    private Long id;
    private String name;
    private List<Long> studentsIdToAdd;
    private List<Long> studentsIdToDelete;

    public List<Long> getStudentsIdToAdd() {
        return studentsIdToAdd;
    }

    public List<Long> getStudentsIdToDelete() {
        return studentsIdToDelete;
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
