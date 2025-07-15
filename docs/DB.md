# db setup

create table user_details (
id integer,
user_name varchar(250) unique,
password varchar(250)

);
insert into user_details values(1,'test77','12345');

create table authority (id integer, name varchar(100) unique);

insert into authority values(2,'admin');

create table user_authority(id integer,
user_name varchar(250) ,
name varchar(100)
);

insert into user_authority values(1,'test77','admin');


