CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "clients" (
  "id" UUID NOT NULL DEFAULT uuid_generate_v4(),
  "name" VARCHAR(50) NOT NULL,
  "email" VARCHAR(50) NOT NULL,
  "username" VARCHAR(50) NOT NULL,
  "password" VARCHAR(64) NULL DEFAULT NULL,
  "salt" VARCHAR(64) NULL DEFAULT NULL,
  "enabled" BOOLEAN NOT NULL,
  PRIMARY KEY ("id")
);