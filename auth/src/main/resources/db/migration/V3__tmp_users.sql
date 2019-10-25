INSERT INTO user_table (id, username, password, email, rank, progress) VALUES (1, 'john', '{noop}john', 'john@ukr.net', 'TRAINEE', '{}');
INSERT INTO user_roles (user_id, roles) VALUES (1, 'ROLE_USER');

INSERT INTO user_table (id, username, password, email, rank, progress) VALUES (2, 'admin', '{noop}admin', 'admin@ukr.net', 'MIDDLE', '{}');
INSERT INTO user_roles (user_id, roles) VALUES (2, 'ROLE_ADMIN');