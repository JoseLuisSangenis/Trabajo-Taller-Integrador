package com.example.demo.controllers;

import com.example.demo.dto.InscripcionDTO;
import com.example.demo.dto.InscripcionRequest;
import com.example.demo.services.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService service;

    // RF22
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InscripcionDTO inscribir(@Valid @RequestBody InscripcionRequest req) {
        return service.inscribir(req);
    }

    // RF23
    @GetMapping("/alumno/{alumnoId}")
    public List<InscripcionDTO> porAlumno(@PathVariable Long alumnoId) {
        return service.porAlumno(alumnoId);
    }

    @GetMapping("/curso/{cursoId}")
    public List<InscripcionDTO> porCurso(@PathVariable Long cursoId) {
        return service.porCurso(cursoId);
    }


    // RF24
    @PutMapping("/{id}/anular")
    public InscripcionDTO anular(@PathVariable Long id) {
        return service.anular(id);
    }
}