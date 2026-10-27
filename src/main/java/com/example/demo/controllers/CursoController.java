package com.example.demo.controllers;

import com.example.demo.dto.CursoDTO;
import com.example.demo.entities.Curso;
import com.example.demo.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos") // Ruta base de la API REST para Cursos
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar(@RequestParam(required = false) Long idCiclo) {
        if (idCiclo != null) {
            return ResponseEntity.ok(cursoService.listarPorCiclo(idCiclo));
        }
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.buscarPorId(id));
    }
    // POST /api/cursos (Crea un curso nuevo)
    // Devuelve código HTTP 201
    @PostMapping
    public ResponseEntity<Curso> crear(@Valid @RequestBody CursoDTO dto) {
        return new ResponseEntity<>(cursoService.crear(dto), HttpStatus.CREATED);
    }
}