DROP DATABASE IF EXISTS cul_d_ampolla;
CREATE DATABASE cul_d_ampolla CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cul_d_ampolla;

CREATE TABLE provider (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          street VARCHAR(100) NOT NULL,
                          number VARCHAR(10) NOT NULL,
                          floor VARCHAR(10),
                          door VARCHAR(10),
                          city VARCHAR(50) NOT NULL,
                          postal_code VARCHAR(10) NOT NULL,
                          country VARCHAR(50) NOT NULL,
                          phone VARCHAR(15) NOT NULL,
                          fax VARCHAR(15),
                          nif VARCHAR(9) UNIQUE NOT NULL
);

CREATE TABLE client (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        address VARCHAR(150) NOT NULL,
                        phone INT NOT NULL,
                        email VARCHAR(100),
                        created DATETIME NOT NULL,
                        recommended_by_id INT DEFAULT NULL,
                        employee VARCHAR(50),

                        CONSTRAINT fk_client_recommender
                            FOREIGN KEY (recommended_by_id)
                                REFERENCES client(id)
                                ON DELETE SET NULL
);

CREATE TABLE glasses (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         brand VARCHAR(50) NOT NULL,
                         left_graduation DECIMAL(4,2) NOT NULL,
                         right_graduation DECIMAL(4,2) NOT NULL,
                         frame_type ENUM('flotante', 'pasta', 'metálica') NOT NULL,
                         frame_color VARCHAR(30) NOT NULL,
                         left_lens_color VARCHAR(30) NOT NULL,
                         right_lens_color VARCHAR(30) NOT NULL,
                         price DECIMAL(8,2) NOT NULL,
                         provider_id INT NOT NULL,

                         CONSTRAINT fk_glasses_provider
                             FOREIGN KEY (provider_id)
                                 REFERENCES provider(id)
                                 ON DELETE RESTRICT
);