package com.example.demo.repositories;

import com.example.demo.entities.Alumno;
import com.example.demo.entities.Calificacion;
import com.example.demo.entities.Evaluacion;
import com.example.demo.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    // RF38: una inscripción no debería tener dos notas en la misma evaluación
    boolean existsByInscripcionAndEvaluacion(Inscripcion inscripcion, Evaluacion evaluacion);

    // RF38/RF39: notas de una evaluación (todos los alumnos)
    List<Calificacion> findByEvaluacion(Evaluacion evaluacion);

    // RF40: notas de un alumno
    List<Calificacion> findByInscripcionAlumno(Alumno alumno);

    // RF44: promedio de un alumno (devuelve null si no tiene notas)
    @Query("select avg(c.nota) from Calificacion c where c.inscripcion.alumno = :alumno")
    Double promedioPorAlumno(@Param("alumno") Alumno alumno);
}