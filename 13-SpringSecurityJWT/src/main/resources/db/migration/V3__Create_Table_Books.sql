CREATE TABLE IF NOT EXISTS `books` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `book_name` VARCHAR(50) NOT NULL,
    `author_name` VARCHAR(50) NOT NULL,
    `number_of_pages` INT NOT NULL
);