ALTER TABLE users DROP CONSTRAINT fk_users_on_company;
ALTER TABLE users DROP COLUMN company_id;

ALTER TABLE users ALTER COLUMN password TYPE VARCHAR(255);

ALTER TABLE companies ADD COLUMN owner_id UUID NOT NULL UNIQUE;
ALTER TABLE companies ADD CONSTRAINT fk_companies_on_user FOREIGN KEY (owner_id) REFERENCES users (id);
