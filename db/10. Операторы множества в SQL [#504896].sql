CREATE TABLE MOVIE (ID SERIAL PRIMARY KEY, NAME TEXT, DIRECTOR TEXT);

CREATE TABLE BOOK (ID SERIAL PRIMARY KEY, TITLE TEXT, AUTHOR TEXT);

INSERT INTO
	MOVIE (NAME, DIRECTOR)
VALUES
	('Марсианин', 'Ридли Скотт'),
	('Матрица', 'Братья Вачовски'),
	('Властелин колец', 'Питер Джексон'),
	(
		'Гарри Поттер и узник Азкабана',
		'Альфонсо Куарон'
	),
	('Железный человек', 'Джон Фавро');

INSERT INTO
	BOOK (TITLE, AUTHOR)
VALUES
	('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
	('Властелин колец', 'Джон Толкин'),
	('1984', 'Джордж Оруэлл'),
	('Марсианин', 'Энди Уир'),
	('Божественная комедия', 'Данте Алигьери');

SELECT
	NAME
FROM
	MOVIE
INTERSECT
SELECT
	TITLE
FROM
	BOOK;

SELECT
	TITLE
FROM
	BOOK
EXCEPT
SELECT
	NAME
FROM
	MOVIE;

(
	SELECT
		NAME
	FROM
		MOVIE
	EXCEPT
	SELECT
		TITLE
	FROM
		BOOK
)
UNION
(
	SELECT
		TITLE
	FROM
		BOOK
	EXCEPT
	SELECT
		NAME
	FROM
		MOVIE
)
ORDER BY
	NAME;