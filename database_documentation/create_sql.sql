SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `PV243`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PV243`.`User` ;

CREATE  TABLE IF NOT EXISTS `PV243`.`User` (
  `idUser` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(255) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `idRole` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idUser`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PV243`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PV243`.`Role` ;

CREATE  TABLE IF NOT EXISTS `PV243`.`Role` (
  `idRole` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `role` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idRole`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PV243`.`Actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PV243`.`Actor` ;

CREATE  TABLE IF NOT EXISTS `PV243`.`Actor` (
  `idActor` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idPerson` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idActor`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PV243`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PV243`.`Person` ;

CREATE  TABLE IF NOT EXISTS `PV243`.`Person` (
  `idPerson` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `surname` VARCHAR(255) NOT NULL ,
  `birthdate` DATETIME NULL ,
  PRIMARY KEY (`idPerson`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PV243`.`Director`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PV243`.`Director` ;

CREATE  TABLE IF NOT EXISTS `PV243`.`Director` (
  `idDector` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idPerson` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idDector`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PV243`.`Movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PV243`.`Movie` ;

CREATE  TABLE IF NOT EXISTS `PV243`.`Movie` (
  `idMovie` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `movieName` VARCHAR(255) NOT NULL ,
  `movieDate` DATETIME NULL ,
  `length` INT UNSIGNED NULL ,
  `description` TEXT NULL ,
  PRIMARY KEY (`idMovie`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PV243`.`ActorAtMovie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PV243`.`ActorAtMovie` ;

CREATE  TABLE IF NOT EXISTS `PV243`.`ActorAtMovie` (
  `idActorAtMovie` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idMovie` INT UNSIGNED NOT NULL ,
  `idActor` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idActorAtMovie`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PV243`.`DirectorAtMovie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PV243`.`DirectorAtMovie` ;

CREATE  TABLE IF NOT EXISTS `PV243`.`DirectorAtMovie` (
  `idActorAtMovie` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idMovie` INT UNSIGNED NOT NULL ,
  `idDirector` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idActorAtMovie`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
