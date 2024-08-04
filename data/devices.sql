create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into people (name)
	values ('Dima'), ('Vladimir'), ('Alexey'), ('Stas');
insert into devices(name, price) 
	values ('Samsung Galaxy S20', 10000), ('IPhone X', 15000), ('XIAOMI',4500), ('Airpods', 5000), ('LG TV', 20000);

insert into devices_people (people_id, device_id)
values (1,1), (1,3), (1,4), (1,5),
(2,2), (2,4),(2,5),
(3,3),
(4,2),(4,5);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people dp
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;


