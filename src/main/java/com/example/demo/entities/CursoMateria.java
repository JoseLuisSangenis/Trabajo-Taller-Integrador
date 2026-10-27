package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;
//relación Muchos a Muchos (N:M) entre Curso y Materia,
// guardando además qué Docente la dicta
@Entity
@Table(name = "curso_materia", uniqueConstraints = {  // Una materia no puede estar asignada dos veces al mismo curso
        @UniqueConstraint(
                name = "uk_curso_materia",
                columnNames = {"id_curso", "id_materia"}
        )
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CursoMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso_materia")
    private Long idCursoMateria;
    // FK al Curso
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
    // FK a la Materia
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;
    // FK al Docente asignado
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_docente", nullable = true)
    private Docente docente;
}