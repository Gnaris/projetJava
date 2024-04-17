package com.school.main.service;

import com.school.main.entity.Bulletin;
import com.school.main.models.Classe;
import com.school.main.models.Homework;
import com.school.main.models.Student;
import com.school.main.repository.ClasseRepository;
import com.school.main.repository.StudentRepository;
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

    public Classe createClasse(Classe classe, List<Long> students_id)
    {
        List<Student> studentsToUpdate = this.studentRepository.findAllById(students_id);
        if(!studentsToUpdate.isEmpty())
        {
            studentsToUpdate.forEach(s -> s.setClass(classe));
            classe.setStudents(studentsToUpdate);
        }
        return this.classeRepository.save(classe);
    }


    public Classe editClasse(Classe classe, List<Long> studentsIdToAdd, List<Long> studentsIdToDelete)
    {
        Optional<Classe> currentClass = this.classeRepository.findById(classe.getId());
        if(currentClass.isPresent())
        {
            Classe updatedClasse = currentClass.get();
            updatedClasse.setName(classe.getName());

            this.studentRepository.deleteClasseOnStudents(updatedClasse, studentsIdToDelete);
            this.studentRepository.addClasseOnStudents(updatedClasse, studentsIdToAdd);

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

    public Object showBulletin(Long classe_id)    {
        Optional<Classe> currentClasse = this.classeRepository.findById(classe_id);
        if(currentClasse.isPresent()){
            return this.classeRepository.getBulletinClasse(currentClasse.get());
        }

        return null;
    }

}
