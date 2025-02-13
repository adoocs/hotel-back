package com.vangogh.hotel.infrastructure.config.bean;

import com.vangogh.hotel.application.services.EmpleadoService;
import com.vangogh.hotel.application.services.ReservaService;
import com.vangogh.hotel.domain.dto.EmpleadoDto;
import com.vangogh.hotel.domain.dto.ReservaDto;
import com.vangogh.hotel.domain.models.entities.Empleado;
import com.vangogh.hotel.domain.models.entities.Habitacion;
import com.vangogh.hotel.domain.models.entities.Huesped;
import com.vangogh.hotel.domain.models.entities.Pago;
import com.vangogh.hotel.domain.models.entities.Role;
import com.vangogh.hotel.domain.models.entities.RoleEnum;
import com.vangogh.hotel.domain.models.entities.TipoHabitacion;
import com.vangogh.hotel.domain.models.entities.Usuario;
import com.vangogh.hotel.infrastructure.persistence.crud.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

@Component
@Configuration
@RequiredArgsConstructor
public class OnInitComponent {

    private final EmpleadoService empleadoService;
    private final IUsuarioCrud iUsuarioCrud;
    private final PasswordEncoder passwordEncoder;
    private final ITipoHabitacionCrud iTipoHabitacionCrud;
    private final IHuespedCrud iHuespedCrud;
    private final IHabitacionCrud iHabitacionCrud;
    private final IPagoCrud iPagoCrud;

    @Bean
    public CommandLineRunner commandLineRunner(ReservaService reservaService) {
        return args -> {

            TipoHabitacion t1 = TipoHabitacion.builder().descripcion("Simple").build();
            TipoHabitacion t2 = TipoHabitacion.builder().descripcion("Doble").build();
            TipoHabitacion t3 = TipoHabitacion.builder().descripcion("Ejecutiva").build();
            iTipoHabitacionCrud.saveAll(Arrays.asList(t1, t2, t3));

            Habitacion h1 = Habitacion.builder()
                    .codigo("HAB001")
                    .precio(80D)
                    .capacidad(1)
                    .descripcion("Para una persona")
                    .estado("A")
                    .tipoHabitacion(t1)
                    .build();

            Habitacion h2 = Habitacion.builder()
                    .codigo("HAB002")
                    .precio(150D)
                    .capacidad(2)
                    .descripcion("Para dos personas")
                    .estado("A")
                    .tipoHabitacion(t2)
                    .build();

            Habitacion h3 = Habitacion.builder()
                    .codigo("HAB003")
                    .precio(200D)
                    .capacidad(2)
                    .descripcion("Hab. doble con vistas al mar")
                    .estado("A")
                    .tipoHabitacion(t2)
                    .build();

            Habitacion h4 = Habitacion.builder()
                    .codigo("HAB004")
                    .precio(300D)
                    .capacidad(1)
                    .descripcion("Habitación Ejecutiva con escritorio")
                    .estado("A")
                    .tipoHabitacion(t3)
                    .build();

            Habitacion h5 = Habitacion.builder()
                    .codigo("HAB005")
                    .precio(120D)
                    .capacidad(2)
                    .descripcion("Habitación Doble con cama extra grande")
                    .estado("A")
                    .tipoHabitacion(t2)
                    .build();

            Habitacion h6 = Habitacion.builder()
                    .codigo("HAB006")
                    .precio(250D)
                    .capacidad(1)
                    .descripcion("Habitación Ejecutiva con vistas")
                    .estado("A")
                    .tipoHabitacion(t3)
                    .build();
            iHabitacionCrud.saveAll(Arrays.asList(h1, h2, h3, h4, h5, h6));

            // Crear algunos huéspedes
            Huesped huesped1 = Huesped.builder()
                    .nombres("Juan Pérez")
                    .dni("12345678")
                    .telefono("987654321")
                    .correo("juan.perez@email.com")
                    .build();

            Huesped huesped2 = Huesped.builder()
                    .nombres("Ana Gómez")
                    .dni("87654321")
                    .telefono("612345678")
                    .correo("ana.gomez@email.com")
                    .build();

            Huesped huesped3 = Huesped.builder()
                    .nombres("Carlos López")
                    .dni("11223344")
                    .telefono("665544332")
                    .correo("carlos.lopez@email.com")
                    .build();

            Huesped huesped4 = Huesped.builder()
                    .nombres("María Rodríguez")
                    .dni("99887766")
                    .telefono("654321987")
                    .correo("maria.rodriguez@email.com")
                    .build();

            Huesped huesped5 = Huesped.builder()
                    .nombres("Pedro Martínez")
                    .dni("22334455")
                    .telefono("699887766")
                    .correo("pedro.martinez@email.com")
                    .build();

            iHuespedCrud.saveAll(Arrays.asList(huesped1, huesped2, huesped3, huesped4, huesped5));

            Pago pago1 = Pago.builder().tipo("Yape").build();
            Pago pago2 = Pago.builder().tipo("Tarjeta").build();
            Pago pago3 = Pago.builder().tipo("Efectivo").build();
            iPagoCrud.saveAll(Arrays.asList(pago1,pago2, pago3));



            EmpleadoDto empleado = EmpleadoDto.builder()
                    .dni("12345678")
                    .nombres("Ricardo Lopez")
                    .correo("r.lopez@gmail.com")
                    .telefono("987456321").build();
            empleadoService.crearEmpleado(empleado);

            Usuario usuario = Usuario.builder()
                    .username("rlopez")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNoLocked(true)
                    .accountNoExpired(true)
                    .credentialNoExpired(true)
                    .empleado(Empleado.builder().Id(1L).build())
                    .roles(Set.of(Role.builder().descripcion(RoleEnum.ADMIN).build())).build();
            iUsuarioCrud.save(usuario);

            ReservaDto reserva1 = ReservaDto.builder()
                    .dniHuesped("12345678")
                    .fechaEntrada(LocalDate.of(2024, 11, 20))
                    .fechaSalida(LocalDate.of(2024, 11, 25))
                    .estado("Confirmada")
                    .usuario("rlopez")
                    .habitaciones(Arrays.asList("HAB001", "HAB002"))
                    .pago("Yape")
                    .build();

            ReservaDto reserva2 = ReservaDto.builder()
                    .dniHuesped("87654321")
                    .fechaEntrada(LocalDate.of(2024, 12, 1))
                    .fechaSalida(LocalDate.of(2024, 12, 5))
                    .estado("Pendiente")
                    .usuario("rlopez")
                    .habitaciones(Arrays.asList("HAB003", "HAB004"))
                    .pago("Tarjeta")
                    .build();

            ReservaDto reserva3 = ReservaDto.builder()
                    .dniHuesped("11223344")
                    .fechaEntrada(LocalDate.of(2024, 12, 10))
                    .fechaSalida(LocalDate.of(2024, 12, 15))
                    .estado("Confirmada")
                    .usuario("rlopez")
                    .habitaciones(Arrays.asList("HAB005", "HAB006"))
                    .pago("Efectivo")
                    .build();

            ReservaDto reserva4 = ReservaDto.builder()
                    .dniHuesped("99887766")
                    .fechaEntrada(LocalDate.of(2024, 11, 30))
                    .fechaSalida(LocalDate.of(2024, 12, 3))
                    .estado("Confirmada")
                    .usuario("rlopez")
                    .habitaciones(Arrays.asList("HAB001", "HAB003"))
                    .pago("Yape")
                    .build();

            ReservaDto reserva5 = ReservaDto.builder()
                    .dniHuesped("22334455")
                    .fechaEntrada(LocalDate.of(2024, 12, 5))
                    .fechaSalida(LocalDate.of(2024, 12, 7))
                    .estado("Pendiente")
                    .usuario("rlopez")
                    .habitaciones(Arrays.asList("HAB002", "HAB004"))
                    .pago("Tarjeta")
                    .build();

            Arrays.asList(reserva1, reserva2, reserva3, reserva4, reserva5).forEach(reservaService::crearReserva);
        };
    }
}
