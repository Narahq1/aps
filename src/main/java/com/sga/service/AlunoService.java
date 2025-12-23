package com.sga.service;

import com.sga.model.Aluno;
import com.sga.model.Curso;
import com.sga.repository.AlunoRepository;
import com.sga.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public List<Aluno> buscarPorCurso(Long cursoId) {
        return alunoRepository.findByCursoId(cursoId);
    }

    public Aluno matricular(Aluno aluno) {
        if (alunoRepository.existsByMatricula(aluno.getMatricula())) {
            throw new IllegalArgumentException("Já existe um aluno com a matrícula: " + aluno.getMatricula());
        }

        if (aluno.getCurso() == null || aluno.getCurso().getId() == null) {
            throw new IllegalArgumentException("É necessário informar um curso válido para matricular o aluno");
        }

        Curso curso = cursoRepository.findById(aluno.getCurso().getId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Curso não encontrado com ID: " + aluno.getCurso().getId()));

        aluno.setCurso(curso);
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + id));

        if (!alunoExistente.getMatricula().equals(alunoAtualizado.getMatricula())) {
            if (alunoRepository.existsByMatricula(alunoAtualizado.getMatricula())) {
                throw new IllegalArgumentException(
                        "Já existe um aluno com a matrícula: " + alunoAtualizado.getMatricula());
            }
        }

        if (alunoAtualizado.getCurso() != null && alunoAtualizado.getCurso().getId() != null) {
            Curso curso = cursoRepository.findById(alunoAtualizado.getCurso().getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Curso não encontrado com ID: " + alunoAtualizado.getCurso().getId()));
            alunoExistente.setCurso(curso);
        }

        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setMatricula(alunoAtualizado.getMatricula());
        alunoExistente.setEmail(alunoAtualizado.getEmail());

        return alunoRepository.save(alunoExistente);
    }

    public void cancelarMatricula(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + id));

        alunoRepository.delete(aluno);
    }
}
