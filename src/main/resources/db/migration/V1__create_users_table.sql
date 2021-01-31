create table if not exists notes
(
    id                       bigserial primary key,
    key                      varchar,
    payload                  varchar,
    note_type                integer,
    removable                bool,
    need_notify              bool,
    notify_email             varchar,
    created                  varchar
);
