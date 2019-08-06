CREATE TABLE user_table
(
    id           BIGSERIAL NOT NULL PRIMARY KEY,
    created_date BIGINT,
    login        VARCHAR(255),
    name         VARCHAR(255),
    age          BIGINT,
    email        VARCHAR(255),
    active       BOOLEAN,
    rank         VARCHAR(255),
    role         VARCHAR(255),
    progress     JSONB
);

CREATE TABLE course
(
    id           BIGSERIAL NOT NULL PRIMARY KEY,
    created_date BIGINT,
    title        VARCHAR(256),
    description  VARCHAR(1024),
    begin_date   BIGINT,
    end_date     BIGINT,
    cost         BIGINT
);

-- ManyToMany
CREATE TABLE user_allowed_course
(
    user_id   BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, course_id),
    CONSTRAINT user_course_user_id_fkey FOREIGN KEY (user_id) REFERENCES user_table (id),
    CONSTRAINT user_course_course_id_fkey FOREIGN KEY (course_id) REFERENCES course (id)
);

CREATE TABLE section
(
    id           BIGSERIAL NOT NULL PRIMARY KEY,
    created_date BIGINT,
    title        VARCHAR(255),
    description  VARCHAR(1024),
    course_id    BIGINT
);

-- ManyToOne
ALTER TABLE section
    ADD CONSTRAINT fk_section_id FOREIGN KEY (course_id) REFERENCES course (id);


CREATE TABLE lesson
(
    id           BIGSERIAL NOT NULL PRIMARY KEY,
    created_date BIGINT,
    title        VARCHAR(255),
    description  VARCHAR(1024),
    section_id   BIGINT
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
    author_id    BIGINT,
    lesson_id    BIGINT,
    content      VARCHAR(1024)
);

-- OneToOne
ALTER TABLE comment
    ADD CONSTRAINT fk_lesson_comment_id FOREIGN KEY (lesson_id) REFERENCES lesson (id);


CREATE TABLE notification
(
    id                BIGSERIAL NOT NULL PRIMARY KEY,
    created_date      BIGINT,
    description       VARCHAR(512),
    publisher_id      BIGINT,
    recipient_id      BIGINT,
    notification_type VARCHAR(255),
    entity_type       VARCHAR(255),
    entity_id         BIGINT,
    is_read           BOOLEAN
);