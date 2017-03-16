CREATE TABLE value_set_category_system (
  id     BIGINT NOT NULL AUTO_INCREMENT,
  system VARCHAR(255),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE value_set_category_system_aud (
  id      BIGINT  NOT NULL,
  rev     INTEGER NOT NULL,
  revtype TINYINT,
  system  VARCHAR(255),
  PRIMARY KEY (id, rev)
)
  ENGINE = InnoDB;

ALTER TABLE value_set_category_system
  ADD CONSTRAINT UKb3e9q77v81onsgnhq09g8g6h2 UNIQUE (system);
ALTER TABLE value_set_category
  ADD value_set_category_system_id BIGINT NOT NULL;
ALTER TABLE value_set_category_aud
  ADD value_set_category_system_id BIGINT;
ALTER TABLE value_set_category
  ADD CONSTRAINT FK86aj26rwxaba94f2lvwxep8t3 FOREIGN KEY (value_set_category_system_id) REFERENCES value_set_category_system (id);
ALTER TABLE value_set_category_system_aud
  ADD CONSTRAINT FKtms34e25c129q9mxcqjv5chqa FOREIGN KEY (rev) REFERENCES revinfo (rev);