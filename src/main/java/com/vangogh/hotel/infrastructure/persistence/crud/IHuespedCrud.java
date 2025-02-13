package com.vangogh.hotel.infrastructure.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vangogh.hotel.domain.models.entities.Huesped;

import java.util.Optional;

public interface IHuespedCrud extends JpaRepository<Huesped, Long> {

    Optional<Huesped> findByDni(String dni);
}
