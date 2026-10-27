package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Evaluacion", uniqueConstraints = {
    @UniqueConstraint(
        name = "uk_evaluacion_cursomateria_fecha_tipo",
        columnNames = {"idCursoMateria", "fecha", "tipo"}
    )
})
public class Evaluacion {

    public enum TipoEvaluacion { PRIMER_PARCIAL, SEGUNDO_PARCIAL, EXAMEN_FINAL, TRABAJO_PRACTICO, RECUPERATORIO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluacion;

    @ManyToOne
    @JoinColumn(name= "idCursoMateria", nullable = false)
    private CursoMateria cursoMateria;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private Evaluacion.TipoEvaluacion tipo = Evaluacion.TipoEvaluacion.PRIMER_PARCIAL;

    private String descripcion;
}
