package com.school.main.controller;

import com.school.main.request.SubjectRequest.CreateSubjectRequest;
import com.school.main.request.SubjectRequest.DeleteSubjectRequest;
import com.school.main.request.SubjectRequest.UpdateSubjectRequest;
import com.school.main.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/subject/create")
    public ResponseEntity<Object> createSubject(CreateSubjectRequest subjectRequest) {
        try{
            return new ResponseEntity<>(this.subjectService.createSubject(subjectRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Une erreur est survenue lors de la création de cette matière", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/subject/delete")
    public ResponseEntity<Object> deleteSubject(DeleteSubjectRequest subjectRequest) {
        try{
            if(this.subjectService.deleteSubject(subjectRequest))
            {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return new ResponseEntity<>("Une erreur est survenue lors de la suppression de cette matière", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/subject/update")
    public ResponseEntity<Object> updateSubject(UpdateSubjectRequest subjectRequest) {
        try{
            return new ResponseEntity<>(this.subjectService.updateSubject(subjectRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Une erreur est survenue lors de la modification de cette matière", HttpStatus.BAD_REQUEST);
        }
    }
}
