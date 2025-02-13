package com.vangogh.hotel.interfaces.rest.controllers;

import com.vangogh.hotel.application.services.HuespedService;
import com.vangogh.hotel.domain.dto.HuespedDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/huespedes")
@RequiredArgsConstructor
public class HuespedController {

    private final HuespedService huespedService;

    @PostMapping
    public ResponseEntity<HuespedDto> create(@RequestBody HuespedDto huespedDto) {
        return ResponseEntity.ok(huespedService.crearHuesped(huespedDto).orElseThrow());
    }

    @GetMapping()
    public ResponseEntity<List<HuespedDto>> getAll() {
        return ResponseEntity.ok(huespedService.listarHuesped());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<HuespedDto> findByDni(@PathVariable String dni) {
        return ResponseEntity.of(huespedService.buscarHuesped(dni));
    }

    @PutMapping()
    public ResponseEntity<HuespedDto> update(@RequestBody HuespedDto huespedDto) {
        return ResponseEntity.ok(huespedService.actualizarHuesped(huespedDto).orElseThrow());
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<HuespedDto> delete(@PathVariable String dni) {
        huespedService.eliminarHuesped(dni);
        return ResponseEntity.ok().build();
    }
}
