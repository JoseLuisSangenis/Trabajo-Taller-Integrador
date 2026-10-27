package com.example.demo.controllers;

import com.example.demo.dto.EvaluacionDTO;
import com.example.demo.dto.EvaluacionRequest;
import com.example.demo.dto.EvaluacionUpdateRequest;
import com.example.demo.services.EvaluacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService service;

    // RF36
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluacionDTO registrar(@Valid @RequestBody EvaluacionRequest req) {
        return service.registrar(req);
    }

    // RF37
    @GetMapping("/{id}")
    public EvaluacionDTO obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/curso/{cursoId}")
    public List<EvaluacionDTO> porCurso(@PathVariable Long cursoId) {
        return service.porCurso(cursoId);
    }

    @GetMapping("/curso-materia/{cursoMateriaId}")
    public List<EvaluacionDTO> porCursoMateria(@PathVariable Long cursoMateriaId) {
        return service.porCursoMateria(cursoMateriaId);
    }

    @PutMapping("/{id}")
    public EvaluacionDTO modificar(@PathVariable Long id,
                                   @Valid @RequestBody EvaluacionUpdateRequest req) {
        return service.modificar(id, req);
    }
}