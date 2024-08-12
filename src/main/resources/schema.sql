CREATE TABLE IF NOT EXISTS customer (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      number INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS product(
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
    price INTEGER NOT NULL
    );

CREATE TABLE IF NOT EXISTS customer_order(
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          id_buyer INTEGER NOT NULL,
                                          id_order INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS order_products(
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          id_order INTEGER NOT NULL,
                                          id_item INTEGER NOT NULL
);