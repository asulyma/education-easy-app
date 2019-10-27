INSERT INTO user_table (id, uuid, username, password) VALUES (1, 'a0550d38-f72d-11e9-8f0b-362b9e155667', 'john', '{noop}john');
INSERT INTO user_roles (user_id, roles) VALUES (1, 'ROLE_USER');

INSERT INTO user_table (id, uuid, username, password) VALUES (2, 'a0550fa4-f72d-11e9-8f0b-362b9e155667', 'admin', '{noop}admin');
INSERT INTO user_roles (user_id, roles) VALUES (2, 'ROLE_ADMIN');