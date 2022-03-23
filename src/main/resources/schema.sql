create schema myschema;

create table customers
(
    id           int primary key auto_increment,
    name         varchar(255) not null,
    surname      varchar(255) not null,
    age          int check (age >= 0),
    phone_number varchar(255) not null default 'unknown'
);

create table orders
(
    id           int primary key auto_increment,
    data         timestamp    not null default now(),
    customer_id  int          not null,
    product_name varchar(255) not null default 'unknown',
    amount       int check (amount > 0),
    foreign key (customer_id) references customers (id)
);