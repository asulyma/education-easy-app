CREATE TABLE user_table
(
    id       BIGSERIAL NOT NULL PRIMARY KEY,
    uuid     UUID      NOT NULL,
    username VARCHAR(256) UNIQUE,
    password VARCHAR(512)
);
CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL
        CONSTRAINT roles_user_fk REFERENCES user_table,
    roles   VARCHAR(512)
);

ALTER TABLE user_table
    OWNER TO postgres;
ALTER TABLE user_roles
    OWNER TO postgres;