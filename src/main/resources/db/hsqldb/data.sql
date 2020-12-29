-- Creamos un usuario para administrador
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- Creamos un usuario para mecanico
INSERT INTO users(username,password,enabled) VALUES ('mecanico1','m3c4nico',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'mecanico1','mecanico');
-- Creamos un usuario para cliente
INSERT INTO users(username,password,enabled) VALUES ('cliente1','cl13nte',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'cliente1','cliente');

--Administrador
INSERT INTO administrador(id,nombre,apellidos,dni,telefono,email) VALUES (1, 'Eugenio', 'Vicente Bravo', '13627495L', '645329832','adminTaLaPlata@gmail.com');

--Mecanicos
INSERT INTO mecanico(id,nombre,apellidos,dni,telefono,email) VALUES (1, 'Juan', 'Perez Barea', '45637892P', '654738291','juanpeba@gmail.com');
INSERT INTO mecanico(id,nombre,apellidos,dni,telefono,email) VALUES (2, 'Pepe', 'Lopez Vazquez', '17483965D', '633846783','pepelova@gmail.com');
INSERT INTO mecanico(id,nombre,apellidos,dni,telefono,email) VALUES (3, 'Paco', 'Garcia Marquez', '84927546F', '717362549','pacogama@gmail.com');

--Clientes
INSERT INTO cliente(id,nombre,apellidos,dni,telefono,email) VALUES (1, 'Manuel', 'Viera Rodriguez', '62748364G', '633572849','manuvierod@gmail.com');
INSERT INTO cliente(id,nombre,apellidos,dni,telefono,email) VALUES (2, 'Francisco', 'Dominguez Rodriguez', '35462748F', '667483749','fradomrod@gmail.com');
INSERT INTO cliente(id,nombre,apellidos,dni,telefono,email) VALUES (3, 'Julian', 'Mota Fernandez', '28463574T', '715635427','julitomotores@gmail.com');
INSERT INTO cliente(id,nombre,apellidos,dni,telefono,email) VALUES (4, 'Aitor', 'Tilla Montesco', '48295647P', '655367264','aitortillapacome@gmail.com');

--Cita
INSERT INTO cita(id,fecha_cita,hora_cita) VALUES (1,'2021-01-04', '10:00');
INSERT INTO cita(id,fecha_cita,hora_cita) VALUES (2,'2021-01-04', '08:00');
INSERT INTO cita(id,fecha_cita,hora_cita) VALUES (3,'2021-01-04', '09:00');
INSERT INTO cita(id,fecha_cita,hora_cita) VALUES (4,'2021-01-04', '09:00');

--Estancia
INSERT INTO estancia(id,fecha_entrada,fecha_salida,duracion) VALUES (1,'2021-01-02', '2021-01-04', 4320);
INSERT INTO estancia(id,fecha_entrada,fecha_salida,duracion) VALUES (2,'2021-01-05', '2021-01-05', 200);
INSERT INTO estancia(id,fecha_entrada,fecha_salida,duracion) VALUES (3,'2021-01-06', '2021-01-08', 3300);
INSERT INTO estancia(id,fecha_entrada,fecha_salida,duracion) VALUES (4,'2021-01-07', '2021-01-08', 1200);

--Factura
INSERT INTO factura(id,descripcion,precio,tipo_pago,fecha_emision,pagado) VALUES (1,'Cambio de aceite', 20.00, 1, '2021-01-08', FALSE);
INSERT INTO factura(id,descripcion,precio,tipo_pago,fecha_emision,pagado) VALUES (2,'Arreglo pastilla frenos', 50.00, 1, '2021-12-14', TRUE);
INSERT INTO factura(id,descripcion,precio,tipo_pago,fecha_emision,pagado) VALUES (3,'Arreglo piston de arranque', 100.33, 2, '2020-12-16', FALSE);
INSERT INTO factura(id,descripcion,precio,tipo_pago,fecha_emision,pagado) VALUES (4,'Cambio de neumaticos', 60.50, 3, '2020-12-16', FALSE);

--Pedido
INSERT INTO pedido(id,fecha_entrada,fecha_emision) VALUES (1,'2020-12-10', '2020-12-14');
INSERT INTO pedido(id,fecha_entrada,fecha_emision) VALUES (2,'2020-12-10', '2020-12-15');
INSERT INTO pedido(id,fecha_entrada,fecha_emision) VALUES (3,'2020-12-13', '2020-12-16');
INSERT INTO pedido(id,fecha_entrada,fecha_emision) VALUES (4,'2020-12-13', '2020-12-16');

--Producto
INSERT INTO producto(referencia,stock,nombre,marca,stock_seguridad) VALUES ('NEU54638',10,'Neumaticos','Nexen',4);
INSERT INTO producto(referencia,stock,nombre,marca,stock_seguridad) VALUES ('71632A',20,'Aceite','Castrol',10);
INSERT INTO producto(referencia,stock,nombre,marca,stock_seguridad) VALUES ('AS73464',4,'Anticongelante','Repsol',1);

--Proveedor
INSERT INTO proveedor(id,nombre,telefono,direccion,email) VALUES (1,'Neumaticos Paco','653746489','c/Ave del Paraiso n31','neumaticospaco@gmail.com');
INSERT INTO proveedor(id,nombre,telefono,direccion,email) VALUES (2,'Recambios JOVAMA','683764346','c/esengano n21','jovama@gmail.com');

--Reparacion
INSERT INTO reparacion(duracion,precio,tipo_reparacion) VALUES (10,20.00,3);
INSERT INTO reparacion(duracion,precio,tipo_reparacion) VALUES (40,60.00,2);
INSERT INTO reparacion(duracion,precio,tipo_reparacion) VALUES (55,100.33,2);

--Revision
INSERT INTO revision(descripcion,duracion,fecha_revision) VALUES ('Necesita aceite',10,'2020-12-10');
INSERT INTO revision(descripcion,duracion,fecha_revision) VALUES ('Pastilla de freno gastada',10,'2020-12-11');
INSERT INTO revision(descripcion,duracion,fecha_revision) VALUES ('Piston atascado',20,'2020-12-11');

--Vehiculo
INSERT INTO vehiculo(matricula,tipo_vehiculo,fecha_fabricacion,kilometraje) VALUES ('4728FPG',1,'2007-10-10',175684);
INSERT INTO vehiculo(matricula,tipo_vehiculo,fecha_fabricacion,kilometraje) VALUES ('2968BPY',1,'2001-06-04',230856);
INSERT INTO vehiculo(matricula,tipo_vehiculo,fecha_fabricacion,kilometraje) VALUES ('6576JDJ',3,'2015-03-23',102302);
INSERT INTO vehiculo(matricula,tipo_vehiculo,fecha_fabricacion,kilometraje) VALUES ('E8942JPF',2,'2016-05-28',5023);
