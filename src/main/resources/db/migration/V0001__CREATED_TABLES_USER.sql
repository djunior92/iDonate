CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(70) NOT NULL UNIQUE KEY,
  `login` varchar(20) NOT NULL UNIQUE KEY,
  `passw` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `registrationDate` datetime NOT NULL,
  `validationDate` datetime,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nameRole` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `users_role` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  UNIQUE KEY `UK_role` (`role_id`),
  UNIQUE KEY `unique_role_user` (`userId`,`roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;