package com.vangogh.hotel.domain.dto;

import lombok.Builder;

@Builder
public record HabitacionDto(
        String codigo,
        String descripcion,
        String estado,
        Integer capacidad,
        String tipoHabitacion,
        Double precio
) {

}
