package com.school.main.service;

import com.school.main.models.Classe;
import com.school.main.models.Homework;
import com.school.main.models.HomeworkPart;
import com.school.main.models.Subject;
import com.school.main.repository.ClasseRepository;
import com.school.main.repository.HomeworkPartRepository;
import com.school.main.repository.HomeworkRepository;
import com.school.main.repository.SubjectRepository;
import com.school.main.request.HomeworkRequest.CreateHomeworkRequest;
import com.school.main.request.HomeworkRequest.DeleteHomeworkRequest;
import com.school.main.request.HomeworkRequest.UpdateHomeworkRequest;
import com.school.main.request.SubjectRequest.UpdateSubjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

     @Autowired
     private HomeworkPartRepository homeworkPartRepository;

     public Homework createHomework(CreateHomeworkRequest homeworkRequest)
     {
         Optional<Classe> classe = this.classeRepository.findById(homeworkRequest.getClasse_id());
         Optional<Subject> subject = this.subjectRepository.findById(homeworkRequest.getSubject_id());
         if(classe.isPresent() && subject.isPresent())
         {
             Homework homework = new Homework();
             homework.setType(homeworkRequest.getHomeworkType());
             homework.setCoefficient(homeworkRequest.getCoefficient());
             homework.setDate(homeworkRequest.getDate());
             homework.setSubject(subject.get());
             homework.setClasse(classe.get());
             List<HomeworkPart> homeworkPartList = new ArrayList<>();
             HomeworkPart homeworkPart;
             for(int point : homeworkRequest.getPoints())
             {
                 homeworkPart = new HomeworkPart();
                 homeworkPart.setHomework(homework);
                 homeworkPart.setPoint(point);
                 homeworkPartList.add(homeworkPart);
             }
             this.homeworkRepository.save(homework);
             this.homeworkPartRepository.saveAll(homeworkPartList);
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

    public Homework updateHomework(UpdateHomeworkRequest homeworkRequest)
    {
        Optional<Homework> homework = this.homeworkRepository.findById(homeworkRequest.getId());
        if(homework.isPresent()){
            List<HomeworkPart> homeworkParts = this.homeworkPartRepository.findHomeworkPartByHomework(homework.get());
            boolean anyMath = homeworkParts.stream().anyMatch(h -> !h.getNotes().isEmpty());
            if(!anyMath)
            {
                Optional<Classe> classe = this.classeRepository.findById(homeworkRequest.getClasse_id());
                Optional<Subject> subject = this.subjectRepository.findById(homeworkRequest.getSubject_id());
                if(classe.isPresent() && subject.isPresent()){
                    Homework homeworkUpdate = homework.get();
                    homeworkUpdate.setType(homeworkRequest.getHomeworkType());
                    homeworkUpdate.setCoefficient(homeworkRequest.getCoefficient());
                    homeworkUpdate.setDate(homeworkRequest.getDate());
                    homeworkUpdate.setSubject(subject.get());
                    homeworkUpdate.setClasse(classe.get());
                    List<HomeworkPart> homeworkPartList = new ArrayList<>();
                    HomeworkPart homeworkPart;
                    for(int point : homeworkRequest.getPoints())
                    {
                        homeworkPart = new HomeworkPart();
                        homeworkPart.setHomework(homeworkUpdate);
                        homeworkPart.setPoint(point);
                        homeworkPartList.add(homeworkPart);
                    }
                    this.homeworkPartRepository.saveAll(homeworkPartList);
                    return this.homeworkRepository.save(homeworkUpdate);
                }
            }
        }
        return null;
    }

}
