CREATE TABLE auth_code
(
    id             BIGSERIAL NOT NULL PRIMARY KEY,
    code           VARCHAR(1024),
    authentication VARCHAR(1024)
);

CREATE TABLE client_details
(
    id                             BIGSERIAL NOT NULL PRIMARY KEY,
    access_token_validity_seconds  INTEGER,
    auto_approve                   BOOLEAN,
    client_id                      VARCHAR(255),
    client_secret                  VARCHAR(512),
    refresh_token_validity_seconds INTEGER,
    scoped                         BOOLEAN,
    secret_required                BOOLEAN
);


CREATE TABLE client_authorities
(
    client_details_id BIGINT NOT NULL
        constraint fk9lte00u7gfkcdxf0orqlikt0g references client_details,
    authority         bytea
);

CREATE TABLE client_grant_type
(
    client_details_id BIGINT NOT NULL
        constraint fk7o9kemo06sk48fj6ulcbal3pt references client_details,
    grant_type        VARCHAR(255)
);

CREATE TABLE client_registered_redirect_uri
(
    client_details_id       BIGINT NOT NULL
        constraint fkiud5vlqpaivr88tgj5pf61tnh references client_details,
    registered_redirect_uri VARCHAR(255)
);

CREATE TABLE client_scope
(
    client_details_id BIGINT NOT NULL
        constraint fkngu7dselxtunaip9v24h8jhqi references client_details,
    scope             VARCHAR(255)
);

CREATE TABLE client_resource
(
    client_details_id BIGINT NOT NULL
        constraint fkawyrotwhg7wsxoxxvcf0k56u4 references client_details,
    resource          VARCHAR(255)
);

ALTER TABLE auth_code OWNER TO postgres;
ALTER TABLE client_details OWNER TO postgres;
ALTER TABLE client_resource OWNER TO postgres;
ALTER TABLE client_scope OWNER TO postgres;
ALTER TABLE client_grant_type OWNER TO postgres;
ALTER TABLE client_authorities OWNER TO postgres;
ALTER TABLE client_registered_redirect_uri OWNER TO postgres;