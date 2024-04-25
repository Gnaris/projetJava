package com.projetjava.main.controller;

import com.projetjava.main.entity.ResponseData;
import com.projetjava.main.request.SubjectRequest.CreateSubjectRequest;
import com.projetjava.main.request.SubjectRequest.DeleteSubjectRequest;
import com.projetjava.main.request.SubjectRequest.UpdateSubjectRequest;
import com.projetjava.main.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subject")
    public ResponseEntity<Object> getSubject()
    {
        return ResponseEntity.ok(new ResponseData(true, this.subjectService.getSubject()));
    }


    @PostMapping("/subject/create")
    public ResponseEntity<Object> createSubject(@RequestBody CreateSubjectRequest subjectRequest) {
        try{
            return ResponseEntity.ok(new ResponseData(true, this.subjectService.createSubject(subjectRequest)));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la création de cette matière"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/subject/delete")
    public ResponseEntity<Object> deleteSubject(@RequestBody DeleteSubjectRequest subjectRequest) {
        System.out.println(subjectRequest.getId());
        try{
            if(this.subjectService.deleteSubject(subjectRequest))
            {
                return ResponseEntity.ok(new ResponseData(true, "La matière à bien été supprimé"));
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la suppression de cette matière"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/subject/update")
    public ResponseEntity<Object> updateSubject(@RequestBody UpdateSubjectRequest subjectRequest) {
        try{
            return ResponseEntity.ok(new ResponseData(true, this.subjectService.updateSubject(subjectRequest)));
        }catch (Exception e){
            return new ResponseEntity<>( new ResponseData(false, "Une erreur est survenue lors de la modification de cette matière"), HttpStatus.BAD_REQUEST);
        }
    }
}
