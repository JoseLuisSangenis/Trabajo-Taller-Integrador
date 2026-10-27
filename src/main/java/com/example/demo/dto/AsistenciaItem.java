package com.example.demo.dto;


import com.example.demo.enums.EstadoAsistencia;
import jakarta.validation.constraints.NotNull;


public record AsistenciaItem(
        @NotNull Long inscripcionId,
        @NotNull EstadoAsistencia estado,
        String observacion) {}