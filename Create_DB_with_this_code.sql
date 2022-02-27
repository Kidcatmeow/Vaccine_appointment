-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema vaccination_system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vaccination_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vaccination_system` DEFAULT CHARACTER SET utf8 ;
USE `vaccination_system` ;

-- -----------------------------------------------------
-- Table `vaccination_system`.`vaccine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vaccination_system`.`vaccine` (
  `vaccine_type` VARCHAR(45) NOT NULL,
  `available_dose` INT(11) NOT NULL,
  PRIMARY KEY (`vaccine_type`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `vaccination_system`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vaccination_system`.`patient` (
  `patientID` INT(11) NOT NULL,
  `Patient_name` VARCHAR(45) NULL DEFAULT NULL,
  `patient_type` VARCHAR(45) NOT NULL,
  `appointment_place` VARCHAR(45) NOT NULL,
  `vaccine_type` VARCHAR(45) NOT NULL,
  `appointment_date` VARCHAR(1000) NOT NULL,
  `second_dose_date` VARCHAR(45) NULL DEFAULT NULL,
  `third_dose_date` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`patientID`),
  CONSTRAINT `patient_ibfk_1`
    FOREIGN KEY (`vaccine_type`)
    REFERENCES `vaccination_system`.`vaccine` (`vaccine_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
