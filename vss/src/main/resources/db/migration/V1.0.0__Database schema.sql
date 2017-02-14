--
-- Table structure for table `code_system_aud`
--
CREATE TABLE code_system_aud (
  id              BIGINT  NOT NULL,
  rev             INTEGER NOT NULL,
  revtype         TINYINT,
  code            VARCHAR(255),
  name            VARCHAR(255),
  code_system_oid VARCHAR(255),
  display_name    VARCHAR(255),
  PRIMARY KEY (id, rev)
)
  ENGINE = InnoDB;

--
-- Table structure for table `code_system_version_aud`
--
CREATE TABLE code_system_version_aud (
  id             BIGINT  NOT NULL,
  rev            INTEGER NOT NULL,
  revtype        TINYINT,
  description    VARCHAR(255),
  version_name   VARCHAR(255),
  version_order  INTEGER,
  code_system_id BIGINT,
  PRIMARY KEY (id, rev)
)
  ENGINE = InnoDB;

--
-- Table structure for table `coded_concept_aud`
--
CREATE TABLE coded_concept_aud (
  id                     BIGINT  NOT NULL,
  rev                    INTEGER NOT NULL,
  revtype                TINYINT,
  code                   VARCHAR(255),
  name                   VARCHAR(255),
  description            VARCHAR(255),
  code_system_version_id BIGINT,
  PRIMARY KEY (id, rev)
)
  ENGINE = InnoDB;

--
-- Table structure for table `coded_concept_value_set`
--
CREATE TABLE coded_concept_value_set (
  coded_concept_id BIGINT NOT NULL,
  value_set_id     BIGINT NOT NULL
)
  ENGINE = InnoDB;

--
-- Table structure for table `coded_concept`
--
CREATE TABLE coded_concept (
  id                     BIGINT NOT NULL AUTO_INCREMENT,
  code                   VARCHAR(255),
  name                   VARCHAR(255),
  description            VARCHAR(255),
  code_system_version_id BIGINT,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

--
-- Table structure for table `code_system`
--
CREATE TABLE code_system (
  id              BIGINT       NOT NULL AUTO_INCREMENT,
  code            VARCHAR(255),
  name            VARCHAR(255),
  code_system_oid VARCHAR(255) NOT NULL,
  display_name    VARCHAR(255),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

--
-- Table structure for table `code_system_version`
--
CREATE TABLE code_system_version (
  id             BIGINT       NOT NULL AUTO_INCREMENT,
  description    VARCHAR(255),
  version_name   VARCHAR(255) NOT NULL,
  version_order  INTEGER      NOT NULL,
  code_system_id BIGINT,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

--
-- Table structure for table `revinfo`
--
CREATE TABLE revinfo (
  id        INTEGER NOT NULL AUTO_INCREMENT,
  timestamp BIGINT  NOT NULL,
  username  VARCHAR(255),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

--
-- Table structure for table `value_set_aud`
--
CREATE TABLE value_set_aud (
  id                    BIGINT  NOT NULL,
  rev                   INTEGER NOT NULL,
  revtype               TINYINT,
  code                  VARCHAR(255),
  name                  VARCHAR(255),
  description           VARCHAR(255),
  value_set_category_id BIGINT,
  PRIMARY KEY (id, rev)
)
  ENGINE = InnoDB;

--
-- Table structure for table `value_set_category_aud`
--
CREATE TABLE value_set_category_aud (
  id            BIGINT  NOT NULL,
  rev           INTEGER NOT NULL,
  revtype       TINYINT,
  code          VARCHAR(255),
  name          VARCHAR(255),
  description   VARCHAR(5000),
  display_order INTEGER,
  is_federal    BIT,
  PRIMARY KEY (id, rev)
)
  ENGINE = InnoDB;

--
-- Table structure for table `value_set`
--
CREATE TABLE value_set (
  id                    BIGINT NOT NULL AUTO_INCREMENT,
  code                  VARCHAR(255),
  name                  VARCHAR(255),
  description           VARCHAR(255),
  value_set_category_id BIGINT,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

--
-- Table structure for table `value_set_category`
--
CREATE TABLE value_set_category (
  id            BIGINT  NOT NULL AUTO_INCREMENT,
  code          VARCHAR(255),
  name          VARCHAR(255),
  description   VARCHAR(5000),
  display_order INTEGER NOT NULL,
  is_federal    BIT     NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

ALTER TABLE coded_concept
  ADD CONSTRAINT UK9vk93un9jpt1res1swfnda56d UNIQUE (code, code_system_version_id);
ALTER TABLE coded_concept
  ADD CONSTRAINT UK_ja446cnbaamiey4p3h1wi5w0r UNIQUE (code);
ALTER TABLE code_system
  ADD CONSTRAINT UK_lhl7tvg1rtoo4adokvf2xwucp UNIQUE (code);
ALTER TABLE code_system
  ADD CONSTRAINT UK_7j0saojr9joix0quj9a65492x UNIQUE (code_system_oid);
CREATE INDEX version_order_idx
  ON code_system_version (version_order);
ALTER TABLE value_set
  ADD CONSTRAINT UK_qqdh21ud433ry6ybr0mwnu6c2 UNIQUE (code);
ALTER TABLE value_set_category
  ADD CONSTRAINT UK_ei6kilxu5rvqu6h6tvsqgqtyh UNIQUE (code);
ALTER TABLE code_system_aud
  ADD CONSTRAINT FK6thihw90qgat59ct81t2ax5fg FOREIGN KEY (rev) REFERENCES revinfo (id);
ALTER TABLE code_system_version_aud
  ADD CONSTRAINT FKat2nhjkv7fafgx9wxk7vpxoim FOREIGN KEY (rev) REFERENCES revinfo (id);
ALTER TABLE coded_concept_aud
  ADD CONSTRAINT FKrfq9igs19yod5kutwtt21vg6 FOREIGN KEY (rev) REFERENCES revinfo (id);
ALTER TABLE coded_concept_value_set
  ADD CONSTRAINT FK9jbtngauqki2ukginep3wc6bj FOREIGN KEY (value_set_id) REFERENCES value_set (id);
ALTER TABLE coded_concept_value_set
  ADD CONSTRAINT FKqdagbyuuubiqypoqd1qtod5m4 FOREIGN KEY (coded_concept_id) REFERENCES coded_concept (id);
ALTER TABLE coded_concept
  ADD CONSTRAINT FKgk7qovubrhf383salf2a67i7y FOREIGN KEY (code_system_version_id) REFERENCES code_system_version (id);
ALTER TABLE code_system_version
  ADD CONSTRAINT FKk0ho4t4jy73dxbuy1aw2du43c FOREIGN KEY (code_system_id) REFERENCES code_system (id);
ALTER TABLE value_set_aud
  ADD CONSTRAINT FKolu81t0x861e0j0a7y6gnc7n0 FOREIGN KEY (rev) REFERENCES revinfo (id);
ALTER TABLE value_set_category_aud
  ADD CONSTRAINT FK7q2dn9tsbgfsv845gp22j8118 FOREIGN KEY (rev) REFERENCES revinfo (id);
ALTER TABLE value_set
  ADD CONSTRAINT FKn2ydxxbemcyr9ecsfpq95rf13 FOREIGN KEY (value_set_category_id) REFERENCES value_set_category (id);