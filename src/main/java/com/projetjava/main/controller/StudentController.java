package com.projetjava.main.controller;

import com.projetjava.main.entity.ResponseData;
import com.projetjava.main.request.StudentRequest.CreateStudentRequest;
import com.projetjava.main.request.StudentRequest.DeleteStudentRequest;
import com.projetjava.main.request.StudentRequest.UpdateStudentRequest;
import com.projetjava.main.service.StudentService;
import com.projetjava.main.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("student/withoutClasse")
    public ResponseEntity<ResponseData> getStudentsWithoutClasse()
    {
        return ResponseEntity.ok(new ResponseData(true, this.studentService.getStudentsWithoutClasse()));
    }

    @GetMapping("/student")
    public ResponseEntity<ResponseData> getStudents()
    {
        return ResponseEntity.ok(new ResponseData(true, this.studentService.getStudent()));
    }

    @PostMapping("/student/create")
    public ResponseEntity<ResponseData> createStudent(@RequestBody CreateStudentRequest studentRequest) {
        try{
            return ResponseEntity.ok(new ResponseData(true, this.studentService.createStudent(studentRequest)));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la création de l'étudiant"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/student/update")
    public ResponseEntity<ResponseData> updateStudent(@RequestBody UpdateStudentRequest studentRequest) {
        try{
            Student updatedStudent = this.studentService.updateStudent(studentRequest);
            if(updatedStudent != null)
            {
                return ResponseEntity.ok(new ResponseData(true, this.studentService.updateStudent(studentRequest)));
            }else{
                throw new Exception();
            }
        }catch (Exception e)
        {
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la mise à jour de l'étudiant"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/student/delete")
    public ResponseEntity<ResponseData> deleteStudent(@RequestBody DeleteStudentRequest studentRequest) {
        try{
            if(this.studentService.deleteStudent(studentRequest))
            {
                return ResponseEntity.ok(new ResponseData(true, "L'étudiant a été supprimé avec succès"));
            }else{
                throw new Exception();
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ResponseData(false, "Une erreur est survenue lors de la suppression de l'étudiant"), HttpStatus.BAD_REQUEST);
        }
    }
}
