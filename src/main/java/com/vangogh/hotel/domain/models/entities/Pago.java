package com.vangogh.hotel.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pago {
    @Id
    @SequenceGenerator(
            name = "pago_sequence",
            sequenceName = "pago_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pago_sequence"
    )
    private Long Id;
    private String tipo;
}
