package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CursoMateriaDTO {
    private Long idCursoMateria;

    @NotNull(message = "El ID del curso es obligatorio")
    private Long idCurso;

    @NotNull(message = "El ID de la materia es obligatorio")
    private Long idMateria;

    private Long idDocente;
    private String nombreCurso;
    private String nombreMateria;
    private String nombreDocente;
}