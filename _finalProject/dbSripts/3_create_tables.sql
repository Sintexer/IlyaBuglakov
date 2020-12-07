
create type characteristic as enum('memory', 'reaction', 'logic', 'calculations');
create type role as enum('user', 'admin');
create type status as enum('active', 'banned');

create table characteristics (
	id bigserial primary key,
	memory int not null,
	reaction int not null,
	logic int not null,
	calculations int not null
);

create table users (
	id bigserial not null primary key,
	email varchar(255) not null unique,
	name varchar(40) not null,
	surname varchar(80) not null,
	password varchar(255) not null,
	role role not null,
	status status not null,
	registration_date date not null,
	characteristic_id bigint not null references characteristics(id) unique
) ;



create table tests (
	id bigserial primary key,
	test_name varchar(255) not null unique,
	difficulty int not null
) ;

create table questions (
	id bigserial primary key,
	content varchar(511) not null,
	test_id bigint not null references tests(id) unique
) ;

create table answers (
	id bigserial primary key,
	content varchar(255) not null,
	correct boolean not null,
	question_id bigint not null references questions(id) unique
) ;

create table test_characteristics (
	id bigserial primary key,
	characteristic characteristic not null,
	test_id bigint not null references tests(id) unique
) ;

create table test_comments (
	id bigserial primary key,
	content varchar(511) not null,
	test_id bigint not null references tests(id) unique
) ;



