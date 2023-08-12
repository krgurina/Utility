create database JDBC;
use JDBC;

create table tablet (
                          id int identity(1,1) primary key,
                          _Message nvarchar(200),
                          _Like int,
                          _Dislike int);

insert into tablet (_Message, _Like, _Dislike)
values  ('test post', 0, 0),
        ('test post2', 0, 0);