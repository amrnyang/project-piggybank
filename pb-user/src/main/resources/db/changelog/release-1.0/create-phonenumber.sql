--liquibase formatted sql

--changeset rohit:105
SET foreign_key_checks = 0;
DROP TABLE IF EXISTS phone_number;
SET foreign_key_checks = 1;

CREATE TABLE phone_number (
  id INT NOT NULL AUTO_INCREMENT,
  country_code INT NOT NULL,
  phone BIGINT(10) NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT pn_u_1 FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);
--rollback drop table phone_number;