-- Courses
INSERT INTO course (created_date, begin_date, cost, description, finish_date, title)
VALUES (1601533333000, 1609000000000, 1299,
        'Java is a popular programming language used to code game and mobile applications, desktop programs and a huge number of soft products for enterprises. The Java curriculum consists of interconnected levels, after which you will develop the knowledge and skills of Java development at the Junior Specialist level.',
        1619000000000, 'Java Core');
INSERT INTO course (created_date, begin_date, cost, description, finish_date, title)
VALUES (1601333333000, 1607000000000, 899,
        'C++ programming courses are a comprehensive training program for one of the most popular and powerful programming languages, C++. The training consists of interconnected stages, after which you will master the basics of programming at basic and advanced levels. The C++ program provides for the acquisition of hard and soft skills necessary for a Junior specialist.',
        1617000000000, 'C++ Core');
INSERT INTO course (created_date, begin_date, cost, description, finish_date, title)
VALUES (1601133333000, 1605000000000, 999,
        'JS development courses are a comprehensive JavaScript programming program from scratch. JavaScript is popular and easy to use. It is widely used in browsers to create interactive web pages, to develop user interface assignments using frameworks.',
        1615000000000, 'JavaScript Core');

-- Lessons
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601533344000,
        'Autoboxing. Today we will talk about autoboxing and unboxing. This is one of the significant changes made to JDK 5. Developers can now write cleaner code, but not understanding how it works can lead to poor performance.',
        'Java Lesson #1 - Autoboxing', (SELECT id FROM course WHERE title = 'Java Core'), 3600);
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601533355000,
        'What is JVM? Java Virtual machine (JVM) is the virtual machine that runs the Java bytecodes. You get this bytecode by compiling the .java files into .class files. .class files contain the bytecodes understood by the JVM. In the real world, JVM is a specification that provides a runtime environment in which Java bytecode can be executed.',
        'Java Lesson #2 - What is JVM?', (SELECT id FROM course WHERE title = 'Java Core'), 7200);
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601533366000,
        'What is JRE? The Java Runtime Environment (JRE) is a software package which bundles the libraries (jars) and the Java Virtual Machine, and other components to run applications written in the Java. JVM is just a part of JRE distributions. To execute any Java application, you need JRE installed in the machine. It’s the minimum requirement to run Java applications on any computer.',
        'Java Lesson #3 - What is JRE?', (SELECT id FROM course WHERE title = 'Java Core'), 7200);
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601533377000,
        'What is JDK? JDK is a superset of JRE. JDK contains everything that JRE has along with development tools for developing, debugging, and monitoring Java applications. You need JDK when you need to develop Java applications.',
        'Java Lesson #4 - What is JDK?', (SELECT id FROM course WHERE title = 'Java Core'), 3600);


INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601333344000,
        'Statements. Statements are by far the most common type of instruction in a C++ program. This is because they are the smallest independent unit of computation in the C++ language. In that regard, they act much like sentences do in natural language. When we want to convey an idea to another person, we typically write or speak in sentences (not in random words or syllables). In C++, when we want to have our program do something, we typically write statements.',
        'C++ Lesson #1 - Statements', (SELECT id FROM course WHERE title = 'C++ Core'), 3600);
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601333355000,
        'Comments. A comment is a programmer-readable note that is inserted directly into the source code of the program. Comments are ignored by the compiler and are for the programmer’s use only. In C++ there are two different styles of comments, both of which serve the same purpose: to help programmers document the code in some way.',
        'C++ Lesson #2 - Comments', (SELECT id FROM course WHERE title = 'C++ Core'), 3600);
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601333366000,
        'Objects and variables. All computers have memory, called RAM (short for random access memory), that is available for your programs to use. You can think of RAM as a series of mailboxes that can be used to hold data while the program is running. A single piece of data, stored in memory somewhere, is called a value.',
        'C++ Lesson #3 - Objects and variables', (SELECT id FROM course WHERE title = 'C++ Core'), 1800);
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601333377000,
        'The input/output library. The input/output library (io library) is part of the C++ standard library that deals with basic input and output. We’ll use the functionality in this library to get input from the keyboard and output data to the console. The io part of iostream stands for input/output. To use the functionality defined within the iostream library, we need to include the iostream header at the top of any code file that uses the content defined in iostream',
        'C++ Lesson #4 - The input/output library', (SELECT id FROM course WHERE title = 'C++ Core'), 1800);


INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601133344000,
        'Variables & Literals. A variable is a container which has a name. We use variables to hold information that may change from one moment to the next while a program is running. A literal, by contrast, doesnt have a name - it only has a value.',
        'JS Lesson #1 - Variables & Literals', (SELECT id FROM course WHERE title = 'JavaScript Core'), 1800);
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601133355000,
        'Functions. In JavaScript, as in other languages, we can create functions. A function is a kind of mini-program that forms part of a larger program. Some functions perform a simple task for which no extra information is needed. However, it is often necessary to supply information to a function so that it can carry out its task.',
        'JS Lesson #2 - Functions', (SELECT id FROM course WHERE title = 'JavaScript Core'), 1800);
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601133366000,
        'JavaScript Comparison Operators. As well as needing to assign values to variables, we sometime need to compare variables or literals. We do this using Comparison Operators. Comparison Operators compare two values and produce an output which is either true or false.',
        'JS Lesson #3 - Comparison Operators', (SELECT id FROM course WHERE title = 'JavaScript Core'), 3600);
INSERT INTO lesson (created_date, description, title, course_id, execution_time)
VALUES (1601133377000,
        'Arrays. An array is a set of variables (e.g., strings or numbers) that are grouped together and given a single name. There are also several methods that add and remove elements directly, some of which are listed below. However, it should be noted that these methods only work with Netscape Navigator, so it is generally preferable to use the methods described above since they work with most browsers.',
        'JS Lesson #4 - Arrays', (SELECT id FROM course WHERE title = 'JavaScript Core'), 3600);
