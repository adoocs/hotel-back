package com.vangogh.hotel.application.services;

import com.vangogh.hotel.domain.dto.HuespedDto;
import com.vangogh.hotel.domain.usecase.IHuespedUsecase;
import com.vangogh.hotel.infrastructure.repositories.HuespedRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuespedService implements IHuespedUsecase {

    private final HuespedRepository huespedRepository;

    @Override
    public Optional<HuespedDto> crearHuesped(HuespedDto huespedDto) {
        return huespedRepository.crearHuesped(huespedDto);
    }

    @Override
    public Optional<HuespedDto> buscarHuesped(String dni) {
        return huespedRepository.buscarHuesped(dni);
    }

    @Override
    public List<HuespedDto> listarHuesped() {
        return huespedRepository.listarHuesped();
    }

    @Override
    public Optional<HuespedDto> actualizarHuesped(HuespedDto huespedDto) {
        return huespedRepository.actualizarHuesped(huespedDto);
    }

    @Transactional
    @Override
    public void eliminarHuesped(String dni) {
        huespedRepository.eliminarHuesped(dni);
    }
}
