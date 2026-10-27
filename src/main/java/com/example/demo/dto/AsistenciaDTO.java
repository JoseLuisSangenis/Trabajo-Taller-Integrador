package com.example.demo.dto;

import com.example.demo.entities.Asistencia.EstadoAsistencia;

import java.time.LocalDate;

public record AsistenciaDTO(
        Long id,
        Long inscripcionId,
        Long alumnoId,
        LocalDate fecha,
        EstadoAsistencia estado,
        String observacion) {}