package com.projetjava.main.repository;

import com.projetjava.main.models.Subject;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Transactional
    List<Subject> findByName(String name);
}
