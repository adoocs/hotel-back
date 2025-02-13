-- Insertar datos en la tabla empleado
INSERT INTO public.empleado (dni, id, telefono, correo, nombres) VALUES
                                                                     ('12345678', 1, '555123456', 'j.perez@ejemplo.com', 'Juan Pérez'),
                                                                     ('23456789', 2, '555234567', 'a.gomez@ejemplo.com', 'Ana Gómez'),
                                                                     ('34567890', 3, '555345678', 'm.torres@ejemplo.com', 'María Torres');

-- Insertar datos en la tabla role
INSERT INTO public.role (id, descripcion) VALUES
                                              (1, 'Administrador'),
                                              (2, 'Recepcionista'),
                                              (3, 'Cliente');

-- Insertar datos en la tabla usuario
INSERT INTO public.usuario (empleado_id, id, password, username) VALUES
                                                                     (1, 1, 'pass123', 'juanp'),
                                                                     (2, 2, 'pass456', 'anag'),
                                                                     (3, 3, 'pass789', 'mariato');


-- Insertar datos en la tabla tipo_habitacion
INSERT INTO public.tipo_habitacion (id, descripcion) VALUES
                                                         (1, 'Doble'),
                                                         (2, 'Individual'),
                                                         (3, 'Cuádruple');

-- Insertar datos en la tabla habitacion
INSERT INTO public.habitacion (capacidad, estado, precio, id, tipo_habitacion_id, codigo, descripcion) VALUES
                                                                                                           (2, 'A', 70.0, 1, 1, 'HAB001', 'Habitación Doble'),
                                                                                                           (4, 'A', 120.0, 2, 1, 'HAB002', 'Habitación Cuádruple'),
                                                                                                           (1, 'A', 50.0, 3, 1, 'HAB003', 'Habitación Individual');

-- Insertar datos en la tabla huesped
INSERT INTO public.huesped (dni, id, correo, telefono, nombres) VALUES
                                                                    ('98765432', 1, 'c.lopez@ejemplo.com', '555987654', 'Carlos López'),
                                                                    ('87654321', 2, 'l.martinez@ejemplo.com', '555876543', 'Lucía Martínez'),
                                                                    ('76543210', 3, 'j.sanchez@ejemplo.com', '555765432', 'Javier Sánchez');

-- Insertar datos en la tabla pago
INSERT INTO public.pago (id, tipo) VALUES
                                       (1, 'Efectivo'),
                                       (2, 'Tarjeta de Crédito'),
                                       (3, 'Transferencia Bancaria');

-- Insertar datos en la tabla reserva
INSERT INTO public.reserva (created_at, fecha_entrada, fecha_salida, huesped_id, id, pago_id, usuario_id, codigo, estado) VALUES
                                                                                                                              (NOW(), '2024-10-25 14:00', '2024-10-30 12:00', 1, 1, 1, 1, 'RES001', 'Confirmada'),
                                                                                                                              (NOW(), '2024-11-05 14:00', '2024-11-10 12:00', 2, 2, 2, 1, 'RES002', 'Pendiente'),
                                                                                                                              (NOW(), '2024-11-15 14:00', '2024-11-20 12:00', 3, 3, 3, 1, 'RES003', 'Confirmada');

-- Insertar datos en la tabla reserva_habitacion
INSERT INTO public.reserva_habitacion (habitacion_id, reserva_id) VALUES
                                                                      (1, 1),
                                                                      (2, 2),
                                                                      (3, 3);

-- Insertar datos en la tabla usuario_roles
INSERT INTO public.usuario_roles (roles_id, usuario_id) VALUES
                                                            (1, 1),
                                                            (2, 2),
                                                            (3, 3);
