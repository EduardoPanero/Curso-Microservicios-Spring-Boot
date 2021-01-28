CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE token_type AS ENUM('validation', 'forgotPassword');

CREATE TABLE IF NOT EXISTS "orders" (
  "order_id" UUID NOT NULL DEFAULT uuid_generate_v4(),
  "client_id" UUID NOT NULL,
  "address_id" BIGSERIAL NOT NULL,
  "order_date" DATE NOT NULL,
  "amount" DECIMAL NOT NULL,
  PRIMARY KEY ("order_id")
);
