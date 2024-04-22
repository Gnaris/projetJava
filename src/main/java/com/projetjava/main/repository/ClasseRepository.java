package com.projetjava.main.repository;

import com.projetjava.main.models.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
}