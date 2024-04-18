package com.school.main.controller;

import com.school.main.entity.ApiResponse;
import com.school.main.request.StudentRequest.CreateStudentRequest;
import com.school.main.request.StudentRequest.DeleteStudentRequest;
import com.school.main.request.StudentRequest.UpdateStudentRequest;
import com.school.main.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student/create")
    public ResponseEntity<Object> createStudent(@RequestBody CreateStudentRequest studentRequest) {
        try{
            return new ResponseEntity<>(this.studentService.createStudent(studentRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse("Une erreur est survenue lors de la création de l'étudiant"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/student/update")
    public ResponseEntity<Object> editStudent(@RequestBody UpdateStudentRequest studentRequest) {
        try{
            return new ResponseEntity<>(this.studentService.editStudent(studentRequest), HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(new ApiResponse("Une erreur est survenue lors de la mise à jour de l'étudiant"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/student/delete")
    public ResponseEntity<Object> deleteStudent(@RequestBody DeleteStudentRequest studentRequest) {
        try{
            if(this.studentService.deleteStudent(studentRequest))
            {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }else{
                throw new Exception();
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ApiResponse("Une erreur est survenue lors de la suppression de l'étudiant"), HttpStatus.BAD_REQUEST);
        }
    }
}
