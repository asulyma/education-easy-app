-- Courses
INSERT INTO course (id, created_date, begin_date, cost, description, end_date, title, name) VALUES (1, 1560709800000, '2019-11-11', 1299, 'The course about JAVA core', '2019-12-12', 'Java Core', 'Java');
INSERT INTO course (id, created_date, begin_date, cost, description, end_date, title, name) VALUES (2, 1560709800000, '2019-11-11', 899, 'The course about С# and .NET', '2019-12-12', '.NET Code', 'C-sharp');
INSERT INTO course (id, created_date, begin_date, cost, description, end_date, title, name) VALUES (3, 1560709800000, '2019-11-11', 999, 'The course about JS (NodeJS)', '2019-12-12', 'JavaScript Core', 'JS');

-- Sections
INSERT INTO section (id, created_date, description, title, course_id) VALUES (1, 1560709800000, 'The concept of object-oriented programming (hereinafter - OOP) consists of three key components: Inheritance, Encapsulation and Polymorphism.', '1. Java OOP', 1);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (2, 1560709800000, 'Inheritance was invented in order to avoid duplication of code.', '2. Java OOP', 1);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (3, 1560709800000, 'Input - Output of data', '3. Java IO', 1);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (4, 1560709800000, 'C # was developed as an application programming language for the CLR and, as such, depends primarily on the capabilities of the CLR itself.', '1. C# OOP', 2);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (5, 1560709800000, 'It is known at least about three independent implementations of C #', '2. C# DB', 2);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (6, 1560709800000, 'Microsoft first used the name C# in 1988 for a variant of the C language designed for incremental compilation.', '3. C# AOP', 2);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (7, 1560709800000, 'This is similar to the language name of C++, where "++" indicates that a variable should be incremented by 1 after being evaluated.', '4. C# Other', 2);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (8, 1560709800000, 'As an asynchronous JavaScript event, the Node is designed to build scalable network applications.', '1. JS Core', 3);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (9, 1560709800000, 'Node is created under the influence of systems such as Event Machine in Ruby or Twisted in Python.', '2. JS NodeJS', 3);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (10, 1560709800000, 'Note the comments in the example above, all of which were preceded with two forward slashes.', '3. NodeJS', 3);
INSERT INTO section (id, created_date, description, title, course_id) VALUES (11, 1560709800000, 'A JavaScript engine (also known as JavaScript interpreter or JavaScript implementation) is an interpreter that interprets JavaScript source code and executes the script accordingly.', '4. Angular', 3);

-- Lessons
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (1, 1560709800000, 'Lesson1 Java info about lection, info about lection todo', 'Lesson1 Java',  1);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (2, 1560709800000, 'Lesson2 Java info about lection, info about lection todo', 'Lesson2 Java',  1);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (3, 1560709800000, 'Lesson3 Java info about lection, info about lection todo', 'Lesson3 Java',  1);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (4, 1560709800000, 'Lesson4 Java info about lection, info about lection todo', 'Lesson4 Java',  2);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (5, 1560709800000, 'Lesson5 Java info about lection, info about lection todo', 'Lesson5 Java',  2);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (6, 1560709800000, 'Lesson6 Java info about lection, info about lection todo', 'Lesson6 Java',  2);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (7, 1560709800000, 'Lesson7 Java info about lection, info about lection todo', 'Lesson7 Java',  3);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (8, 1560709800000, 'Lesson8 Java info about lection, info about lection todo', 'Lesson8 Java',  3);

INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (9, 1560709800000, 'Lesson1 .NET info about lection, info about lection todo', 'Lesson1 .NET',  4);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (10, 1560709800000, 'Lesson2 .NET info about lection, info about lection todo', 'Lesson2 .NET',  4);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (11, 1560709800000, 'Lesson3 .NET info about lection, info about lection todo', 'Lesson3 .NET',  4);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (12, 1560709800000, 'Lesson4 .NET info about lection, info about lection todo', 'Lesson4 .NET',  5);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (13, 1560709800000, 'Lesson5 .NET info about lection, info about lection todo', 'Lesson5 .NET',  5);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (14, 1560709800000, 'Lesson6 .NET info about lection, info about lection todo', 'Lesson6 .NET',  5);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (15, 1560709800000, 'Lesson7 .NET info about lection, info about lection todo', 'Lesson7 .NET',  6);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (16, 1560709800000, 'Lesson8 .NET info about lection, info about lection todo', 'Lesson8 .NET',  6);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (17, 1560709800000, 'Lesson9 .NET info about lection, info about lection todo', 'Lesson9 .NET',  7);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (18, 1560709800000, 'Lesson10 .NET info about lection, info about lection todo', 'Lesson10 .NET',  7);

INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (19, 1560709800000, 'Lesson1 JS info about lection, info about lection todo', 'Lesson1 JS',  8);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (20, 1560709800000, 'Lesson2 JS info about lection, info about lection todo', 'Lesson2 JS',  8);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (21, 1560709800000, 'Lesson3 JS info about lection, info about lection todo', 'Lesson3 JS',  8);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (22, 1560709800000, 'Lesson4 JS info about lection, info about lection todo', 'Lesson4 JS',  9);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (23, 1560709800000, 'Lesson5 JS info about lection, info about lection todo', 'Lesson5 JS',  9);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (24, 1560709800000, 'Lesson6 JS info about lection, info about lection todo', 'Lesson6 JS',  9);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (25, 1560709800000, 'Lesson7 JS info about lection, info about lection todo', 'Lesson7 JS',  10);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (26, 1560709800000, 'Lesson8 JS info about lection, info about lection todo', 'Lesson8 JS',  10);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (27, 1560709800000, 'Lesson9 JS info about lection, info about lection todo', 'Lesson9 JS',  11);
INSERT INTO lesson (id, created_date, description, title,  section_id) VALUES (28, 1560709800000, 'Lesson10 JS info about lection, info about lection todo', 'Lesson10 JS',  11);
