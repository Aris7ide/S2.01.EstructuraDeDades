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

CREATE TABLE sales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    glasses_id INT NOT NULL,
    client_id INT NOT NULL,
    date DATE NOT NULL,

    CONSTRAINT fk_sale_glasses FOREIGN KEY (glasses_id) REFERENCES glasses(id),
    CONSTRAINT fk_sale_client FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    phone VARCHAR(30),
    date DATE NOT NULL
);

    INSERT INTO provider (name, street, number, floor, door, city, postal_code, country, phone, fax, nif) VALUES
('Ópticas Centrales S.A.', 'Calle Mayor', '12', '1', 'A', 'Barcelona', '08001', 'España', '933000001', '933000002', 'A12345678'),
('Vision Global', 'Avenida Diagonal', '455', '3', '2', 'Barcelona', '08036', 'España', '934000001', '934000002', 'B87654321'),
('Medical Lens SL', 'Calle Valencia', '200', 'Bajo', '', 'Valencia', '46001', 'España', '963111222', '963111223', 'C11223344'),
('Gafas Express', 'Gran Vía', '50', '2', '4', 'Madrid', '28013', 'España', '915444555', '915444556', 'A99887766'),
('Òptica del Norte', 'Calle Real', '5', '', '', 'Bilbao', '48001', 'España', '944666777', NULL, 'B55443322'),
('Solaris Eyewear', 'Passeig de Gràcia', '88', 'Pral', '1', 'Barcelona', '08008', 'España', '932223344', '932223345', 'A33221144'),
('Distribuidores Ópticos', 'Calle Serrano', '110', '4', 'Izq', 'Madrid', '28006', 'España', '915112233', NULL, 'C99882211'),
('Eco Glasses', 'Calle Betis', '15', 'Bajo', '', 'Sevilla', '41010', 'España', '954223344', NULL, 'B77665544'),
('Moda Visual', 'Calle Larios', '22', '1', 'B', 'Málaga', '29015', 'España', '952334455', '952334456', 'A55667788'),
('Lux Eye Tech', 'Paseo de la Independencia', '8', '2', '', 'Zaragoza', '50004', 'España', '976112233', NULL, 'C12398764');


INSERT INTO client (name, address, phone, email, created, recommended_by_id, employee) VALUES
                                                                                           ('Ana García', 'Calle Aribau 45, Barcelona', 600111222, 'ana.garcia@email.com', '2026-01-10 10:00:00', NULL, 'Laura Martí'),
                                                                                           ('Carlos Pérez', 'Paseo de Gracia 12, Barcelona', 600333444, 'carlos.perez@email.com', '2026-01-12 11:30:00', 1, 'Laura Martí'),
                                                                                           ('Lucía Gómez', 'Calle Mayor 8, Madrid', 600555666, 'lucia.gomez@email.com', '2026-01-15 09:15:00', NULL, 'Jordi Puig'),
                                                                                           ('David Ruiz', 'Avenida América 34, Madrid', 600777888, 'david.ruiz@email.com', '2026-01-20 16:45:00', 2, 'Jordi Puig'),
                                                                                           ('Marta Gil', 'Calle Colón 50, Valencia', 600999000, 'marta.gil@email.com', '2026-02-01 12:00:00', NULL, 'Laura Martí'),
                                                                                           ('Jorge Sanz', 'Calle San Vicente 5, Valencia', 601111222, 'jorge.sanz@email.com', '2026-02-05 14:20:00', 5, 'Jordi Puig'),
                                                                                           ('Elena Torres', 'Gran Vía 80, Bilbao', 601333444, 'elena.torres@email.com', '2026-02-10 17:10:00', NULL, 'Laura Martí'),
                                                                                           ('Sergio Ramos', 'Calle Betis 10, Sevilla', 601555666, 'sergio.ramos@email.com', '2026-02-15 13:00:00', 3, 'Jordi Puig'),
                                                                                           ('Claudia Valls', 'Calle Larios 4, Málaga', 601777888, 'claudia.valls@email.com', '2026-02-18 10:30:00', NULL, 'Laura Martí'),
                                                                                           ('Marc Ribas', 'Paseo Independencia 3, Zaragoza', 601999000, 'marc.ribas@email.com', '2026-02-20 18:00:00', 4, 'Jordi Puig');

INSERT INTO glasses (brand, left_graduation, right_graduation, frame_type, frame_color, left_lens_color, right_lens_color, price, provider_id) VALUES
                                                                                                                                                   ('Ray-Ban', -1.50, -1.75, 'pasta', 'Negro', 'Transparente', 'Transparente', 149.99, 1),
                                                                                                                                                   ('Oakley', 2.00, 2.25, 'flotante', 'Azul', 'Oscuro', 'Oscuro', 189.50, 1),
                                                                                                                                                   ('Arnette', -0.75, -0.50, 'pasta', 'Carey', 'Verde', 'Verde', 99.00, 2),
                                                                                                                                                   ('Carrera', -2.00, -2.00, 'metálica', 'Dorado', 'Transparente', 'Transparente', 159.00, 3),
                                                                                                                                                   ('Police', 1.25, 1.00, 'metálica', 'Gris', 'Transparente', 'Transparente', 130.00, 4),
                                                                                                                                                   ('Persol', -3.25, -3.00, 'pasta', 'Azul', 'Marrón', 'Marrón', 210.00, 5),
                                                                                                                                                   ('Prada', -0.50, -0.75, 'flotante', 'Negro', 'Transparente', 'Transparente', 280.00, 6),
                                                                                                                                                   ('Tom Ford', 1.50, 1.50, 'pasta', 'Carey', 'Transparente', 'Transparente', 250.00, 7),
                                                                                                                                                   ('Hawkers', -1.00, -1.25, 'pasta', 'Rosa', 'Rosa', 'Rosa', 59.99, 8),
                                                                                                                                                   ('Vogue', -2.50, -2.25, 'metálica', 'Plateado', 'Transparente', 'Transparente', 115.50, 9);

INSERT INTO sales (glasses_id, client_id, date) VALUES
                                                    (1, 1, '2026-02-01'),
                                                    (2, 2, '2026-02-02'),
                                                    (3, 3, '2026-02-03'),
                                                    (4, 4, '2026-02-05'),
                                                    (5, 5, '2026-02-08'),
                                                    (6, 6, '2026-02-10'),
                                                    (7, 7, '2026-02-12'),
                                                    (8, 8, '2026-02-15'),
                                                    (9, 9, '2026-02-18'),
                                                    (10, 10, '2026-02-20'),
                                                    (4, 4, '2025-02-05');

INSERT INTO employee (firstName, lastName, phone, date) VALUES
                                                            ('Juan', 'Pérez', '+34600123456', '2024-01-15'),
                                                            ('María', 'García', '+34600234567', '2024-03-20'),
                                                            ('Carlos', 'López', '+34600345678', '2024-05-10'),
                                                            ('Ana', 'Martínez', '+34600456789', '2024-07-01'),
                                                            ('David', 'Ruiz', '+34600567890', '2024-09-12');
