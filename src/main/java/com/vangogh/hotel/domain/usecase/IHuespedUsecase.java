package com.vangogh.hotel.domain.usecase;

import com.vangogh.hotel.domain.dto.HuespedDto;

import java.util.List;
import java.util.Optional;

public interface IHuespedUsecase {

    Optional<HuespedDto> crearHuesped(HuespedDto huespedDto);
    Optional<HuespedDto> buscarHuesped(String dni);
    List<HuespedDto> listarHuesped();
    Optional<HuespedDto> actualizarHuesped(HuespedDto huespedDto);
    void eliminarHuesped(String dni);
}
