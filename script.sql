DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS supplier;

CREATE TABLE product (
	id_product INT NOT NULL auto_increment,
	name_product VARCHAR(20) NOT NULL,
	cost_product NUMERIC(8, 2) NOT NULL,
	unit_product VARCHAR(2) NOT NULL,
    PRIMARY KEY (id_product)
);
ALTER TABLE product auto_increment = 1;


CREATE TABLE client (
	id_client INT NOT NULL auto_increment,
	name_client VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_client)
);
ALTER TABLE client auto_increment = 1;


CREATE TABLE supplier (
	id_supplier INT NOT NULL auto_increment,
	name_supplier VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_supplier)
);
ALTER TABLE supplier auto_increment = 1;


CREATE TABLE purchase (
	id_purchase INT NOT NULL auto_increment,
	quantity NUMERIC(4,2) NOT NULL,
	datePurchase DATE,
	id_product INT NOT NULL,
	id_supplier INT NOT NULL ,
	total_purchase NUMERIC(8,2) NOT NULL,
    PRIMARY KEY (id_purchase)
);
ALTER TABLE purchase auto_increment = 1;
ALTER TABLE purchase ADD CONSTRAINT id_product_fk
	FOREIGN KEY (id_product) REFERENCES product (id_product);
ALTER TABLE purchase ADD CONSTRAINT id_supplier_fk
	FOREIGN KEY (id_supplier) REFERENCES supplier (id_supplier);


CREATE TABLE stock (
	id_movement INT NOT NULL auto_increment,
    id_product INT NOT NULL,
    lot_number VARCHAR(20) NOT NULL,
    id_purchase INT NOT NULL,
    quantity DECIMAL(4,2) NOT NULL,
    total_stock NUMERIC(8,2) NOT NULL,
    PRIMARY KEY (id_movement)
);
ALTER TABLE stock auto_increment = 1;
ALTER TABLE stock ADD CONSTRAINT id_product_stock_fk
	FOREIGN KEY (id_product) REFERENCES product (id_product);
ALTER TABLE stock ADD CONSTRAINT id_purchase_stock_fk 
	FOREIGN KEY (id_purchase) REFERENCES purchase (id_purchase);
	

CREATE TABLE sales (
	id_sales INT NOT NULL auto_increment,
	price NUMERIC(10,2) NOT NULL,
	sale_date DATE NOT NULL,
	id_client INT NOT NULL,
	id_product INT NOT NULL,
	quantity DECIMAL(4,2) NOT NULL,
	total NUMERIC(10,2) NOT NULL,
    PRIMARY KEY (id_sales)
);
ALTER TABLE sales auto_increment = 1;
ALTER TABLE sales ADD CONSTRAINT id_client_sales_fk
	FOREIGN KEY (id_client) REFERENCES client (id_client);
ALTER TABLE sales ADD CONSTRAINT id_product_sales_fk
	FOREIGN KEY (id_product) REFERENCES product (id_product);