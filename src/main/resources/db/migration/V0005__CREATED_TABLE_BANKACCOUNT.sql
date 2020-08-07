CREATE TABLE `bank_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `perfil_id` bigint(20) NOT NULL,
  `account_type` char(1) NOT NULL,
  `bank` varchar(100) NOT NULL,
  `agency` varchar(6) NOT NULL,
  `dg_agency` varchar(2),
  `number_account` varchar(10) NOT NULL,
  `dg_account` varchar(2),
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `bank_account` ADD CONSTRAINT `fk_bankAccount_perfil` FOREIGN KEY (`perfil_id`) REFERENCES perfil(id);