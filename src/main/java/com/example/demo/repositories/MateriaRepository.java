package com.example.demo.repositories;

import com.example.demo.entities.Materia;
import com.example.demo.enums.EstadoRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    boolean existsByNombreIgnoreCase(String nombre); // Valida si existe una materia con ese nombre ignorando mayúsculas/minúsculas
    List<Materia> findByEstado(EstadoRegistro estado); // Para listar únicamente materias en estado ACTIVO en los selectores
}