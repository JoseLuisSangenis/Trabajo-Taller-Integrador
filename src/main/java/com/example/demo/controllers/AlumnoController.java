package com.example.demo.controllers;

import com.example.demo.entities.Alumno;
import com.example.demo.services.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> listar() {
        return alumnoService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Alumno> buscarPorId(@PathVariable Long id) {
        return alumnoService.buscarPorId(id);
    }

    @PostMapping
    public Alumno guardar(@RequestBody Alumno alumno) {
        return alumnoService.guardar(alumno);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        alumnoService.eliminar(id);
    }

    @PutMapping("/{id}")
    public Alumno actualizar(
            @PathVariable Long id,
            @RequestBody Alumno alumno) {

        Alumno existente = alumnoService.buscarPorId(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Alumno no encontrado"
                        ));

        alumno.setIdAlumno(existente.getIdAlumno());

        return alumnoService.guardar(alumno);
    }
}
