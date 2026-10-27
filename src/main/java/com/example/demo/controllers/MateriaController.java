package com.example.demo.controllers;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.entities.Materia;
import com.example.demo.services.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<Materia>> listar(@RequestParam(defaultValue = "true") boolean soloActivas) {
        if (soloActivas) {
            return ResponseEntity.ok(materiaService.listarActivas());
        }
        return ResponseEntity.ok(materiaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Materia> crear(@Valid @RequestBody MateriaDTO dto) {
        return new ResponseEntity<>(materiaService.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> actualizar(@PathVariable Long id, @Valid @RequestBody MateriaDTO dto) {
        return ResponseEntity.ok(materiaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> bajaLogica(@PathVariable Long id) {
        materiaService.darDeBajaLogica(id);
        return ResponseEntity.noContent().build();
    }
}