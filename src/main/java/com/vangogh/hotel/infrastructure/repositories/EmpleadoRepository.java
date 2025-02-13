package com.vangogh.hotel.infrastructure.repositories;

import com.vangogh.hotel.application.mappers.IEmpleadoMapper;
import com.vangogh.hotel.domain.dto.EmpleadoDto;
import com.vangogh.hotel.domain.models.entities.Empleado;
import com.vangogh.hotel.domain.usecase.IEmpleadoUsecase;
import com.vangogh.hotel.infrastructure.persistence.crud.IEmpleadoCrud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmpleadoRepository implements IEmpleadoUsecase {

    private final IEmpleadoCrud iEmpleadoCrud;
    private final IEmpleadoMapper iEmpleadoMapper;

    public Optional<EmpleadoDto> crearEmpleado(EmpleadoDto empleadoDto) {
        Empleado empleado = iEmpleadoMapper.toEntity(empleadoDto);
        return Optional.of(iEmpleadoMapper.toDto(iEmpleadoCrud.save(empleado)));
    }

    public Optional<EmpleadoDto> buscarEmpleado(String dni) {
        return iEmpleadoCrud.findByDni(dni).map(iEmpleadoMapper::toDto);
    }

    public Optional<EmpleadoDto> actualizarEmpleado(EmpleadoDto empleadoDto) {
        Optional<Empleado> optionalEmpleado = iEmpleadoCrud.findByDni(empleadoDto.dni());
        if (optionalEmpleado.isEmpty()) {
            return Optional.empty();
        }

        Empleado empleado = optionalEmpleado.get();
        empleado.setTelefono(empleadoDto.telefono());
        empleado.setCorreo(empleadoDto.correo());
        return Optional.of(iEmpleadoMapper.toDto(iEmpleadoCrud.save(empleado)));
    }

    public void eliminarEmpleado(String dni) {
        iEmpleadoCrud.deleteByDni(dni);
    }

    @Override
    public List<EmpleadoDto> getEmpleados() {
        return iEmpleadoMapper.toListDto(iEmpleadoCrud.findAll());
    }

}
