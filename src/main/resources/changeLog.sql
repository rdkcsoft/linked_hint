--liquibase formatted sql


--changeset dana:1

CREATE TABLE IF NOT EXISTS `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `val` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);
   
--rollback drop table test;
--------------------------------------------------------------------------