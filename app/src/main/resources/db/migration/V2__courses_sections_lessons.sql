-- Courses
INSERT INTO course (id, created_date, begin_date, cost, description, end_date, title)
VALUES (1, 1560709800000, 1564660800000, 1299, 'The course about JAVA core', 1575201600000, 'Java Core');
INSERT INTO course (id, created_date, begin_date, cost, description, end_date, title)
VALUES (2, 1560709800000, 1564660800000, 899, 'The course about С# and .NET', 1575201600000, '.NET Code');
INSERT INTO course (id, created_date, begin_date, cost, description, end_date, title)
VALUES (3, 1560709800000, 1564660800000, 999, 'The course about JS (NodeJS)', 1575201600000, 'JavaScript Core');

-- Lessons
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (1, 1560709800000, 'Lesson1 Java info about lecture, info about lecture todo', 'Lesson1 Java', 1);
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (2, 1560709800000, 'Lesson2 Java info about lecture, info about lecture todo', 'Lesson2 Java', 1);
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (3, 1560709800000, 'Lesson3 Java info about lecture, info about lecture todo', 'Lesson3 Java', 1);
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (4, 1560709800000, 'Lesson4 Java info about lecture, info about lecture todo', 'Lesson4 Java', 1);

INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (5, 1560709800000, 'Lesson1 .NET info about lecture, info about lecture todo', 'Lesson1 .NET', 2);
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (6, 1560709800000, 'Lesson2 .NET info about lecture, info about lecture todo', 'Lesson2 .NET', 2);
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (7, 1560709800000, 'Lesson3 .NET info about lecture, info about lecture todo', 'Lesson3 .NET', 2);
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (8, 1560709800000, 'Lesson4 .NET info about lecture, info about lecture todo', 'Lesson4 .NET', 2);

INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (9, 1560709800000, 'Lesson1 JS info about lecture, info about lecture todo', 'Lesson1 JS', 3);
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (10, 1560709800000, 'Lesson2 JS info about lecture, info about lecture todo', 'Lesson2 JS', 3);
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (11, 1560709800000, 'Lesson3 JS info about lecture, info about lecture todo', 'Lesson3 JS', 3);
INSERT INTO lesson (id, created_date, description, title, course_id)
VALUES (12, 1560709800000, 'Lesson4 JS info about lecture, info about lecture todo', 'Lesson4 JS', 3);
