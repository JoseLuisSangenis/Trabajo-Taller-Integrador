package com.example.demo.services;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entities.Rol;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.RolRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public boolean existeUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    public Usuario guardar(Usuario usuario) {

        if (usuario.getPasswordHash() == null ||
                usuario.getPasswordHash().isBlank()) {
            throw new IllegalArgumentException(
                    "La contraseña es obligatoria"
            );
        }

        String passwordCifrada =
                passwordEncoder.encode(usuario.getPasswordHash());

        usuario.setPasswordHash(passwordCifrada);

        return usuarioRepository.save(usuario);
    }

    public void registrar(UsuarioDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setUsername(dto.getUsername());
        usuario.setPasswordHash(dto.getPassword());
        usuario.setActivo(true);
        usuario.setFechaAlta(LocalDate.now());

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow();

        usuario.setRol(rol);

        guardar(usuario);
    }

    public Optional<Usuario> autenticar(String username, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isEmpty()) {
            return Optional.empty();
        }
        Usuario usuario = usuarioOpt.get();
        if (Boolean.FALSE.equals(usuario.getActivo())) {
            throw new RuntimeException("El usuario está inactivo");
        }
        if (passwordEncoder.matches(password, usuario.getPasswordHash()) || password.equals(usuario.getPasswordHash())) {
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
