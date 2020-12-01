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
    id              BIGSERIAL    NOT NULL PRIMARY KEY,
    created_date    BIGINT,
    title           VARCHAR(256) NOT NULL UNIQUE,
    description     VARCHAR(2048),
    begin_date      BIGINT,
    finish_date     BIGINT,
    cost            BIGINT,
    additional_info JSONB
);

CREATE TABLE lesson
(
    id             BIGSERIAL NOT NULL PRIMARY KEY,
    created_date   BIGINT,
    title          VARCHAR(255),
    description    VARCHAR(2048),
    course_id      BIGINT,
    execution_time BIGINT
);

-- ManyToOne
ALTER TABLE lesson
    ADD CONSTRAINT fk_lesson_id FOREIGN KEY (course_id) REFERENCES course (id);

CREATE TABLE comment
(
    id           BIGSERIAL not null primary key,
    created_date BIGINT,
    author_uuid  uuid,
    author_name  VARCHAR(256),
    lesson_id    BIGINT,
    content      VARCHAR(1024)
);

-- OneToOne
ALTER TABLE comment
    ADD CONSTRAINT fk_lesson_comment_id FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE user_data
    OWNER TO "education-app";
ALTER TABLE course
    OWNER TO "education-app";
ALTER TABLE lesson
    OWNER TO "education-app";
ALTER TABLE comment
    OWNER TO "education-app";