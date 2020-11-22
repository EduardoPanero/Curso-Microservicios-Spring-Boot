CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE token_type AS ENUM('validation', 'forgotPassword');

CREATE TABLE IF NOT EXISTS "clients" (
  "client_id" UUID NOT NULL DEFAULT uuid_generate_v4(),
  "name" VARCHAR(50) NOT NULL,
  "email" VARCHAR(50) NOT NULL,
  "password" BYTEA NOT NULL DEFAULT NULL,
  "salt" BYTEA NULL DEFAULT NULL,
  "enabled" BOOLEAN NOT NULL,
  PRIMARY KEY ("client_id")
);

CREATE TABLE IF NOT EXISTS "addresses" (
  "address_id" BIGSERIAL NOT NULL,
  "client_id" UUID NOT NULL,
  "street" varchar(100) NOT NULL,
  "number" bigint NOT NULL,
  "zip_code" varchar(6) NOT NULL,
  "default_address" boolean DEFAULT false,
  "flat" bigint,
  "door" varchar(6),
  PRIMARY KEY ("address_id"),
  FOREIGN KEY("client_id")
     REFERENCES "clients"("client_id")
);

CREATE TABLE IF NOT EXISTS "tokens" (
  "token_id" BIGSERIAL NOT NULL,
  "client_id" UUID NOT NULL,
  "token_type" VARCHAR(20) NOT NULL,
  "expiration_date" DATE NOT NULL,
  "token" VARCHAR(50) NOT NULL,
  PRIMARY KEY ("token_id"),
  FOREIGN KEY("client_id")
     REFERENCES "clients"("client_id"));
