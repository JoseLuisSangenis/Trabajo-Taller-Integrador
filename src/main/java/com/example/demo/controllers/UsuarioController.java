package com.example.demo.controllers;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    public void registrar(@RequestBody UsuarioDTO dto) {
        usuarioService.registrar(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String username = credenciales.get("username");
        String password = credenciales.get("password");
        try {
            Optional<Usuario> usuarioOpt = usuarioService.autenticar(username, password);
            if (usuarioOpt.isPresent()) {
                Usuario u = usuarioOpt.get();
                Map<String, Object> resp = new HashMap<>();
                resp.put("idUsuario", u.getIdUsuario());
                resp.put("username", u.getUsername());
                resp.put("rol", u.getRol() != null ? u.getRol().getNombre() : "ADMIN");
                resp.put("idAlumno", u.getAlumno() != null ? u.getAlumno().getIdAlumno() : null);
                resp.put("idDocente", u.getDocente() != null ? u.getDocente().getIdDocente() : null);
                return ResponseEntity.ok(resp);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuario o contraseña incorrectos"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
