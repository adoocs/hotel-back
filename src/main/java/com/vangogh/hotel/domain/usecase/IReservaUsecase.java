package com.vangogh.hotel.domain.usecase;

import com.vangogh.hotel.domain.dto.ReservaDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReservaUsecase {

    Optional<ReservaDto> crearReserva(ReservaDto reservaDto);
    List<ReservaDto> listarReservas();
    List<ReservaDto> listarReservasFecha(LocalDate fecha);
    Optional<ReservaDto> buscarReserva(String codigo);
    void eliminarReserva(String codigo);
    void actualizarEstado(String codigo, String estado);
    double calcularDescuento(ReservaDto reservaDto);
}
