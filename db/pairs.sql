create table teens(
	id serial primary key,
	name text,
gender boolean
);

insert into teens (name, gender) 
values ('Dima', true), ('Katya', false), ('Andrey', true), ('Alisa', false);

select t1.name, t2.name,  concat(t1.name, ' ', t2.name) as "result" 
from teens t1
cross join teens t2
	where t1.gender is true and t2.gender is false 
	and t1.gender != t2.gender; 