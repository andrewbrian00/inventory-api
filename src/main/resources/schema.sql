DROP TABLE IF EXISTS "inventory";
DROP TABLE IF EXISTS "users";
DROP TABLE IF EXISTS "suppliers";
DROP TABLE IF EXISTS "customers";
DROP TABLE IF EXISTS "orders";
DROP TABLE IF EXISTS "products";

DROP SEQUENCE IF EXISTS supplier_id_seq;
DROP SEQUENCE IF EXISTS product_id_seq;
DROP SEQUENCE IF EXISTS user_id_seq;
DROP SEQUENCE IF EXISTS inventory_id_seq;
DROP SEQUENCE IF EXISTS customers_id_seq;
DROP SEQUENCE IF EXISTS orders_id_seq;

-- SEQUENCE
CREATE SEQUENCE customers_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE supplier_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE orders_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE inventory_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE SEQUENCE product_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "suppliers" (
    "id" bigint DEFAULT nextval('supplier_id_seq') NOT NULL,
    "name" text,
    "address" text,
    CONSTRAINT "suppliers_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "products" (
    "id" bigint DEFAULT nextval('product_id_seq') NOT NULL,
    "part_number" text,
    "name" text,
    "amount" decimal,
    "unit_measure" text,
    "description" text,
    "category" text,
    CONSTRAINT "products_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "users" (
    "id" bigint DEFAULT nextval('user_id_seq') NOT NULL,
    "user_name" text,
    "role" text,
    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "customers" (
    "id" bigint DEFAULT nextval('customers_id_seq') NOT NULL,
    "name" text,
    "address" text,
    "contact_number" text,
    CONSTRAINT "customers_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "inventory" (
    "id" bigint DEFAULT nextval('inventory_id_seq') NOT NULL,
    "item_name" text,
    "item_description" text,
    "products_id" bigint,
    "suppliers_id" bigint,
    CONSTRAINT "inventory_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fk_products" FOREIGN KEY(products_id) REFERENCES products(id),
    CONSTRAINT "fk_suppliers" FOREIGN KEY(suppliers_id) REFERENCES suppliers(id)
);

CREATE TABLE "orders" (
    "id" bigint DEFAULT nextval('orders_id_seq') NOT NULL,
    "order_description" text,
    "order_quantity" bigint,
    "product_id" bigint,
    CONSTRAINT "orders_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fk_product" FOREIGN KEY(product_id) REFERENCES products(id)
);
