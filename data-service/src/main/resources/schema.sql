CREATE TABLE IF NOT EXISTS study_groups
(
    id     BIGSERIAL PRIMARY KEY,
    name   VARCHAR(255),
    course INT
);

CREATE TABLE IF NOT EXISTS students
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255),
    group_id BIGINT REFERENCES study_groups (id)
);

CREATE TABLE IF NOT EXISTS lessons
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255),
    lesson_datetime TIMESTAMP
);

CREATE TABLE IF NOT EXISTS lesson_groups
(
    lesson_id BIGINT REFERENCES lessons (id),
    group_id  BIGINT REFERENCES study_groups (id),
    PRIMARY KEY (lesson_id, group_id)
);

CREATE TABLE IF NOT EXISTS checkins
(
    id         BIGSERIAL PRIMARY KEY,
    lesson_id  BIGINT REFERENCES lessons (id),
    student_id BIGINT REFERENCES students (id),
    UNIQUE (lesson_id, student_id)
);