create table if not exists variables
(
    id                       bigserial primary key,
    key                      varchar,
    value                    varchar,
    simple_date              varchar,
    created                  varchar
);

create table if not exists variable_history
(
    id                       bigserial primary key,
    key                      varchar,
    value                    varchar,
    simple_date              varchar,
    created                  varchar
);
