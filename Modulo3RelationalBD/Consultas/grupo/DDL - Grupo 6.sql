-- DROP TABLES
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;
DROP TABLE MANAGER CASCADE CONSTRAINTS;
DROP TABLE DEALERSHIP CASCADE CONSTRAINTS;
DROP TABLE OUTLET CASCADE CONSTRAINTS;
DROP TABLE MODEL CASCADE CONSTRAINTS;
DROP TABLE ALLOWED_MODELS CASCADE CONSTRAINTS;
DROP TABLE BRAND_GROUP CASCADE CONSTRAINTS;
DROP TABLE FACTORY CASCADE CONSTRAINTS;
DROP TABLE CAR CASCADE CONSTRAINTS;
DROP TABLE PART_SUPPLIER CASCADE CONSTRAINTS;
DROP TABLE PART CASCADE CONSTRAINTS;
DROP TABLE SUPPLY CASCADE CONSTRAINTS;
DROP TABLE CAR_CARRIER CASCADE CONSTRAINTS;
DROP TABLE CAR_SHIPMENT CASCADE CONSTRAINTS;
DROP TABLE SALES CASCADE CONSTRAINTS;
DROP TABLE CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE ORGANIZATION CASCADE CONSTRAINTS;
DROP TABLE INDIVIDUAL_CLIENT CASCADE CONSTRAINTS;
DROP TABLE SURVEY CASCADE CONSTRAINTS;
DROP TABLE QUESTION CASCADE CONSTRAINTS;
DROP TABLE ANSWER_RADIO_CHOICE CASCADE CONSTRAINTS;
DROP TABLE ANSWER_MULTIPLECHOICE CASCADE CONSTRAINTS;
DROP TABLE ANSWER_NUMERIC CASCADE CONSTRAINTS;
DROP TABLE ANSWER_FREE_TEXT CASCADE CONSTRAINTS;
DROP TABLE GOOGLE_REVIEW CASCADE CONSTRAINTS;
DROP TABLE SEMANTIC_ANALYSIS CASCADE CONSTRAINTS;
DROP TABLE TOPIC CASCADE CONSTRAINTS;

-- CREATE TABLES WITHOUT FOREIGN KEYS

CREATE TABLE EMPLOYEE (
    Employee_ssn INT PRIMARY KEY, 
    Salary NUMBER(*, 2),
    Works_on_dealer_name VARCHAR2(30) NOT NULL,
    Works_on_outlet_name VARCHAR2(30) NOT NULL
);

CREATE TABLE MANAGER (
    Employee_ssn INT PRIMARY KEY, 
    Sales_target NUMBER(*, 2),
    Manages_dealer_name VARCHAR2(30) NOT NULL,
    Manages_outlet_name VARCHAR2(30) NOT NULL,
    Mgr_start_date DATE NOT NULL
);

CREATE TABLE DEALERSHIP (
    Dealer_name VARCHAR2(30) PRIMARY KEY,
    Dealer_cif CHAR(8) CHECK (LENGTH(Dealer_cif) = 8) NOT NULL
);

CREATE TABLE OUTLET (
    Dealer_name VARCHAR2(30),
    Outlet_name VARCHAR2(30),
    Cp CHAR(5) CHECK (REGEXP_LIKE(Cp, '^\d{5}$')) NOT NULL,
    City VARCHAR2(30) NOT NULL,
    Street VARCHAR2(30) NOT NULL,
    PRIMARY KEY (Dealer_name, Outlet_name)
);

CREATE TABLE MODEL (
    Model_name VARCHAR2(30),
    Brand VARCHAR2(30),
    "Version" VARCHAR2(30),
    Safety_score INT CHECK (Safety_score BETWEEN 1 AND 10), 
    PRIMARY KEY (Model_name, Brand, "Version")
);

CREATE TABLE ALLOWED_MODELS (
    Dealer_name VARCHAR2(30),
    Model_name VARCHAR2(30),
    Brand VARCHAR2(30),
    "Version" VARCHAR2(30),
    PRIMARY KEY (Dealer_name, Model_name, Brand, "Version")
);

CREATE TABLE BRAND_GROUP (
    Brand_name VARCHAR2(30) PRIMARY KEY,
    HQ_location VARCHAR2(30) NOT NULL,
    Director VARCHAR2(30)
);

CREATE TABLE FACTORY (
    Factory_name VARCHAR2(30) PRIMARY KEY,
    Location VARCHAR2(30) NOT NULL,
    Lines_quantity INT CHECK (Lines_quantity > 0), 
    Workforce_size INT CHECK (Workforce_size > 0), 
    Owned_by_brand_group_name VARCHAR2(30) NOT NULL
);

CREATE TABLE CAR (
    VIN VARCHAR2(30) PRIMARY KEY,
    Plaque VARCHAR2(7) CHECK (LENGTH(Plaque) = 7), 
    Color VARCHAR2(10) NOT NULL,
    Model_name VARCHAR2(30) NOT NULL,
    Brand VARCHAR2(30) NOT NULL,
    "Version" VARCHAR2(30) NOT NULL,
    Produced_factory_name VARCHAR2(30) NOT NULL
);

CREATE TABLE PART_SUPPLIER (
    Supplier_cif CHAR(8) PRIMARY KEY CHECK (LENGTH(Supplier_cif) = 8),
    Name VARCHAR2(30) NOT NULL,
    Location VARCHAR2(30)
);

CREATE TABLE PART (
    Part_id INT PRIMARY KEY, 
    Name VARCHAR2(30) NOT NULL,
    Description VARCHAR2(100)
);

CREATE TABLE SUPPLY (
    Factory_name VARCHAR2(30),
    Supplier_cif CHAR(8),
    Part_id INT, 
    Quantity INT CHECK (Quantity > 0) NOT NULL, 
    PRIMARY KEY (Factory_name, Supplier_cif, Part_id)
);

CREATE TABLE CAR_CARRIER (
    Carrier_Plaque VARCHAR2(7) PRIMARY KEY CHECK (LENGTH(Carrier_Plaque) = 7),
    Capacity INT CHECK (Capacity > 0) NOT NULL, 
    Supplier_name VARCHAR2(30) NOT NULL,
    Truck_model VARCHAR2(30)
);

CREATE TABLE CAR_SHIPMENT (
    Dealer_name VARCHAR2(30),
    Outlet_name VARCHAR2(30),
    Factory_name VARCHAR2(30),
    Carrier_Plaque VARCHAR2(7),
    VIN VARCHAR2(30),
    Shipment_date DATE NOT NULL,
    PRIMARY KEY (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN)
);

CREATE TABLE SALES (
    Customer_id INT, 
    Employee_ssn INT, 
    VIN VARCHAR2(30),
    Buy_date DATE NOT NULL,
    PRIMARY KEY (Customer_id, Employee_ssn, VIN)
);

CREATE TABLE CUSTOMER (
    Customer_id INT PRIMARY KEY,
    Phone_number VARCHAR2(30) CHECK (REGEXP_LIKE(Phone_number, '^\+?\d{7,15}$'))
);

CREATE TABLE ORGANIZATION (
    Org_cif CHAR(8) PRIMARY KEY CHECK (LENGTH(Org_cif) = 8), 
    Name VARCHAR2(30) NOT NULL,
    Activity VARCHAR2(30),
    Customer_id INT NOT NULL
);

CREATE TABLE INDIVIDUAL_CLIENT (
    Client_dni CHAR(9) PRIMARY KEY CHECK (LENGTH(Client_dni) = 9), 
    Cp CHAR(5) CHECK (REGEXP_LIKE(Cp, '^\d{5}$')),
    City VARCHAR2(30),
    Street VARCHAR2(30),
    Fname VARCHAR2(30) NOT NULL,
    Lname VARCHAR2(30) NOT NULL,
    Sex CHAR(1) CHECK (Sex IN ('F', 'M', 'O')), 
    Customer_id INT NOT NULL
);

CREATE TABLE SURVEY (
    Survey_id INT PRIMARY KEY, 
    Completed_date DATE NOT NULL,
    Completed_by_client_dni CHAR(9) NOT NULL
);

CREATE TABLE QUESTION (
    Survey_id INT,
    Question_code INT,
    Question_text VARCHAR2(50) NOT NULL,
    PRIMARY KEY (Survey_id, Question_code)
);

CREATE TABLE ANSWER_RADIO_CHOICE (
    Survey_id INT, 
    Question_code INT, 
    Yes_no VARCHAR2(3) CHECK (Yes_no IN ('Yes', 'No')) NOT NULL, 
    PRIMARY KEY (Survey_id, Question_code)
);

CREATE TABLE ANSWER_MULTIPLECHOICE (
    Survey_id INT, 
    Question_code INT, 
    Choice VARCHAR2(30) NOT NULL,
    PRIMARY KEY (Survey_id, Question_code)
);

CREATE TABLE ANSWER_NUMERIC (
    Survey_id INT, 
    Question_code INT, 
    Score INT CHECK (Score BETWEEN 1 AND 10) NOT NULL,
    PRIMARY KEY (Survey_id, Question_code)
);

CREATE TABLE ANSWER_FREE_TEXT (
    Survey_id INT, 
    Question_code INT,
    Text VARCHAR2(100),
    Text_id INT NOT NULL, 
    PRIMARY KEY (Survey_id, Question_code)
);

CREATE TABLE GOOGLE_REVIEW (
    Review_id INT PRIMARY KEY, 
    Completed_date DATE NOT NULL,
    Text VARCHAR2(255),
    Star_Rating INT CHECK (Star_Rating BETWEEN 1 AND 5) NOT NULL,  
    Text_id INT NOT NULL, 
    Written_by_client_dni CHAR(9) NOT NULL
);

CREATE TABLE SEMANTIC_ANALYSIS (
    Text_id INT PRIMARY KEY,
    Valid NUMBER(1) CHECK (Valid IN (0, 1)) NOT NULL,
    Sentiment VARCHAR2(10) CHECK (Sentiment IN ('Positive', 'Negative', 'Mixed', 'Neutral'))
);

CREATE TABLE TOPIC (
    Text_id INT, 
    Topic VARCHAR2(30),
    PRIMARY KEY (Text_id, Topic)
);


-- ADD DATA HERE

-- Insert data into EMPLOYEE
INSERT INTO EMPLOYEE (Employee_ssn, Salary, Works_on_dealer_name, Works_on_outlet_name)
VALUES (1001, 3000.00, 'AutoHouse', 'AH Madrid');
INSERT INTO EMPLOYEE (Employee_ssn, Salary, Works_on_dealer_name, Works_on_outlet_name)
VALUES (1002, 3200.00, 'AutoHouse', 'AH Madrid');
INSERT INTO EMPLOYEE (Employee_ssn, Salary, Works_on_dealer_name, Works_on_outlet_name)
VALUES (1003, 3500.00, 'CarWorld', 'CW Valencia');
INSERT INTO EMPLOYEE (Employee_ssn, Salary, Works_on_dealer_name, Works_on_outlet_name)
VALUES (1004, 3800.00, 'SpeedZone', 'SZ Sevilla');
INSERT INTO EMPLOYEE (Employee_ssn, Salary, Works_on_dealer_name, Works_on_outlet_name)
VALUES (1005, 3200.00, 'SpeedZone', 'SZ Sevilla');
INSERT INTO EMPLOYEE (Employee_ssn, Salary, Works_on_dealer_name, Works_on_outlet_name)
VALUES (1006, 3400.00, 'CarWorld', 'CW Valencia');
INSERT INTO EMPLOYEE (Employee_ssn, Salary, Works_on_dealer_name, Works_on_outlet_name)
VALUES (1007, 3100.00, 'SpeedZone', 'SZ Granada'); -- Employee in Granada
INSERT INTO EMPLOYEE (Employee_ssn, Salary, Works_on_dealer_name, Works_on_outlet_name)
VALUES (1008, 3400.00, 'CarWorld', 'CW Madrid'); -- Employee in Madrid

-- Insert data into MANAGER
INSERT INTO MANAGER (Employee_ssn, Sales_target, Manages_dealer_name, Manages_outlet_name, Mgr_start_date)
VALUES (1001, 50000.00, 'AutoHouse', 'AH Madrid', TO_DATE('2023-01-01', 'YYYY-MM-DD'));
INSERT INTO MANAGER (Employee_ssn, Sales_target, Manages_dealer_name, Manages_outlet_name, Mgr_start_date)
VALUES (1003, 55000.00, 'CarWorld', 'CW Valencia', TO_DATE('2023-02-01', 'YYYY-MM-DD'));
INSERT INTO MANAGER (Employee_ssn, Sales_target, Manages_dealer_name, Manages_outlet_name, Mgr_start_date)
VALUES (1004, 60000.00, 'SpeedZone', 'SZ Sevilla', TO_DATE('2023-03-01', 'YYYY-MM-DD'));

-- Insert data into DEALERSHIP
INSERT INTO DEALERSHIP (Dealer_name, Dealer_cif)
VALUES ('AutoHouse', 'AH123456');
INSERT INTO DEALERSHIP (Dealer_name, Dealer_cif)
VALUES ('CarWorld', 'CW654321');
INSERT INTO DEALERSHIP (Dealer_name, Dealer_cif)
VALUES ('SpeedZone', 'SZ234567');

-- Insert data into OUTLET
INSERT INTO OUTLET (Dealer_name, Outlet_name, Cp, City, Street)
VALUES ('AutoHouse', 'AH Madrid', '28001', 'Madrid', 'Calle Mayor 2');
INSERT INTO OUTLET (Dealer_name, Outlet_name, Cp, City, Street)
VALUES ('AutoHouse', 'AH Barcelona', '08001', 'Barcelona', 'Carrer de Gran Via 1');
INSERT INTO OUTLET (Dealer_name, Outlet_name, Cp, City, Street)
VALUES ('CarWorld', 'CW Valencia', '46001', 'Valencia', 'Avenida del Puerto 3');
INSERT INTO OUTLET (Dealer_name, Outlet_name, Cp, City, Street)
VALUES ('SpeedZone', 'SZ Sevilla', '41001', 'Sevilla', 'Calle San Fernando 4');
INSERT INTO OUTLET (Dealer_name, Outlet_name, Cp, City, Street)
VALUES ('SpeedZone', 'SZ Malaga', '29001', 'Malaga', 'Paseo del Parque 5');
INSERT INTO OUTLET (Dealer_name, Outlet_name, Cp, City, Street)
VALUES ('SpeedZone', 'SZ Granada', '18001', 'Granada', 'Plaza Nueva 10');
INSERT INTO OUTLET (Dealer_name, Outlet_name, Cp, City, Street)
VALUES ('CarWorld', 'CW Madrid', '28002', 'Madrid', 'Calle Velázquez 15');


-- Insert data into MODEL
INSERT INTO MODEL (Model_name, Brand, "Version", Safety_score)
VALUES ('Golf', 'Volkswagen', '2023', 9);
INSERT INTO MODEL (Model_name, Brand, "Version", Safety_score)
VALUES ('X5', 'BMW', '2023', 8);
INSERT INTO MODEL (Model_name, Brand, "Version", Safety_score)
VALUES ('Q7', 'Audi', '2023', 9);
INSERT INTO MODEL (Model_name, Brand, "Version", Safety_score)
VALUES ('Passat', 'Volkswagen', '2023', 8);

-- Insert data into ALLOWED_MODELS
INSERT INTO ALLOWED_MODELS (Dealer_name, Model_name, Brand, "Version")
VALUES ('AutoHouse', 'Golf', 'Volkswagen', '2023');
INSERT INTO ALLOWED_MODELS (Dealer_name, Model_name, Brand, "Version")
VALUES ('CarWorld', 'X5', 'BMW', '2023');
INSERT INTO ALLOWED_MODELS (Dealer_name, Model_name, Brand, "Version")
VALUES ('SpeedZone', 'Q7', 'Audi', '2023');
INSERT INTO ALLOWED_MODELS (Dealer_name, Model_name, Brand, "Version")
VALUES ('AutoHouse', 'Passat', 'Volkswagen', '2023');

-- Insert data into BRAND_GROUP
INSERT INTO BRAND_GROUP (Brand_name, HQ_location, Director)
VALUES ('Volkswagen', 'Wolfsburg, Germany', 'Herbert Diess');
INSERT INTO BRAND_GROUP (Brand_name, HQ_location, Director)
VALUES ('BMW', 'Munich, Germany', 'Oliver Zipse');
INSERT INTO BRAND_GROUP (Brand_name, HQ_location, Director)
VALUES ('Audi', 'Ingolstadt, Germany', 'Markus Duesmann');

-- Insert data into FACTORY
INSERT INTO FACTORY (Factory_name, Location, Lines_quantity, Workforce_size, Owned_by_brand_group_name)
VALUES ('Volkswagen Plant A', 'Wolfsburg, Germany', 10, 1500, 'Volkswagen');
INSERT INTO FACTORY (Factory_name, Location, Lines_quantity, Workforce_size, Owned_by_brand_group_name)
VALUES ('Volkswagen Plant B', 'Hanover, Germany', 12, 1700, 'Volkswagen');
INSERT INTO FACTORY (Factory_name, Location, Lines_quantity, Workforce_size, Owned_by_brand_group_name)
VALUES ('BMW Plant', 'Munich, Germany', 15, 2000, 'BMW');
INSERT INTO FACTORY (Factory_name, Location, Lines_quantity, Workforce_size, Owned_by_brand_group_name)
VALUES ('Audi Plant', 'Ingolstadt, Germany', 8, 1800, 'Audi');

-- Insert data into CAR
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN12345', 'ABC1234', 'Red', 'Golf', 'Volkswagen', '2023', 'Volkswagen Plant A');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN54321', 'DEF5678', 'White', 'Golf', 'Volkswagen', '2023', 'Volkswagen Plant A');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN67890', 'XYZ5678', 'Blue', 'X5', 'BMW', '2023', 'BMW Plant');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN98765', 'LMN4321', 'Black', 'Q7', 'Audi', '2023', 'Audi Plant');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN65432', 'UVW5678', 'Grey', 'Passat', 'Volkswagen', '2023', 'Volkswagen Plant B');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN34567', 'JKL1234', 'Silver', 'Golf', 'Volkswagen', '2023', 'Volkswagen Plant A');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN87654', 'MNO9876', 'Blue', 'X5', 'BMW', '2023', 'BMW Plant');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN45678', 'PQR4321', 'Red', 'Q7', 'Audi', '2023', 'Audi Plant');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN56789', 'STU6543', 'Black', 'Passat', 'Volkswagen', '2023', 'Volkswagen Plant B');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN67891', 'VWX3210', 'White', 'Golf', 'Volkswagen', '2023', 'Volkswagen Plant A');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN78912', 'ABC0987', 'Green', 'Passat', 'Volkswagen', '2023', 'Volkswagen Plant B');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN89012', 'DEF7654', 'Yellow', 'Q7', 'Audi', '2023', 'Audi Plant');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN90123', 'GHI5432', 'Blue', 'X5', 'BMW', '2023', 'BMW Plant');
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN112233', 'ABC7890', 'Red', 'Golf', 'Volkswagen', '2023', 'Volkswagen Plant A'); -- Same factory, model, and brand
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN223344', 'DEF8901', 'Blue', 'Golf', 'Volkswagen', '2023', 'Volkswagen Plant A'); -- Another car with same model, brand, and factory
INSERT INTO CAR (VIN, Plaque, Color, Model_name, Brand, "Version", Produced_factory_name)
VALUES ('VIN334455', 'XYZ5678', 'Black', 'X5', 'BMW', '2023', 'BMW Plant'); -- Different factory and brand



-- Insert data into PART_SUPPLIER
INSERT INTO PART_SUPPLIER (Supplier_cif, Name, Location)
VALUES ('DE123456', 'Bosch', 'Stuttgart, Germany');
INSERT INTO PART_SUPPLIER (Supplier_cif, Name, Location)
VALUES ('DE987654', 'Continental', 'Hannover, Germany');
INSERT INTO PART_SUPPLIER (Supplier_cif, Name, Location)
VALUES ('FR123789', 'Valeo', 'Paris, France');
INSERT INTO PART_SUPPLIER (Supplier_cif, Name, Location)
VALUES ('IT456321', 'Marelli', 'Milan, Italy');

-- Insert data into PART
INSERT INTO PART (Part_id, Name, Description)
VALUES (1, 'Brake Pads', 'High-performance brake pads.');
INSERT INTO PART (Part_id, Name, Description)
VALUES (2, 'Suspension Coil', 'Durable suspension coils.');
INSERT INTO PART (Part_id, Name, Description)
VALUES (3, 'Oil Filter', 'Engine oil filters.');
INSERT INTO PART (Part_id, Name, Description)
VALUES (4, 'Headlights', 'Advanced LED headlights.');
INSERT INTO PART (Part_id, Name, Description)
VALUES (5, 'Exhaust System', 'High-quality exhaust systems.');
INSERT INTO PART (Part_id, Name, Description)
VALUES (6, 'Clutch', 'Reliable clutch systems.');
INSERT INTO PART (Part_id, Name, Description)
VALUES (7, 'Windshield', 'Tempered glass windshields.');
INSERT INTO PART (Part_id, Name, Description)
VALUES (8, 'Radiator', 'Efficient radiators.');

-- Insert data into SUPPLY
INSERT INTO SUPPLY (Factory_name, Supplier_cif, Part_id, Quantity)
VALUES ('Volkswagen Plant A', 'DE123456', 1, 300);
INSERT INTO SUPPLY (Factory_name, Supplier_cif, Part_id, Quantity)
VALUES ('BMW Plant', 'DE987654', 2, 250);
INSERT INTO SUPPLY (Factory_name, Supplier_cif, Part_id, Quantity)
VALUES ('Audi Plant', 'FR123789', 3, 400);
INSERT INTO SUPPLY (Factory_name, Supplier_cif, Part_id, Quantity)
VALUES ('Volkswagen Plant B', 'IT456321', 4, 350);

-- Insert data into CAR_CARRIER
INSERT INTO CAR_CARRIER (Carrier_Plaque, Capacity, Supplier_name, Truck_model)
VALUES ('4321DEF', 50, 'Bosch', 'Scania R-Series');
INSERT INTO CAR_CARRIER (Carrier_Plaque, Capacity, Supplier_name, Truck_model)
VALUES ('8765JKL', 60, 'Continental', 'Volvo FH16');
INSERT INTO CAR_CARRIER (Carrier_Plaque, Capacity, Supplier_name, Truck_model)
VALUES ('5678XYZ', 55, 'Valeo', 'MAN TGX');
INSERT INTO CAR_CARRIER (Carrier_Plaque, Capacity, Supplier_name, Truck_model)
VALUES ('1234ABC', 70, 'Marelli', 'Mercedes Actros');

-- Insert data into CAR_SHIPMENT
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('AutoHouse', 'AH Madrid', 'Volkswagen Plant A', '4321DEF', 'VIN12345', TO_DATE('2023-06-01', 'YYYY-MM-DD'));
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('AutoHouse', 'AH Madrid', 'Volkswagen Plant A', '4321DEF', 'VIN54321', TO_DATE('2023-06-01', 'YYYY-MM-DD')); -- Same carrier carries another car
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('CarWorld', 'CW Valencia', 'BMW Plant', '8765JKL', 'VIN67890', TO_DATE('2023-06-15', 'YYYY-MM-DD'));
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('CarWorld', 'CW Valencia', 'BMW Plant', '8765JKL', 'VIN90123', TO_DATE('2023-06-15', 'YYYY-MM-DD')); -- Same carrier carries another car
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('SpeedZone', 'SZ Sevilla', 'Audi Plant', '5678XYZ', 'VIN98765', TO_DATE('2023-07-01', 'YYYY-MM-DD'));
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('SpeedZone', 'SZ Sevilla', 'Audi Plant', '5678XYZ', 'VIN89012', TO_DATE('2023-07-01', 'YYYY-MM-DD')); -- Same carrier carries another car
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('AutoHouse', 'AH Barcelona', 'Volkswagen Plant B', '1234ABC', 'VIN65432', TO_DATE('2023-07-10', 'YYYY-MM-DD'));
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('AutoHouse', 'AH Barcelona', 'Volkswagen Plant B', '1234ABC', 'VIN56789', TO_DATE('2023-07-10', 'YYYY-MM-DD')); -- Same carrier carries another car
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('AutoHouse', 'AH Madrid', 'Volkswagen Plant A', '4321DEF', 'VIN67891', TO_DATE('2023-06-05', 'YYYY-MM-DD'));
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('CarWorld', 'CW Valencia', 'BMW Plant', '8765JKL', 'VIN34567', TO_DATE('2023-06-20', 'YYYY-MM-DD'));
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('SpeedZone', 'SZ Malaga', 'Audi Plant', '5678XYZ', 'VIN45678', TO_DATE('2023-07-15', 'YYYY-MM-DD'));
INSERT INTO CAR_SHIPMENT (Dealer_name, Outlet_name, Factory_name, Carrier_Plaque, VIN, Shipment_date)
VALUES ('AutoHouse', 'AH Barcelona', 'Volkswagen Plant B', '1234ABC', 'VIN78912', TO_DATE('2023-08-01', 'YYYY-MM-DD'));


-- Insert data into SALES
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (1, 1001, 'VIN12345', TO_DATE('2023-07-01', 'YYYY-MM-DD'));
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (2, 1003, 'VIN67890', TO_DATE('2023-08-01', 'YYYY-MM-DD'));
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (3, 1005, 'VIN98765', TO_DATE('2023-09-01', 'YYYY-MM-DD'));
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (4, 1002, 'VIN54321', TO_DATE('2023-10-01', 'YYYY-MM-DD'));
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (5, 1004, 'VIN65432', TO_DATE('2023-06-01', 'YYYY-MM-DD')); -- Customer 5
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (6, 1002, 'VIN34567', TO_DATE('2023-05-15', 'YYYY-MM-DD')); -- Customer 6
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (1, 1006, 'VIN87654', TO_DATE('2023-04-20', 'YYYY-MM-DD')); -- Customer 1 buys another car
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (2, 1003, 'VIN45678', TO_DATE('2023-03-25', 'YYYY-MM-DD')); -- Customer 2 buys another car
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (5, 1001, 'VIN56789', TO_DATE('2023-02-18', 'YYYY-MM-DD')); -- Customer 5
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (6, 1005, 'VIN67891', TO_DATE('2023-01-10', 'YYYY-MM-DD')); -- Customer 6
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (3, 1004, 'VIN78912', TO_DATE('2022-12-30', 'YYYY-MM-DD')); -- Customer 3 buys another car
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (4, 1006, 'VIN89012', TO_DATE('2022-11-15', 'YYYY-MM-DD')); -- Customer 4 buys another car
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (5, 1002, 'VIN90123', TO_DATE('2022-10-05', 'YYYY-MM-DD')); -- Customer 5 buys another car
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (7, 1007, 'VIN34567', TO_DATE('2023-09-01', 'YYYY-MM-DD')); -- Sale in Granada
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (8, 1008, 'VIN45678', TO_DATE('2023-10-01', 'YYYY-MM-DD')); -- Sale in Madrid
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (7, 1007, 'VIN112233', TO_DATE('2023-09-01', 'YYYY-MM-DD')); -- Car sold to customer 7
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (8, 1008, 'VIN223344', TO_DATE('2023-09-10', 'YYYY-MM-DD')); -- Car sold to customer 8
INSERT INTO SALES (Customer_id, Employee_ssn, VIN, Buy_date)
VALUES (9, 1009, 'VIN334455', TO_DATE('2023-09-15', 'YYYY-MM-DD')); -- Another car for testing



-- Insert data into CUSTOMER
INSERT INTO CUSTOMER (Customer_id, Phone_number)
VALUES (1, '+12345678901'); -- Individual Client
INSERT INTO CUSTOMER (Customer_id, Phone_number)
VALUES (2, '+19876543210'); -- Individual Client
INSERT INTO CUSTOMER (Customer_id, Phone_number)
VALUES (3, '+11223344556'); -- Organization
INSERT INTO CUSTOMER (Customer_id, Phone_number)
VALUES (4, '+10987654321'); -- Organization
INSERT INTO CUSTOMER (Customer_id, Phone_number)
VALUES (5, '+12309876543'); -- Individual Client
INSERT INTO CUSTOMER (Customer_id, Phone_number)
VALUES (6, '+19870987654'); -- Organization
INSERT INTO CUSTOMER (Customer_id, Phone_number)
VALUES (7, '+34567890123');
INSERT INTO CUSTOMER (Customer_id, Phone_number)
VALUES (8, '+34987654321');

-- Insert data into ORGANIZATION
INSERT INTO ORGANIZATION (Org_cif, Name, Activity, Customer_id)
VALUES ('ORG12345', 'TechCorp', 'Technology', 3);
INSERT INTO ORGANIZATION (Org_cif, Name, Activity, Customer_id)
VALUES ('ORG67890', 'GreenEnergy', 'Energy Solutions', 4);
INSERT INTO ORGANIZATION (Org_cif, Name, Activity, Customer_id)
VALUES ('ORG54321', 'HealthCarePlus', 'Healthcare Services', 6);

-- Insert data into INDIVIDUAL_CLIENT
INSERT INTO INDIVIDUAL_CLIENT (Client_dni, Cp, City, Street, Fname, Lname, Sex, Customer_id)
VALUES ('IDV123456', '28001', 'Madrid', 'Calle Mayor 10', 'Carlos', 'Martinez', 'M', 1);
INSERT INTO INDIVIDUAL_CLIENT (Client_dni, Cp, City, Street, Fname, Lname, Sex, Customer_id)
VALUES ('IDV654321', '46001', 'Valencia', 'Avenida del Puerto 20', 'Lucia', 'Gomez', 'F', 2);
INSERT INTO INDIVIDUAL_CLIENT (Client_dni, Cp, City, Street, Fname, Lname, Sex, Customer_id)
VALUES ('IDV112233', '08001', 'Barcelona', 'Carrer de Gran Via 30', 'Ana', 'Lopez', 'F', 5);
INSERT INTO INDIVIDUAL_CLIENT (Client_dni, Cp, City, Street, Fname, Lname, Sex, Customer_id)
VALUES ('IDV789012', '18001', 'Granada', 'Plaza Nueva 20', 'Juan', 'Perez', 'M', 7); -- Granada customer
INSERT INTO INDIVIDUAL_CLIENT (Client_dni, Cp, City, Street, Fname, Lname, Sex, Customer_id)
VALUES ('IDV890123', '28002', 'Madrid', 'Calle Velázquez 25', 'Maria', 'Lopez', 'F', 8); -- Madrid customer


-- Insert data into SURVEY
INSERT INTO SURVEY (Survey_id, Completed_date, Completed_by_client_dni)
VALUES (1, TO_DATE('2023-07-01', 'YYYY-MM-DD'), 'IDV123456');
INSERT INTO SURVEY (Survey_id, Completed_date, Completed_by_client_dni)
VALUES (2, TO_DATE('2023-08-15', 'YYYY-MM-DD'), 'IDV654321');
INSERT INTO SURVEY (Survey_id, Completed_date, Completed_by_client_dni)
VALUES (1, TO_DATE('2023-07-01', 'YYYY-MM-DD'), 'IDV123456'); -- Client Carlos Martinez
INSERT INTO SURVEY (Survey_id, Completed_date, Completed_by_client_dni)
VALUES (2, TO_DATE('2023-08-15', 'YYYY-MM-DD'), 'IDV654321'); -- Client Lucia Gomez
INSERT INTO SURVEY (Survey_id, Completed_date, Completed_by_client_dni)
VALUES (3, TO_DATE('2023-07-15', 'YYYY-MM-DD'), 'IDV123456'); -- Client Carlos Martinez
INSERT INTO SURVEY (Survey_id, Completed_date, Completed_by_client_dni)
VALUES (4, TO_DATE('2023-08-20', 'YYYY-MM-DD'), 'IDV654321'); -- Client Lucia Gomez
INSERT INTO SURVEY (Survey_id, Completed_date, Completed_by_client_dni)
VALUES (5, TO_DATE('2023-09-20', 'YYYY-MM-DD'), 'IDV789012'); -- Survey for customer 7
INSERT INTO SURVEY (Survey_id, Completed_date, Completed_by_client_dni)
VALUES (6, TO_DATE('2023-09-25', 'YYYY-MM-DD'), 'IDV890123'); -- Survey for customer 8



-- Insert data into QUESTION
INSERT INTO QUESTION (Survey_id, Question_code, Question_text)
VALUES (1, 1, 'How satisfied are you with our service?');
INSERT INTO QUESTION (Survey_id, Question_code, Question_text)
VALUES (1, 2, 'Would you recommend us to others?');
INSERT INTO QUESTION (Survey_id, Question_code, Question_text)
VALUES (2, 1, 'Rate your experience with our support team.');
INSERT INTO QUESTION (Survey_id, Question_code, Question_text)
VALUES (2, 2, 'Any additional comments?');
INSERT INTO QUESTION (Survey_id, Question_code, Question_text)
VALUES (1, 3, 'Rate your overall experience with our service.');
INSERT INTO QUESTION (Survey_id, Question_code, Question_text)
VALUES (2, 3, 'How would you describe your experience?');
INSERT INTO QUESTION (Survey_id, Question_code, Question_text)
VALUES (3, 3, 'How would you rate your experience with our team?');
INSERT INTO QUESTION (Survey_id, Question_code, Question_text)
VALUES (4, 3, 'Describe your experience with our brand.');


-- Insert data into ANSWER_RADIO_CHOICE
INSERT INTO ANSWER_RADIO_CHOICE (Survey_id, Question_code, Yes_no)
VALUES (1, 2, 'Yes');
INSERT INTO ANSWER_RADIO_CHOICE (Survey_id, Question_code, Yes_no)
VALUES (2, 2, 'No');

-- Insert data into ANSWER_MULTIPLECHOICE
INSERT INTO ANSWER_MULTIPLECHOICE (Survey_id, Question_code, Choice)
VALUES (1, 1, 'Very Satisfied');
INSERT INTO ANSWER_MULTIPLECHOICE (Survey_id, Question_code, Choice)
VALUES (2, 1, 'Neutral');

-- Insert data into ANSWER_NUMERIC
INSERT INTO ANSWER_NUMERIC (Survey_id, Question_code, Score)
VALUES (1, 1, 9);
INSERT INTO ANSWER_NUMERIC (Survey_id, Question_code, Score)
VALUES (2, 1, 7);
INSERT INTO ANSWER_NUMERIC (Survey_id, Question_code, Score)
VALUES (1, 3, 8); -- Good experience
INSERT INTO ANSWER_NUMERIC (Survey_id, Question_code, Score)
VALUES (2, 3, 7); -- Neutral experience
INSERT INTO ANSWER_NUMERIC (Survey_id, Question_code, Score)
VALUES (3, 3, 9); -- Excellent experience
INSERT INTO ANSWER_NUMERIC (Survey_id, Question_code, Score)
VALUES (4, 3, 6); -- Satisfactory experience


-- Insert data into ANSWER_FREE_TEXT
INSERT INTO ANSWER_FREE_TEXT (Survey_id, Question_code, Text, Text_id)
VALUES (1, 2, 'Great experience overall!', 1);
INSERT INTO ANSWER_FREE_TEXT (Survey_id, Question_code, Text, Text_id)
VALUES (2, 2, 'Support could be improved.', 2);
INSERT INTO ANSWER_FREE_TEXT (Survey_id, Question_code, Text, Text_id)
VALUES (1, 3, 'The service was exceptional and prompt.', 5);
INSERT INTO ANSWER_FREE_TEXT (Survey_id, Question_code, Text, Text_id)
VALUES (2, 3, 'It was okay, but there is room for improvement.', 6);
INSERT INTO ANSWER_FREE_TEXT (Survey_id, Question_code, Text, Text_id)
VALUES (3, 3, 'Fantastic support and guidance throughout.', 7);
INSERT INTO ANSWER_FREE_TEXT (Survey_id, Question_code, Text, Text_id)
VALUES (4, 3, 'Satisfactory, but improvements needed in delivery.', 8);
INSERT INTO ANSWER_FREE_TEXT (Survey_id, Question_code, Text, Text_id)
VALUES (5, 1, 'The brakes were unresponsive during my drive.', 13); -- Negative comment on brakes
INSERT INTO ANSWER_FREE_TEXT (Survey_id, Question_code, Text, Text_id)
VALUES (6, 1, 'I had a terrible experience with the braking system.', 14); -- Another negative comment



-- Insert data into GOOGLE_REVIEW
INSERT INTO GOOGLE_REVIEW (Review_id, Completed_date, Text, Star_Rating, Text_id, Written_by_client_dni)
VALUES (1, TO_DATE('2023-07-02', 'YYYY-MM-DD'), 'Excellent service and fast support.', 5, 3, 'IDV123456');
INSERT INTO GOOGLE_REVIEW (Review_id, Completed_date, Text, Star_Rating, Text_id, Written_by_client_dni)
VALUES (2, TO_DATE('2023-08-16', 'YYYY-MM-DD'), 'The service is okay, but there is room for improvement.', 3, 4, 'IDV654321');
INSERT INTO GOOGLE_REVIEW (Review_id, Completed_date, Text, Star_Rating, Text_id, Written_by_client_dni)
VALUES (3, TO_DATE('2023-09-05', 'YYYY-MM-DD'), 'Great service and friendly staff.', 5, 9, 'IDV789012'); -- Granada review
INSERT INTO GOOGLE_REVIEW (Review_id, Completed_date, Text, Star_Rating, Text_id, Written_by_client_dni)
VALUES (4, TO_DATE('2023-10-10', 'YYYY-MM-DD'), 'Average experience, could be better.', 3, 10, 'IDV890123'); -- Madrid review
INSERT INTO GOOGLE_REVIEW (Review_id, Completed_date, Text, Star_Rating, Text_id, Written_by_client_dni)
VALUES (5, TO_DATE('2023-10-15', 'YYYY-MM-DD'), 'Exceptional service!', 4, 11, 'IDV789012'); -- Another Granada review
INSERT INTO GOOGLE_REVIEW (Review_id, Completed_date, Text, Star_Rating, Text_id, Written_by_client_dni)
VALUES (6, TO_DATE('2023-10-20', 'YYYY-MM-DD'), 'Very disappointing.', 2, 12, 'IDV890123'); -- Another Madrid review
INSERT INTO GOOGLE_REVIEW (Review_id, Completed_date, Text, Star_Rating, Text_id, Written_by_client_dni)
VALUES (7, TO_DATE('2023-09-22', 'YYYY-MM-DD'), 'The brakes failed unexpectedly.', 2, 15, 'IDV789012'); -- Negative brakes review
INSERT INTO GOOGLE_REVIEW (Review_id, Completed_date, Text, Star_Rating, Text_id, Written_by_client_dni)
VALUES (8, TO_DATE('2023-09-28', 'YYYY-MM-DD'), 'Brake performance is unreliable.', 1, 16, 'IDV890123'); -- Another negative review


-- Insert data into SEMANTIC_ANALYSIS
INSERT INTO SEMANTIC_ANALYSIS (Text_id, Valid, Sentiment)
VALUES (1, 1, 'Positive');
INSERT INTO SEMANTIC_ANALYSIS (Text_id, Valid, Sentiment)
VALUES (2, 1, 'Negative');
INSERT INTO SEMANTIC_ANALYSIS (Text_id, Valid, Sentiment)
VALUES (3, 1, 'Positive');
INSERT INTO SEMANTIC_ANALYSIS (Text_id, Valid, Sentiment)
VALUES (4, 1, 'Neutral');
INSERT INTO SEMANTIC_ANALYSIS (Text_id, Valid, Sentiment)
VALUES (13, 1, 'Negative'); -- Negative sentiment for brakes (Survey)
INSERT INTO SEMANTIC_ANALYSIS (Text_id, Valid, Sentiment)
VALUES (14, 1, 'Negative'); -- Negative sentiment for brakes (Survey)
INSERT INTO SEMANTIC_ANALYSIS (Text_id, Valid, Sentiment)
VALUES (15, 1, 'Negative'); -- Negative sentiment for brakes (Review)
INSERT INTO SEMANTIC_ANALYSIS (Text_id, Valid, Sentiment)
VALUES (16, 1, 'Negative'); -- Negative sentiment for brakes (Review)



-- Insert data into TOPIC
INSERT INTO TOPIC (Text_id, Topic)
VALUES (1, 'Customer Experience');
INSERT INTO TOPIC (Text_id, Topic)
VALUES (2, 'Support Quality');
INSERT INTO TOPIC (Text_id, Topic)
VALUES (3, 'Service Satisfaction');
INSERT INTO TOPIC (Text_id, Topic)
VALUES (4, 'Suggestions for Improvement');
INSERT INTO TOPIC (Text_id, Topic)
VALUES (13, 'Brakes');
INSERT INTO TOPIC (Text_id, Topic)
VALUES (14, 'Brakes');
INSERT INTO TOPIC (Text_id, Topic)
VALUES (15, 'Brakes');
INSERT INTO TOPIC (Text_id, Topic)
VALUES (16, 'Brakes');



-- ADD FOREIGN KEYS
-- EMPLOYEE
ALTER TABLE EMPLOYEE 
ADD CONSTRAINT fk_employee_outlet FOREIGN KEY (Works_on_dealer_name, Works_on_outlet_name) REFERENCES OUTLET (Dealer_name, Outlet_name);

-- MANAGER
ALTER TABLE MANAGER
ADD (
    CONSTRAINT fk_manager_employee FOREIGN KEY (Employee_ssn) REFERENCES EMPLOYEE (Employee_ssn),
    CONSTRAINT fk_manager_outlet FOREIGN KEY (Manages_dealer_name, Manages_outlet_name) REFERENCES OUTLET (Dealer_name, Outlet_name)
);

-- OUTLET
ALTER TABLE OUTLET 
ADD CONSTRAINT fk_outlet_dealer FOREIGN KEY (Dealer_name) REFERENCES DEALERSHIP (Dealer_name);

-- DEALERSHIP
-- No additional foreign keys needed

-- MODEL
-- No additional foreign keys needed

-- ALLOWED_MODELS
ALTER TABLE ALLOWED_MODELS
ADD CONSTRAINT fk_allowed_models_dealer FOREIGN KEY (Dealer_name) REFERENCES DEALERSHIP (Dealer_name);
ALTER TABLE ALLOWED_MODELS
ADD CONSTRAINT fk_allowed_models_model FOREIGN KEY (Model_name, Brand, "Version") REFERENCES MODEL (Model_name, Brand, "Version");

-- BRAND_GROUP
-- No additional foreign keys needed

-- FACTORY
ALTER TABLE FACTORY 
ADD CONSTRAINT fk_factory_brand_group FOREIGN KEY (Owned_by_brand_group_name) REFERENCES BRAND_GROUP (Brand_name);

-- CAR
ALTER TABLE CAR 
ADD CONSTRAINT fk_car_model FOREIGN KEY (Model_name, Brand, "Version") REFERENCES MODEL (Model_name, Brand, "Version");
ALTER TABLE CAR 
ADD CONSTRAINT fk_car_factory FOREIGN KEY (Produced_factory_name) REFERENCES FACTORY (Factory_name);

-- PART_SUPPLIER
-- No additional foreign keys needed

-- PART
-- No additional foreign keys needed

-- SUPPLY
ALTER TABLE SUPPLY
ADD CONSTRAINT fk_supply_factory FOREIGN KEY (Factory_name) REFERENCES FACTORY (Factory_name);
ALTER TABLE SUPPLY
ADD CONSTRAINT fk_supply_supplier FOREIGN KEY (Supplier_cif) REFERENCES PART_SUPPLIER (Supplier_cif);
ALTER TABLE SUPPLY
ADD CONSTRAINT fk_supply_part FOREIGN KEY (Part_id) REFERENCES PART (Part_id);

-- CAR_CARRIER
-- No additional foreign keys needed

-- CAR_SHIPMENT
ALTER TABLE CAR_SHIPMENT
ADD CONSTRAINT fk_shipment_outlet FOREIGN KEY (Dealer_name, Outlet_name) REFERENCES OUTLET (Dealer_name, Outlet_name);
ALTER TABLE CAR_SHIPMENT
ADD CONSTRAINT fk_shipment_factory FOREIGN KEY (Factory_name) REFERENCES FACTORY (Factory_name);
ALTER TABLE CAR_SHIPMENT
ADD CONSTRAINT fk_shipment_carrier FOREIGN KEY (Carrier_Plaque) REFERENCES CAR_CARRIER (Carrier_Plaque);
ALTER TABLE CAR_SHIPMENT
ADD CONSTRAINT fk_shipment_car FOREIGN KEY (VIN) REFERENCES CAR (VIN);

-- SALES
ALTER TABLE SALES
ADD CONSTRAINT fk_sales_customer FOREIGN KEY (Customer_id) REFERENCES CUSTOMER (Customer_id);
ALTER TABLE SALES
ADD CONSTRAINT fk_sales_employee FOREIGN KEY (Employee_ssn) REFERENCES EMPLOYEE (Employee_ssn);
ALTER TABLE SALES
ADD CONSTRAINT fk_sales_car FOREIGN KEY (VIN) REFERENCES CAR (VIN);

-- CUSTOMER
-- No additional foreign keys needed

-- ORGANIZATION
ALTER TABLE ORGANIZATION 
ADD CONSTRAINT fk_organization_customer FOREIGN KEY (Customer_id) REFERENCES CUSTOMER (Customer_id);

-- INDIVIDUAL_CLIENT
ALTER TABLE INDIVIDUAL_CLIENT 
ADD CONSTRAINT fk_individual_client_customer FOREIGN KEY (Customer_id) REFERENCES CUSTOMER (Customer_id);

-- SURVEY
ALTER TABLE SURVEY
ADD CONSTRAINT fk_survey_individual_client FOREIGN KEY (Completed_by_client_dni) REFERENCES INDIVIDUAL_CLIENT (Client_dni);

-- QUESTION
ALTER TABLE QUESTION 
ADD CONSTRAINT fk_question_survey FOREIGN KEY (Survey_id) REFERENCES SURVEY (Survey_id);

-- ANSWER_FREE_TEXT
ALTER TABLE ANSWER_FREE_TEXT
ADD CONSTRAINT fk_answer_free_text_question FOREIGN KEY (Survey_id, Question_code) REFERENCES QUESTION (Survey_id, Question_code);
ALTER TABLE ANSWER_FREE_TEXT
ADD CONSTRAINT fk_answer_free_text_analysis FOREIGN KEY (Text_id) REFERENCES SEMANTIC_ANALYSIS (Text_id);

-- ANSWER_RADIO_CHOICE
ALTER TABLE ANSWER_RADIO_CHOICE
ADD CONSTRAINT fk_answer_radio_choice_question FOREIGN KEY (Survey_id, Question_code) REFERENCES QUESTION (Survey_id, Question_code);

-- ANSWER_MULTIPLECHOICE
ALTER TABLE ANSWER_MULTIPLECHOICE
ADD CONSTRAINT fk_answer_multiplechoice_question FOREIGN KEY (Survey_id, Question_code) REFERENCES QUESTION (Survey_id, Question_code);

-- ANSWER_NUMERIC
ALTER TABLE ANSWER_NUMERIC
ADD CONSTRAINT fk_answer_numeric_question FOREIGN KEY (Survey_id, Question_code) REFERENCES QUESTION (Survey_id, Question_code);

-- GOOGLE_REVIEW
ALTER TABLE GOOGLE_REVIEW
ADD CONSTRAINT fk_google_review_analysis FOREIGN KEY (Text_id) REFERENCES SEMANTIC_ANALYSIS (Text_id);
ALTER TABLE GOOGLE_REVIEW
ADD CONSTRAINT fk_google_review_client FOREIGN KEY (Written_by_client_dni) REFERENCES INDIVIDUAL_CLIENT (Client_dni);
