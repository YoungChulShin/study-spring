CREATE TABLE IF NOT EXISTS users
(
    id       bigint not null auto_increment primary key,
    name     varchar(255),
    surname  varchar(255),
    email    varchar(255),
    username varchar(255),
    password varchar(255)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;