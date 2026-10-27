package com.example.demo.services;

import com.example.demo.dto.CursoMateriaDTO;
import com.example.demo.entities.Curso;
import com.example.demo.entities.CursoMateria;
import com.example.demo.entities.Materia;
import com.example.demo.repositories.CursoMateriaRepository;
import com.example.demo.repositories.CursoRepository;
import com.example.demo.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoMateriaService {

    @Autowired
    private CursoMateriaRepository cursoMateriaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Transactional(readOnly = true)
    public List<CursoMateria> listarPorCurso(Long idCurso) {
        return cursoMateriaRepository.findByCursoIdCurso(idCurso);
    }

    @Transactional
    public CursoMateria asignarMateriaACurso(CursoMateriaDTO dto) {
        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Materia materia = materiaRepository.findById(dto.getIdMateria())
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));

        if (cursoMateriaRepository.existsByCursoIdCursoAndMateriaIdMateria(dto.getIdCurso(), dto.getIdMateria())) {
            throw new IllegalStateException("La materia ya está asociada a este curso");
        }

        CursoMateria cm = CursoMateria.builder()
                .curso(curso)
                .materia(materia)
                .build();

        return cursoMateriaRepository.save(cm);
    }
}