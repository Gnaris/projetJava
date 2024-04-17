package com.school.main.controller;

import com.school.main.entity.ApiResponse;
import com.school.main.entity.Bulletin;
import com.school.main.models.Classe;
import com.school.main.request.ClasseRequest.*;
import com.school.main.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    @PostMapping("/classe/create")
    public ResponseEntity<Object> createClasse(@RequestBody CreateClasseRequest classeRequest){
        try{
            Classe newClasse = classeService.createClasse(classeRequest.getClasse(), classeRequest.getStudents_id());
            return new ResponseEntity<>(newClasse, HttpStatus.OK);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ApiResponse("Une erreur est survenue lors de la création de la classe"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/classe/edit")
    public ResponseEntity<Object> editClasse(@RequestBody EditClasseRequest classeRequest){
        try{
            Classe updatedClasse = classeService.editClasse(classeRequest.getClasse(), classeRequest.getStudentsIdToAdd(), classeRequest.getStudentsIdToDelete());
            return new ResponseEntity<>(Objects.requireNonNullElseGet(updatedClasse, () -> new ApiResponse("Cette classe n'existe pas ")), HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(new ApiResponse("Une erreur est survenue lors de la modification de la classe"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/classe/delete")
    public ResponseEntity<Object> deleteClasse(@RequestBody DeleteClasseRequest classeRequest)
    {
        try{
            boolean isDeleted = classeService.deleteClasse(classeRequest.getId());
            if(isDeleted)
            {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse("Une erreur est survenue lors de la suppression de la classe"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/classe/bulletin")
    public ResponseEntity<Object> getClasseBulletin(@RequestBody GetClasseBulletinRequest classeRequest){
        try {

            return new ResponseEntity<>(classeService.showBulletin(classeRequest.getId()), HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(new ApiResponse("Une erreur est survenue lors de la suppression de la classe"), HttpStatus.BAD_REQUEST);
        }
    }
}
