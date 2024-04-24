package com.projetjava.main.service;

import com.projetjava.main.request.StudentRequest.CreateStudentRequest;
import com.projetjava.main.request.StudentRequest.DeleteStudentRequest;
import com.projetjava.main.request.StudentRequest.UpdateStudentRequest;
import com.projetjava.main.models.Classe;
import com.projetjava.main.models.Student;
import com.projetjava.main.repository.ClasseRepository;
import com.projetjava.main.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClasseRepository classeRepository;

    public List<Student> getStudent()
    {
        return this.studentRepository.findAll();
    }

    public List<Student> getStudentsWithoutClasse()
    {
        return this.studentRepository.findAllStudentWithoutClasse();
    }

    public Student createStudent(CreateStudentRequest studentRequest) {

        Student student = new Student();
        System.out.println(studentRequest.getFirstname());
        student.setFirstname(studentRequest.getFirstname());
        student.setLastname(studentRequest.getLastname());
        student.setPhoto(studentRequest.getPhoto());
        if(studentRequest.getClasse_id() != null)
        {
            Optional<Classe> classe = this.classeRepository.findById(studentRequest.getClasse_id());
            classe.ifPresent(student::setClass);
        }

        return this.studentRepository.save(student);
    }

    public Student updateStudent(UpdateStudentRequest studentRequest){
        Optional<Student> student = this.studentRepository.findById(studentRequest.getId());

        if(student.isPresent())
        {
            student.get().setFirstname(studentRequest.getFirstname());
            student.get().setLastname(studentRequest.getLastname());
            student.get().setPhoto(studentRequest.getPhoto());

            if(student.get().getNotes().isEmpty() && studentRequest.getClasse_id() != null)
            {
                Optional<Classe> classe = this.classeRepository.findById(studentRequest.getClasse_id());
                classe.ifPresent(value -> student.get().setClass(value));
            }

            return this.studentRepository.save(student.get());
        }else{
            return null;
        }
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
