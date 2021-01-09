CREATE TYPE characteristic AS enum ('memory', 'reaction', 'logic', 'calculations');
CREATE TYPE role AS enum ('USER', 'ADMIN');
CREATE TYPE status AS enum ('ACTIVE', 'BANNED');

CREATE TABLE usr
(
    id                bigserial PRIMARY KEY,
    email             varchar(256) NOT NULL UNIQUE,
    name              varchar(40)  NOT NULL,
    surname           varchar(80)  NOT NULL,
    password          varchar(256) NOT NULL,
    role              role         NOT NULL,
    status            status       NOT NULL,
    registration_date DATE         NOT NULL
);


create table test
(
    id         bigserial primary key,
    test_name  varchar(256) not null unique,
    difficulty int          not null
);

create table question
(
    id      bigserial primary key,
    content varchar(512) not null,
    test_id bigint       not null references test (id) unique
);

create table answer
(
    id          bigserial primary key,
    content     varchar(256) not null,
    correct     boolean      not null,
    question_id bigint       not null references question (id) unique
);

create table test_characteristic
(
    id             bigserial primary key,
    characteristic characteristic not null,
    test_id        bigint         not null references test (id)
);

create table test_comment
(
    id        bigserial primary key,
    content   varchar(512) not null,
    timestamp timestamp,
    test_id   bigint       not null references test (id),
    user_id   bigint       not null references usr (id)
);

create table user_test_result
(
    id      bigserial primary key,
    user_id bigint  not null references usr (id) unique,
    test_id bigint  not null references test (id) unique,
    result  integer not null
);


