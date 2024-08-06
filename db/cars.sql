create table car_bodies(
	id serial primary key,
	name text
);

create table car_engines(
	id serial primary key,
	name_engine text
);

create table car_transmissions (
	id serial primary key,
	name_transmission text
);

create table cars(
	id serial primary key,
	car_name text,
	car_body int references car_bodies(id),
	car_engine int references car_engines(id),
	car_transmission int references car_transmissions(id)
);

insert into car_bodies (name)
values ('Хэтчбэк'), ('Седан'), ('Пикап');

insert into car_engines (name_engine)
values ('V6'), ('V8'), ('V12');

insert into car_transmissions (name_transmission)
values ('Трансмиссии с механической КПП'), ('Трансмиссии с АКПП'), ('Трансмиссии с роботизированной КПП');

insert into cars (car_name, car_body, car_engine, car_transmission)
values ('Mercedes',2,1,2), ('Renault', 1, 1, 1), ('Lada', 1, null, 1);


select * from car_bodies cb
left join cars c on cb.id = c.car_body
	where c.car_body is null;

select * from car_engines ce
left join cars c on ce.id = c.car_engine
where c.car_engine is null;

select * from car_transmissions ct
left join cars c on ct.id = c.car_transmission
where car_transmission is null;










