drop table if exists HEROES;
drop table if exists USERS;

create table HEROES(id bigint primary key AUTO_INCREMENT, name varchar(255) not null);
create table USERS(id bigint primary key AUTO_INCREMENT, username varchar(255) not null, password varchar(255) not null);

INSERT INTO HEROES (name) values ('Spider-Man');
INSERT INTO HEROES (name) values ('Superman');
INSERT INTO HEROES (name) values ('Batman');
INSERT INTO HEROES (name) values ('Ironman');
INSERT INTO HEROES (name) values ('Robin');
INSERT INTO HEROES (name) values ('Wonder Woman');

INSERT INTO USERS (username, password) values ('admin', '$2a$10$AIFesEBzZSWaU7DxbW/oyeCEUNpXs7JbnQlqh/5AcA6tuEu/4XqBq');