CREATE TABLE reservas(
    id INT,
    fecha_entrada DATE,
    fecha_salida DATE,
    num_habitaciones INT,
    tipo_habitacion ENUM('Doble uso individual', 'Doble', 'Junior Suite', 'Suite'),
    fumador BOOLEAN,
    regimen ENUM('Alojamiento y Desayuno', 'Media Pensión', 'Pensión Completa'),
	cliente VARCHAR(9),
	PRIMARY KEY(id),
	FOREIGN KEY(cliente) REFERENCES cliente(dni))
