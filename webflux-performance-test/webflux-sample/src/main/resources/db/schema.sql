CREATE TABLE IF NOT EXISTS schools
(
    id       bigint not null auto_increment primary key,
    name     varchar(255)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS students
(
    id          bigint not null auto_increment primary key,
    name        varchar(255),
    age         int,
    gender      varchar(20),
    school_id    bigint
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;