package com.school.main.repository;

import com.school.main.models.Classe;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

    @Transactional
    @Query("SELECT NEW map(" +
            "s.id AS student_id, " +
            "s.firstname AS firstname, " +
            "s.lastname AS lastname, " +
            "COALESCE(s2.name, '') AS subject, " +
            "COALESCE(h.type, '') AS homework_type, " +
            "COALESCE(SUM(n.note), 0) AS total_score, " +
            "COALESCE(h.coefficient, 0) AS coefficient, " +
            "h.date AS date) " +
            "FROM Student s " +
            "JOIN s.classe_student c " +
            "JOIN s.notes n " +
            "JOIN n.homework_part_id hp " +
            "JOIN hp.homework h " +
            "JOIN h.subject s2 " +
            "WHERE c.id = 1 " +
            "GROUP BY s.id, s2.id, h.type " +
            "ORDER BY s.id, s2.id, h.type")
    List<Map<String, Object>> getBulletinClasse(@Param("classe") Classe classe);
}