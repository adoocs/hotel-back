package com.vangogh.hotel.domain.dto;

import lombok.Builder;

@Builder
public record HuespedDto(
        String dni,
        String nombres,
        String telefono,
        String correo
){
}
