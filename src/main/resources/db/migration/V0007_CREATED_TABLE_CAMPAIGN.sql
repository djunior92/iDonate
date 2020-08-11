CREATE TABLE `campaign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime NOT NULL,
  `end_date` datetime,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `logo` text,
  `goal_points` integer NOT NULL,
  `points_received` integer NOT NULL,
  `perfil_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `campaign` ADD CONSTRAINT `fk_campaign_perfil` FOREIGN KEY (`perfil_id`) REFERENCES perfil(id);