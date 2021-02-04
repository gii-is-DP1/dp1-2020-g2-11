-- Creamos un usuario para administrador
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- Creamos un usuario para mecanico
INSERT INTO users(username,password,enabled) VALUES ('mecanico1','m3c4nico',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'mecanico1','mecanico1');

INSERT INTO users(username,password,enabled) VALUES ('mecanico2','m3c4nico',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'mecanico2','mecanico2');

INSERT INTO users(username,password,enabled) VALUES ('mecanico3','m3c4nico',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'mecanico3','mecanico3');

-- Creamos un usuario para cliente
INSERT INTO users(username,password,enabled) VALUES ('cliente1','cl13nte',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'cliente1','cliente1');

INSERT INTO users(username,password,enabled) VALUES ('cliente2','cl13nte',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'cliente2','cliente2');

INSERT INTO users(username,password,enabled) VALUES ('cliente3','cl13nte',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'cliente3','cliente3');

INSERT INTO users(username,password,enabled) VALUES ('cliente4','cl13nte',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'cliente4','cliente4');

--Administrador
INSERT INTO administrador(id,nombre,apellidos,dni,telefono,email,username) VALUES (1, 'Eugenio', 'Vicente Bravo', '13627495L', '645329832','adminTaLaPlata@gmail.com','admin1');

--Mecanicos
INSERT INTO mecanico(id,nombre,apellidos,dni,telefono,email,username) VALUES (1, 'Juan', 'Perez Barea', '45637892P', '654738291','juanpeba@gmail.com','mecanico1');
INSERT INTO mecanico(id,nombre,apellidos,dni,telefono,email,username) VALUES (2, 'Pepe', 'Lopez Vazquez', '17483965D', '633846783','pepelova@gmail.com','mecanico2');
INSERT INTO mecanico(id,nombre,apellidos,dni,telefono,email,username) VALUES (3, 'Paco', 'Garcia Marquez', '84927546F', '717362549','pacogama@gmail.com','mecanico3');

--Clientes
INSERT INTO cliente(id,nombre,apellidos,dni,telefono,email,username) VALUES (1, 'Manuel', 'Viera Rodriguez', '62748364G', '633572849','manuvierod@gmail.com','cliente1');
INSERT INTO cliente(id,nombre,apellidos,dni,telefono,email,username) VALUES (2, 'Manuel', 'Dominguez Rodriguez', '35462748F', '667483749','fradomrod@gmail.com','cliente2');
INSERT INTO cliente(id,nombre,apellidos,dni,telefono,email,username) VALUES (3, 'Julian', 'Mota Fernandez', '28463574T', '715635427','julitomotores@gmail.com','cliente3');
INSERT INTO cliente(id,nombre,apellidos,dni,telefono,email,username) VALUES (4, 'Aitor', 'Tilla Montesco', '48295647P', '655367264','aitortillapacome@gmail.com','cliente4');

--Cita
INSERT INTO cita(id,fecha_cita,hora_cita) VALUES (1,'2021-01-04', '10:00');
INSERT INTO cita(id,fecha_cita,hora_cita) VALUES (2,'2021-01-04', '08:00');
INSERT INTO cita(id,fecha_cita,hora_cita) VALUES (3,'2021-01-04', '09:00');
INSERT INTO cita(id,fecha_cita,hora_cita) VALUES (4,'2021-01-04', '09:00');

--Vehiculo
INSERT INTO vehiculo(id,matricula,tipo_vehiculo,fecha_fabricacion,kilometraje,cliente_id) VALUES (1,'4728FPG',0,'2007-10-10',175684,1);
INSERT INTO vehiculo(id,matricula,tipo_vehiculo,fecha_fabricacion,kilometraje,cliente_id) VALUES (2,'2968BPY',0,'2001-06-04',230856,1);
INSERT INTO vehiculo(id,matricula,tipo_vehiculo,fecha_fabricacion,kilometraje,cliente_id) VALUES (3,'6576JDJ',2,'2015-03-23',102302,2);
INSERT INTO vehiculo(id,matricula,tipo_vehiculo,fecha_fabricacion,kilometraje,cliente_id) VALUES (4,'E8942JPF',1,'2016-05-28',5023,3);

--Estancia
INSERT INTO estancia(id,fecha_entrada,hora_entrada,fecha_salida,hora_salida,duracion,vehiculo_id) VALUES (1,'2021-01-02','10:00', '2021-01-04','12:00', 4320, 1);
INSERT INTO estancia(id,fecha_entrada,hora_entrada,fecha_salida,hora_salida,duracion,vehiculo_id) VALUES (2,'2021-01-05','10:00',  '2021-01-05','12:00', 200, 2);
INSERT INTO estancia(id,fecha_entrada,hora_entrada,fecha_salida,hora_salida,duracion,vehiculo_id) VALUES (3,'2021-01-06','10:00',  '2021-01-08','12:00', 3300, 3);
INSERT INTO estancia(id,fecha_entrada,hora_entrada,fecha_salida,hora_salida,duracion,vehiculo_id) VALUES (4,'2021-01-07','12:00',  '2021-01-08','13:00', 1200, 4);

--Factura
INSERT INTO factura(id,descripcion,precio,tipo_pago,fecha_emision,pagado,cliente_id) VALUES (1,'Cambio de aceite', 20.00, 1, '2021-01-08', FALSE,1);
INSERT INTO factura(id,descripcion,precio,tipo_pago,fecha_emision,pagado,cliente_id) VALUES (2,'Arreglo pastilla frenos', 50.00, 1, '2021-12-14', TRUE,2);
INSERT INTO factura(id,descripcion,precio,tipo_pago,fecha_emision,pagado,cliente_id) VALUES (3,'Arreglo piston de arranque', 100.33, 2, '2020-12-16', FALSE,3);
INSERT INTO factura(id,descripcion,precio,tipo_pago,fecha_emision,pagado,cliente_id) VALUES (4,'Cambio de neumaticos', 60.50, 0, '2020-12-16', FALSE,4);

--Pedido
INSERT INTO pedido(id,fecha_entrada,fecha_emision) VALUES (1,'2020-12-10', '2020-12-14');
INSERT INTO pedido(id,fecha_entrada,fecha_emision) VALUES (2,'2020-12-10', '2020-12-15');
INSERT INTO pedido(id,fecha_entrada,fecha_emision) VALUES (3,'2020-12-13', '2020-12-16');
INSERT INTO pedido(id,fecha_entrada,fecha_emision) VALUES (4,'2020-12-13', '2020-12-16');

--Producto
INSERT INTO producto(id,referencia,stock,nombre,marca,stock_seguridad) VALUES (1,'NEU54638',10,'Neumaticos','Nexen',4);
INSERT INTO producto(id,referencia,stock,nombre,marca,stock_seguridad) VALUES (2,'71632A',20,'Aceite','Castrol',10);
INSERT INTO producto(id,referencia,stock,nombre,marca,stock_seguridad) VALUES (3,'AS73464',4,'Anticongelante','Repsol',1);

--Proveedor
INSERT INTO proveedor(id,nombre,telefono,direccion,email) VALUES (1,'Neumaticos Paco','653746489','c/Ave del Paraiso n31','neumaticospaco@gmail.com');
INSERT INTO proveedor(id,nombre,telefono,direccion,email) VALUES (2,'Recambios JOVAMA','683764346','c/esengano n21','jovama@gmail.com');

--Reparacion
INSERT INTO reparacion(id,duracion,precio,tipo_reparacion) VALUES (1,10,20.00,2);
INSERT INTO reparacion(id,duracion,precio,tipo_reparacion) VALUES (2,40,60.00,1);
INSERT INTO reparacion(id,duracion,precio,tipo_reparacion) VALUES (3,55,100.33,1);

--Revision
INSERT INTO revision(id,descripcion,duracion,fecha_revision) VALUES (1,'Necesita aceite',10,'2020-12-10');
INSERT INTO revision(id,descripcion,duracion,fecha_revision) VALUES (2,'Pastilla de freno gastada',10,'2020-12-13');
INSERT INTO revision(id,descripcion,duracion,fecha_revision) VALUES (3,'Piston atascado',20,'2020-12-11');
INSERT INTO revision(id,descripcion,duracion,fecha_revision) VALUES (4,'Piston atascado',20,'2020-12-11');


