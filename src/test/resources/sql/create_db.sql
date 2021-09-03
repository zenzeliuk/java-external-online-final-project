CREATE SCHEMA IF NOT EXISTS `shop`;

CREATE TABLE IF NOT EXISTS `shop`.`category`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `parent_id`   INT          NULL,
    `description` VARCHAR(255) NULL,
    PRIMARY KEY (`id`),

    CONSTRAINT `fk_category_category`
        FOREIGN KEY (`parent_id`)
            REFERENCES `shop`.`category` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `shop`.`item`
(
    `id`          INT           NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255)  NOT NULL,
    `code`        VARCHAR(255)  NOT NULL,
    `price`       DECIMAL       NOT NULL,
    `description` VARCHAR(1024) NULL,
    `category_id` INT           NULL,
    PRIMARY KEY (`id`),

    CONSTRAINT `fk_item_category`
        FOREIGN KEY (`category_id`)
            REFERENCES `shop`.`category` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `shop`.`status`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `shop`.`role`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `shop`.`user`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `role_id`  INT          NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_role`
        FOREIGN KEY (`role_id`)
            REFERENCES `shop`.`role` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `shop`.`cart`
(
    `id`               INT       NOT NULL AUTO_INCREMENT,
    `user_id`          INT       NULL,
    `status_id`        INT       NULL,
    `approved_cart_by` INT       NULL,
    `create_time`      TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`      TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_cart_status`
        FOREIGN KEY (`status_id`)
            REFERENCES `shop`.`status` (`id`)
            ON DELETE SET NULL
            ON UPDATE CASCADE,
    CONSTRAINT `fk_cart_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `shop`.`user` (`id`)
            ON DELETE SET NULL
            ON UPDATE CASCADE,
    CONSTRAINT `fk_cart_user_approved`
        FOREIGN KEY (`approved_cart_by`)
            REFERENCES `shop`.`user` (`id`)
            ON DELETE SET NULL
            ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS `shop`.`cart_item`
(
    `id`          INT       NOT NULL AUTO_INCREMENT,
    `card_id`     INT       NOT NULL,
    `item_id`     INT       NOT NULL,
    `price`       DECIMAL   NULL,
    `count`       INT       NULL,
    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_cart_item_item`
        FOREIGN KEY (`item_id`)
            REFERENCES `shop`.`item` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_cart_item_cart`
        FOREIGN KEY (`card_id`)
            REFERENCES `shop`.`cart` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `shop`.`user_details`
(
    `id`        INT          NOT NULL,
    `firs_name` VARCHAR(255) NULL,
    `last_name` VARCHAR(255) NULL,
    `email`     VARCHAR(255) NULL,
    `phone`     BIGINT       NULL,
    `age`       INT          NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_details_user`
        FOREIGN KEY (`id`)
            REFERENCES `shop`.`user` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
