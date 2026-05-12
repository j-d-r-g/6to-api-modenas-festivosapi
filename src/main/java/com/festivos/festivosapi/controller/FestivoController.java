package com.festivos.festivosapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import com.festivos.festivosapi.entity.CalendarioEntity;
import com.festivos.festivosapi.model.Festivo;
import com.festivos.festivosapi.service.FestivoService;

@RestController
@RequestMapping("/api/festivos")
public class FestivoController {

     @Autowired
    private FestivoService festivoService;

    // 🔹 Obtener festivos por año
    @GetMapping("/{anio}")
    public List<Festivo> obtenerFestivos(@PathVariable int anio) {
        return festivoService.obtenerFestivos(anio);
    }

    // 🔹 Generar calendario
    @PostMapping("/generar/{anio}")
    public String generarCalendario(@PathVariable int anio) {
        festivoService.generarCalendario(anio);
        return "Calendario generado correctamente";
    }
}