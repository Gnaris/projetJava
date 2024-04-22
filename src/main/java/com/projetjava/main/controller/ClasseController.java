package com.projetjava.main.controller;

import com.projetjava.main.request.ClasseRequest.CreateClasseRequest;
import com.projetjava.main.request.ClasseRequest.DeleteClasseRequest;
import com.projetjava.main.request.ClasseRequest.UpdateClasseRequest;
import com.projetjava.main.service.ClasseService;
import com.projetjava.main.entity.ApiResponse;
import com.projetjava.main.models.Classe;
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
            Classe newClasse = classeService.createClasse(classeRequest);
            return ResponseEntity.ok(newClasse);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ApiResponse("Une erreur est survenue lors de la création de la classe"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/classe/update")
    public ResponseEntity<Object> updateClasse(@RequestBody UpdateClasseRequest classeRequest){
        try{
            Classe updatedClasse = classeService.updateClasse(classeRequest);
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
}
