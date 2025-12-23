package com.sga.service;

import com.sga.model.Curso;
import com.sga.repository.AlunoRepository;
import com.sga.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso criar(Curso curso) {
        if (cursoRepository.existsBySigla(curso.getSigla())) {
            throw new IllegalArgumentException("Já existe um curso com a sigla: " + curso.getSigla());
        }
        return cursoRepository.save(curso);
    }

    public void deletar(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com ID: " + id));

        long quantidadeAlunos = alunoRepository.countByCursoId(id);
        if (quantidadeAlunos > 0) {
            throw new IllegalStateException(
                    "Não é possível deletar o curso. Existem " + quantidadeAlunos + " aluno(s) matriculado(s).");
        }

        cursoRepository.delete(curso);
    }
}
