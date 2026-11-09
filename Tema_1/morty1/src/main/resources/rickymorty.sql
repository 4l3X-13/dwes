-- Crear base de datos
CREATE DATABASE rick_morty;

-- Conectarse a la base de datos
\c rick_morty;

-- Tabla personajes
CREATE TABLE personajes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    especie VARCHAR(50),
    dimension VARCHAR(20) DEFAULT 'C-137',
    habilidad_especial VARCHAR(100),
    genero VARCHAR(10),
    nivel_inteligencia INT CHECK (nivel_inteligencia BETWEEN 1 AND 100),
    afiliacion VARCHAR(50)
);

-- Tabla planetas
CREATE TABLE planetas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    tipo VARCHAR(30),
    habitantes_estimados INT CHECK (habitantes_estimados >= 0),
    clima VARCHAR(50),
    nivel_tecnologico INT CHECK (nivel_tecnologico BETWEEN 1 AND 10),
    afiliacion_gobierno VARCHAR(50)
);

-- Tabla aventuras
CREATE TABLE aventuras (
    id SERIAL PRIMARY KEY,
    descripcion TEXT NOT NULL,
    fecha DATE NOT NULL,
    planeta_id INT NOT NULL,
    personaje_id INT NOT NULL,
    FOREIGN KEY (planeta_id) REFERENCES planetas(id) ON DELETE CASCADE,
    FOREIGN KEY (personaje_id) REFERENCES personajes(id) ON DELETE CASCADE
);

-- Insertar personajes
INSERT INTO personajes (nombre, especie, dimension, habilidad_especial, genero, nivel_inteligencia, afiliacion) VALUES
('Rick Sanchez','Humano','C-137','Portal Gun','Masculino',100,'C-137'),
('Morty Smith','Humano','C-137','Valentía','Masculino',70,'C-137'),
('Summer Smith','Humano','C-137','Ingenio','Femenino',85,'C-137'),
('Beth Smith','Humano','C-137','Medicina avanzada','Femenino',90,'C-137'),
('Jerry Smith','Humano','C-137','Negociación','Masculino',40,'C-137'),
('Mr. Meeseeks','Meeseeks','??','Cumplir objetivos','Masculino',60,'Independiente'),
('Birdperson','Alien','Bird Dimension','Liderazgo','Masculino',80,'Alianza Bird'),
('Squanchy','Alien','Squanch Dimension','Fuerza y fiesta','Masculino',75,'Squanch Clan'),
('Abradolf Lincler','Humano-Clone','C-137','Ingeniería genética','Masculino',85,'C-137'),
('Unity','Alien','Multiple','Control mental','Femenino',95,'Multiverso');

-- Insertar planetas
INSERT INTO planetas (nombre, tipo, habitantes_estimados, clima, nivel_tecnologico, afiliacion_gobierno) VALUES
('Earth','Rocoso',8000000,'Templado',8,'Independiente'),
('Gazorpazorp','Alienígena',10000000,'Árido',7,'Federación Galáctica'),
('Cronenberg World','Mutante',5000000,'Variable',6,'Anarquía'),
('Planet Squanch','Alienígena',2000000,'Multiclima',6,'Squanch Clan'),
('Froopyland','Mágico',100000,'Colorido y extraño',5,'Independiente'),
('Interdimensional Customs','Administrativo',5000,'Controlado',9,'Federación Galáctica'),
('Blips and Chitz','Entretenimiento',20000,'Artificial',7,'Corporativo'),
('Unity Planet','Alienígena',8000000,'Variable',10,'Multiverso'),
('Anatomy Park','Miniatura',500,'Artificial',5,'Rick Labs'),
('Bird World','Alienígena',300000,'Helado y rocoso',7,'Alianza Bird');

-- Insertar 50 aventuras
INSERT INTO aventuras (descripcion, fecha, planeta_id, personaje_id) VALUES
('Escaparon de los Gazorpazorpianos','2020-05-12',2,1),
('Combatieron con versiones Cronenberg','2020-06-05',3,2),
('Visitaron Planet Squanch y conocieron squanches','2020-07-10',4,3),
('Rick construyó un portal para escapar de la federación galáctica','2020-08-01',1,1),
('Mr. Meeseeks cumple su objetivo de hacer que Jerry haga ejercicio','2020-08-15',1,6),
('Jerry fue atrapado por un sistema interdimensional','2020-09-01',6,5),
('Rick y Morty jugaron en Blips and Chitz','2020-09-15',7,1),
('Morty se perdió en Froopyland','2020-10-01',5,2),
('Rick intentó convencer a Unity de unirse a su plan','2020-10-15',8,1),
('Birdperson luchó contra enemigos galácticos','2020-11-01',10,7),
('Squanchy celebró su cumpleaños con Rick y Morty','2020-11-10',8,8),
('Abradolf Lincler escapó del laboratorio de Rick','2020-11-20',3,9),
('Summer se perdió en Blips and Chitz','2020-12-01',7,3),
('Morty y Rick rescataron a Jerry de la federación galáctica','2020-12-10',1,2),
('Rick destruye planetas en miniatura','2021-01-05',9,1),
('Beth descubre secretos del laboratorio','2021-01-10',1,4),
('Jerry se pierde en Planet Squanch','2021-01-15',4,5),
('Morty pelea contra criaturas mutantes','2021-02-01',3,2),
('Rick y Birdperson luchan juntos','2021-02-10',10,1),
('Summer y Morty exploran Froopyland','2021-02-20',5,3),
('Rick viaja a Cronenberg World','2021-03-01',3,1),
('Unity absorbe un planeta entero','2021-03-05',8,10),
('Mr. Meeseeks ayuda a Summer con la escuela','2021-03-10',1,6),
('Squanchy y Rick organizan fiesta alien','2021-03-15',8,8),
('Morty y Rick viajan a Anatomy Park','2021-03-20',9,2),
('Rick construye gadget interdimensional','2021-04-01',1,1),
('Beth investiga anomalías en Earth','2021-04-05',1,4),
('Jerry queda atrapado en Froopyland','2021-04-10',5,5),
('Morty sobrevive a Cronenberg World','2021-04-15',3,2),
('Summer es rescatada por Rick','2021-04-20',1,3),
('Birdperson enfrenta batalla alienígena','2021-05-01',10,7),
('Rick crea portal a Bird World','2021-05-05',10,1),
('Mr. Meeseeks ayuda Rick a construir máquina','2021-05-10',1,6),
('Morty explora Blips and Chitz','2021-05-15',7,2),
('Summer participa en torneo Blips and Chitz','2021-05-20',7,3),
('Rick roba recursos de Gazorpazorp','2021-06-01',2,1),
('Beth se une a misión espacial','2021-06-05',1,4),
('Squanchy pelea contra invasores','2021-06-10',8,8),
('Jerry descubre nueva dimensión','2021-06-15',6,5),
('Morty y Rick evitan desastre interdimensional','2021-06-20',1,2),
('Rick y Morty viajan a Planet Squanch','2021-07-01',4,1),
('Summer y Morty enfrentan criaturas de Froopyland','2021-07-05',5,3),
('Birdperson lidera ejército alienígena','2021-07-10',10,7),
('Unity toma control de planeta alien','2021-07-15',8,10),
('Rick destruye laboratorio rival','2021-07-20',1,1),
('Morty sobrevive a secuestro alien','2021-07-25',2,2),
('Summer ayuda a Rick en misión','2021-07-30',1,3),
('Jerry intenta negociar con alienígenas','2021-08-01',6,5),
('Mr. Meeseeks cumple objetivos difíciles','2021-08-05',1,6),
('Rick y Morty visitan Anatomy Park','2021-08-10',9,1),
('Beth ayuda a Rick a reparar portal','2021-08-15',1,4),
('Squanchy explora Blips and Chitz','2021-08-20',7,8);
