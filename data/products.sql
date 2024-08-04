create table type(
	id serial primary key,
	type_name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type (type_name) values ('Молоко'), ('Мясные продукты'), ('Хлебобулочные изделия'), ('Напитки'), ('Сыр');
insert into product(name, type_id, expired_date, price)
		values ('Сыр сметанковый', 5, '2024-03-15', 500),
	('Сыр моцарелла', 5 , '2024-08-12', 575), 
	('Булочка с маком', 3, '2024-08-06', 15),
	('Coca-Cola', 4, '2025-01-01', 100), 
	('Колбаса', 2, '2024-10-12', 950), 
	('Ветчина', 2, '2024-09-10', 1000), 
	('Хлеб заводской', 3, '2024-08-11', 40),
	('Молоко', 1, '2024-08-29', 300),
	('Fanta', 4, '2024-06-18', 100),
	('Мороженое "Снеговик"', 1, '2024-05-09', 130),
	('Вино', 4, '2027-05-09', 1000);

select * from product p
join type t on p.type_id = t.id
where t.type_name = 'Сыр';

select * from product where expired_date < '2024-08-04';

select * from product
where price = (select max(price) from product);

select type_id, count(*)
from product
group by type_id;

select * from product p
join type t on p.type_id = t.id
where lower(t.type_name) like lower('%Сыр%') or lower(t.type_name) like lower('%Молоко%');

select * from product;

select p.type_id, t.type_name, count(*)
from product as p
join type as t
on p.type_id = t.id
group by p.type_id, t.type_name
having count(*) < 10;

select * from product p
join type t on p.type_id = t.id; 