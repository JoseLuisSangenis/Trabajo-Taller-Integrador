package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Calificacion", uniqueConstraints = {
    @UniqueConstraint(
        name = "uk_calificacion_evaluacion_inscripcion",
        columnNames = {"idEvaluacion", "idInscripcion"}
    )
})
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificacion;

    @ManyToOne
    @JoinColumn(name= "idEvaluacion", nullable = false)
    private Evaluacion evaluacion;

    @ManyToOne
    @JoinColumn(name= "idInscripcion", nullable = false)
    private Inscripcion inscripcion;

    private double nota;

    public boolean esNotaValida() {
        return nota >= 1 && nota <= 10;
    }

    private String observacion;
}
