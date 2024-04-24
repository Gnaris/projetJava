package com.projetjava.main.controller;

import com.projetjava.main.entity.ResponseData;
import com.projetjava.main.request.ClasseRequest.CreateClasseRequest;
import com.projetjava.main.request.ClasseRequest.DeleteClasseRequest;
import com.projetjava.main.request.ClasseRequest.UpdateClasseRequest;
import com.projetjava.main.service.ClasseService;
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

    @GetMapping("/classe")
    public ResponseEntity<ResponseData> getClasse()
    {
        return ResponseEntity.ok(new ResponseData(true, this.classeService.getClasses()));
    }

    @PostMapping("/classe/create")
    public ResponseEntity<ResponseData> createClasse(@RequestBody CreateClasseRequest classeRequest){
        try{
            return ResponseEntity.ok(new ResponseData(true, classeService.createClasse(classeRequest)));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la création de la classe"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/classe/update")
    public ResponseEntity<ResponseData> updateClasse(@RequestBody UpdateClasseRequest classeRequest){
        try{
            System.out.println(classeRequest.getName());
            Classe updatedClasse = classeService.updateClasse(classeRequest);
            if(updatedClasse != null)
            {
                return ResponseEntity.ok(new ResponseData(true, updatedClasse));
            }else{throw new Exception();}
        }catch (Exception e)
        {
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la modification de la classe"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/classe/delete")
    public ResponseEntity<ResponseData> deleteClasse(@RequestBody DeleteClasseRequest classeRequest)
    {
        try{
            if(classeService.deleteClasse(classeRequest.getId()))
            {
                return ResponseEntity.ok(new ResponseData(true, "La classe a été supprimée avec succès"));
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la suppression de la classe"), HttpStatus.BAD_REQUEST);
        }
    }
}
