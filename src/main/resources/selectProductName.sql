select c.name, c.surname, o.product_name, o.amount
from customers c
         join orders o on c.id = o.customer_id
where name =