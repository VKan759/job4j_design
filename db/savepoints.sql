SELECT * FROM products;

BEGIN;

insert into products (name, producer, count, price)
VALUES ('product_5', 'producer_5', 5, 5000);
savepoint product_5;
select * from products;

insert into products (name, producer, count, price)
VALUES ('product_6', 'producer_6', 6, 6000);
savepoint product_6;
select * from products;

insert into products (name, producer, count, price)
VALUES ('product_7', 'producer_7', 7, 7000);
savepoint product_7;

insert into products (name, producer, count, price)
VALUES ('product_8', 'producer_8', 8, 8000);

rollback to product_7;
select*from products;

release savepoint product_6;
select * from products;

rollback to product_5;
select * from products;
