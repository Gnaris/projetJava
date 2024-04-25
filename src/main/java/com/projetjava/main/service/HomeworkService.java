package com.projetjava.main.service;

import com.projetjava.main.models.Classe;
import com.projetjava.main.models.Subject;
import com.projetjava.main.request.HomeworkRequest.CreateHomeworkRequest;
import com.projetjava.main.request.HomeworkRequest.DeleteHomeworkRequest;
import com.projetjava.main.models.Homework;
import com.projetjava.main.repository.ClasseRepository;
import com.projetjava.main.repository.HomeworkRepository;
import com.projetjava.main.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HomeworkService {

     @Autowired
    private HomeworkRepository homeworkRepository;

     @Autowired
     private ClasseRepository classeRepository;

     @Autowired
     private SubjectRepository subjectRepository;

     public List<Homework> getHomework()
     {
         return this.homeworkRepository.findAll();
     }
     public Homework createHomework(CreateHomeworkRequest homeworkRequest)
     {
         Optional<Classe> currentClasse = this.classeRepository.findById(homeworkRequest.getClasse_id());
         Optional<Subject> currentSubject = this.subjectRepository.findById(homeworkRequest.getSubject_id());
         if(currentSubject.isPresent() && currentClasse.isPresent())
         {
             Homework homework = new Homework();
             homework.setClasse(currentClasse.get());
             homework.setSubject(currentSubject.get());
             homework.setCoefficient(homeworkRequest.getCoefficient());
             homework.setDate(homeworkRequest.getDate());
             homework.setType(homeworkRequest.getHomeworkType());
             return this.homeworkRepository.save(homework);
         }
         return null;
     }

    public boolean deleteHomework(DeleteHomeworkRequest homeworkRequest)
    {
        Optional<Homework> homework = this.homeworkRepository.findById(homeworkRequest.getId());
        if(homework.isPresent())
        {
            this.homeworkRepository.delete(homework.get());
            return true;
        }
        return false;
    }

}
