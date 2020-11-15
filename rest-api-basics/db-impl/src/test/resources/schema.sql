
-- -----------------------------------------------------
-- Schema gift-certificates-db
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS "gift-certificates-db" AUTHORIZATION sa;
CREATE SEQUENCE IF NOT EXISTS "gift-certificates-db"."tag" START WITH 1 INCREMENT BY 1;
-- -----------------------------------------------------
-- Table `gift-certificates-db`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tag
(
    id   BIGINT(19)   NOT NULL AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL

);


-- -----------------------------------------------------
-- Table `gift-certificates-db`.`gift_certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS gift_certificate
(
    id               BIGINT(19)    NOT NULL AUTO_INCREMENT,
    name            VARCHAR(100)  NOT NULL,
    description      VARCHAR(2000) NOT NULL,
    price            DECIMAL       NOT NULL,
    create_date      TIMESTAMP     NOT NULL,
    last_update_date TIMESTAMP     NOT NULL,
    duration         INT     NOT NULL,
    PRIMARY KEY (id)
);


-- -----------------------------------------------------
-- Table `gift-certificates-db`.`gift_certificate_has_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS gift_certificate_has_tag (
  gift_certificate_id BIGINT(19) NOT NULL,
  tag_id BIGINT(19) NOT NULL,
  PRIMARY KEY (gift_certificate_id, tag_id),
  CONSTRAINT fk_gift_certificate_has_tag_gift_certificate
    FOREIGN KEY (gift_certificate_id)
    REFERENCES gift_certificate (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_gift_certificate_has_tag_tag1
    FOREIGN KEY (tag_id)
    REFERENCES tag (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



