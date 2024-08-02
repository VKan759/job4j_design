
create table roles(
	id serial primary key,		
	role text
);

create table rules(
	id serial primary key,
	rule text
);

create table role_rule(
	id serial primary key,
	role int references roles(id),
	rule int references rules(id)
);

create table users(
	id serial primary key,
	name text,
	role int references roles(id)
);

create table states(
	id serial primary key,
	state boolean
);

create table categories(
	id serial primary key,
	name text
);

create table items(
	id serial primary key,
	name text,
	state int references states(id),
	category int references categories(id),
	user_item int references users(id)
);

create table comments(
	id serial primary key,
	comment text,
	item_comment int references items(id)
);

create table attachs(
	id serial primary key,
	attachment text,
	item_attachment int references items(id)
);







