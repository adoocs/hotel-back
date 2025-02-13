package com.vangogh.hotel.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Huesped {
    @Id
    @SequenceGenerator(
            name = "huesped_sequence",
            sequenceName = "huesped_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "huesped_sequence"
    )
    private Long Id;
    private String nombres;
    @Column(unique = true, length = 8)
    private String dni;
    @Column(length = 20, unique = true)
    private String telefono;
    @Column(unique = true)
    private String correo;
}
