CREATE TABLE companies
(
    id          UUID         NOT NULL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    website     VARCHAR(150) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE users
(
    id         UUID         NOT NULL PRIMARY KEY,
    name       VARCHAR(150) NOT NULL UNIQUE,
    email      VARCHAR(150) NOT NULL UNIQUE,
    password   VARCHAR(50)  NOT NULL,
    company_id UUID         NOT NULL,

    CONSTRAINT fk_users_on_company FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TYPE modality_enum AS ENUM ('CLT', 'PJ', 'FREELANCER');

CREATE TABLE jobs
(
    id          UUID         NOT NULL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    description VARCHAR(255),
    modality    modality_enum NOT NULL,
    salary      DECIMAL(10, 2)      NOT NULL,
    active      BOOLEAN      NOT NULL,
    location    VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    company_id  UUID         NOT NULL,

    CONSTRAINT fk_jobs_on_company FOREIGN KEY (company_id) REFERENCES companies (id)
);