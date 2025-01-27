CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `first_name` varchar(225) NOT NULL,
  `last_name` varchar(225) NOT NULL,
  `address` varchar(225) NOT NULL,
  `gender` varchar(6) NOT NULL
);