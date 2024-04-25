package com.projetjava.main.controller;

import com.projetjava.main.entity.ResponseData;
import com.projetjava.main.models.Homework;
import com.projetjava.main.request.HomeworkRequest.CreateHomeworkRequest;
import com.projetjava.main.request.HomeworkRequest.DeleteHomeworkRequest;
import com.projetjava.main.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @GetMapping("/homework")
    public ResponseEntity<ResponseData> getHomework()
    {
        return ResponseEntity.ok(new ResponseData(true, this.homeworkService.getHomework()));
    }

    @PostMapping("/homework/create")
    public ResponseEntity<ResponseData> createHomework(@RequestBody CreateHomeworkRequest homeworkRequest){
        try{
            return ResponseEntity.ok(new ResponseData(true, this.homeworkService.createHomework(homeworkRequest)));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la création du devoir"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/homework/delete")
    public ResponseEntity<ResponseData> deleteHomwork(@RequestBody DeleteHomeworkRequest homeworkRequest)
    {
        try{
            if(this.homeworkService.deleteHomework(homeworkRequest)){
                return ResponseEntity.ok(new ResponseData(true, "Ce devoir a bien été supprimé"));
            }else{
                throw new Exception();
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la suppression de ce devoir"), HttpStatus.BAD_REQUEST);
        }
    }

}
