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

--Cita
INSERT INTO estancia(id,fechaEntrada,fechaSalida,duracion) VALUES (1,'02/01/2021', '04/01/2021', 4320);
INSERT INTO estancia(id,fechaEntrada,fechaSalida,duracion) VALUES (2,'05/01/2021', '05/01/2021', 200);
INSERT INTO estancia(id,fechaEntrada,fechaSalida,duracion) VALUES (3,'06/01/2021', '08/01/2021', 3300);
INSERT INTO estancia(id,fechaEntrada,fechaSalida,duracion) VALUES (4,'07/01/2021', '08/01/2021', 1200);

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

--INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
--INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
--INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
--INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO users(username, password, enabled) VALUES ('mecanico1', '1234', TRUE);
INSERT INTO authorities(id, username, authority) VALUES (13, 'mecanico1', 'mecanico');
INSERT INTO mecanico VALUES(1,'Juan','Pérez','788624578K','juanperez9@gmail.com','644895623','mecanico1')

