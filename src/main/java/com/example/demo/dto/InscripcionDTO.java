package com.example.demo.dto;

import com.example.demo.entities.Inscripcion.EstadoInscripcion;

import java.time.LocalDate;

public record InscripcionDTO(
        Long id,
        Long alumnoId,
        Long cursoId,
        LocalDate fecha,
        EstadoInscripcion estado) {}