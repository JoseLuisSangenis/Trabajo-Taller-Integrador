package com.example.demo.services;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.entities.Materia;
import com.example.demo.enums.EstadoRegistro;
import com.example.demo.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Transactional(readOnly = true)
    public List<Materia> listarTodas() {
        return materiaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Materia> listarActivas() {
        return materiaRepository.findByEstado(EstadoRegistro.ACTIVO);
    }

    @Transactional(readOnly = true)
    public Materia buscarPorId(Long id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada con ID: " + id));
    }

    @Transactional
    public Materia crear(MateriaDTO dto) {  // Regla: No duplicar nombres de materias
        if (materiaRepository.existsByNombreIgnoreCase(dto.getNombre().trim())) {
            throw new IllegalStateException("Ya existe una materia con el nombre: " + dto.getNombre());
        }

        Materia materia = Materia.builder()
                .nombre(dto.getNombre().trim())
                .descripcion(dto.getDescripcion())
                .estado(EstadoRegistro.ACTIVO)
                .build();

        return materiaRepository.save(materia);
    }

    @Transactional
    public Materia actualizar(Long id, MateriaDTO dto) {
        Materia materia = buscarPorId(id);
        materia.setNombre(dto.getNombre().trim());
        materia.setDescripcion(dto.getDescripcion());
        return materiaRepository.save(materia);
    }
    // Regla RNF16: Implementación de BAJA LÓGICA
    @Transactional
    public void darDeBajaLogica(Long id) {
        Materia materia = buscarPorId(id);
        materia.setEstado(EstadoRegistro.INACTIVO);
        materiaRepository.save(materia);
    }
}