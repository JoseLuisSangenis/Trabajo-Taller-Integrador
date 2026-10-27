package com.example.demo.dto;

import com.example.demo.enums.EstadoRegistro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MateriaDTO {
    private Long idMateria;

    @NotBlank(message = "El nombre de la materia es obligatorio")
    @Size(max = 100)
    private String nombre;

    private String descripcion;
    private EstadoRegistro estado;
}