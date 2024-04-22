package com.projetjava.main.request.StudentRequest;

public class CreateStudentRequest {

    private String firstname;
    private String lastname;

    private String photo;
    private Long classe_id;


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoto(){return photo;}

    public Long getClasse_id() {
        return classe_id;
    }
}
