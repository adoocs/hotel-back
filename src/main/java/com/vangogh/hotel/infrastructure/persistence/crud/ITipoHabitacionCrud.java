package com.vangogh.hotel.infrastructure.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vangogh.hotel.domain.models.entities.TipoHabitacion;

import java.util.Optional;

public interface ITipoHabitacionCrud extends JpaRepository<TipoHabitacion, Integer> {
    Optional<TipoHabitacion> findByDescripcion(String descripcion);
}
