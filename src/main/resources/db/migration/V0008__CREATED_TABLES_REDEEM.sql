CREATE TABLE `redeem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_redeem` datetime NOT NULL,
  `profile_id` bigint(20) NOT NULL,
  `value_redeemed` decimal(12,2) NOT NULL,
  `value_rate` decimal(12,2),
  `points_redeemed` int NOT NULL,
  `quotation_id` bigint(20) NOT NULL,
  `bank_account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `redeem` ADD CONSTRAINT `fk_redeem_profile` FOREIGN KEY (`profile_id`) REFERENCES profile(id);
ALTER TABLE `redeem` ADD CONSTRAINT `fk_redeem_quotation` FOREIGN KEY (`quotation_id`) REFERENCES quotation(id);
ALTER TABLE `redeem` ADD CONSTRAINT `fk_redeem_bank_account` FOREIGN KEY (`bank_account_id`) REFERENCES bank_account(id);


