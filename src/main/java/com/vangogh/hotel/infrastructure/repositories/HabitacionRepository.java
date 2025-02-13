package com.vangogh.hotel.infrastructure.repositories;

import com.vangogh.hotel.application.mappers.IHabitacionMapper;
import com.vangogh.hotel.domain.dto.HabitacionDto;
import com.vangogh.hotel.domain.models.entities.Habitacion;
import com.vangogh.hotel.domain.models.entities.TipoHabitacion;
import com.vangogh.hotel.domain.usecase.IHabitacionUsecase;
import com.vangogh.hotel.infrastructure.persistence.crud.IHabitacionCrud;
import com.vangogh.hotel.infrastructure.persistence.crud.ITipoHabitacionCrud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HabitacionRepository implements IHabitacionUsecase {

    private final IHabitacionCrud iHabitacionCrud;
    private final IHabitacionMapper iHabitacionMapper;
    private final ITipoHabitacionCrud iTipoHabitacionCrud;

    @Override
    public Optional<HabitacionDto> crearHabitacion(HabitacionDto habitacionDto) {
        Optional<TipoHabitacion> tipoHabitacionOptional = iTipoHabitacionCrud.findByDescripcion(habitacionDto.tipoHabitacion());
        if (tipoHabitacionOptional.isEmpty()) {
            throw new IllegalArgumentException("Tipo de habitacion no encontrado");
        }
        Habitacion habitacion = iHabitacionMapper.toEntity(habitacionDto);
        habitacion.setTipoHabitacion(tipoHabitacionOptional.get());
        return Optional.of(iHabitacionMapper.toDto(iHabitacionCrud.save(habitacion)));
    }

    @Override
    public Optional<HabitacionDto> buscarHabitacion(String codigo) {
        return Optional.of(iHabitacionMapper
                .toDto(iHabitacionCrud.findByCodigo(codigo).orElseThrow()));

    }

    @Override
    public Optional<HabitacionDto> actualizarHabitacion(HabitacionDto habitacionDto) {
        Optional<Habitacion> habitacionOptional = iHabitacionCrud.findByCodigo(habitacionDto.codigo());
        if (habitacionOptional.isEmpty()) {
            return Optional.empty();
        }
        Habitacion habitacion = habitacionOptional.get();
        habitacion.setDescripcion(habitacionDto.descripcion());
        habitacion.setCapacidad(habitacionDto.capacidad());
        return Optional.of(iHabitacionMapper.toDto(habitacion));
    }

    @Override
    public void eliminarHabitacion(String codigo) {
        iHabitacionCrud.deleteByCodigo(codigo);
    }

    @Override
    public List<HabitacionDto> listarHabitaciones() {
        return iHabitacionMapper.toListDto(iHabitacionCrud.findAll());
    }

    @Override
    public List<HabitacionDto> listarHabitacionesDisponibles(LocalDate fecha) {
        return iHabitacionMapper.toListDto(iHabitacionCrud.findAllAvailableByFecha(fecha));
    }
}
