drop table if exists room;
drop table if exists reservation;

create table room (
    id bigint not null auto_increment,
    description varchar(255),
    price double,
    date_of_creation datetime,
    primary key (id));

create table booking (
    id bigint primary key not null auto_increment,
    date_start datetime,
    date_end datetime,
    room_id bigint,
    foreign key (room_id) references room (id) on update cascade on delete cascade);

