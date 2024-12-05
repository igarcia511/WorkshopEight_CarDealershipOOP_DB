DROP DATABASE IF EXISTS dealership_ws;
CREATE DATABASE IF NOT EXISTS  dealership_ws;
USE dealership_ws;

CREATE TABLE `dealership` (
  `dealership_id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone` varchar(20),
  CONSTRAINT `pk_dealership` PRIMARY KEY (`dealership_id`)
);


CREATE TABLE `vehicle` (
  `vin`   varchar(255) NOT NULL,
  `year`  INT,
  `make`  varchar(255),
  `model` varchar(255),
  `type`  varchar(255),
  `color` varchar(255),
  `odometer` INT,
  `price`   DOUBLE,
  `sold`    boolean,
  `dealership_id` INT,
  CONSTRAINT `pk_vin` PRIMARY KEY (`vin`),
  FOREIGN KEY (`dealership_id`) REFERENCES dealership(`dealership_id`)
);

INSERT INTO `dealership` (`name`, `address`, `phone`) VALUES
('ABC Motors', '123 Main St, Cityville', '555-123-4567'),
('Luxury Autos', '456 Elm St, Townsville', '555-567-3458'),
('Super Deals Cars', '789 Oak St, Villagetown', '555-987-5436');


INSERT INTO `vehicle` (`vin`, `year`, `make`, `model`, `type`, `color`, `odometer`, `price`, `sold`, `dealership_id`) VALUES

('1HGBH41JXMN109186', 2022, 'Toyota', 'Corolla', 'Sedan', 'Blue', 15000, 20000, false, 1),
('2HGFG1A53EH543892', 2023, 'Honda', 'Civic', 'Sedan', 'Red', 12000, 22000, true, 1),
('3FA6P0H70JR188216', 2021, 'Ford', 'Focus', 'Sedan', 'Black', 20000, 18000, false, 1),
('1G1ZD5ST9JF214823', 2022, 'Chevrolet', 'Malibu', 'Sedan', 'Silver', 18000, 21000, false, 1),
('5NPD84LF8KH336974', 2023, 'Hyundai', 'Elantra', 'Sedan', 'White', 5000, 23000, false, 1),
('1N4AL3AP0JC220593', 2021, 'Nissan', 'Altima', 'Sedan', 'Grey', 22000, 19000, true, 1),
('4T1B11HK6LU227533', 2020, 'Toyota', 'Camry', 'Sedan', 'Green', 30000, 24000, false, 1),
('JM1BN1W77M1234567', 2023, 'Mazda', 'Mazda3', 'Hatchback', 'Orange', 8000, 25000, true, 1),
('1FA6P0H78JG228176', 2022, 'Ford', 'Fusion', 'Sedan', 'Yellow', 17000, 21500, false, 1),
('3VWF17AT5HM232095', 2023, 'Volkswagen', 'Jetta', 'Sedan', 'Blue', 9000, 24000, false, 1),
('5XXGT4L39KG227529', 2021, 'Kia', 'Optima', 'Sedan', 'Purple', 25000, 20500, true, 1),
('1HGCV1F31JA123456', 2020, 'Honda', 'Accord', 'Sedan', 'Red', 33000, 23000, false, 1),
('2G1WB58KX38123456', 2023, 'Chevrolet', 'Impala', 'Sedan', 'Black', 12000, 26000, false, 1),
('4S3BNAT64F3234567', 2021, 'Subaru', 'Legacy', 'Sedan', 'White', 19000, 20000, false, 1),
('2C3CDZAG1GH232323', 2022, 'Chrysler', '300', 'Sedan', 'Silver', 16000, 25000, true, 1),


('WBA3A9C56DF123456', 2023, 'BMW', '3 Series', 'Sedan', 'Black', 10000, 35000, false, 2),
('1C3CCBFB0FN122333', 2022, 'Mercedes-Benz', 'C-Class', 'Sedan', 'Silver', 8000, 38000, true, 2),
('WAUZ2AF32EA456789', 2023, 'Audi', 'A4', 'Sedan', 'White', 7000, 37000, false, 2),
('JTHBK1GG8B2187654', 2021, 'Lexus', 'ES', 'Sedan', 'Blue', 15000, 33000, false, 2),
('WP1AB2A57FL560123', 2023, 'Porsche', 'Macan', 'SUV', 'Red', 12000, 60000, false, 2),
('SAJEA51A0FCP48965', 2021, 'Jaguar', 'XE', 'Sedan', 'Grey', 16000, 35000, true, 2),
('5YJ3E1EA9JF318456', 2022, 'Tesla', 'Model 3', 'Sedan', 'Black', 4000, 45000, true, 2),
('SALFR2BG1EH146981', 2021, 'Land Rover', 'Discovery', 'SUV', 'Silver', 18000, 52000, false, 2),
('YV440MHL5B1234567', 2023, 'Volvo', 'XC60', 'SUV', 'Green', 3000, 47000, false, 2),
('5UXKR6C56H0328479', 2022, 'BMW', 'X5', 'SUV', 'Blue', 10000, 55000, false, 2),
('4JGDA5HB5KA244784', 2023, 'Mercedes-Benz', 'GLE', 'SUV', 'White', 5000, 58000, true, 2),
('WA1LAAF77HD263146', 2021, 'Audi', 'Q5', 'SUV', 'Yellow', 14000, 48000, false, 2),
('SADCJ2BV5HA221112', 2022, 'Jaguar', 'F-Pace', 'SUV', 'Purple', 12000, 50000, false, 2),
('5YJ3E1EA5KF123456', 2021, 'Tesla', 'Model Y', 'SUV', 'Orange', 11000, 55000, true, 2),
('SALGW2TF1AA432189', 2023, 'Land Rover', 'Range Rover', 'SUV', 'Pink', 6000, 65000, false, 2),


('1FMCU0GD9KUC12345', 2020, 'Ford', 'Escape', 'SUV', 'Blue', 22000, 17000, false, 3),
('2GNAXKEV7L6145678', 2021, 'Chevrolet', 'Equinox', 'SUV', 'Silver', 20000, 18000, true, 3),
('JTMDFREV8J5302041', 2022, 'Toyota', 'RAV4', 'SUV', 'Red', 15000, 20000, false, 3),
('7FARW1H54ME121234', 2023, 'Honda', 'CR-V', 'SUV', 'White', 8000, 23000, false, 3),
('5N1AT2MV2LC617832', 2020, 'Nissan', 'Rogue', 'SUV', 'Grey', 28000, 19000, false, 3),
('JM3KFBDM9K0512345', 2022, 'Mazda', 'CX-5', 'SUV', 'Green', 10000, 21000, true, 3),
('4S4BSACC2H3409872', 2021, 'Subaru', 'Forester', 'SUV', 'Yellow', 10000,21000, true, 3);