package com.example.demo.entities;

import com.example.demo.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materia")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long idMateria;
    // El nombre de la materia es único en el instituto
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;
    // Soporte para BAJA LÓGICA (Requisito RNF16):
    // No se borra físicamente con DELETE de SQL para no perder el historial académico.
    // Pasa a estado INACTIVO.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private EstadoRegistro estado = EstadoRegistro.ACTIVO;
}