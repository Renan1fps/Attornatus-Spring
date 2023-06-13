create table if not exists users(
    id uuid primary key,
    name varchar(255) not null,
    birthday varchar,
    password varchar(256)
)