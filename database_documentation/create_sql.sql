SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `pv243`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pv243`.`User` ;

CREATE  TABLE IF NOT EXISTS `pv243`.`User` (
  `idUser` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(255) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `idRole` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idUser`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pv243`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pv243`.`Role` ;

CREATE  TABLE IF NOT EXISTS `pv243`.`Role` (
  `idRole` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `role` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idRole`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pv243`.`Actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pv243`.`Actor` ;

CREATE  TABLE IF NOT EXISTS `pv243`.`Actor` (
  `idActor` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idPerson` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idActor`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pv243`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pv243`.`Person` ;

CREATE  TABLE IF NOT EXISTS `pv243`.`Person` (
  `idPerson` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `surname` VARCHAR(255) NOT NULL ,
  `birthdate` DATETIME NULL ,
  PRIMARY KEY (`idPerson`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pv243`.`Director`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pv243`.`Director` ;

CREATE  TABLE IF NOT EXISTS `pv243`.`Director` (
  `idDector` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idPerson` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idDector`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pv243`.`Movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pv243`.`Movie` ;

CREATE  TABLE IF NOT EXISTS `pv243`.`Movie` (
  `idMovie` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `movieName` VARCHAR(255) NOT NULL ,
  `movieDate` DATETIME NULL ,
  `length` INT UNSIGNED NULL ,
  `description` TEXT NULL ,
  `url` CHAR(255) NULL ,
  PRIMARY KEY (`idMovie`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pv243`.`ActorAtMovie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pv243`.`ActorAtMovie` ;

CREATE  TABLE IF NOT EXISTS `pv243`.`ActorAtMovie` (
  `idActorAtMovie` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idMovie` INT UNSIGNED NOT NULL ,
  `idActor` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idActorAtMovie`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pv243`.`DirectorAtMovie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pv243`.`DirectorAtMovie` ;

CREATE  TABLE IF NOT EXISTS `pv243`.`DirectorAtMovie` (
  `idActorAtMovie` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `idMovie` INT UNSIGNED NOT NULL ,
  `idDirector` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`idActorAtMovie`) )
ENGINE = InnoDB;


INSERT INTO PV243.`Role` (`role`) VALUES ('admin');
INSERT INTO PV243.`Role` (`role`) VALUES ('moderator');
INSERT INTO PV243.`Role` (`role`) VALUES ('user');
-- admin / hesloA --
INSERT INTO PV243.`User` (login, password, idRole, salt) VALUES ('admin', '89b0d4ca22e6ec2b87487383237a01a4', 1, 'BJ%qOLGtvrGicAx');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
