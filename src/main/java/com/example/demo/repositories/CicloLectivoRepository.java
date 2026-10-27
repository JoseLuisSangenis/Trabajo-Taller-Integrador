package com.example.demo.repositories;

import com.example.demo.entities.CicloLectivo;
import com.example.demo.enums.EstadoCiclo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CicloLectivoRepository extends JpaRepository<CicloLectivo, Long> {
    Optional<CicloLectivo> findByAnio(Integer anio);
    boolean existsByAnio(Integer anio);
    Optional<CicloLectivo> findByEstado(EstadoCiclo estado);
}