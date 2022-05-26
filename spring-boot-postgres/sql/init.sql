create table users (
    id          serial          not null,
    username    varchar(50)     not null,
    constraint users_pk primary key(id)
);

create table addresses (
    id          serial          not null,
    street      varchar(400)    not null,
    number      int             not null,
    complement  varchar(100)    not null,
    state       varchar(2)      not null,
    country     varchar(100)    not null,
    user_id     serial          not null,
    constraint addresses_pk primary key(id),
    constraint users_fk foreign key(user_id) references users(id)
);