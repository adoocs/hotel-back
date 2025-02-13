package com.vangogh.hotel.domain.dto.request;

import java.time.LocalDate;
import java.util.List;

public record ValidarHabitacionRequest(
        List<String> codigos,
        LocalDate fechaEntrada,
        LocalDate fechaSalida
) {
}
