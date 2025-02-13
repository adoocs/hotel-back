package com.vangogh.hotel.domain.models.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Reserva {
    @Id
//    @SequenceGenerator(
//            name = "reserva_sequence",
//            sequenceName = "reserva_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "reserva_sequence"
//    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Setter(value = AccessLevel.NONE)
    private String codigo;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String estado;
    private Double total;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Habitacion.class)
    @JoinTable(
            name = "reserva_habitacion",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "habitacion_id")
    )
    private List<Habitacion> habitaciones;
    @ManyToOne
    private Huesped huesped;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Pago pago;

    public Reserva() {
        this.codigo = UUID.randomUUID().toString();
        this.estado = "pendiente";
    }

    @PrePersist
    public void setDefaults() {
        this.codigo = UUID.randomUUID().toString().substring(0, 10);
        this.estado = "pendiente";
    }

    public double calcularTotal() {
        int dias = Period.between(fechaEntrada, fechaSalida).getDays();
        return habitaciones.stream().mapToDouble(h -> h.getPrecio() * dias)
                .sum();
    }
}
