CREATE SCHEMA IF NOT EXISTS `shop`;

CREATE SCHEMA IF NOT EXISTS `shop` DEFAULT CHARACTER SET utf8;

-- -----------------------------------------------------
-- Table `shop`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`category`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    `parent_id`   INT          NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_category_category_idx` (`parent_id` ASC) VISIBLE,
    CONSTRAINT `fk_category_category`
        FOREIGN KEY (`parent_id`)
            REFERENCES `shop`.`category` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `shop`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`product`
(
    `id`          INT           NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255)  NOT NULL,
    `price`       DECIMAL       NOT NULL DEFAULT 0,
    `amount`      INT UNSIGNED  NOT NULL DEFAULT 0,
    `description` VARCHAR(1024) NULL,
    `category_id` INT           NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_product_category_idx` (`category_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_category`
        FOREIGN KEY (`category_id`)
            REFERENCES `shop`.`category` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`role`
(
    `id`          INT           NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255)  NOT NULL,
    `description` VARCHAR(1024) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`account`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `login`       VARCHAR(255) NOT NULL,
    `email`       VARCHAR(255) NULL,
    `password`    VARCHAR(255) NOT NULL,
    `create_time` TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP,
    `role_id`     INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_account_role_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_role`
        FOREIGN KEY (`role_id`)
            REFERENCES `shop`.`role` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `shop`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`status`
(
    `id`          INT           NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(1024) NOT NULL,
    `description` VARCHAR(1024) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`order`
(
    `id`             INT       NOT NULL AUTO_INCREMENT,
    `account_id`     INT       NULL,
    `create_time`    TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `status_id`      INT       NOT NULL,
    `approved_by_id` INT       NULL,
    `total`          DOUBLE    NULL,
    INDEX `fk_order_account_account_idx` (`account_id` ASC) VISIBLE,
    PRIMARY KEY (`id`),
    INDEX `fk_order_status_idx` (`status_id` ASC) VISIBLE,
    INDEX `fk_order_account_approved_idx` (`approved_by_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_account`
        FOREIGN KEY (`account_id`)
            REFERENCES `shop`.`account` (`id`)
            ON DELETE SET NULL
            ON UPDATE CASCADE,
    CONSTRAINT `fk_order_status`
        FOREIGN KEY (`status_id`)
            REFERENCES `shop`.`status` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_account`
        FOREIGN KEY (`approved_by_id`)
            REFERENCES `shop`.`account` (`id`)
            ON DELETE SET NULL
            ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `shop`.`receipt_has_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`order_product`
(
    `receipt_id`  INT          NOT NULL,
    `product_id`  INT          NOT NULL,
    `price`       DECIMAL      NOT NULL,
    `count`       INT UNSIGNED NOT NULL,
    `create_date` DATETIME     NULL,
    `last_update` DATETIME     NULL,
    PRIMARY KEY (`receipt_id`, `product_id`),
    INDEX `fk_order_product_product_idx` (`product_id` ASC) VISIBLE,
    INDEX `fk_order_product_order_idx` (`receipt_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_product_order`
        FOREIGN KEY (`receipt_id`)
            REFERENCES `shop`.`order` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_order_product_product`
        FOREIGN KEY (`product_id`)
            REFERENCES `shop`.`product` (`id`)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `shop`.`account_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`account_details`
(
    `id`          INT          NOT NULL,
    `name`        VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    `age`         INT          NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_account_details_id`
        FOREIGN KEY (`id`)
            REFERENCES `shop`.`account` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

