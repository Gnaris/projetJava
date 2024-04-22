package com.projetjava.main.service;

import com.projetjava.main.request.HomeworkRequest.DeleteHomeworkRequest;
import com.projetjava.main.models.Homework;
import com.projetjava.main.repository.ClasseRepository;
import com.projetjava.main.repository.HomeworkRepository;
import com.projetjava.main.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeworkService {

     @Autowired
    private HomeworkRepository homeworkRepository;

     @Autowired
     private ClasseRepository classeRepository;

     @Autowired
     private SubjectRepository subjectRepository;

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
