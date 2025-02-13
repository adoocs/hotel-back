package com.vangogh.hotel.infrastructure.repositories;

import com.vangogh.hotel.application.mappers.IReservaMapper;
import com.vangogh.hotel.domain.dto.ReservaDto;
import com.vangogh.hotel.domain.models.entities.Habitacion;
import com.vangogh.hotel.domain.models.entities.Huesped;
import com.vangogh.hotel.domain.models.entities.Pago;
import com.vangogh.hotel.domain.models.entities.Reserva;
import com.vangogh.hotel.domain.models.entities.Usuario;
import com.vangogh.hotel.infrastructure.persistence.crud.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReservaRepository {

    private final IReservaCrud iReservaCrud;
    private final IReservaMapper iReservaMapper;
    private final IHuespedCrud iHuespedCrud;
    private final IUsuarioCrud iUsuarioCrud;
    private final IPagoCrud iPagoCrud;
    private final IHabitacionCrud iHabitacionCrud;

    @Transactional
    public Optional<ReservaDto> crearReserva(ReservaDto reservaDto) {
        Huesped huesped = iHuespedCrud.findByDni(reservaDto.dniHuesped()).orElseThrow();
        Usuario usuario = iUsuarioCrud.findByUsername(reservaDto.usuario()).orElseThrow();
        Pago pago = iPagoCrud.findByTipo(reservaDto.pago()).orElseThrow();
        List<Habitacion> habitacions = reservaDto.habitaciones().stream().map(x ->
                iHabitacionCrud.findByCodigo(x).orElseThrow()).toList();
        Reserva reserva = iReservaMapper.toEntity(reservaDto);
        reserva.setUsuario(usuario);
        reserva.setHuesped(huesped);
        reserva.setPago(pago);
        reserva.setHabitaciones(habitacions);
        reserva.setTotal(reserva.calcularTotal());
        return Optional.of(iReservaMapper.toDto(iReservaCrud.save(reserva)));
    }

    public List<ReservaDto> listarReservas() {
        return iReservaMapper.toListDto(iReservaCrud.findAll());
    }

    public Optional<ReservaDto> buscarReserva(String codigo) {
        return Optional.of(iReservaMapper.toDto(iReservaCrud.findByCodigo(codigo).orElseThrow()));
    }

    public void eliminarReserva(String codigo) {
        iReservaCrud.deleteByCodigo(codigo);
    }

    public void actualizarEstado(String codigo, String estado) {
        iReservaCrud.updateEstadoByCodigo(codigo,estado);
    }

    public List<ReservaDto> listarFechaEntrada(LocalDate fechaEntrada) {
        return iReservaMapper.toListDto(iReservaCrud.findAllByFechaEntrada(fechaEntrada));
    }

    public boolean isHabitacionValida(String codigo, LocalDate fechaEntrada, LocalDate fechaSalida) {
        return iReservaCrud.isHabitacionValida(codigo,fechaEntrada, fechaSalida) == 0;
    }

    public int countByHuesped(String dni, int year) {
        return iReservaCrud.countByHuesped(dni, year);
    }
}
