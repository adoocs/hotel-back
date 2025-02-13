package com.vangogh.hotel.domain.models.entities;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {
    @Id
    @SequenceGenerator(
            name = "usuario_sequence",
            sequenceName = "usuario_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usuario_sequence"
    )
    private Long Id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;

    @OneToOne
    private Empleado empleado;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;
}
