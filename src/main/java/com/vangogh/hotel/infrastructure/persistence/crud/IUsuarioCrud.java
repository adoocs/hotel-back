package com.vangogh.hotel.infrastructure.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vangogh.hotel.domain.models.entities.Usuario;

import java.util.Optional;

@Repository
public interface IUsuarioCrud extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String nombre);
}
