package com.vangogh.hotel.domain.usecase;

import com.vangogh.hotel.domain.dto.HabitacionDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHabitacionUsecase {

    Optional<HabitacionDto> crearHabitacion(HabitacionDto habitacionDto);
    Optional<HabitacionDto> buscarHabitacion(String codigo);
    Optional<HabitacionDto> actualizarHabitacion(HabitacionDto habitacionDto);
    void eliminarHabitacion(String codigo);
    List<HabitacionDto> listarHabitaciones();
    List<HabitacionDto> listarHabitacionesDisponibles(LocalDate fecha);
}
