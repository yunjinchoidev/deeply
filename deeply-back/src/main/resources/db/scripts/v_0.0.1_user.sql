# liquibase formatted sql
# changeset minisha:v1

create table if not exists users (
id bigint not null AUTO_INCREMENT,
first_name varchar(255),
last_name varchar(255),
mobile_number integer not null,
primary key (id)) engine=MyISAM;


