create table departments(
	id serial primary key,
	department text
);

create table employees(
	id serial primary key,
	employee text,
	department_id int references departments(id)
);

insert into departments (department)
values ('department1'), ('department2'), ('department3'), ('department4');


insert into employees (employee, department_id)
values ('Dima', 1), ('Stas', 2), ('Anton', 3), ('Lena', null), ('Timur', 2);

select * from employees e
left join departments d on e.department_id = d.id; 

select * from departments d 
right join employees e on d.id = e.department_id; 

select * from departments d 
full join employees e on d.id = e.department_id;

select * from departments
cross join employees;

select * from departments d
left join employees e on d.id = e.department_id
	where employee is null;

select * from departments d
left join employees e on d.id = e.department_id
	order by department;

select d.id, d.department, e.id, e.employee, e.department_id from employees e
right join departments d on d.id = e.department_id
	order by department;




