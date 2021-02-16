create table if not exists notes
(
    id                       bigserial primary key,
    key                      varchar,
    payload                  varchar,
    extra_payload            varchar,
    note_type                integer,
    removable                bool,
    need_notify              bool,
    notified                 bool,
    notify_email             varchar,
    simple_date              varchar,
    created                  varchar
);
create table if not exists notes_history
(
    id                       bigserial primary key,
    key                      varchar,
    payload                  varchar,
    extra_payload            varchar,
    note_type                integer,
    removable                bool,
    need_notify              bool,
    notified                 bool,
    notify_email             varchar,
    simple_date              varchar,
    created                  varchar
);
