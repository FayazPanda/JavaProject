CREATE TABLE customer (
	user_id	INT(16) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    firstname TINYTEXT NOT NULL,
    surname TINYTEXT NOT NULL
);

CREATE TABLE item (
	item_id	INT(16) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    value DECIMAL(5,2) NOT NULL
);

CREATE TABLE order (
	order_id INT(16) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    FOREIGN KEY (user_id) REFERENCES customer(user_id)
);

CREATE TABLE orders_item (
	FOREIGN KEY (order_id) REFERENCES order(order_id),
	FOREIGN KEY (item_id) REFERENCES item(item_id),
	quantity INT(8) NOT NULL
);