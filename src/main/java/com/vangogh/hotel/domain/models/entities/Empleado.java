package com.vangogh.hotel.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Empleado {
    @Id
    @SequenceGenerator(
            name = "empleado_sequence",
            sequenceName = "empleado_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "empleado_sequence"
    )
    private Long Id;
    private String nombres;
    @Column(unique = true, length = 8)
    private String dni;
    @Column(unique = true, length = 20)
    private String telefono;
    @Column(unique = true)
    private String correo; 
}
