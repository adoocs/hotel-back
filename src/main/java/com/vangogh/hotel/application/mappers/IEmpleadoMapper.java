package com.vangogh.hotel.application.mappers;

import com.vangogh.hotel.domain.dto.EmpleadoDto;
import com.vangogh.hotel.domain.models.entities.Empleado;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmpleadoMapper {

    @Mapping(source = "dni", target = "dni")
    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "telefono", target = "telefono")
    EmpleadoDto toDto(Empleado empleado);

    @InheritInverseConfiguration
    Empleado toEntity(EmpleadoDto empleadoDto);

    List<EmpleadoDto> toListDto(List<Empleado> empleados);
}
