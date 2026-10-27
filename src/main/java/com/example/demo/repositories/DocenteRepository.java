package com.example.demo.repositories;

import com.example.demo.entities.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository
        extends JpaRepository<Docente, Long> {
}
