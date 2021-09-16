-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema shop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shop` DEFAULT CHARACTER SET utf8 ;
USE `shop` ;

-- -----------------------------------------------------
-- Table `shop`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`item` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `count` INT NULL,
                                             `name` VARCHAR(255) NOT NULL,
    `image` VARCHAR(1024) NULL,
    `price` DECIMAL NOT NULL,
    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`status` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `name` VARCHAR(255) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`role` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`user` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `login` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_user_role_idx` (`role_id` ASC) VISIBLE,
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `shop`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`cart` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `user_id` INT NULL,
                                             `status_id` INT NULL,
                                             `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
                                             `update_time` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
                                             PRIMARY KEY (`id`),
    INDEX `fk_cart_status_idx` (`status_id` ASC) VISIBLE,
    INDEX `fk_cart_user_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_cart_status`
    FOREIGN KEY (`status_id`)
    REFERENCES `shop`.`status` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_cart_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`user` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`cart_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`cart_item` (
                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                  `card_id` INT NOT NULL,
                                                  `item_id` INT NOT NULL,
                                                  `price` DECIMAL UNSIGNED NULL,
                                                  `count` INT UNSIGNED NULL,
                                                  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
                                                  `update_time` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
                                                  PRIMARY KEY (`id`),
    INDEX `fk_cart_item_cart_idx` (`card_id` ASC) INVISIBLE,
    INDEX `fk_cart_item_item_idx` (`item_id` ASC) VISIBLE,
    CONSTRAINT `fk_cart_item_item`
    FOREIGN KEY (`item_id`)
    REFERENCES `shop`.`item` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_cart_item_cart`
    FOREIGN KEY (`card_id`)
    REFERENCES `shop`.`cart` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`user_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`user_details` (
                                                     `id` INT NOT NULL,
                                                     `firs_name` VARCHAR(255) NULL,
    `last_name` VARCHAR(255) NULL,
    `email` VARCHAR(255) NULL,
    `phone` BIGINT UNSIGNED NULL,
    `age` INT UNSIGNED NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_details_user`
    FOREIGN KEY (`id`)
    REFERENCES `shop`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`category` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`brand` (
                                              `id` INT NOT NULL AUTO_INCREMENT,
                                              `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`color` (
                                              `id` INT NOT NULL AUTO_INCREMENT,
                                              `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`item_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`item_details` (
                                                     `item_id` INT NOT NULL,
                                                     `category_id` INT NULL,
                                                     `brand_id` INT NULL,
                                                     `color_id` INT NULL,
                                                     PRIMARY KEY (`item_id`),
    INDEX `fk_item_details_category_idx` (`category_id` ASC) VISIBLE,
    INDEX `fk_item_details_brand_idx` (`brand_id` ASC) VISIBLE,
    INDEX `fk_item_details_color_idx` (`color_id` ASC) VISIBLE,
    CONSTRAINT `fk_item_details_item`
    FOREIGN KEY (`item_id`)
    REFERENCES `shop`.`item` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_item_details_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `shop`.`category` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_item_details_brand`
    FOREIGN KEY (`brand_id`)
    REFERENCES `shop`.`brand` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_item_details_color`
    FOREIGN KEY (`color_id`)
    REFERENCES `shop`.`color` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
