DROP SCHEMA IF EXISTS CAJAP CASCADE;
CREATE SCHEMA CAJAP;
SET search_path TO cajap;

DROP TABLE IF EXISTS CAJAP.inventory;
DROP TABLE IF EXISTS CAJAP.users;
DROP TABLE IF EXISTS CAJAP.suppliers;
DROP TABLE IF EXISTS CAJAP.customers;
DROP TABLE IF EXISTS CAJAP.orders;
DROP TABLE IF EXISTS CAJAP.products;

DROP SEQUENCE IF EXISTS CAJAP.supplier_id_seq;
DROP SEQUENCE IF EXISTS CAJAP.product_id_seq;
DROP SEQUENCE IF EXISTS CAJAP.user_id_seq;
DROP SEQUENCE IF EXISTS CAJAP.inventory_id_seq;
DROP SEQUENCE IF EXISTS CAJAP.customers_id_seq;
DROP SEQUENCE IF EXISTS CAJAP.orders_id_seq;

CREATE SEQUENCE CAJAP.customers_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE CAJAP.user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE CAJAP.supplier_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE CAJAP.orders_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE CAJAP.inventory_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE CAJAP.product_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE CAJAP.suppliers (
    "id" bigint DEFAULT nextval('CAJAP.supplier_id_seq') NOT NULL,
    "name" text,
    "address" text,
    CONSTRAINT "suppliers_pkey" PRIMARY KEY ("id")
);

CREATE TABLE CAJAP.products (
    "id" bigint DEFAULT nextval('CAJAP.product_id_seq') NOT NULL,
    "part_number" text,
    "name" text,
    "amount" decimal,
    "unit_measure" text,
    "description" text,
    "category" text,
    CONSTRAINT "products_pkey" PRIMARY KEY ("id")
);

CREATE TABLE CAJAP.users (
    "id" bigint DEFAULT nextval('CAJAP.user_id_seq') NOT NULL,
    "user_name" text,
    "role" text,
    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

CREATE TABLE CAJAP.customers (
    "id" bigint DEFAULT nextval('CAJAP.customers_id_seq') NOT NULL,
    "name" text,
    "address" text,
    "contact_number" text,
    CONSTRAINT "customers_pkey" PRIMARY KEY ("id")
);


CREATE TABLE CAJAP.inventory (
	"id" BIGINT DEFAULT NEXTVAL('CAJAP.inventory_id_seq') NOT NULL,
	"item_name" CLOB,
	"item_description" CLOB,
	"product_id" BIGINT,
	"supplier_id" BIGINT,
	CONSTRAINT "inventory_pkey" PRIMARY KEY ("id")
);
CREATE INDEX INVENTORY_PRODUCTS_FK_INDEX_8 ON CAJAP.inventory ("product_id");
CREATE INDEX INVENTORY_SUPPLIERS_FK_INDEX_8 ON CAJAP.inventory ("supplier_id");
CREATE UNIQUE INDEX PRIMARY_KEY_8 ON CAJAP.inventory ("id");


-- CAJAP."inventory" foreign keys

ALTER TABLE CAJAP.inventory ADD CONSTRAINT INVENTORY_PRODUCTS_FK FOREIGN KEY ("product_id") REFERENCES CAJAP.products("id") ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE CAJAP.inventory ADD CONSTRAINT INVENTORY_SUPPLIERS_FK FOREIGN KEY ("supplier_id") REFERENCES CAJAP.suppliers("id") ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE CAJAP.orders (
    "id" bigint DEFAULT nextval('cajap.orders_id_seq') NOT NULL,
    "order_description" text,
    "order_quantity" bigint,
    "product_id" bigint,
    CONSTRAINT "orders_pkey" PRIMARY KEY ("id")
);
CREATE UNIQUE INDEX PRIMARY_KEY_9 ON CAJAP.orders ("id");

ALTER TABLE CAJAP.orders ADD CONSTRAINT ORDERS_PRODUCTS_FK FOREIGN KEY ("product_id") REFERENCES CAJAP.products("id") ON DELETE RESTRICT ON UPDATE RESTRICT;