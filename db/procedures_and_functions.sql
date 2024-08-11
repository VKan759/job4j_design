call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_2', 'producer_2', 5, 100);
call insert_data('product_3', 'producer_3', 8, 115);
call insert_data('product_4', 'producer_4', 9, 530);
call insert_data('product_5', 'producer_5', 2, 120);
call insert_data('product_6', 'producer_6', 1, 15);

CREATE OR replace PROCEDURE delete_p(id_v INTEGER)
LANGUAGE plpgsql
as 
$$
begin
delete from products where id = id_v;
end;
$$;

call delete_p(1);

CREATE OR replace function f_delete_elements_less_than(count_v integer)
returns integer 
LANGUAGE plpgsql
as 
$$
declare 
result integer;
begin 
select count(*) into result from products  where count < count_v;
delete from products where count < count_v;
return result;
end;
$$;

SELECT f_delete_elements_less_than(8);

