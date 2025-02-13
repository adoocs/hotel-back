package com.vangogh.hotel.infrastructure.repositories;

import com.vangogh.hotel.application.mappers.IHuespedMapper;
import com.vangogh.hotel.domain.dto.HuespedDto;
import com.vangogh.hotel.domain.models.entities.Huesped;
import com.vangogh.hotel.domain.usecase.IHuespedUsecase;
import com.vangogh.hotel.infrastructure.persistence.crud.IHuespedCrud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HuespedRepository implements IHuespedUsecase {

    private final IHuespedMapper iHuespedMapper;
    private final IHuespedCrud iHuespedCrud;

    @Override
    public Optional<HuespedDto> crearHuesped(HuespedDto huespedDto) {
        return Optional.of(
                iHuespedMapper.toDto(
                        iHuespedCrud.save(
                                iHuespedMapper.toEntity(huespedDto))));
    }

    @Override
    public Optional<HuespedDto> buscarHuesped(String dni) {
        return Optional.of(
                iHuespedMapper.toDto(iHuespedCrud.findByDni(dni).orElseThrow()));
    }

    @Override
    public List<HuespedDto> listarHuesped() {
        return iHuespedMapper.toListDto(iHuespedCrud.findAll());
    }

    @Override
    public Optional<HuespedDto> actualizarHuesped(HuespedDto huespedDto) {
        Optional<Huesped> optionalHuesped = iHuespedCrud.findByDni(huespedDto.dni());
        if (optionalHuesped.isEmpty()) {
            return Optional.empty();
        }

        Huesped huesped = optionalHuesped.get();
        huesped.setCorreo(huespedDto.correo());
        huesped.setTelefono(huespedDto.telefono());
        return Optional.of(iHuespedMapper.toDto(iHuespedCrud.save(huesped)));
    }

    @Override
    public void eliminarHuesped(String dni) {
        iHuespedCrud.delete(iHuespedCrud.findByDni(dni).orElseThrow());
    }
}
