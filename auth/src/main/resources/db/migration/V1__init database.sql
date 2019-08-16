CREATE TABLE user_table
(
    id        BIGSERIAL NOT NULL PRIMARY KEY,
    name      VARCHAR(256),
    email     VARCHAR(256),
    active    BOOLEAN,
    rank      VARCHAR(32),
    user_data JSONB,
    progress  JSONB
);