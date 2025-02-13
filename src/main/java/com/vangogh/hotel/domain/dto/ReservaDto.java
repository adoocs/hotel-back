package com.vangogh.hotel.domain.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
public record ReservaDto(
        String codigo,
        String dniHuesped,
        LocalDate fechaEntrada,
        LocalDate fechaSalida,
        Double total,
        String estado,
        String usuario,
        List<String> habitaciones,
        String pago
) {
}
