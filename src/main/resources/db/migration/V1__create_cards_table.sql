CREATE TABLE cards
(
    id         serial not null primary key,
    definition text   not null,
    concept    text   not null,
    confidence text   not null
)
