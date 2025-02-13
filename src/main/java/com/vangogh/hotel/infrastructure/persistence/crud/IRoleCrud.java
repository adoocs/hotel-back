package com.vangogh.hotel.infrastructure.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vangogh.hotel.domain.models.entities.Role;

import java.util.List;

@Repository
public interface IRoleCrud extends JpaRepository<Role, Long> {

    List<Role> findRolesByDescripcionIn(List<String> descripcion);
}
