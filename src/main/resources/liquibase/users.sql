-- liquibase formatted sql
-- changeset g

CREATE TABLE IF NOT EXISTS sock
(
    id          INTEGER PRIMARY KEY,
    color       VARCHAR,
    cotton_part  INT,
    quantity    INT
);