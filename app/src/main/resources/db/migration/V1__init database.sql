CREATE TABLE user_data
(
    id       BIGSERIAL NOT NULL PRIMARY KEY,
    uuid     uuid,
    username VARCHAR(256),
    email    VARCHAR(256),
    rank     VARCHAR(256),
    progress JSONB
);

CREATE TABLE course
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    created_date    BIGINT,
    title           VARCHAR(256),
    description     VARCHAR(1024),
    begin_date      BIGINT,
    end_date        BIGINT,
    cost            BIGINT,
    additional_info VARCHAR(512)
);

CREATE TABLE lesson
(
    id           BIGSERIAL NOT NULL PRIMARY KEY,
    created_date BIGINT,
    title        VARCHAR(255),
    description  VARCHAR(1024),
    course_id    BIGINT
);

-- ManyToOne
ALTER TABLE lesson
    ADD CONSTRAINT fk_lesson_id FOREIGN KEY (course_id) REFERENCES course (id);

CREATE TABLE comment
(
    id           BIGSERIAL not null primary key,
    created_date BIGINT,
    author_uuid  uuid,
    lesson_id    BIGINT,
    content      VARCHAR(1024)
);

-- OneToOne
ALTER TABLE comment
    ADD CONSTRAINT fk_lesson_comment_id FOREIGN KEY (lesson_id) REFERENCES lesson (id);