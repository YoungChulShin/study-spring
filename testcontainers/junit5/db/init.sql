create table student
(
    id   bigint  not null auto_increment,
    age  integer not null,
    name varchar(255),
    primary key (id)
) engine=InnoDB