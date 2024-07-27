create table members(
	id serial primary key,
	name text
);

create table course_abonement(
	id serial primary key,
	abonement_number int
);

create table fitness_centre(
	id serial primary key,
	member_id int references members(id),
	abonement_id int references course_abonement(id)
);
