--liquibase formatted sql

--changeset rohit:2
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);
--rollback drop table user;