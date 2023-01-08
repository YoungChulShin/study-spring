DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id serial primary key,
    name varchar(255),
    surname varchar(255),
    username varchar(255),
    password varchar(255)
);