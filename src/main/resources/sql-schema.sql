drop schema ims;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customer` (
                                                user_id	INT(16) AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                                firstname TINYTEXT NOT NULL,
                                                surname TINYTEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `ims`.`item` (
                                            item_id	INT(16) AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                            name VARCHAR(40) NOT NULL,
                                            value DECIMAL(5,2) NOT NULL
);
CREATE TABLE IF NOT EXISTS `ims`.`order` (
                                             order_id INT(16) AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                             user_id	INT(16) NOT NULL
);
CREATE TABLE IF NOT EXISTS `ims`.`order_item` (
                                                  order_id INT(16) NOT NULL,
                                                  item_id	INT(16) NOT NULL,
                                                  quantity INT(8) NOT NULL
);
