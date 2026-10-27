package com.example.demo.controllers;

import com.example.demo.dto.AsistenciaCursoRequest;
import com.example.demo.dto.AsistenciaDTO;
import com.example.demo.dto.ResumenAsistencia;
import com.example.demo.services.AsistenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService service;

    // RF41
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<AsistenciaDTO> registrar(@Valid @RequestBody AsistenciaCursoRequest req) {
        return service.registrar(req);
    }

    // RF42
    @GetMapping("/alumno/{alumnoId}")
    public List<AsistenciaDTO> historial(@PathVariable Long alumnoId) {
        return service.historial(alumnoId);
    }

    // RF43
    @GetMapping("/alumno/{alumnoId}/resumen")
    public ResumenAsistencia resumen(@PathVariable Long alumnoId) {
        return service.resumen(alumnoId);
    }
}

