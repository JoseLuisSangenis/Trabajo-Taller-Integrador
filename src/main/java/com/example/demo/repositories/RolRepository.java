package com.example.demo.repositories;

import com.example.demo.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository
        extends JpaRepository<Rol, Long> {
}