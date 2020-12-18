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
INSERT INTO cita(id,fechaCita,horaCita) VALUES (1,'02/01/2021', '10:00');
INSERT INTO cita(id,fechaCita,horaCita) VALUES (2,'05/01/2021', '08:00');
INSERT INTO cita(id,fechaCita,horaCita) VALUES (3,'06/01/2021', '09:00');
INSERT INTO cita(id,fechaCita,horaCita) VALUES (4,'07/01/2021', '09:00');

--Estancia
INSERT INTO estancia(id,fechaEntrada,fechaSalida,duracion) VALUES (1,'02/01/2021', '04/01/2021', 4320);
INSERT INTO estancia(id,fechaEntrada,fechaSalida,duracion) VALUES (2,'05/01/2021', '05/01/2021', 200);
INSERT INTO estancia(id,fechaEntrada,fechaSalida,duracion) VALUES (3,'06/01/2021', '08/01/2021', 3300);
INSERT INTO estancia(id,fechaEntrada,fechaSalida,duracion) VALUES (4,'07/01/2021', '08/01/2021', 1200);

--Factura
INSERT INTO factura(id,descripcion,precio,tipoPago,fechaEmision,pagado) VALUES (1,'Cambio de aceite', 20.00, 'EFECTIVO', '08/01/2021', FALSE);
INSERT INTO factura(id,descripcion,precio,tipoPago,fechaEmision,pagado) VALUES (2,'Arreglo pastilla frenos', 50.00, 'EFECTIVO', '14/12/2020', TRUE);
INSERT INTO factura(id,descripcion,precio,tipoPago,fechaEmision,pagado) VALUES (3,'Arreglo piston de arranque', 100.33, 'TARJETA', '16/12/2020', FALSE);
INSERT INTO factura(id,descripcion,precio,tipoPago,fechaEmision,pagado) VALUES (4,'Cambio de neumaticos', 60.50, 'TRANSFERENCIA', '16/12/2020', FALSE);

--Pedido
INSERT INTO pedido(id,fechaEntrada,fechaEmision) VALUES (1,'10/12/2020', '14/12/2020');
INSERT INTO pedido(id,fechaEntrada,fechaEmision) VALUES (2,'10/12/2020', '15/12/2020');
INSERT INTO pedido(id,fechaEntrada,fechaEmision) VALUES (3,'13/12/2020', '16/12/2020');
INSERT INTO pedido(id,fechaEntrada,fechaEmision) VALUES (4,'13/12/2020', '16/12/2020');

--Producto
INSERT INTO producto(referencia,stock,nombre,marca,stockseguridad) VALUES ('NEU54638',10,'Neumaticos','Nexen',4);
INSERT INTO producto(referencia,stock,nombre,marca,stockseguridad) VALUES ('71632A',20,'Aceite','Castrol',10);
INSERT INTO producto(referencia,stock,nombre,marca,stockseguridad) VALUES ('AS73464',4,'Anticongelante','Repsol',1);

--Proveedor
INSERT INTO proveedor(id,nombre,telefono,direccion,email) VALUES (1,'Neumaticos Paco','653746489','c/Ave del Paraiso nº31','neumaticospaco@gmail.com');
INSERT INTO proveedor(id,nombre,telefono,direccion,email) VALUES (1,'Recambios JOVAMA','683764346','c/Desengano nº21','jovama@gmail.com');

--Reparacion
INSERT INTO reparacion(duracion,precio,tipoReparacion) VALUES (10,20.00,'RECAMBIO');
INSERT INTO reparacion(duracion,precio,tipoReparacion) VALUES (40,60.00,'MECANICA');
INSERT INTO reparacion(duracion,precio,tipoReparacion) VALUES (55,100.33,'MECANICA');

--Revision
INSERT INTO revision(descripcion,duracion,fechaRevision) VALUES ('Necesita aceite',10,'10/12/2020');
INSERT INTO revision(descripcion,duracion,fechaRevision) VALUES ('Pastilla de freno gastada',10,'11/12/2020');
INSERT INTO revision(descripcion,duracion,fechaRevision) VALUES ('Piston atascado',20,'11/12/2020');

--Vehiculo
INSERT INTO vehiculo(matricula,tipoVehiculo,fechafabricacion,kilometraje) VALUES ('4728FPG','COCHE','10/10/2007',175684;
INSERT INTO vehiculo(matricula,tipoVehiculo,fechafabricacion,kilometraje) VALUES ('2968BPY','COCHE','04/06/2001',230856);
INSERT INTO vehiculo(matricula,tipoVehiculo,fechafabricacion,kilometraje) VALUES ('6576JDJ','MOTO','23/03/2015',102302);
INSERT INTO vehiculo(matricula,tipoVehiculo,fechafabricacion,kilometraje) VALUES ('E8942JPF','TRACTOR','28/05/2016',5023);
