package com.vangogh.hotel.interfaces.rest.controllers;

import com.vangogh.hotel.application.services.ReservaService;
import com.vangogh.hotel.domain.dto.ReservaDto;
import com.vangogh.hotel.domain.dto.request.ValidarHabitacionRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDto>> getAllReservas() {
        return ResponseEntity.ok(reservaService.listarReservas());
    }

    @PostMapping()
    public ResponseEntity<ReservaDto> createReserva(@RequestBody ReservaDto reservaDto) {
        return ResponseEntity.ok(reservaService.crearReserva(reservaDto).orElseThrow());
    }

    @GetMapping("/codigo")
    public ResponseEntity<ReservaDto> buscarReserva(String codigo) {
        return ResponseEntity.ok(reservaService.buscarReserva(codigo).orElseThrow());
    }

    @PutMapping("/cancelar/{codigo}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable String codigo) {
        reservaService.cancelarReserva(codigo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/confirmar/{codigo}")
    public ResponseEntity<Void> confirmarReserva(@PathVariable String codigo) {
        reservaService.confirmarReserva(codigo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{fecha}")
    public ResponseEntity<List<ReservaDto>> buscarReservasFecha(@PathVariable String fecha) {
        return ResponseEntity.ok(reservaService.listarReservasFecha(LocalDate.parse(fecha)));
    }

    @GetMapping("/valid")
    boolean verificarHabitaciones(@RequestBody ValidarHabitacionRequest request) {
        return reservaService.verificarHabitaciones(request.codigos(),request.fechaEntrada(), request.fechaSalida());
    }
}
