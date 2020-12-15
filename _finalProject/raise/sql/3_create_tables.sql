
create type characteristic as enum('memory', 'reaction', 'logic', 'calculations');
create type role as enum('user', 'admin');
create type status as enum('active', 'banned');

create table usr (
	id bigserial not null primary key,
	email varchar(256) not null unique,
	name varchar(40) not null,
	surname varchar(80) not null,
	password varchar(256) not null,
	role role not null,
	status status not null,
	registration_date date not null,
	characteristic_id bigint not null references characteristic(id) unique
) ;


create table test (
	id bigserial primary key,
	test_name varchar(256) not null unique,
	difficulty int not null
) ;

create table question (
	id bigserial primary key,
	content varchar(512) not null,
	test_id bigint not null references test(id) unique
) ;

create table answer (
	id bigserial primary key,
	content varchar(256) not null,
	correct boolean not null,
	question_id bigint not null references question(id) unique
) ;

create table test_characteristic (
	id bigserial primary key,
	characteristic characteristic not null,
	test_id bigint not null references test(id)
) ;

create table test_comment (
	id bigserial primary key,
	content varchar(512) not null,
	timestamp timestamp,
	test_id bigint not null references test(id),
	user_id bigint not null references usr(id)
) ;

create table user_test_result (
    user_id bigint not null references usr(id),
    test_id bigint not null references test(id),
    result integer not null,
    PRIMARY KEY (user_id, test_id)
);


