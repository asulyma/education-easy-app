-- Courses
insert into course (id, begin_date, cost, description, end_date, title, name) values (1, '2018-11-11', 1299, 'The course about JAVA core', '2018-12-12', 'Java Core', 'Java');
insert into course (id, begin_date, cost, description, end_date, title, name) values (2, '2018-11-11', 899, 'The course about С# and .NET', '2018-12-12', '.NET Code', 'C-sharp');
insert into course (id, begin_date, cost, description, end_date, title, name) values (3, '2018-11-11', 999, 'The course about JS (NodeJS)', '2018-12-12', 'JavaScript Core', 'JS');

-- Sections
insert into section (id, description, title, course_id) values (1, 'The concept of object-oriented programming (hereinafter - OOP) consists of three key components: Inheritance, Encapsulation and Polymorphism.', '1. Java OOP', 1);
insert into section (id, description, title, course_id) values (2, 'Inheritance was invented in order to avoid duplication of code.', '2. Java OOP', 1);
insert into section (id, description, title, course_id) values (3, 'Input - Output of data', '3. Java IO', 1);
insert into section (id, description, title, course_id) values (4, 'C # was developed as an application programming language for the CLR and, as such, depends primarily on the capabilities of the CLR itself.', '1. C# OOP', 2);
insert into section (id, description, title, course_id) values (5, 'It is known at least about three independent implementations of C #', '2. C# DB', 2);
insert into section (id, description, title, course_id) values (6, 'Microsoft first used the name C# in 1988 for a variant of the C language designed for incremental compilation.', '3. C# AOP', 2);
insert into section (id, description, title, course_id) values (7, 'This is similar to the language name of C++, where "++" indicates that a variable should be incremented by 1 after being evaluated.', '4. C# Other', 2);
insert into section (id, description, title, course_id) values (8, 'As an asynchronous JavaScript event, the Node is designed to build scalable network applications.', '1. JS Core', 3);
insert into section (id, description, title, course_id) values (9, 'Node is created under the influence of systems such as Event Machine in Ruby or Twisted in Python.', '2. JS NodeJS', 3);
insert into section (id, description, title, course_id) values (10, 'Note the comments in the example above, all of which were preceded with two forward slashes.', '3. NodeJS', 3);
insert into section (id, description, title, course_id) values (11, 'A JavaScript engine (also known as JavaScript interpreter or JavaScript implementation) is an interpreter that interprets JavaScript source code and executes the script accordingly.', '4. Angular', 3);

-- Lessons
insert into lesson (id, description, title, update_date, section_id) values (1, 'Lesson1 Java bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson1 Java', '2018-10-18', 1);
insert into lesson (id, description, title, update_date, section_id) values (2, 'Lesson2 Java bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson2 Java', '2018-10-18', 1);
insert into lesson (id, description, title, update_date, section_id) values (3, 'Lesson3 Java bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson3 Java', '2018-10-18', 1);
insert into lesson (id, description, title, update_date, section_id) values (4, 'Lesson4 Java bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson4 Java', '2018-10-18', 2);
insert into lesson (id, description, title, update_date, section_id) values (5, 'Lesson5 Java bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson5 Java', '2018-10-18', 2);
insert into lesson (id, description, title, update_date, section_id) values (6, 'Lesson6 Java bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson6 Java', '2018-10-18', 2);
insert into lesson (id, description, title, update_date, section_id) values (7, 'Lesson7 Java bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson7 Java', '2018-10-18', 3);
insert into lesson (id, description, title, update_date, section_id) values (8, 'Lesson8 Java bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson8 Java', '2018-10-18', 3);

insert into lesson (id, description, title, update_date, section_id) values (9, 'Lesson1 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson1 .NET', '2018-10-18', 4);
insert into lesson (id, description, title, update_date, section_id) values (10, 'Lesson2 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson2 .NET', '2018-10-18', 4);
insert into lesson (id, description, title, update_date, section_id) values (11, 'Lesson3 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson3 .NET', '2018-10-18', 4);
insert into lesson (id, description, title, update_date, section_id) values (12, 'Lesson4 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson4 .NET', '2018-10-18', 5);
insert into lesson (id, description, title, update_date, section_id) values (13, 'Lesson5 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson5 .NET', '2018-10-18', 5);
insert into lesson (id, description, title, update_date, section_id) values (14, 'Lesson6 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson6 .NET', '2018-10-18', 5);
insert into lesson (id, description, title, update_date, section_id) values (15, 'Lesson7 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson7 .NET', '2018-10-18', 6);
insert into lesson (id, description, title, update_date, section_id) values (16, 'Lesson8 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson8 .NET', '2018-10-18', 6);
insert into lesson (id, description, title, update_date, section_id) values (17, 'Lesson9 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson9 .NET', '2018-10-18', 7);
insert into lesson (id, description, title, update_date, section_id) values (18, 'Lesson10 .NET bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson10 .NET', '2018-10-18', 7);

insert into lesson (id, description, title, update_date, section_id) values (19, 'Lesson1 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson1 JS', '2018-10-18', 8);
insert into lesson (id, description, title, update_date, section_id) values (20, 'Lesson2 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson2 JS', '2018-10-18', 8);
insert into lesson (id, description, title, update_date, section_id) values (21, 'Lesson3 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson3 JS', '2018-10-18', 8);
insert into lesson (id, description, title, update_date, section_id) values (22, 'Lesson4 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson4 JS', '2018-10-18', 9);
insert into lesson (id, description, title, update_date, section_id) values (23, 'Lesson5 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson5 JS', '2018-10-18', 9);
insert into lesson (id, description, title, update_date, section_id) values (24, 'Lesson6 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson6 JS', '2018-10-18', 9);
insert into lesson (id, description, title, update_date, section_id) values (25, 'Lesson7 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson7 JS', '2018-10-18', 10);
insert into lesson (id, description, title, update_date, section_id) values (26, 'Lesson8 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson8 JS', '2018-10-18', 10);
insert into lesson (id, description, title, update_date, section_id) values (27, 'Lesson9 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson9 JS', '2018-10-18', 11);
insert into lesson (id, description, title, update_date, section_id) values (28, 'Lesson10 JS bla-bla-bla-bla-bla-bla-bla-bla-bla', 'Lesson10 JS', '2018-10-18', 11);
