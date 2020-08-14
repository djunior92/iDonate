CREATE TABLE `donation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_donation` datetime NOT NULL,
  `donor_id` bigint(20) NOT NULL,
  `benefited_id` bigint(20),
  `campaign_id` bigint(20),
  `donated_points` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `donation` ADD CONSTRAINT `fk_donation_profile_donor` FOREIGN KEY (`donor_id`) REFERENCES profile(id);
ALTER TABLE `donation` ADD CONSTRAINT `fk_donation_profile_benefited` FOREIGN KEY (`benefited_id`) REFERENCES profile(id);
ALTER TABLE `donation` ADD CONSTRAINT `fk_donation_campaign` FOREIGN KEY (`campaign_id`) REFERENCES campaign(id);

