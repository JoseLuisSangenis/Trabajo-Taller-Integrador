package com.example.demo.controllers;

import com.example.demo.entities.Cuota;
import com.example.demo.services.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cuotas")
public class CuotaController {

    @Autowired
    private CuotaService cuotaService;

    @GetMapping
    public ResponseEntity<List<Cuota>> listar() {
        return ResponseEntity.ok(cuotaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return cuotaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricula/{matriculaId}")
    public ResponseEntity<List<Cuota>> buscarPorMatricula(@PathVariable Long matriculaId) {
        return ResponseEntity.ok(cuotaService.buscarPorMatricula(matriculaId));
    }

    @PostMapping("/generar")
    public ResponseEntity<?> generar(@RequestBody Map<String, Object> req) {
        try {
            Long idMatricula = Long.valueOf(req.get("idMatricula").toString());
            int cantidad = Integer.parseInt(req.get("cantidad").toString());
            BigDecimal monto = new BigDecimal(req.get("monto").toString());
            LocalDate primerVencimiento = LocalDate.parse(req.get("primerVencimiento").toString());

            cuotaService.generarCuotasParaMatricula(idMatricula, cantidad, monto, primerVencimiento);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Cuotas generadas exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
