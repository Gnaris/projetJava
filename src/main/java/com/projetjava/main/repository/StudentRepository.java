package com.projetjava.main.repository;

import com.projetjava.main.models.Classe;
import com.projetjava.main.models.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student as s WHERE s.classe IS NULL")
    List<Student> findAllStudentWithoutClasse();

    @Transactional
    @Modifying
    @Query("UPDATE Student AS s SET s.classe = null WHERE s.classe = :classe AND s.id IN :students_id")
    void deleteClasseOnStudents(@Param("classe") Classe classe, @Param("students_id") List<Long> students_id);

    @Transactional
    @Modifying
    @Query("UPDATE Student AS s SET s.classe = null WHERE s.classe = :classe")
    void clearClasseOnStudents(@Param("classe") Classe cLasse);

    @Transactional
    @Modifying
    @Query("UPDATE Student AS s SET s.classe = :classe WHERE s.id IN :students_id")
    void updateClasseOnStudents(@Param("classe") Classe classe, @Param("students_id") List<Long> students_id);
}
