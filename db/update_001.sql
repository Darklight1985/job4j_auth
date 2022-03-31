create table employee (
    id serial primary key not null,
    name varchar(50),
    surname varchar(50),
    itx bigint,
    hiring timestamp
);


create table person (
                        id serial primary key not null,
                        login varchar(2000),
                        password varchar(2000),
                        empl_id int references employee(id)
);

insert into employee (name, surname, itx, hiring) values ('Vanya', 'Ivanov', 12345678, '2010-06-12 20:11');
insert into person (login, password, empl_id) values ('parsentev', '123', 1);
insert into person (login, password, empl_id) values ('ban', '123', 1);
insert into person (login, password, empl_id) values ('ivan', '123', 1);