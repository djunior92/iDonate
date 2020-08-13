CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `profile_id` bigint(20),
  `email` varchar(70) NOT NULL UNIQUE KEY,
  `login` varchar(20) NOT NULL UNIQUE KEY,
  `passw` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `registration_date` datetime NOT NULL,
  `validation_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `user` ADD CONSTRAINT `fk_user_profile` FOREIGN KEY (`profile_id`) REFERENCES profile(id);

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `users_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_role` (`role_id`),
  UNIQUE KEY `unique_role_user` (`user_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;