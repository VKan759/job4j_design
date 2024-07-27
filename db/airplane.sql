create table airplane(
	id serial primary key,
	name text
);

create table passengers(
	id serial primary key,
	name text,
	airplaneNumber int references airplane(id)
);

insert into airplane (name) values ('Boeing 747');
insert into passengers (name, airplaneNumber) values ('Ivan', 1);
insert into passengers (name, airplaneNumber) values ('Kirill', 1);

select*from airplane;
select*from passengers;
select*from airplane where id in (select airplaneNumber from passengers);

