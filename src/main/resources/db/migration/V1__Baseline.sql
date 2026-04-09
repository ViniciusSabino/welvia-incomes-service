-- =================================================
-- Welvia Incomes — schema.sql
-- =================================================

-- users
CREATE TABLE IF NOT EXISTS users (
    id      BIGSERIAL       PRIMARY KEY,
    name    VARCHAR(255)    NOT NULL,
    email   VARCHAR(255)    NOT NULL UNIQUE
);

-- account_type
CREATE TABLE IF NOT EXISTS account_type (
    id      BIGSERIAL       PRIMARY KEY,
    name    VARCHAR(255)    NOT NULL UNIQUE
);

-- accounts
CREATE TABLE IF NOT EXISTS accounts (
    id              BIGSERIAL       PRIMARY KEY,
    account_type_id BIGINT          NOT NULL REFERENCES account_type(id),
    name            VARCHAR(255)    NOT NULL UNIQUE,
    balance         NUMERIC(19, 2)  NOT NULL DEFAULT 0.00,
    is_main         BOOLEAN         NOT NULL DEFAULT FALSE,
    is_active       BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMPTZ     NOT NULL,
    updated_at      TIMESTAMPTZ     NOT NULL
);

-- income_types
CREATE TABLE IF NOT EXISTS income_types (
    id      BIGSERIAL       PRIMARY KEY,
    type    VARCHAR(255)    NOT NULL UNIQUE
);

-- income_statuses
CREATE TABLE IF NOT EXISTS income_statuses (
    id      BIGSERIAL       PRIMARY KEY,
    status  VARCHAR(255)    NOT NULL UNIQUE
);

-- incomes
CREATE TABLE IF NOT EXISTS incomes (
    id                BIGSERIAL       PRIMARY KEY,
    user_id           BIGINT          NOT NULL REFERENCES users(id),
    account_id        BIGINT          NOT NULL REFERENCES accounts(id),
    income_type_id    BIGINT          NOT NULL REFERENCES income_types(id),
    income_status_id  BIGINT          NOT NULL REFERENCES income_statuses(id),
    amount            NUMERIC(19, 2)  NOT NULL,
    date              TIMESTAMPTZ     NOT NULL,
    received_at       TIMESTAMPTZ,
    description       VARCHAR(500),
    created_at        TIMESTAMPTZ     NOT NULL,
    updated_at        TIMESTAMPTZ     NOT NULL
);

-- =================================================
-- Índices
-- =================================================
CREATE INDEX IF NOT EXISTS idx_accounts_account_type_id  ON accounts(account_type_id);
CREATE INDEX IF NOT EXISTS idx_incomes_user_id           ON incomes(user_id);
CREATE INDEX IF NOT EXISTS idx_incomes_account_id        ON incomes(account_id);
CREATE INDEX IF NOT EXISTS idx_incomes_income_type_id    ON incomes(income_type_id);
CREATE INDEX IF NOT EXISTS idx_incomes_income_status_id  ON incomes(income_status_id);
CREATE INDEX IF NOT EXISTS idx_incomes_date              ON incomes(date);

-- =================================================
-- Dados iniciais
-- =================================================
INSERT INTO account_type (name) VALUES
    ('Corrente'),
    ('Poupança'),
    ('Investimento'),
    ('Carteira')
ON CONFLICT DO NOTHING;

INSERT INTO income_types (type) VALUES
    ('Salário'),
    ('Variável'),
    ('Empresarial'),
    ('Investimentos'),
    ('Bônus'),
	('Outros')
ON CONFLICT DO NOTHING;

INSERT INTO income_statuses (status) VALUES
    ('Pendente'),
    ('Recebido'),
    ('Cancelado')
ON CONFLICT DO NOTHING;