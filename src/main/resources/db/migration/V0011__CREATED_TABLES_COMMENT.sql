CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_comment` datetime NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `profile_id` bigint(20),
  `campaign_id` bigint(20),
  `title` varchar(256),
  `description` varchar(512),
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `comment` ADD CONSTRAINT `fk_comment_profile_author` FOREIGN KEY (`author_id`) REFERENCES profile(id);
ALTER TABLE `comment` ADD CONSTRAINT `fk_comment_profile` FOREIGN KEY (`profile_id`) REFERENCES profile(id);
ALTER TABLE `comment` ADD CONSTRAINT `fk_comment_campaign` FOREIGN KEY (`campaign_id`) REFERENCES campaign(id);



