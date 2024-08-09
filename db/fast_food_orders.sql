create table customers( 
id serial primary key,
name text
);

create table restaurants(
id serial primary key,
name text unique 
);

create table dishes(
id serial primary key ,
name text,  
id_restaraunt int references restaurants(id)
);


create table orders(
id serial primary key ,
customer_id int references customers(id),
dish_id int references dishes(id),
take_away_status boolean
);

insert into customers(name)
values ('Denis'), ('Vova'), ('Vlad');

insert into restaurants (name)
values ('MCDonald''s'), ('Burger King'), ('KFC');

insert into dishes(name, id_restaraunt)
values ('MC Burger', 1), ('Ice cream', 1), ('Chicken tasty', 1), ('Big tasty', 1),
('Whopper', 2), ('Crispy chicken', 2), ('Ice cream BK', 2), ('Burger combo', 2),
('Spicy wings', 3), ('Coca-Cola',3);

insert into orders (customer_id, dish_id, take_away_status)
values (1, 1, true), (1, 2, true), (1, 3, true), (1, 4, true),
(2, 1, true ), (2, 4, true), (2, 5, false), (2,6, true ),
(3, 9, false ), (3, 10, false );

create view fast_food_orders as
select c."name" as cust_name, r.name as rest_name, count(r.name) 
from customers c
join orders o on o.customer_id = c."id"
join dishes d on o.dish_id = d."id"
join restaurants r on d.id_restaraunt = r."id"
group by c."name", r.name
having count(r."name") > 3;
