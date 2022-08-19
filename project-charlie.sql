DROP TABLE IF EXISTS cart_item;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS item;

BEGIN TRANSACTION;

CREATE TABLE user (
    user_id serial,
    user_name varchar(32) NOT NULL,
    user_pw varchar(32) NOT NULL,
    balance decimal(6,2) DEFAULT 0.00,
    address varchar(64) NOT NULL,
    role varchar(8) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE cart (
    cart_id serial,
    user_id integer NOT NULL,
    total decimal(6,2) NOT NULL,
    CONSTRAINT PK_cart PRIMARY KEY (cart_id),
    CONSTRAINT FK_cart_user FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE item (
    item_id serial,
    item_name varchar(64) NOT NULL,
    cost decimal(6,2) NOT NULL,
    item_type varchar(32) NOT NULL,
    size varchar(5) NOT NULL,
    color varchar(16) NOT NULL,
    stock integer NOT NULL,
    CONSTRAINT PK_item PRIMARY KEY (item_id),
    CONSTRAINT CHK_stock CHECK (stock > 0)
);

CREATE TABLE cart_item (
    cart_id integer NOT NULL,
    item_id integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT PK_cart_item PRIMARY KEY (cart_id, item_id),
    CONSTRAINT FK_cart_item_cart FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
    CONSTRAINT FK_cart_item_item FOREIGN KEY (item_id) REFERENCES item(item_id),
    CONSTRAINT CHK_quantity CHECK (quantity > 0)
);

COMMIT;

INSERT INTO item (item_name, cost, item_type, size, color, stock)
VALUES ('plain tee', 8.99, 'shirt', 'M', 'blue', 8),
 ('plain polo', 18.99, 'shirt', 'L', 'maroon', 6),
 ('chinos', 8.99, 'pants', '34-34', 'khaki', 12);