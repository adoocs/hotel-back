package com.vangogh.hotel.infrastructure.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vangogh.hotel.domain.models.entities.Habitacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHabitacionCrud extends JpaRepository<Habitacion, Long> {

    @Query("SELECT h FROM Habitacion h " +
            "WHERE NOT EXISTS (" +
            "  SELECT 1 FROM h.reservas r " +
            "  WHERE r.fechaEntrada <= :fecha " +
            "  AND r.fechaSalida >= :fecha" +
            ") ORDER BY h.codigo")
    List<Habitacion> findAllAvailableByFecha(@Param("fecha") LocalDate fecha);


    Optional<Habitacion> findByCodigo(String codigo);

    void deleteByCodigo(String codigo);
}
