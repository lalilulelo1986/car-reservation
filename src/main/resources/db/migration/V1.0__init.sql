create table car (
        ID bigint,
    	MAKE VARCHAR2(32),
    	MODEL VARCHAR2(32),
    	NUMBER VARCHAR2(32),
    	active integer,
    	primary key (id)
);
create index IDX_car_NUMBER on car(NUMBER);

create table car_reservation (
    id bigint,
    car_id bigint,
    user_id bigint,
    from_time timestamp,
    to_time timestamp,
    primary key (id)
);
alter table car_reservation add constraint FK_car_reservation_car_car_id foreign key (car_id) references car;
create index IDX_car_reservation_car_id_from_time on car_reservation(car_id, from_time);
create index IDX_car_reservation_from_time_to_time on car_reservation(from_time, to_time);
create index IDX_car_reservation_user_id_from_time on car_reservation(user_id, from_time);
