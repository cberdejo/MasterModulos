CREATE TABLE Dates (
    idDate NUMBER(10) PRIMARY KEY,
    year NUMBER(10) NOT NULL,
    month NUMBER(10) NOT NULL,
    day NUMBER(10) NOT NULL
);

CREATE TABLE Product (
    idProduct NUMBER(10) PRIMARY KEY,
    category VARCHAR2(255 CHAR),
    sub_category VARCHAR2(255 CHAR),
    name VARCHAR2(255 CHAR)
);

CREATE TABLE Shipment (
    idShipment NUMBER(10) PRIMARY KEY,
    shipment_mode VARCHAR2(45 CHAR)
);

CREATE TABLE Priority (
    idPriority NUMBER(10) PRIMARY KEY,
    order_priority VARCHAR2(45 CHAR)
);


CREATE TABLE Returns (
    idReturns NUMBER(10) PRIMARY KEY,
    returned VARCHAR2(45 CHAR)
);

CREATE TABLE Customer (
    idCustomer NUMBER(10) PRIMARY KEY,
    customer_number VARCHAR(20),
    name VARCHAR2(255 CHAR),
    country VARCHAR2(45 CHAR),
    customer_type VARCHAR2(45 CHAR),
    postal_code VARCHAR2(20 CHAR),
    market VARCHAR2(45 CHAR),
    region VARCHAR2(45 CHAR),
    state VARCHAR2(45 CHAR),
    city VARCHAR2(45 CHAR)
);

CREATE TABLE Fact_Order (
    idFact_order NUMBER(10) PRIMARY KEY,
    Product_idProduct NUMBER(10) NOT NULL,
    Date_idship NUMBER(10) NOT NULL,
    Date_idOrder NUMBER(10) NOT NULL,
    Shipment_idShipment NUMBER(10) NOT NULL,
    Priority_idPriority NUMBER(10) NOT NULL,
    Customer_idCustomer NUMBER(10) NOT NULL,
    Returns_idReturns NUMBER(10),
    sales BINARY_DOUBLE,
    quantity BINARY_DOUBLE,
    discount BINARY_DOUBLE,
    profit BINARY_DOUBLE,
    shipping_cost BINARY_DOUBLE,
    preparation_time BINARY_DOUBLE,
    CONSTRAINT fk_Product FOREIGN KEY (Product_idProduct) REFERENCES Product(idProduct),
    CONSTRAINT fk_DateShip FOREIGN KEY (Date_idship) REFERENCES DATES(idDate),
    CONSTRAINT fk_DateOrder FOREIGN KEY (Date_idOrder) REFERENCES DATES(idDate),
    CONSTRAINT fk_Shipment FOREIGN KEY (Shipment_idShipment) REFERENCES Shipment(idShipment),
    CONSTRAINT fk_Priority FOREIGN KEY (Priority_idPriority) REFERENCES Priority(idPriority),
    CONSTRAINT fk_Customer FOREIGN KEY (Customer_idCustomer) REFERENCES Customer(idCustomer),
    CONSTRAINT fk_Returns FOREIGN KEY (Returns_idReturns) REFERENCES Returns(idReturns)
);
