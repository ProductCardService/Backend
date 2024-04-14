create schema if not exists fitcha_card_service;

set timezone = 'Europe/Moscow';

create table if not exists fitcha_card_service.cards
(
    id            serial  not null primary key,
    title         text    not null,
    description   text    not null,
    image_base64  text    not null
);

create table if not exists fitcha_card_service.tags
(
    id          serial  not null primary key,
    card_id     bigint  not null references cards (id),
    "name"      text    not null
);