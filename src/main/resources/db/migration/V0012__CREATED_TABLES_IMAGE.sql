CREATE TABLE `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_image` datetime NOT NULL,
  `profile_id` bigint(20),
  `campaign_id` bigint(20),
  `name` varchar(256),
  `description` varchar(512),
  `image_file` longblob,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `comment` ADD CONSTRAINT `fk_comment_profile` FOREIGN KEY (`profile_id`) REFERENCES profile(id);
ALTER TABLE `comment` ADD CONSTRAINT `fk_comment_campaign` FOREIGN KEY (`campaign_id`) REFERENCES campaign(id);




