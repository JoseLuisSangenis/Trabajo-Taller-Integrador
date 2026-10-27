package com.example.demo.controllers;

import com.example.demo.entities.Alumno;
import com.example.demo.entities.CicloLectivo;
import com.example.demo.entities.Matricula;
import com.example.demo.services.AlumnoService;
import com.example.demo.services.CicloLectivoService;
import com.example.demo.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private CicloLectivoService cicloLectivoService;

    @GetMapping
    public ResponseEntity<List<Matricula>> listar() {
        return ResponseEntity.ok(matriculaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return matriculaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Matricula>> buscarPorAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(matriculaService.buscarPorAlumno(alumnoId));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Map<String, Object> req) {
        try {
            Long alumnoId = Long.valueOf(req.get("idAlumno").toString());
            Long cicloId = Long.valueOf(req.get("idCiclo").toString());
            BigDecimal importe = new BigDecimal(req.get("importe").toString());

            Alumno alumno = alumnoService.buscarPorId(alumnoId)
                    .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
            CicloLectivo ciclo = cicloLectivoService.buscarPorId(cicloId);

            Matricula matricula = new Matricula();
            matricula.setAlumno(alumno);
            matricula.setCicloLectivo(ciclo);
            matricula.setFecha(LocalDate.now());
            matricula.setImporte(importe);
            matricula.setEstado("ACTIVA");

            return new ResponseEntity<>(matriculaService.guardar(matricula), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            String nuevoEstado = body.get("estado");
            Matricula m = matriculaService.cambiarEstado(id, nuevoEstado);
            return ResponseEntity.ok(m);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
