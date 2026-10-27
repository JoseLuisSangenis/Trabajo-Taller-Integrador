package com.example.demo.dto;

import com.example.demo.entities.Evaluacion.TipoEvaluacion;

import java.time.LocalDate;

public record EvaluacionDTO(
        Long id,
        Long cursoMateriaId,
        Long cursoId,
        LocalDate fecha,
        TipoEvaluacion tipo,
        String descripcion) {}