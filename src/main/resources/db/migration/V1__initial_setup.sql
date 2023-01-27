drop table if exists HEROES;

create table HEROES(id bigint primary key AUTO_INCREMENT, name varchar(255) not null);

INSERT INTO HEROES (name) values ('Spider-Man');
INSERT INTO HEROES (name) values ('Superman');
INSERT INTO HEROES (name) values ('Batman');
INSERT INTO HEROES (name) values ('Ironman');
INSERT INTO HEROES (name) values ('Robin');
INSERT INTO HEROES (name) values ('Wonder Woman');
