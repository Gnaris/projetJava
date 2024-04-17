package com.school.main.request.ClasseRequest;

import java.util.List;

public class EditClasseRequest extends ClasseRequest {

    private List<Long> studentsIdToAdd;
    private List<Long> studentsIdToDelete;

    public List<Long> getStudentsIdToAdd() {
        return studentsIdToAdd;
    }

    public List<Long> getStudentsIdToDelete() {
        return studentsIdToDelete;
    }
}
