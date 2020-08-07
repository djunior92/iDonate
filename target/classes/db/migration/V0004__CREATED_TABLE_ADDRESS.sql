CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `perfil_id` bigint(20) NOT NULL,
  `street_address` varchar(100) NOT NULL,
  `number_address` varchar(6) NOT NULL,
  `complement_address` varchar(50),
  `cep` varchar(10) NOT NULL,
  `neighborhood` varchar(50) NOT NULL,
  `city` varchar(100) NOT NULL,
  `uf` char(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `address` ADD CONSTRAINT `fk_address_perfil` FOREIGN KEY (`perfil_id`) REFERENCES perfil(id);