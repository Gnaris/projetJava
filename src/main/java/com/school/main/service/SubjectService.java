package com.school.main.service;

import com.school.main.models.Subject;
import com.school.main.repository.SubjectRepository;
import com.school.main.request.SubjectRequest.CreateSubjectRequest;
import com.school.main.request.SubjectRequest.DeleteSubjectRequest;
import com.school.main.request.SubjectRequest.UpdateSubjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject createSubject(CreateSubjectRequest subjectRequest) {
        List<Subject> subject = this.subjectRepository.findByName(subjectRequest.getName());
        if(subject.isEmpty()) {
            Subject newSubject = new Subject();
            newSubject.setName(subjectRequest.getName());
            return this.subjectRepository.save(newSubject);
        }
        return null;
    }

    public boolean deleteSubject(DeleteSubjectRequest subjectRequest) {
        Optional<Subject> subject = this.subjectRepository.findById(subjectRequest.getId());
        if(subject.isPresent()) {
            if(subject.get().getHomeworks().isEmpty())
            {
                this.subjectRepository.delete(subject.get());
                return true;
            }
        }
        return false;
    }

    public Subject updateSubject(UpdateSubjectRequest subjectRequest) {
        Optional<Subject> subject = this.subjectRepository.findById(subjectRequest.getId());
        if(subject.isPresent()) {
            Subject updatedSubject = new Subject();
            updatedSubject.setName(subjectRequest.getName());
            this.subjectRepository.save(updatedSubject);
            return updatedSubject;
        }
        return null;
    }
}
