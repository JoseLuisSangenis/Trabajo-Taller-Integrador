package com.example.demo.repositories;

import com.example.demo.entities.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Long> {
    List<Cuota> findByEstado(String estado);
    List<Cuota> findByMatriculaIdMatricula(Long idMatricula);
}
