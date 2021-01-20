CREATE TYPE characteristic AS enum ('MEMORY', 'REACTION', 'LOGIC', 'CALCULATIONS');
CREATE TYPE status AS ENUM ('ACTIVE', 'UNCONFIRMED', 'BANNED');
CREATE TYPE test_status AS ENUM ('NEW', 'CONFIRMED', 'BANNED');

CREATE TABLE usr
(
    id                bigserial PRIMARY KEY,
    email             varchar(256) NOT NULL UNIQUE,
    name              varchar(40)  NOT NULL,
    surname           varchar(80)  NOT NULL,
    password          varchar(64)  NOT NULL,
--     role              role         NOT NULL,
    status            STATUS       NOT NULL,
    registration_date DATE         NOT NULL
);

--
--
--
CREATE TABLE role
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE user_roles
(
    id   SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES usr(id),
    role_id BIGINT NOT NULL REFERENCES role(id)
);

CREATE TABLE role_permissions
(
    id         SERIAL PRIMARY KEY,
    role_id BIGINT NOT NULL REFERENCES role(id),
    permission VARCHAR(50) NOT NULL
);
--
--
--

create table test
(
    id         SERIAL primary key,
    author_id INTEGER not null references usr(id),
    status test_status not null,
    test_name  varchar(256) not null,
    difficulty INTEGER          not null
);

create table question
(
    id      SERIAL primary key,
    name varchar(256) not null unique,
    content varchar(512) not null,
    test_id INTEGER       not null references test (id)
);

create table answer
(
    id          SERIAL primary key,
    content     varchar(256) not null,
    correct     boolean      not null,
    question_id INTEGER       not null references question (id)
);

create table test_characteristic
(
    id             SERIAL primary key,
    characteristic characteristic not null,
    test_id        INTEGER         not null references test (id)
);

create table test_comment
(
    id        SERIAL primary key,
    content   varchar(512) not null,
    timestamp timestamp,
    test_id   INTEGER       not null references test (id),
    user_id   INTEGER       not null references usr (id)
);

create table user_test_result
(
    id      SERIAL primary key,
    user_id INTEGER  not null references usr (id) unique,
    test_id INTEGER  not null references test (id) unique,
    result  INTEGER not null
);


