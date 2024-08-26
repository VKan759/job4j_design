CREATE TABLE CUSTOMERS (
	ID SERIAL PRIMARY KEY,
	FIRST_NAME TEXT,
	LAST_NAME TEXT,
	AGE INT,
	COUNTRY TEXT
);

INSERT INTO
	customers (first_name, last_name, age, country)
VALUES
	('Ivan', 'Ivanov', '30', 'Kazakhstan'),
	('Sveta', 'Svetikova', '22', 'Russia'),
	('Lee', 'Lee', 56, 'China'),
	('Yun', 'Yun', 18, 'Korea');

SELECT
	*
FROM
	customers
WHERE
	age = (
		SELECT
			min(age)
		FROM
			customers
	);

CREATE TABLE ORDERS (
	ID SERIAL PRIMARY KEY,
	AMOUNT INT,
	CUSTOMER_ID INT REFERENCES CUSTOMERS (ID)
);

INSERT INTO
	orders (amount, customer_id)
VALUES
	(111, 1),
	(333, 3);

SELECT
	*
FROM
	customers
WHERE
	id NOT IN (
		SELECT
			customer_id
		FROM
			orders
	);