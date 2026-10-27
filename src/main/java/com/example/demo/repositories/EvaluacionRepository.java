package com.example.demo.repositories;

import com.example.demo.entities.Curso;
import com.example.demo.entities.CursoMateria;
import com.example.demo.entities.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    // RF37: evaluaciones de una materia de un curso
    List<Evaluacion> findByCursoMateria(CursoMateria cursoMateria);

    // RF37: todas las evaluaciones de un curso, las más recientes primero
    List<Evaluacion> findByCursoMateriaCursoOrderByFechaDesc(Curso curso);
}