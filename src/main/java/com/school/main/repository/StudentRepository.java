package com.school.main.repository;

import com.school.main.models.Classe;
import com.school.main.models.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Student AS s SET s.classe_student = null WHERE s.classe_student = :classe AND s.id IN :students_id")
    void deleteClasseOnStudents(@Param("classe") Classe classe, @Param("students_id") List<Long> students_id);

    @Transactional
    @Modifying
    @Query("UPDATE Student AS s SET s.classe_student = :classe WHERE s.id IN :students_id")
    void addClasseOnStudents(@Param("classe") Classe classe, @Param("students_id") List<Long> students_id);
}
