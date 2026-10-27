package com.example.demo.repositories;

import com.example.demo.entities.Alumno;
import com.example.demo.entities.Asistencia;
import com.example.demo.entities.Asistencia.EstadoAsistencia;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    // RF41: evitar registrar dos veces la asistencia de una inscripción en la misma fecha
    boolean existsByInscripcionAndFecha(Inscripcion inscripcion, LocalDate fecha);

    // RF41: ver lo registrado de un curso en una fecha
    List<Asistencia> findByInscripcionCursoAndFecha(Curso curso, LocalDate fecha);

    // RF42: historial de un alumno, de lo más reciente a lo más antiguo
    List<Asistencia> findByInscripcionAlumnoOrderByFechaDesc(Alumno alumno);

    // RF43: totales para calcular porcentaje, asistencias e inasistencias
    long countByInscripcionAlumno(Alumno alumno);
    long countByInscripcionAlumnoAndEstado(Alumno alumno, EstadoAsistencia estado);
}