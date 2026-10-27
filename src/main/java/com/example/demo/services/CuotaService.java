package com.example.demo.services;

import com.example.demo.entities.Cuota;
import com.example.demo.entities.Matricula;
import com.example.demo.repositories.CuotaRepository;
import com.example.demo.repositories.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CuotaService {

    @Autowired
    private CuotaRepository cuotaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Cuota> listar() {
        return cuotaRepository.findAll();
    }

    public Optional<Cuota> buscarPorId(Long id) {
        return cuotaRepository.findById(id);
    }

    public List<Cuota> buscarPorMatricula(Long idMatricula) {
        return cuotaRepository.findByMatriculaIdMatricula(idMatricula);
    }

    public void generarCuotasParaMatricula(Long idMatricula, int cantidad, BigDecimal monto, LocalDate primerVencimiento) {
        Matricula matricula = matriculaRepository.findById(idMatricula)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));

        // [RN06] Las cuotas solo pueden generarse para matrículas en estado ACTIVA
        if (!"ACTIVA".equals(matricula.getEstado())) {
            throw new IllegalStateException("No se pueden generar cuotas: La matrícula no está ACTIVA");
        }

        for (int i = 1; i <= cantidad; i++) {
            Cuota cuota = new Cuota();
            cuota.setMatricula(matricula);
            cuota.setNumeroCuota(i); // [RN05] Número correlativo
            cuota.setImporte(monto);
            cuota.setFechaVencimiento(primerVencimiento.plusMonths(i - 1));
            cuota.setEstado("PENDIENTE");
            cuotaRepository.save(cuota);
        }
    }

    // [RN07] Tarea programada diaria automática para vencer cuotas impagas expiradas
    @Scheduled(cron = "0 5 0 * * ?") // Se ejecuta todos los días a las 00:05 hs de la madrugada
    public void verificarCuotasVencidas() {
        List<Cuota> pendientes = cuotaRepository.findByEstado("PENDIENTE");
        LocalDate hoy = LocalDate.now();
        for (Cuota cuota : pendientes) {
            if (hoy.isAfter(cuota.getFechaVencimiento())) {
                cuota.setEstado("VENCIDA");
                cuotaRepository.save(cuota);
            }
        }
    }
}
