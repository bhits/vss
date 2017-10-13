Alter TABLE value_set ADD vsac_value_set_name VARCHAR(255), ADD vsac_oid VARCHAR(255);
Alter TABLE value_set_aud ADD vsac_value_set_name VARCHAR(255), ADD vsac_oid VARCHAR(255);

ALTER TABLE coded_concept
CHANGE COLUMN `name` `name` MEDIUMTEXT NULL DEFAULT NULL ,
CHANGE COLUMN `description` `description` MEDIUMTEXT NULL DEFAULT NULL ;

ALTER TABLE coded_concept
  DROP INDEX `UK_ja446cnbaamiey4p3h1wi5w0r`;