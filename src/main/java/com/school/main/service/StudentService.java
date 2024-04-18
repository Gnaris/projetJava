package com.school.main.service;

import com.school.main.models.Classe;
import com.school.main.models.Student;
import com.school.main.repository.ClasseRepository;
import com.school.main.repository.StudentRepository;
import com.school.main.request.StudentRequest.CreateStudentRequest;
import com.school.main.request.StudentRequest.DeleteStudentRequest;
import com.school.main.request.StudentRequest.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClasseRepository classeRepository;

    public Student createStudent(CreateStudentRequest studentRequest) {
        Optional<Classe> classe = this.classeRepository.findById(studentRequest.getClasse_id());

        Student student = new Student();
        student.setFirstname(studentRequest.getFirstname());
        student.setLastname(studentRequest.getLastname());
        classe.ifPresent(student::setClass);

        return this.studentRepository.save(student);
    }

    public Student editStudent(UpdateStudentRequest studentRequest){
        Optional<Student> student = this.studentRepository.findById(studentRequest.getId());

        student.get().setFirstname(studentRequest.getFirstname());
        student.get().setLastname(studentRequest.getLastname());
        if(student.get().getNotes().isEmpty())
        {
            Optional<Classe> classe = this.classeRepository.findById(studentRequest.getClasse_id());
            classe.ifPresent(value -> student.get().setClass(value));
        }

        return this.studentRepository.save(student.get());
    }

    public boolean deleteStudent(DeleteStudentRequest studentRequest)
    {
        Optional<Student> student = this.studentRepository.findById(studentRequest.getId());
        if(student.isPresent())
        {
            this.studentRepository.delete(student.get());
            return true;
        }

        return false;
    }


}
