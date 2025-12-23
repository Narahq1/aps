package com.sga.repository;

import com.sga.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByCursoId(Long cursoId);

    Optional<Aluno> findByMatricula(String matricula);

    boolean existsByMatricula(String matricula);

    long countByCursoId(Long cursoId);
}
