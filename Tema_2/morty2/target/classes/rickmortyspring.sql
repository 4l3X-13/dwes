DROP DATABASE IF EXISTS rick_morty_spring;
CREATE DATABASE rick_morty_spring;
\c rick_morty_spring;

CREATE TYPE gender_enum AS ENUM ('Male', 'Female', 'Genderless', 'unknown');


CREATE TABLE locations (
    id SERIAL PRIMARY KEY,
    location_name VARCHAR(255) NOT NULL,   
    location_type VARCHAR(255),            
    dimension_name VARCHAR(255)            
);

CREATE TABLE episodes (
    id SERIAL PRIMARY KEY,
    episode_name VARCHAR(255) NOT NULL,    
    air_date DATE,
    episode_code VARCHAR(20)
);

CREATE TABLE characters (
    id SERIAL PRIMARY KEY,
    character_name VARCHAR(255) NOT NULL,  
    status VARCHAR(50),
    species VARCHAR(100),
    character_type VARCHAR(100) DEFAULT 'Common', 
    gender gender_enum,
    location_id INTEGER REFERENCES locations(id),
    image_url VARCHAR(255),                
    weight FLOAT,
    height FLOAT
);

CREATE TABLE character_episode (
    character_id INTEGER REFERENCES characters(id),
    episode_id INTEGER REFERENCES episodes(id),
    PRIMARY KEY (character_id, episode_id)
);

INSERT INTO locations (location_name, location_type, dimension_name) VALUES
('Earth (C-137)', 'Planet', 'Dimension C-137'),
('Abadango', 'Cluster', 'unknown'),
('Citadel of Ricks', 'Space station', 'unknown'),
('Interdimensional Cable', 'Dimension', 'unknown'),
('Earth (Replacement Dimension)', 'Planet', 'Replacement Dimension'),
('Post-Apocalyptic Earth', 'Planet', 'Post-Apocalyptic Dimension'),
('Purge Planet', 'Planet', 'Replacement Dimension'),
('Venzenulon 7', 'Planet', 'unknown'),
('Bepis 9', 'Planet', 'unknown'),
('Cronenberg Earth', 'Planet', 'Cronenberg Dimension'),
('Nuptia 4', 'Planet', 'unknown'),
('Testicle Monster Dimension', 'Dimension', 'Testicle Monster Dimension'),
('Anatomy Park', 'Microverse', 'Dimension C-137'),
('Immortality Field Resort', 'Resort', 'unknown'),
('Worldender''s lair', 'Planet', 'unknown'),
('Bird World', 'Planet', 'Bird Dimension'),
('Giant''s Town', 'City', 'Giant Dimension'),
('Froopyland', 'Artificial Dimension', 'Froopy Dimension'),
('Gazorpazorp', 'Planet', 'unknown'),
('Pluto', 'Planet', 'Solar System'),
('Cronenberg Dimension', 'Dimension', 'unknown'),
('Krootabulon', 'Planet', 'unknown'),
('Shleemypants Planet', 'Planet', 'unknown'),
('Earth (J-19 Zeta 7)', 'Planet', 'Dimension J-19 Zeta 7'),
('Froopyland 2', 'Artificial Dimension', 'Froopy Dimension'),
('Purge Planet 2', 'Planet', 'Replacement Dimension'),
('Gazorpazorpfield', 'Planet', 'unknown'),
('Unity Planet', 'Planet', 'unknown'),
('Birdperson Homeworld', 'Planet', 'Bird Dimension'),
('Mr. Meeseeks Box', 'Artificial', 'unknown');

INSERT INTO episodes (episode_name, air_date, episode_code) VALUES
('Pilot', '2013-12-02', 'S01E01'),
('Lawnmower Dog', '2013-12-09', 'S01E02'),
('Anatomy Park', '2013-12-16', 'S01E03'),
('M. Night Shaym-Aliens!', '2013-12-23', 'S01E04'),
('Meeseeks and Destroy', '2014-01-06', 'S01E05'),
('Rick Potion #9', '2014-01-13', 'S01E06'),
('Raising Gazorpazorp', '2014-01-20', 'S01E07'),
('Rixty Minutes', '2014-01-27', 'S01E08'),
('Something Ricked This Way Comes', '2014-03-10', 'S01E09'),
('Close Rick-Counters of the Rick Kind', '2014-04-07', 'S01E10'),
('Ricksy Business', '2014-04-14', 'S01E11'),
('A Rickle in Time', '2015-07-26', 'S02E01'),
('Mortynight Run', '2015-08-02', 'S02E02'),
('Auto Erotic Assimilation', '2015-08-09', 'S02E03'),
('Total Rickall', '2015-08-16', 'S02E04'),
('Get Schwifty', '2015-08-23', 'S02E05'),
('The Ricks Must Be Crazy', '2015-08-30', 'S02E06'),
('Big Trouble in Little Sanchez', '2015-09-13', 'S02E07'),
('Interdimensional Cable 2: Tempting Fate', '2015-09-20', 'S02E08'),
('Look Who''s Purging Now', '2015-09-27', 'S02E09'),
('The Wedding Squanchers', '2015-10-04', 'S02E10'),
('The Rickshank Rickdemption', '2017-04-01', 'S03E01'),
('Rickmancing the Stone', '2017-07-30', 'S03E02'),
('Pickle Rick', '2017-08-06', 'S03E03'),
('Vindicators 3: The Return of Worldender', '2017-08-13', 'S03E04'),
('The Whirly Dirly Conspiracy', '2017-08-20', 'S03E05'),
('Rest and Ricklaxation', '2017-08-27', 'S03E06'),
('The Ricklantis Mixup', '2017-09-10', 'S03E07'),
('Morty''s Mind Blowers', '2017-09-17', 'S03E08'),
('The ABCs of Beth', '2017-09-24', 'S03E09');

INSERT INTO characters (character_name, status, species, gender, location_id, image_url, weight, height) VALUES
('Rick Sanchez', 'Alive', 'Human', 'Male', 1, 'https://rickandmortyapi.com/api/character/avatar/1.jpeg', 70.5, 1.72),
('Morty Smith', 'Alive', 'Human', 'Male', 1, 'https://rickandmortyapi.com/api/character/avatar/2.jpeg', 60.0, 1.62),
('Summer Smith', 'Alive', 'Human', 'Female', 1, 'https://rickandmortyapi.com/api/character/avatar/3.jpeg', 52.0, 1.58),
('Beth Smith', 'Alive', 'Human', 'Female', 1, 'https://rickandmortyapi.com/api/character/avatar/4.jpeg', 55.0, 1.65),
('Jerry Smith', 'Alive', 'Human', 'Male', 1, 'https://rickandmortyapi.com/api/character/avatar/5.jpeg', 78.0, 1.75),
('Abadango Cluster Princess', 'Alive', 'Alien', 'Female', 2, 'https://rickandmortyapi.com/api/character/avatar/6.jpeg', 65.0, 1.70),
('Abradolf Lincler', 'unknown', 'Human', 'Male', 3, 'https://rickandmortyapi.com/api/character/avatar/7.jpeg', 80.0, 1.80),
('Adjudicator Rick', 'Dead', 'Human', 'Male', 3, 'https://rickandmortyapi.com/api/character/avatar/8.jpeg', 72.0, 1.75),
('Agency Director', 'Dead', 'Human', 'Male', 3, 'https://rickandmortyapi.com/api/character/avatar/9.jpeg', 68.0, 1.70),
('Alan Rails', 'Dead', 'Human', 'Male', 3, 'https://rickandmortyapi.com/api/character/avatar/10.jpeg', 75.0, 1.78),
('Albert Einstein', 'Dead', 'Human', 'Male', 3, 'https://rickandmortyapi.com/api/character/avatar/11.jpeg', 70.0, 1.72),
('Alexander', 'Alive', 'Human', 'Male', 3, 'https://rickandmortyapi.com/api/character/avatar/12.jpeg', 65.0, 1.68),
('Alien Googah', 'unknown', 'Alien', 'unknown', 4, 'https://rickandmortyapi.com/api/character/avatar/13.jpeg', 90.0, 2.10),
('Alien Morty', 'unknown', 'Alien', 'Male', 4, 'https://rickandmortyapi.com/api/character/avatar/14.jpeg', 60.0, 1.65),
('Alien Rick', 'unknown', 'Alien', 'Male', 4, 'https://rickandmortyapi.com/api/character/avatar/15.jpeg', 70.0, 1.72),
('Amish Cyborg', 'Dead', 'Alien', 'Male', 5, 'https://rickandmortyapi.com/api/character/avatar/16.jpeg', 85.0, 1.90),
('Annie', 'Alive', 'Human', 'Female', 5, 'https://rickandmortyapi.com/api/character/avatar/17.jpeg', 50.0, 1.60),
('Antenna Morty', 'Alive', 'Human', 'Male', 6, 'https://rickandmortyapi.com/api/character/avatar/18.jpeg', 58.0, 1.60),
('Antenna Rick', 'Alive', 'Human', 'Male', 6, 'https://rickandmortyapi.com/api/character/avatar/19.jpeg', 68.0, 1.70),
('Ants in my Eyes Johnson', 'unknown', 'Human', 'Male', 7, 'https://rickandmortyapi.com/api/character/avatar/20.jpeg', 72.0, 1.73),
('Aqua Morty', 'unknown', 'Humanoid', 'Male', 8, 'https://rickandmortyapi.com/api/character/avatar/21.jpeg', 60.0, 1.64),
('Aqua Rick', 'unknown', 'Humanoid', 'Male', 8, 'https://rickandmortyapi.com/api/character/avatar/22.jpeg', 70.0, 1.75),
('Arcade Alien', 'Alive', 'Alien', 'Male', 9, 'https://rickandmortyapi.com/api/character/avatar/23.jpeg', 95.0, 2.05),
('Armagheadon', 'Dead', 'Alien', 'Male', 9, 'https://rickandmortyapi.com/api/character/avatar/24.jpeg', 120.0, 2.50),
('Arthricia', 'Alive', 'Alien', 'Female', 10, 'https://rickandmortyapi.com/api/character/avatar/25.jpeg', 68.0, 1.68),
('Attila Starwar', 'Dead', 'Alien', 'Male', 10, 'https://rickandmortyapi.com/api/character/avatar/26.jpeg', 80.0, 1.85),
('Antenna Baby', 'Alive', 'Alien', 'Male', 11, 'https://rickandmortyapi.com/api/character/avatar/27.jpeg', 15.0, 0.50),
('Baby Legs', 'Alive', 'Alien', 'Female', 12, 'https://rickandmortyapi.com/api/character/avatar/28.jpeg', 25.0, 0.60),
('Bearded Lady', 'Alive', 'Human', 'Female', 13, 'https://rickandmortyapi.com/api/character/avatar/29.jpeg', 58.0, 1.65),
('Beebo', 'Alive', 'Alien', 'Male', 14, 'https://rickandmortyapi.com/api/character/avatar/30.jpeg', 150.0, 2.50);

INSERT INTO character_episode (character_id, episode_id) VALUES
(1, 1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),
(2, 1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),
(3, 1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),(3,8),(3,9),(3,10),
(4, 1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),(4,8),(4,9),(4,10),
(5, 1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(5,8),(5,9),(5,10),
(6, 2),(6,4),(6,6),(6,8),(6,10),(6,12),(6,14),(6,16),(6,18),(6,20),
(7, 3),(7,6),(7,9),(7,12),(7,15),(7,18),(7,21),(7,24),(7,27),(7,30),
(8, 1),(8,4),(8,7),(8,10),(8,13),(8,16),(8,19),(8,22),(8,25),(8,28),
(9, 2),(9,5),(9,8),(9,11),(9,14),(9,17),(9,20),(9,23),(9,26),(9,29),
(10,1),(10,3),(10,5),(10,7),(10,9),(10,11),(10,13),(10,15),(10,17),(10,19),
(11,2),(11,4),(11,6),(11,8),(11,10),(11,12),(11,14),(11,16),(11,18),(11,20),
(12,1),(12,3),(12,5),(12,7),(12,9),(12,11),(12,13),(12,15),(12,17),(12,19),
(13,2),(13,4),(13,6),(13,8),(13,10),(13,12),(13,14),(13,16),(13,18),(13,20),
(14,1),(14,5),(14,9),(14,13),(14,17),(14,21),(14,25),(14,29),
(15,2),(15,6),(15,10),(15,14),(15,18),(15,22),(15,26),(15,30),
(16,3),(16,7),(16,11),(16,15),(16,19),(16,23),(16,27),
(17,4),(17,8),(17,12),(17,16),(17,20),(17,24),(17,28),
(18,5),(18,9),(18,13),(18,17),(18,21),(18,25),(18,29),
(19,1),(19,6),(19,11),(19,16),(19,21),(19,26),
(20,2),(20,7),(20,12),(20,17),(20,22),(20,27),
(21,3),(21,8),(21,13),(21,18),(21,23),(21,28),
(22,4),(22,9),(22,14),(22,19),(22,24),(22,29),
(23,5),(23,10),(23,15),(23,20),(23,25),(23,30),
(24,1),(24,6),(24,11),(24,16),(24,21),(24,26),
(25,2),(25,7),(25,12),(25,17),(25,22),(25,27),
(26,3),(26,8),(26,13),(26,18),(26,23),(26,28),
(27,4),(27,9),(27,14),(27,19),(27,24),(27,29),
(28,5),(28,10),(28,15),(28,20),(28,25),(28,30),
(29,1),(29,6),(29,11),(29,16),(29,21),(29,26),
(30,2),(30,7),(30,12),(30,17),(30,22),(30,27);

