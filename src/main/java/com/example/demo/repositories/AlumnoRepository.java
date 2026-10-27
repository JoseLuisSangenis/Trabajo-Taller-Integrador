package com.example.demo.repositories;

import com.example.demo.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository
        extends JpaRepository<Alumno, Long> {
}
