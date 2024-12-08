-- init.sql

CREATE DATABASE IF NOT EXISTS cdk;

USE cdk;

CREATE TABLE dealers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);

-- Dealer 1
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('Metro Auto', '123 Main St', 'Los Angeles', 'CA', '90001', 34.052235, -118.243683);

-- Dealer 2
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('City Motors', '456 Sunset Blvd', 'Los Angeles', 'CA', '90028', 34.097803, -118.328661);

-- Dealer 3
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('Mountain View Cars', '789 Maple Dr', 'San Francisco', 'CA', '94102', 37.774929, -122.419418);

-- Dealer 4
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('Bay Area Motors', '321 Oak St', 'San Francisco', 'CA', '94103', 37.784929, -122.407832);

-- Dealer 5
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('Sunshine Auto', '555 Beach Ave', 'San Diego', 'CA', '92101', 32.715736, -117.161087);

-- Dealer 6
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('Desert Auto Sales', '123 Palm St', 'Phoenix', 'AZ', '85001', 33.448376, -112.074036);

-- Dealer 7
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('Red Rock Cars', '789 Canyon Blvd', 'Phoenix', 'AZ', '85004', 33.451036, -112.073043);

-- Dealer 8
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('Lone Star Auto', '111 Texas St', 'Austin', 'TX', '73301', 30.267153, -97.743061);

-- Dealer 9
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('Capitol Motors', '222 Congress Ave', 'Austin', 'TX', '73301', 30.274665, -97.740349);

-- Dealer 10
INSERT INTO dealers (name, address, city, state, zip_code, latitude, longitude)
VALUES ('Windy City Autos', '999 Michigan Ave', 'Chicago', 'IL', '60601', 41.878113, -87.629799);
