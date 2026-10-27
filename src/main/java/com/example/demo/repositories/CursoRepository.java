package com.example.demo.repositories;

import com.example.demo.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByCicloLectivoIdCiclo(Long idCiclo); // Spring genera la consulta: SELECT * FROM curso WHERE id_ciclo =....
    boolean existsByCicloLectivoIdCicloAndAnioCursadoAndDivisionIgnoreCase(Long idCiclo, Integer anioCursado, String division); // Consulta para validar si ya existe el curso antes de crearlo
}