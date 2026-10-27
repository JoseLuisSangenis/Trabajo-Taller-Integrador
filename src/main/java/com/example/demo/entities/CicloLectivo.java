package com.example.demo.entities;

import com.example.demo.enums.EstadoCiclo;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "ciclo_lectivo") //tabla ciclo lectivo en Postgre
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CicloLectivo {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PostgreSQL gestiona el autoincremento
    @Column(name = "id_ciclo")
    private Long idCiclo;

    @Column(nullable = false, unique = true) //regla de negocio, pj: no puede existir 2 veces el mismo año y tmb restriccion not null para la bd
    private Integer anio;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)  //Guarda el texto "PLANIFICADO", "VIGENTE" en la BD
    @Column(nullable = false, length = 20)
    @Builder.Default
    private EstadoCiclo estado = EstadoCiclo.PLANIFICADO;

    public boolean estaVigente() {
        return this.estado == EstadoCiclo.VIGENTE;
    }
}