package com.vangogh.hotel.interfaces.rest.controllers;

import com.vangogh.hotel.application.services.EmpleadoService;
import com.vangogh.hotel.domain.dto.EmpleadoDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @PostMapping("")
    public ResponseEntity<EmpleadoDto> create(@RequestBody EmpleadoDto empleadoDto) {
        empleadoService.crearEmpleado(empleadoDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<EmpleadoDto>> getAll(){
        return ResponseEntity.ok(empleadoService.getEmpleados());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<EmpleadoDto> getEmpleado(@PathVariable String dni){
        return ResponseEntity.ok(empleadoService.buscarEmpleado(dni).orElseThrow());
    }

    @PutMapping("")
    public ResponseEntity<EmpleadoDto> update(@RequestBody EmpleadoDto empleadoDto) {
        return ResponseEntity.ok().body(empleadoService.actualizarEmpleado(empleadoDto).orElseThrow());
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> delete(@PathVariable String dni) {
        empleadoService.eliminarEmpleado(dni);
        return ResponseEntity.noContent().build();
    }
}
