package com.vangogh.hotel.infrastructure.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vangogh.hotel.domain.models.entities.Empleado;

import java.util.Optional;

public interface IEmpleadoCrud extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByDni(String dni);

    void deleteByDni(String dni);
}
