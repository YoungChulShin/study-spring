create table agents
(
    id                  bigint not null auto_increment primary key ,
    name                varchar(100) not null,
    age                 int not null,
    delivery_sum        int not null,
    agent_location_id   bigint not null
) engine=InnoDB;

create table agents_location
(
    id          bigint not null auto_increment primary key ,
    longitude   double null,
    latitude    double null
) engine=InnoDB;

