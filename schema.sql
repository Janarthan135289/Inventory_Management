CREATE TABLE IF NOT EXISTS products(
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    product_description VARCHAR(255),
    product_price VARCHAR(20),
    product_buydate DATE,
    product_expirydate DATE
    );
