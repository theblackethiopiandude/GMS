--
-- File generated with SQLiteStudio v3.4.4 on Thu Sep 28 13:32:07 2023
--
-- Text encoding used: System
--

-- Table: Cars
CREATE TABLE IF NOT EXISTS Cars (
    PlateNo VARCHAR(15) PRIMARY KEY,
    Make VARCHAR(50),
    Model VARCHAR(50),
    VIN VARCHAR(17),
    CustomerID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customers(ID)
);
INSERT INTO Cars (PlateNo, Make, Model, VIN, CustomerID) VALUES ('02-C29534-AA', 'Toyota', 'Tacoma', '1FMJK1F5XAE084591', 3);
INSERT INTO Cars (PlateNo, Make, Model, VIN, CustomerID) VALUES ('03-B25750-AA', 'Hyundai', 'Starex', 'JN1BJ0HR2DM808165', 2);
INSERT INTO Cars (PlateNo, Make, Model, VIN, CustomerID) VALUES ('02-A42229-AA', 'Chevrolet', 'Camaro', '1G4PP5SK5C4020808', 1);

-- Table: Customers
CREATE TABLE IF NOT EXISTS Customers (
    ID INTEGER PRIMARY KEY,
    Name VARCHAR(255),
    PhoneNumber VARCHAR(20),
    Address VARCHAR(255)
);
INSERT INTO Customers (ID, Name, PhoneNumber, Address) VALUES (1, 'Valle Minchella', '945-443-4071', '3738 Forest Run Road');
INSERT INTO Customers (ID, Name, PhoneNumber, Address) VALUES (2, 'Nonnah Feather', '728-423-1109', '62446 Maywood Avenue');
INSERT INTO Customers (ID, Name, PhoneNumber, Address) VALUES (3, 'Aurora Abrahmer', '418-866-4660', '293 Donald Way');

-- Table: History
CREATE TABLE IF NOT EXISTS History (
    ID INTEGER PRIMARY KEY,
    PlateNo VARCHAR(15),
    ServiceID INT,
    Notes VARCHAR(255),
    FOREIGN KEY (PlateNo) REFERENCES Cars(PlateNo),
    FOREIGN KEY (ServiceID) REFERENCES Services(ID)
);
INSERT INTO History (ID, PlateNo, ServiceID, Notes) VALUES (1, '02-A42229-AA', 2, 'New Brake Pads Installed');

-- Table: Services
CREATE TABLE IF NOT EXISTS Services (
    ID INTEGER PRIMARY KEY,
    Description VARCHAR(255)
);
INSERT INTO Services (ID, Description) VALUES (1, 'Oil Change');
INSERT INTO Services (ID, Description) VALUES (2, 'Brake Replacement');
INSERT INTO Services (ID, Description) VALUES (3, 'Transmission Service');
INSERT INTO Services (ID, Description) VALUES (4, 'Engine Diagnostic');
INSERT INTO Services (ID, Description) VALUES (5, 'Electrical Repairs');
INSERT INTO Services (ID, Description) VALUES (6, 'Oil Change');
INSERT INTO Services (ID, Description) VALUES (7, 'Brake Replacement');
INSERT INTO Services (ID, Description) VALUES (8, 'Transmission Service');
INSERT INTO Services (ID, Description) VALUES (9, 'Engine Diagnostic');
INSERT INTO Services (ID, Description) VALUES (10, 'Electrical Repairs');

-- View: CarHistory
CREATE VIEW IF NOT EXISTS CarHistory AS
SELECT History.ID, Services.Description AS Service, History.Notes, History.PlateNo
FROM History
JOIN Services ON History.ServiceID = Services.ID;

-- View: CarInfo
CREATE VIEW IF NOT EXISTS CarInfo AS
SELECT *
FROM Customers
JOIN Cars ON Customers.ID = Cars.CustomerID;

-- View: CarSearch
CREATE VIEW IF NOT EXISTS CarSearch AS
SELECT Customers.Name, Cars.PlateNo, Customers.PhoneNumber, Cars.Make, Cars.Model
FROM Customers
JOIN Cars ON Customers.ID = Cars.CustomerID;

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
