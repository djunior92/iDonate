CREATE TABLE `recharge` (
  `id` bigint(20) NOT NULL,
  `date_recharge` datetime NOT NULL,
  `profile_id` bigint(20) NOT NULL,
  `value_recharged` decimal(12,2) NOT NULL,
  `value_rate` decimal(12,2),
  `points_recharged` int NOT NULL,
  `quotation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `recharge` ADD CONSTRAINT `fk_recharge_profile` FOREIGN KEY (`profile_id`) REFERENCES profile(id);
ALTER TABLE `recharge` ADD CONSTRAINT `fk_recharge_quotation` FOREIGN KEY (`quotation_id`) REFERENCES quotation(id);


