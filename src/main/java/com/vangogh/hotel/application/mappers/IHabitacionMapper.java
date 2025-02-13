package com.vangogh.hotel.application.mappers;

import com.vangogh.hotel.domain.dto.HabitacionDto;
import com.vangogh.hotel.domain.models.entities.Habitacion;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IHabitacionMapper {

    @Mapping(source = "tipoHabitacion.descripcion", target = "tipoHabitacion")
    HabitacionDto  toDto(Habitacion habitacion);

    @InheritInverseConfiguration
    Habitacion toEntity(HabitacionDto habitacionDto);

    List<HabitacionDto> toListDto(List<Habitacion> habitacion);
}
