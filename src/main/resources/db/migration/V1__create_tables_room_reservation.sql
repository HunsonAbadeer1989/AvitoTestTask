drop table if exists room;
drop table if exists reservation;

create table room (
    id bigint not null auto_increment,
    description varchar(255),
    price double,
    creation_date datetime,
    primary key (id));

create table reservation (
    id bigint not null auto_increment,
    beginning datetime,
    ending datetime,
    room_id bigint,
    primary key (id));