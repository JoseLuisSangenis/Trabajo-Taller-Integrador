package com.example.demo.dto;

import com.example.demo.enums.EstadoCiclo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CicloLectivoDTO {
    private Long idCiclo;

    @NotNull(message = "El año es obligatorio")
    private Integer anio;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    private EstadoCiclo estado;
}