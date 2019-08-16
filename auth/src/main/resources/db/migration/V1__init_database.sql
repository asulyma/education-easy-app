CREATE TABLE user_table
(
    id        BIGSERIAL NOT NULL PRIMARY KEY,
    username  VARCHAR(256),
    password  VARCHAR(256),
    email     VARCHAR(256),
    rank      VARCHAR(32),
    user_data JSONB,
    progress  JSONB
);
CREATE TABLE roles
(
    user_id BIGINT NOT NULL
        CONSTRAINT roles_user_fk REFERENCES user_table,
    roles   bytea
);

ALTER TABLE roles
    OWNER TO postgres;