CREATE TABLE accounts (
	id serial primary KEY,
	name text,
balance INT
);

INSERT INTO accounts(name, balance)
VALUES ('Andrey', 1000), ('Vova', 2000);

SELECT * FROM accounts;