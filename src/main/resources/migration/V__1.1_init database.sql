CREATE TABLE user_table
(
    id           BIGSERIAL not null primary key,
    created_date BIGINT,
    login        varchar(255),
    name         varchar(255),
    age          BIGINT,
    email        varchar(255),
    active       BOOLEAN,
    rank         varchar(255),
    role         varchar(255),
    gender       varchar(255),
    cost         BIGINT,
    progress     JSONB
);

CREATE TABLE course
(
    id           BIGSERIAL not null primary key,
    created_date BIGINT,
    name         varchar(32),
    title        varchar(256),
    description  varchar(1024),
    begin_date   date,
    end_date     date,
    cost         BIGINT
);

-- ManyToMany
CREATE TABLE user_course
(
    user_id   BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, course_id),
    CONSTRAINT user_course_user_id_fkey FOREIGN KEY (user_id) REFERENCES user_table (id),
    CONSTRAINT user_course_course_id_fkey FOREIGN KEY (course_id) REFERENCES course (id)
);

CREATE TABLE section
(
    id           BIGSERIAL not null primary key,
    created_date BIGINT,
    title        varchar(255),
    description  varchar(1024),
    course_id    bigint
);

-- ManyToOne
ALTER TABLE section
    ADD CONSTRAINT fk_section_id FOREIGN KEY (course_id) REFERENCES course (id);


CREATE TABLE lesson
(
    id           BIGSERIAL not null primary key,
    created_date BIGINT,
    title        varchar(255),
    description  varchar(1024),
    section_id   bigint
);

-- ManyToOne
ALTER TABLE lesson
    ADD CONSTRAINT fk_lesson_id FOREIGN KEY (section_id) REFERENCES section (id);

-- ManyToMany
CREATE TABLE user_already_done_lessons
(
    user_id   BIGINT NOT NULL,
    lesson_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, lesson_id),
    CONSTRAINT user_lesson_user_id_fkey FOREIGN KEY (user_id) REFERENCES user_table (id),
    CONSTRAINT user_lesson_lesson_id_fkey FOREIGN KEY (lesson_id) REFERENCES lesson (id)
);

CREATE TABLE comment
(
    id           BIGSERIAL not null primary key,
    created_date BIGINT,
    author_id    bigint,
    lesson_id    bigint,
    content      varchar(1024)
);

-- OneToOne
ALTER TABLE comment
    ADD CONSTRAINT fk_user_comment_id FOREIGN KEY (author_id) REFERENCES user_table (id);
ALTER TABLE comment
    ADD CONSTRAINT fk_lesson_comment_id FOREIGN KEY (lesson_id) REFERENCES lesson (id);


CREATE TABLE notification
(
    id                BIGSERIAL not null primary key,
    created_date      BIGINT,
    description       varchar(512),
    publisher_id      bigint,
    recipient_id      bigint,
    notification_type varchar(255),
    entity_type       varchar(255),
    entity_id         bigint,
    is_read           boolean
);