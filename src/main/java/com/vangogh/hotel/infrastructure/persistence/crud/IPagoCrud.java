package com.vangogh.hotel.infrastructure.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vangogh.hotel.domain.models.entities.Pago;

import java.util.Optional;

public interface IPagoCrud extends JpaRepository<Pago, Long> {

    Optional<Pago> findByTipo(String tipo);
}
