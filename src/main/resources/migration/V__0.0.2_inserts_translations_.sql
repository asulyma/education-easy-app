-- notification for users choice of course
insert into notification_translation (id, notification_entity_type, notification_type, title) values (1, 'COURSE', 'INFO_TO_USER', 'Thank you about your choice. As soon as the administrator approves the application, we will send you an alert.');
-- notification for admin about someones choice of course
insert into notification_translation (id, notification_entity_type, notification_type, title) values (2, 'COURSE', 'PERMISSION_TO_ADMIN', 'The user asks for permission to access the course.');
-- notification for user about approve access to course
insert into notification_translation (id, notification_entity_type, notification_type, title) values (3, 'COURSE', 'APPROVE_PERMISSION', 'Congratulations! You were given access to the course. You can go in and learn.');
-- notification for user about decline access to course
insert into notification_translation (id, notification_entity_type, notification_type, title) values (4, 'COURSE', 'DECLINE_PERMISSION', 'We apologize, but you were not given access to the course.');
-- notification for admin about someones is start section
insert into notification_translation (id, notification_entity_type, notification_type, title) values (5, 'SECTION', 'INFO_TO_ADMIN', 'The user has started the passage of the section.');


-- users
INSERT INTO user_table (id, age, email, gender, given_name, is_active, is_locked, login, rank, registration_date, roles) VALUES (1, null, null, 'uses@user.com', 'UserLastName', null, 'UserName', true, false, 'user', null, '2018-10-09', 'offline_access, uma_authorization, user');
INSERT INTO user_table (id, age, email, gender, given_name, is_active, is_locked, login, rank, registration_date, roles) VALUES (2, null, null, 'admin@admin.com', 'Administrator', null, 'Administrator', true, false, 'admin', null, '2018-10-09', 'offline_access, moderator, admin, uma_authorization, user');

-- allow to courses
INSERT INTO user_courses (user_id, course_id) VALUES (1, 3);

-- progress for user
INSERT INTO course_progress (user_id, progress, progress_key) VALUES (1, 0, 'JS');

-- a few notifications
INSERT INTO notification (id, id_of_entity, is_read, notification_entity_type, notification_type, publisher_id, recipient_id, title, update_date) VALUES (3, 3, true, 'COURSE', 'PERMISSION_TO_ADMIN', 1, 2, 'The user asks for permission to access the course.', '2018-12-12');
INSERT INTO notification (id, id_of_entity, is_read, notification_entity_type, notification_type, publisher_id, recipient_id, title, update_date) VALUES (4, 3, true, 'COURSE', 'INFO_TO_USER', 2, 1, 'Thank you about your choice. As soon as the administrator approves the application, we will send you an alert.', '2018-12-12');
INSERT INTO notification (id, id_of_entity, is_read, notification_entity_type, notification_type, publisher_id, recipient_id, title, update_date) VALUES (6, 3, true, 'COURSE', 'APPROVE_PERMISSION', 2, 1, 'Congratulations! You were given access to the course. You can go in and learn.', '2018-12-12');

-- allow to sections
INSERT INTO user_sections (user_id, section_id)  VALUES (1, 8);






insert into comment (id, content, update_date, author_id, lesson_id) values (1, 'I think this is great lesson!', '2018-10-18', 1, 1);
insert into comment (id, content, update_date, author_id, lesson_id) values (2, 'I think this is bad lesson!', '2018-10-18', 1, 2);
insert into comment (id, content, update_date, author_id, lesson_id) values (3, 'I think this is nice lesson!', '2018-10-18', 2, 3);
insert into comment (id, content, update_date, author_id, lesson_id) values (4, 'I think this is good lesson!', '2018-10-18', 3, 4);

