GRANT ALL PRIVILEGES ON *.* TO 'user'@'%';

DROP DATABASE IF EXISTS `school-reactive-db`;
CREATE DATABASE `school-reactive-db` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `school-reactive-db`;

DROP TABLE IF EXISTS schools;
DROP TABLE IF EXISTS students;

CREATE TABLE schools
(
    id       bigint not null auto_increment primary key,
    name     varchar(255)
    ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE students
(
    id          bigint not null auto_increment primary key,
    name        varchar(255),
    age         int,
    gender      varchar(20),
    school_id    bigint
    ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;