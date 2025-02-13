package com.vangogh.hotel.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TipoHabitacion {
    @Id
    @SequenceGenerator(
            name = "tipohabitacion_sequence",
            sequenceName = "tipohabitacion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tipohabitacion_sequence"
    )
    private Long Id;
    private String descripcion;

}
