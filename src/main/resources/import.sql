drop table if exists account;

create table account (id int primary key auto_increment, name varchar);

insert into account (name) values ('My Personal Account');
insert into account (name) values ('My secondary account');
