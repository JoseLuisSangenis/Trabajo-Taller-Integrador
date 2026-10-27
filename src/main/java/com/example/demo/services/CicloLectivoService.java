package com.example.demo.services;

import com.example.demo.dto.CicloLectivoDTO;
import com.example.demo.entities.CicloLectivo;
import com.example.demo.enums.EstadoCiclo;
import com.example.demo.repositories.CicloLectivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CicloLectivoService {

    @Autowired
    private CicloLectivoRepository cicloRepository;

    @Transactional(readOnly = true)
    public List<CicloLectivo> listarTodos() {
        return cicloRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CicloLectivo buscarPorId(Long id) {
        return cicloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ciclo lectivo no encontrado con ID: " + id));
    }

    @Transactional
    public CicloLectivo crear(CicloLectivoDTO dto) {
        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
        if (cicloRepository.existsByAnio(dto.getAnio())) {
            throw new IllegalStateException("Ya existe un ciclo lectivo para el año " + dto.getAnio());
        }

        CicloLectivo ciclo = CicloLectivo.builder()
                .anio(dto.getAnio())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .estado(dto.getEstado() != null ? dto.getEstado() : EstadoCiclo.PLANIFICADO)
                .build();

        return cicloRepository.save(ciclo);
    }

    @Transactional
    public CicloLectivo actualizar(Long id, CicloLectivoDTO dto) {
        CicloLectivo ciclo = buscarPorId(id);
        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de inicio");
        }
        ciclo.setFechaInicio(dto.getFechaInicio());
        ciclo.setFechaFin(dto.getFechaFin());
        if (dto.getEstado() != null) {
            ciclo.setEstado(dto.getEstado());
        }
        return cicloRepository.save(ciclo);
    }
}