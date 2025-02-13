package com.vangogh.hotel.application.mappers;

import com.vangogh.hotel.domain.dto.ReservaDto;
import com.vangogh.hotel.domain.models.entities.Habitacion;
import com.vangogh.hotel.domain.models.entities.Reserva;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IReservaMapper {

    @Mapping(target = "dniHuesped", source = "huesped.dni")
    @Mapping(target = "usuario", source = "usuario.username")
    @Mapping(target = "pago", source = "pago.tipo")
    @Mapping(target = "habitaciones", expression = "java(mapHabitaciones(reserva.getHabitaciones()))")
    ReservaDto toDto(Reserva reserva);

    @InheritInverseConfiguration
    @Mapping(target = "habitaciones", expression = "java(mapHabitacionesToEntities(reservaDto.habitaciones()))")
    Reserva toEntity(ReservaDto reservaDto);

    List<ReservaDto> toListDto(List<Reserva> reservas);

    default List<String> mapHabitaciones(List<Habitacion> habitaciones) {
        return habitaciones.stream()
                .map(Habitacion::getCodigo)
                .collect(Collectors.toList());
    }

    default List<Habitacion> mapHabitacionesToEntities(List<String> habitaciones) {
        return habitaciones.stream()
                .map(codigo -> Habitacion.builder().codigo(codigo).build())
                .collect(Collectors.toList());
    }

}
