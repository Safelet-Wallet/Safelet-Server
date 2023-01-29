-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema safelet-wallet
-- -----------------------------------------------------
-- Base de Datos del proyecto de PSP Safelet Wallet.
DROP SCHEMA IF EXISTS `safelet-wallet` ;

-- -----------------------------------------------------
-- Schema safelet-wallet
--
-- Base de Datos del proyecto de PSP Safelet Wallet.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `safelet-wallet` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `safelet-wallet` ;

-- -----------------------------------------------------
-- Table `safelet-wallet`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelet-wallet`.`user` ;

CREATE TABLE IF NOT EXISTS `safelet-wallet`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NULL,
  `surnames` VARCHAR(60) NULL,
  `username` VARCHAR(60) NOT NULL,
  `registry_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_username` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelet-wallet`.`coin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelet-wallet`.`coin` ;

CREATE TABLE IF NOT EXISTS `safelet-wallet`.`coin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(4) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `description` VARCHAR(250) NULL,
  `value_euro` DOUBLE NOT NULL,
  `value_dollar` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelet-wallet`.`wallet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelet-wallet`.`wallet` ;

CREATE TABLE IF NOT EXISTS `safelet-wallet`.`wallet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `public_key` VARCHAR(64) NOT NULL,
  `private_key` VARCHAR(64) NOT NULL,
  `user` INT NOT NULL,
  `coin` INT NOT NULL,
  `balance` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cartera_usuario_idx` (`user` ASC) INVISIBLE,
  UNIQUE INDEX `uq_usuario_tipo` (`user` ASC, `coin` ASC) INVISIBLE,
  UNIQUE INDEX `uq_clave_publica` (`public_key` ASC) VISIBLE,
  INDEX `fk_cartera_moneda_idx` (`coin` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelet-wallet`.`transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelet-wallet`.`transaction` ;

CREATE TABLE IF NOT EXISTS `safelet-wallet`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `source` INT NOT NULL,
  `destiny` INT NOT NULL,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` DOUBLE NOT NULL,
  `coin` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transaccion_destino_idx` (`destiny` ASC) INVISIBLE,
  INDEX `fk_transaccion_origen_idx` (`source` ASC) INVISIBLE,
  INDEX `fk_transaccion_moneda1_idx` (`coin` ASC) VISIBLE,
  UNIQUE INDEX `uq_transaccion` (`source` ASC, `destiny` ASC, `date` ASC) INVISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `safelet-wallet`.`contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `safelet-wallet`.`contact` ;

CREATE TABLE IF NOT EXISTS `safelet-wallet`.`contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `self` INT NOT NULL,
  `other` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contacto_contacto_idx` (`other` ASC) INVISIBLE,
  INDEX `fk_contacto_yo_idx` (`self` ASC) VISIBLE,
  UNIQUE INDEX `uq_contacto` (`self` ASC, `other` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
