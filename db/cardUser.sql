create table person(
	id serial primary key,
	name text
);

create table creditCard(
	id serial primary key,
	number int,
	person_id int references person(id) unique
);

select*from person;
select*from creditCard;