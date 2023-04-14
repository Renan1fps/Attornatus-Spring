create table if not exists address(
    id uuid primary key,
       cep varchar(255) not null,
       number int not null,
       street varchar(255) not null,
       city varchar(255) not null,
       is_main boolean,
       id_user uuid not null
)