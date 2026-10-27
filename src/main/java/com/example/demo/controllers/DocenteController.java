
package com.example.demo.controllers;

import com.example.demo.entities.Docente;
import com.example.demo.services.DocenteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping
    public List<Docente> listar() {
        return docenteService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Docente> buscarPorId(@PathVariable Long id) {
        return docenteService.buscarPorId(id);
    }

    @PostMapping
    public Docente guardar(@RequestBody Docente docente) {
        return docenteService.guardar(docente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        docenteService.eliminar(id);
    }
    @PutMapping("/{id}")
    public Docente actualizar(
            @PathVariable Long id,
            @RequestBody Docente docente) {

        Docente existente = docenteService.buscarPorId(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Docente no encontrado"
                        ));

        docente.setIdDocente(existente.getIdDocente());

        return docenteService.guardar(docente);
    }
}
