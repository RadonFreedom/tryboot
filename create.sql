create database account_service;
create database auth_service;
create database order_service;

# use account_service;
# create table user
# (
#     id          int(11)     NOT NULL AUTO_INCREMENT,
#     account     varchar(50) NOT NULL unique,
#     password    varchar(50) NOT NULL,
#     name        varchar(50) NOT NULL,
#     create_time char(19)    not null,
#     PRIMARY KEY (id)
# ) ENGINE = InnoDB;

use auth_service;
create table user
(
    id          bigint        NOT NULL AUTO_INCREMENT,
    username    varchar(50) NOT NULL unique,
    password    varchar(50) NOT NULL,
    email       varchar(50) NOT NULL,
    create_time char(19)    not null,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

drop table user;
