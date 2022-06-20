create table students
(
    id   bigint  not null auto_increment,
    age  integer not null,
    name varchar(255),
    primary key (id)
) engine=InnoDB;

create unique index unique_age_name on students (age, name);