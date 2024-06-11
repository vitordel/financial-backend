CREATE TABLE bank_account (
    id BIGSERIAL PRIMARY KEY,
    bank_name VARCHAR(50) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transaction_type (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transaction (
    id BIGSERIAL PRIMARY KEY,
    bank_account_id BIGINT NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    transaction_type_id BIGINT NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    FOREIGN KEY (bank_account_id) REFERENCES bank_account(id),
    FOREIGN KEY (transaction_type_id) REFERENCES transaction_type(id),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

