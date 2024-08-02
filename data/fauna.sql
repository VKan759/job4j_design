insert into fauna (name, avg_age, discovery_date) 
values ('tirrex', 50000, '1200-05-12');
insert into fauna(name, avg_age, discovery_date)
values('dinosaur', 500, '2-04-05');
insert into fauna(name, avg_age, discovery_date)
values('oscelotopus', 15000, '1783-09-09');
insert into fauna(name, avg_age, discovery_date)
values('swordfish', 100, '1500-02-24');
insert into fauna(name, avg_age, discovery_date)
values('piranha', 5,'1870-06-19');
insert into fauna(name, avg_age)
values ('unknown animal', 100000);
select * from fauna where name like '%fish%';
select * from fauna where avg_age <= 21000 and avg_age >= 10000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';