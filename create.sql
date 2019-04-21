create database auth_service;
use auth_service;
create table user
(
    id          bigint        NOT NULL AUTO_INCREMENT,
    username    varchar(50) NOT NULL unique,
    password    varchar(60) NOT NULL,
    email       varchar(50) NOT NULL,
    create_time char(19)    not null,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

# create database order_service;