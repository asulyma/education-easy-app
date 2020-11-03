INSERT INTO user_table (uuid, username, password)
VALUES ('a0000a00-f72d-11e9-8f0b-362b9e155667', 'john', '{noop}john');
INSERT INTO user_table (uuid, username, password)
VALUES ('b1111b11-f72d-11e9-8f0b-362b9e155667', 'admin', '{noop}admin');

INSERT INTO user_roles (user_id, roles)
VALUES ((SELECT id FROM user_table WHERE username = 'john'), 'ROLE_USER');
INSERT INTO user_roles (user_id, roles)
VALUES ((SELECT id FROM user_table WHERE username = 'admin'), 'ROLE_ADMIN');