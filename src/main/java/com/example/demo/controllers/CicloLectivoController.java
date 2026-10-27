package com.example.demo.controllers;

import com.example.demo.dto.CicloLectivoDTO;
import com.example.demo.entities.CicloLectivo;
import com.example.demo.services.CicloLectivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ciclos")
public class CicloLectivoController {

    @Autowired
    private CicloLectivoService cicloService;

    @GetMapping
    public ResponseEntity<List<CicloLectivo>> listar() {
        return ResponseEntity.ok(cicloService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CicloLectivo> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(cicloService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CicloLectivo> crear(@Valid @RequestBody CicloLectivoDTO dto) {
        return new ResponseEntity<>(cicloService.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CicloLectivo> actualizar(@PathVariable Long id, @Valid @RequestBody CicloLectivoDTO dto) {
        return ResponseEntity.ok(cicloService.actualizar(id, dto));
    }
}