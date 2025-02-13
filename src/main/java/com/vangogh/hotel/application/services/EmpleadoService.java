package com.vangogh.hotel.application.services;

import com.vangogh.hotel.domain.dto.EmpleadoDto;
import com.vangogh.hotel.domain.usecase.IEmpleadoUsecase;
import com.vangogh.hotel.infrastructure.repositories.EmpleadoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmpleadoService implements IEmpleadoUsecase {

    private final EmpleadoRepository empleadoRepository;
    @Override
    public Optional<EmpleadoDto> crearEmpleado(EmpleadoDto empleadoDto) {
        return empleadoRepository.crearEmpleado(empleadoDto);
    }

    @Override
    public Optional<EmpleadoDto> buscarEmpleado(String dniEmpleado) {
        return empleadoRepository.buscarEmpleado(dniEmpleado);
    }

    @Override
    public Optional<EmpleadoDto> actualizarEmpleado(EmpleadoDto empleadoDto) {
        return empleadoRepository.actualizarEmpleado(empleadoDto);
    }

    @Transactional
    @Override
    public void eliminarEmpleado(String dniEmpleado) {
        empleadoRepository.eliminarEmpleado(dniEmpleado);
    }

    @Override
    public List<EmpleadoDto> getEmpleados() {
        return empleadoRepository.getEmpleados();
    }
}
