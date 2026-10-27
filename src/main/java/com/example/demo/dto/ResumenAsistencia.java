package com.example.demo.dto;

public record ResumenAsistencia(
        long total,
        long presentes,
        long ausentes,
        double porcentaje) {}