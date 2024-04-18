package com.school.main.request.StudentRequest;

public class UpdateStudentRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private Long classe_id;


    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getClasse_id() {
        return classe_id;
    }
}
