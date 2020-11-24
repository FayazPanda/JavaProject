INSERT INTO `ims`.`customer` (`firstname`, `surname`) VALUES ('jordan', 'patel');
INSERT INTO `ims`.`item` (`name`, `value`) VALUES ('figure', 99.99);
INSERT INTO `ims`.`item` (`name`, `value`) VALUES ('cup', 4.99);
INSERT INTO `ims`.`order` (`user_id`) VALUES (1);
INSERT INTO `ims`.`order_item` (`order_id`, `item_id`, `quantity`) VALUES (1, 1, 5);
INSERT INTO `ims`.`order_item` (`order_id`, `item_id`, `quantity`) VALUES (1, 2, 3);
