CREATE TABLE user_table
(
    id       BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(256),
    password VARCHAR(256),
    email    VARCHAR(256),
    rank     VARCHAR(32),
    progress JSONB
);
CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL
        CONSTRAINT roles_user_fk REFERENCES user_table,
    roles   VARCHAR(256)
);

ALTER TABLE user_table OWNER TO postgres;
ALTER TABLE user_roles OWNER TO postgres;