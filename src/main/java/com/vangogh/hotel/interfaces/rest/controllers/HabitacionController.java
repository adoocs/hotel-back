package com.vangogh.hotel.interfaces.rest.controllers;

import com.vangogh.hotel.application.services.HabitacionService;
import com.vangogh.hotel.domain.dto.HabitacionDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;

    @PostMapping()
    public ResponseEntity<HabitacionDto> create(@RequestBody HabitacionDto habitacionDto) {
        return ResponseEntity.ok(habitacionService.crearHabitacion(habitacionDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creando la habitación")));
    }

    @GetMapping()
    public ResponseEntity<List<HabitacionDto>> findAll() {
        return ResponseEntity.ok(habitacionService.listarHabitaciones());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<HabitacionDto> findByCodigo(@PathVariable String codigo) {
        return habitacionService.buscarHabitacion(codigo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habitación no encontrada"));
    }

    @GetMapping("/disponible/{fecha}")
    public ResponseEntity<List<HabitacionDto>> findByFecha(@PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        System.out.println(localDate);
        return ResponseEntity.ok(habitacionService.listarHabitacionesDisponibles(localDate));
    }

    @PutMapping()
    public ResponseEntity<HabitacionDto> update(@RequestBody HabitacionDto habitacionDto) {
        return ResponseEntity.ok(habitacionService.actualizarHabitacion(habitacionDto).orElseThrow());
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable String codigo) {
        habitacionService.eliminarHabitacion(codigo);
        return ResponseEntity.noContent().build();
    }
}
