package com.vangogh.hotel.infrastructure.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vangogh.hotel.domain.models.entities.Reserva;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReservaCrud extends JpaRepository<Reserva, Long> {

    @Modifying
    @Query("UPDATE Reserva r SET r.estado = :estado WHERE r.codigo = :codigo")
    void updateEstadoByCodigo(@Param("codigo") String codigo, @Param("estado") String estado);

    void deleteByCodigo(String codigo);

    Optional<Reserva> findByCodigo(String codigo);

    @Query("SELECT r FROM Reserva r WHERE r.fechaEntrada = :fecha AND r.estado = 'pendiente'" )
    List<Reserva> findAllByFechaEntrada(LocalDate fecha);

    @Query("SELECT COUNT(r) FROM Reserva r " +
            "JOIN r.habitaciones h " +
            "WHERE h.codigo = :codigo " +
            "AND r.fechaEntrada < :fechaSalida " +
            "AND r.fechaSalida >= :fechaEntrada")
    int isHabitacionValida(@Param("codigo") String codigo,
                           @Param("fechaEntrada") LocalDate fechaEntrada,
                           @Param("fechaSalida") LocalDate fechaSalida);

    @Query("SELECT COUNT(r) FROM Reserva r " +
            "JOIN r.huesped h " +
            "WHERE h.dni = :dni " +
            "AND YEAR(r.fechaEntrada) = :year")
    int countByHuesped(@Param("dni") String dni, @Param("year") int year);
}
