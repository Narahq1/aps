package com.sga.repository;

import com.sga.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findBySigla(String sigla);

    boolean existsBySigla(String sigla);
}
