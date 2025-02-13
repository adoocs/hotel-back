package com.vangogh.hotel.application.services;

import com.vangogh.hotel.domain.dto.HabitacionDto;
import com.vangogh.hotel.domain.dto.ReservaDto;
import com.vangogh.hotel.domain.usecase.IReservaUsecase;
import com.vangogh.hotel.infrastructure.repositories.ReservaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaUsecase {

    private final ReservaRepository reservaRepository;
    private final HabitacionService habitacionService;

    @Override
    public Optional<ReservaDto> crearReserva(ReservaDto reservaDto) {
        double totalReserva = reservaDto.habitaciones().stream()
                .mapToDouble(codigo ->
                        habitacionService.buscarHabitacion(codigo)
                                .stream().mapToDouble(HabitacionDto::precio).sum()
                )
                .sum();
        ReservaDto newReservaDto = ReservaDto.builder()
                .pago(reservaDto.pago())
                .usuario(reservaDto.usuario())
                .estado(reservaDto.estado())
                .dniHuesped(reservaDto.dniHuesped())
                .fechaEntrada(reservaDto.fechaEntrada())
                .fechaSalida(reservaDto.fechaSalida())
                .habitaciones(reservaDto.habitaciones())
                .total(totalReserva * calcularDescuento(reservaDto))
                .build();
        return reservaRepository.crearReserva(newReservaDto);
    }

    @Override
    public List<ReservaDto> listarReservas() {
        return reservaRepository.listarReservas();
    }

    @Override
    public Optional<ReservaDto> buscarReserva(String codigo) {
        return reservaRepository.buscarReserva(codigo);
    }

    @Override
    public void eliminarReserva(String codigo) {
        reservaRepository.eliminarReserva(codigo);
    }

    @Override
    public void actualizarEstado(String codigo, String estado) {
        reservaRepository.actualizarEstado(codigo, estado);
    }

    @Override
    public List<ReservaDto> listarReservasFecha(LocalDate fecha) {
        return reservaRepository.listarFechaEntrada(fecha);
    }

    public boolean verificarHabitaciones(List<String> codigos, LocalDate fechaEntrada, LocalDate fechaSalida) {
        for (String codigo : codigos) {
            if (!reservaRepository.isHabitacionValida(codigo, fechaEntrada, fechaSalida)) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public void cancelarReserva(String codigo) {
        reservaRepository.actualizarEstado(codigo, "cancelada");
    }

    @Transactional
    public void confirmarReserva(String codigo) {
        reservaRepository.actualizarEstado(codigo, "confirmada");
    }

    @Override
    public double calcularDescuento(ReservaDto reservaDto) {
        int count = reservaRepository.countByHuesped(reservaDto.dniHuesped(), LocalDate.now().getYear());
        if (count > 10) return 0.1;
        if (count > 5) return 0.5;
        return 0;
    }
}
