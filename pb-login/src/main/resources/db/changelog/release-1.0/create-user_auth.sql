--liquibase formatted sql

--changeset rohit:112
SET foreign_key_checks = 0;
DROP TABLE IF EXISTS user_auth;
SET foreign_key_checks = 1;

CREATE TABLE user_auth (
  id INT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(45) NOT NULL,
  password VARCHAR(255) NOT NULL,
  roles VARCHAR(255) NOT NULL,
  active BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);
--rollback drop table user_auth;