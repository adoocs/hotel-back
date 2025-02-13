package com.vangogh.hotel.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Habitacion {

    @Id
    @SequenceGenerator(
            name = "habitacion_sequence",
            sequenceName = "habitacion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "habitacion_sequence"
    )
    private Long Id;
    private Integer capacidad;
    private String descripcion;
    @Column(length = 1)
    private String estado;
    @Column(length = 10)
    private String codigo;
    private Double precio;
    @ManyToOne
    private TipoHabitacion tipoHabitacion;

    @ManyToMany(mappedBy = "habitaciones")  // Relación inversa con Reserva
    private List<Reserva> reservas;
}
