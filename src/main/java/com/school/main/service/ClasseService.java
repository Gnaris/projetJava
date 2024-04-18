package com.school.main.service;

import com.school.main.entity.Bulletin;
import com.school.main.models.Classe;
import com.school.main.models.Homework;
import com.school.main.models.Student;
import com.school.main.repository.ClasseRepository;
import com.school.main.repository.StudentRepository;
import com.school.main.request.ClasseRequest.CreateClasseRequest;
import com.school.main.request.ClasseRequest.UpdateClasseRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private StudentRepository studentRepository;

    public Classe createClasse(CreateClasseRequest classeRequest)
    {
        List<Student> studentsToUpdate = this.studentRepository.findAllById(classeRequest.getStudents_id());
        Classe classe = new Classe();
        classe.setName(classeRequest.getName());
        studentsToUpdate.forEach(s -> s.setClass(classe));
        classe.setStudents(studentsToUpdate);
        return this.classeRepository.save(classe);
    }


    public Classe updateClasse(UpdateClasseRequest classeRequest)
    {
        Optional<Classe> currentClass = this.classeRepository.findById(classeRequest.getId());
        if(currentClass.isPresent())
        {
            Classe updatedClasse = currentClass.get();
            updatedClasse.setName(classeRequest.getName());

            if(!classeRequest.getStudentsIdToDelete().isEmpty())
            {
                this.studentRepository.deleteClasseOnStudents(updatedClasse, classeRequest.getStudentsIdToDelete());
            }
            if(!classeRequest.getStudentsIdToAdd().isEmpty())
            {
                this.studentRepository.addClasseOnStudents(updatedClasse, classeRequest.getStudentsIdToAdd());
            }

            return this.classeRepository.save(updatedClasse);
        }else{
            return null;
        }
    }

    public boolean deleteClasse(Long id){
        Optional<Classe> currentClasse = this.classeRepository.findById(id);

        if(currentClasse.isPresent())
        {
            Classe deletedClasse = currentClasse.get();
            List<Long> students_id = deletedClasse.getStudents().stream().mapToLong(Student::getId).boxed().toList();
            this.studentRepository.deleteClasseOnStudents(deletedClasse, students_id);
            this.classeRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
