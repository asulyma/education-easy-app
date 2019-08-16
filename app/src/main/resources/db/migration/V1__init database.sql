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