package com.school.main.repository;

import com.school.main.models.Homework;
import com.school.main.models.HomeworkPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkPartRepository extends JpaRepository<HomeworkPart, Integer> {

    List<HomeworkPart> findHomeworkPartByHomework(Homework homework);
}
