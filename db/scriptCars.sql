create table cars(
	id serial primary key,
	name text,
	model text
);
insert into cars (name, model) values ('Toyota', 'camry');
update cars set name = 'Mercedes', model = 'CLK';
delete from cars;
select*from cars;
