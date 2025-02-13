package com.vangogh.hotel.application.services;

import com.vangogh.hotel.domain.dto.HabitacionDto;
import com.vangogh.hotel.domain.usecase.IHabitacionUsecase;
import com.vangogh.hotel.infrastructure.repositories.HabitacionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitacionService implements IHabitacionUsecase {

    private final HabitacionRepository habitacionRepository;

    @Override
    public Optional<HabitacionDto> crearHabitacion(HabitacionDto habitacionDto) {
        return Optional.of(habitacionRepository.crearHabitacion(habitacionDto).orElseThrow());
    }

    @Override
    public Optional<HabitacionDto> buscarHabitacion(String codigo) {
        return Optional.of(habitacionRepository.buscarHabitacion(codigo).orElseThrow());
    }

    @Override
    public Optional<HabitacionDto> actualizarHabitacion(HabitacionDto habitacionDto) {
        return Optional.of(habitacionRepository.actualizarHabitacion(habitacionDto).orElseThrow());
    }

    @Transactional
    @Override
    public void eliminarHabitacion(String codigo) {
        habitacionRepository.eliminarHabitacion(codigo);
    }

    @Override
    public List<HabitacionDto> listarHabitaciones() {
        return habitacionRepository.listarHabitaciones();
    }

    @Override
    public List<HabitacionDto> listarHabitacionesDisponibles(LocalDate fecha) {
        return habitacionRepository.listarHabitacionesDisponibles(fecha);
    }
}
