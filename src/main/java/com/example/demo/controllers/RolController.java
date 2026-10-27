package com.example.demo.controllers;


import com.example.demo.entities.Rol;
import com.example.demo.services.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public List<Rol> listar() {
        return rolService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Rol> buscarPorId(@PathVariable Long id) {
        return rolService.buscarPorId(id);
    }

    @PostMapping
    public Rol guardar(@RequestBody Rol rol) {
        return rolService.guardar(rol);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
    }
    @PutMapping("/{id}")
    public Rol actualizar(
            @PathVariable Long id,
            @RequestBody Rol rol) {

        Rol existente = rolService.buscarPorId(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Rol no encontrado"
                        ));

        rol.setIdRol(existente.getIdRol());

        return rolService.guardar(rol);
    }
}