create table carts
(
    id           BINARY(16) default (UUID_TO_BIN(UUID())) not null
        primary key,
    date_created date       default (curdate())           not null
);
;

create table cart_items
(
    id         BIGINT auto_increment
        primary key,
    cart_id    BINARY(16)    not null,
    product_id BIGINT        not null,
    quantity   int default 1 not null,
    CONSTRAINT uq_cart_product UNIQUE (cart_id, product_id)
);

ALTER TABLE cart_items
    ADD CONSTRAINT cart_items_cart__fk foreign key (cart_id) references carts (id) on delete cascade;

ALTER TABLE cart_items
    add constraint cart_items_products__fk foreign key (product_id) references products(id) on delete cascade;
