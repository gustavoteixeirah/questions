CREATE TABLE question
(
    id          SERIAL PRIMARY KEY,
    description TEXT      NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255)
);

CREATE TABLE alternative
(
    id          SERIAL PRIMARY KEY,
    letter      CHAR(1)   NOT NULL,
    description TEXT      NOT NULL,
    correct     BOOLEAN   NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    question_id INTEGER   NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question (id)
);
