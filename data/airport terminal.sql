create table airport_terminal(
	id serial primary key,
	name varchar(255),
	floor int
);

create table passengers(
	id serial primary key,
	name text,
	passport_number int,
	gate int references airport_terminal(id)
);

insert into airport_terminal(name, floor) values('A', 1), ('B', 2), ('C', 1);
insert into passengers(name, passport_number, gate) values ('Ivan', 11111, 1), ('Boris', 222222, 2), ('Dima', 333333, 3);

select passengers.name, passengers.Passport_number, airport_terminal.name, airport_terminal.floor
from passengers
join airport_terminal
on passengers.gate = airport_terminal.id;

select p.name, p.passport_number, a.name, a.floor 
from passengers p
join airport_terminal a
on p.gate = a.id;

select p.name as "Имя пассажира", p.passport_number as "Номер паспорта", a.name as Gate, a.floor as Этаж
from passengers as p
join airport_terminal as a
on p.gate = a.id;