

-- users
INSERT INTO user_table (id, age, email, gender, given_name, is_active, is_locked, login, rank, registration_date, roles) VALUES (1, null, null, 'uses@user.com', 'UserLastName', null, 'UserName', true, false, 'user', null, '2018-10-09', 'offline_access, uma_authorization, user');
INSERT INTO user_table (id, age, email, gender, given_name, is_active, is_locked, login, rank, registration_date, roles) VALUES (2, null, null, 'admin@admin.com', 'Administrator', null, 'Administrator', true, false, 'admin', null, '2018-10-09', 'offline_access, moderator, admin, uma_authorization, user');

-- allow to courses
INSERT INTO user_courses (user_id, course_id) VALUES (1, 3);

-- progress for user
INSERT INTO course_progress (user_id, progress, progress_key) VALUES (1, 0, 'JS');




-- allow to sections
INSERT INTO user_sections (user_id, section_id)  VALUES (1, 8);






insert into comment (id, content, update_date, author_id, lesson_id) values (1, 'I think this is great lesson!', '2018-10-18', 1, 1);
insert into comment (id, content, update_date, author_id, lesson_id) values (2, 'I think this is bad lesson!', '2018-10-18', 1, 2);
insert into comment (id, content, update_date, author_id, lesson_id) values (3, 'I think this is nice lesson!', '2018-10-18', 2, 3);
insert into comment (id, content, update_date, author_id, lesson_id) values (4, 'I think this is good lesson!', '2018-10-18', 3, 4);

