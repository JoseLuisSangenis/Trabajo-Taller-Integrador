package com.example.demo.dto;

import com.example.demo.entities.Evaluacion.TipoEvaluacion;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EvaluacionRequest(
        @NotNull Long cursoMateriaId,
        @NotNull LocalDate fecha,
        @NotNull TipoEvaluacion tipo,
        String descripcion) {}