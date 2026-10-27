package com.example.demo.controllers;

import com.example.demo.dto.CursoMateriaDTO;
import com.example.demo.entities.CursoMateria;
import com.example.demo.services.CursoMateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos/{idCurso}/materias")
public class CursoMateriaController {

    @Autowired
    private CursoMateriaService cursoMateriaService;

    @GetMapping
    public ResponseEntity<List<CursoMateria>> listarPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(cursoMateriaService.listarPorCurso(idCurso));
    }

    @PostMapping
    public ResponseEntity<CursoMateria> asignarMateria(
            @PathVariable Long idCurso,
            @Valid @RequestBody CursoMateriaDTO dto) {
        dto.setIdCurso(idCurso);
        return new ResponseEntity<>(cursoMateriaService.asignarMateriaACurso(dto), HttpStatus.CREATED);
    }
}