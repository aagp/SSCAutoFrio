SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `SSCAutoFrio` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `SSCAutoFrio` ;

-- -----------------------------------------------------
-- Table `SSCAutoFrio`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSCAutoFrio`.`cliente` (
  `idCliente` VARCHAR(10) NOT NULL,
  `Nombre` VARCHAR(25) NULL,
  `ApellidoPat` VARCHAR(15) NULL,
  `ApellidoMat` VARCHAR(15) NULL,
  `Direccion` VARCHAR(60) NULL,
  `NumExt` VARCHAR(15) NULL,
  `NumInt` VARCHAR(15) NULL,
  `Telefono` VARCHAR(10) NULL,
  `Colonia` VARCHAR(20) NULL,
  `Municipio` VARCHAR(20) NULL,
  `Ciudad` VARCHAR(20) NULL,
  `Estado` VARCHAR(20) NULL,
  `CP` VARCHAR(5) NULL,
  `Pais` VARCHAR(6) NULL,
  `RFC` VARCHAR(13) NULL,
  `Email` VARCHAR(50) NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SSCAutoFrio`.`vehiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSCAutoFrio`.`vehiculo` (
  `idVehiculo` INT NOT NULL AUTO_INCREMENT,
  `Año` INT(4) NULL,
  `Marca` VARCHAR(10) NULL,
  `Modelo` VARCHAR(15) NULL,
  `Cilindros` VARCHAR(10) NULL,
  `Motor` DOUBLE NULL,
  PRIMARY KEY (`idVehiculo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SSCAutoFrio`.`servicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSCAutoFrio`.`servicio` (
  `idServicio` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(45) NULL,
  `Precio` DOUBLE NULL,
  PRIMARY KEY (`idServicio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SSCAutoFrio`.`orden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSCAutoFrio`.`orden` (
  `idOrden` INT NOT NULL AUTO_INCREMENT,
  `idCliente` VARCHAR(10) NOT NULL,
  `idVehiculo` INT NOT NULL,
  `Fecha` VARCHAR(10) NULL,
  `Subtotal` DOUBLE NULL,
  `Iva` DOUBLE NULL,
  `Total` DOUBLE NULL,
  `Comentarios` VARCHAR(100) NULL,
  `Estado` VARCHAR(10) NULL,
  PRIMARY KEY (`idOrden`),
  INDEX `fk_Orden_Cliente1_idx` (`idCliente` ASC),
  CONSTRAINT `fk_Orden_Cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `SSCAutoFrio`.`cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SSCAutoFrio`.`detalleOrden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSCAutoFrio`.`detalleOrden` (
  `idDetalle` INT NOT NULL AUTO_INCREMENT,
  `idOrden` INT NOT NULL,
  `idServicio` INT NOT NULL,
  `Cantidad` DOUBLE NULL,
  `PUnit` DOUBLE NULL,
  `Importe` DOUBLE NULL,
  INDEX `fk_DetalleOrden_Orden1_idx` (`idOrden` ASC),
  INDEX `fk_DetalleOrden_Servicio1_idx` (`idServicio` ASC),
  PRIMARY KEY (`idDetalle`),
  CONSTRAINT `fk_DetalleOrden_Orden1`
    FOREIGN KEY (`idOrden`)
    REFERENCES `SSCAutoFrio`.`orden` (`idOrden`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetalleOrden_Servicio1`
    FOREIGN KEY (`idServicio`)
    REFERENCES `SSCAutoFrio`.`servicio` (`idServicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SSCAutoFrio`.`tiene`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSCAutoFrio`.`tiene` (
  `idTiene` INT NOT NULL AUTO_INCREMENT,
  `idCliente` VARCHAR(10) NOT NULL,
  `idVehiculo` INT NOT NULL,
  PRIMARY KEY (`idTiene`),
  INDEX `idVehiculo_idx` (`idVehiculo` ASC),
  CONSTRAINT `idCliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `SSCAutoFrio`.`cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idVehiculo`
    FOREIGN KEY (`idVehiculo`)
    REFERENCES `SSCAutoFrio`.`vehiculo` (`idVehiculo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
