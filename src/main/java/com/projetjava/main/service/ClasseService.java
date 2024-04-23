package com.projetjava.main.service;

import com.projetjava.main.repository.ClasseRepository;
import com.projetjava.main.repository.StudentRepository;
import com.projetjava.main.request.ClasseRequest.UpdateClasseRequest;
import com.projetjava.main.models.Classe;
import com.projetjava.main.models.Student;
import com.projetjava.main.request.ClasseRequest.CreateClasseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<Classe> getClasses()
    {
        return this.classeRepository.findAll();
    }
    public Classe createClasse(CreateClasseRequest classeRequest)
    {
        List<Student> studentsToUpdate = this.studentRepository.findAllById(classeRequest.getStudents_id());
        Classe classe = new Classe();
        classe.setName(classeRequest.getName());
        classe.setStudents(studentsToUpdate);
        Classe currentCreateClasse = this.classeRepository.save(classe);
        this.studentRepository.updateClasseOnStudents(classe, classeRequest.getStudents_id());
        return currentCreateClasse;
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
                this.studentRepository.updateClasseOnStudents(updatedClasse, classeRequest.getStudentsIdToAdd());
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
