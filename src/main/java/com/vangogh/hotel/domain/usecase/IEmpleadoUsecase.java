package com.vangogh.hotel.domain.usecase;

import com.vangogh.hotel.domain.dto.EmpleadoDto;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoUsecase {

    Optional<EmpleadoDto> crearEmpleado(EmpleadoDto empleadoDto);
    Optional<EmpleadoDto> buscarEmpleado(String dniEmpleado);
    Optional<EmpleadoDto> actualizarEmpleado(EmpleadoDto empleadoDto);
    void eliminarEmpleado(String dniEmpleado);
    List<EmpleadoDto> getEmpleados();
}
