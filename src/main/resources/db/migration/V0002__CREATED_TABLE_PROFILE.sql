CREATE TABLE `profile` (
  `id` bigint(20) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` text,
  `registration_date` datetime NOT NULL,
  `facebook` varchar(255),
  `instagram` varchar(255),
  `youtube` varchar(255),
  `website` varchar(255),
  `my_points` int NOT NULL,
  `points_received` int NOT NULL,
  `people_type` char(1) NOT NULL,
  `document` varchar(14) NOT NULL,
  `date_birth` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

