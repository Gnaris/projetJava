package com.projetjava.main.repository;

import com.projetjava.main.models.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
