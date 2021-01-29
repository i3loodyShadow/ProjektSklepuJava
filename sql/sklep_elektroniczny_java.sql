-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sklep_elektroniczny
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sklep_elektroniczny` ;

-- -----------------------------------------------------
-- Schema sklep_elektroniczny
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sklep_elektroniczny` DEFAULT CHARACTER SET utf8 ;
USE `sklep_elektroniczny` ;

-- -----------------------------------------------------
-- Table `sklep_elektroniczny`.`grupy_towarow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sklep_elektroniczny`.`grupy_towarow` ;

CREATE TABLE IF NOT EXISTS `sklep_elektroniczny`.`grupy_towarow` (
  `idgrupy_towarow` INT(11) NOT NULL AUTO_INCREMENT,
  `nazwa_grupy` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idgrupy_towarow`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sklep_elektroniczny`.`nazwa_parametrow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sklep_elektroniczny`.`nazwa_parametrow` ;

CREATE TABLE IF NOT EXISTS `sklep_elektroniczny`.`nazwa_parametrow` (
  `idnazwa_parametrow` INT(11) NOT NULL AUTO_INCREMENT,
  `nazwa_parametru` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idnazwa_parametrow`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` ;

CREATE TABLE IF NOT EXISTS `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (
  `grupy_towarow_idgrupy_towarow` INT(11) NOT NULL,
  `nazwa_parametrow_idnazwa_parametrow` INT(11) NOT NULL,
  PRIMARY KEY (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`),
  INDEX `fk_grupy_towarow_has_nazwa_parametrow_nazwa_parametrow1_idx` (`nazwa_parametrow_idnazwa_parametrow` ASC) ,
  INDEX `fk_grupy_towarow_has_nazwa_parametrow_grupy_towarow1_idx` (`grupy_towarow_idgrupy_towarow` ASC) ,
  CONSTRAINT `fk_grupy_towarow_has_nazwa_parametrow_grupy_towarow1`
    FOREIGN KEY (`grupy_towarow_idgrupy_towarow`)
    REFERENCES `sklep_elektroniczny`.`grupy_towarow` (`idgrupy_towarow`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupy_towarow_has_nazwa_parametrow_nazwa_parametrow1`
    FOREIGN KEY (`nazwa_parametrow_idnazwa_parametrow`)
    REFERENCES `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sklep_elektroniczny`.`rola`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sklep_elektroniczny`.`rola` ;

CREATE TABLE IF NOT EXISTS `sklep_elektroniczny`.`rola` (
  `idrola` INT(11) NOT NULL AUTO_INCREMENT,
  `nazwa_roli` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`idrola`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sklep_elektroniczny`.`konto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sklep_elektroniczny`.`konto` ;

CREATE TABLE IF NOT EXISTS `sklep_elektroniczny`.`konto` (
  `idkonto` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(15) NOT NULL,
  `haslo` VARCHAR(15) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `Rola_idrola` INT(11) NOT NULL,
  PRIMARY KEY (`idkonto`),
  INDEX `fk_konto_Rola_idx` (`Rola_idrola` ASC) ,
  CONSTRAINT `fk_konto_Rola`
    FOREIGN KEY (`Rola_idrola`)
    REFERENCES `sklep_elektroniczny`.`rola` (`idrola`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sklep_elektroniczny`.`towar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sklep_elektroniczny`.`towar` ;

CREATE TABLE IF NOT EXISTS `sklep_elektroniczny`.`towar` (
  `idtowar` INT(11) NOT NULL AUTO_INCREMENT,
  `id_grupy_towarow` INT(11) NOT NULL,
  `producent` VARCHAR(15) NOT NULL,
  `model` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idtowar`),
  INDEX `fk_towar_grupy_towarow1_idx` (`id_grupy_towarow` ASC) ,
  CONSTRAINT `fk_towar_grupy_towarow1`
    FOREIGN KEY (`id_grupy_towarow`)
    REFERENCES `sklep_elektroniczny`.`grupy_towarow` (`idgrupy_towarow`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sklep_elektroniczny`.`zamowienie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sklep_elektroniczny`.`zamowienie` ;

CREATE TABLE IF NOT EXISTS `sklep_elektroniczny`.`zamowienie` (
  `idzamowienie` INT(11) NOT NULL AUTO_INCREMENT,
  `status` DECIMAL(10,0) NOT NULL,
  `koszt` VARCHAR(20) NOT NULL,
  `konto_idkonto` INT(11) NOT NULL,
  PRIMARY KEY (`idzamowienie`),
  INDEX `fk_zamowienie_konto1_idx` (`konto_idkonto` ASC) ,
  CONSTRAINT `fk_zamowienie_konto1`
    FOREIGN KEY (`konto_idkonto`)
    REFERENCES `sklep_elektroniczny`.`konto` (`idkonto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sklep_elektroniczny`.`towar_zamowienia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sklep_elektroniczny`.`towar_zamowienia` ;

CREATE TABLE IF NOT EXISTS `sklep_elektroniczny`.`towar_zamowienia` (
  `idtowar_zamowienia` INT(11) NOT NULL AUTO_INCREMENT,
  `zamowienie_idzamowienie` INT(11) NOT NULL,
  `producent` VARCHAR(15) NOT NULL,
  `model` VARCHAR(15) NOT NULL,
  `cena` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idtowar_zamowienia`),
  INDEX `fk_towar_zamowienia_zamowienie1_idx` (`zamowienie_idzamowienie` ASC) ,
  CONSTRAINT `fk_towar_zamowienia_zamowienie1`
    FOREIGN KEY (`zamowienie_idzamowienie`)
    REFERENCES `sklep_elektroniczny`.`zamowienie` (`idzamowienie`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sklep_elektroniczny`.`wartosc_parametrow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sklep_elektroniczny`.`wartosc_parametrow` ;

CREATE TABLE IF NOT EXISTS `sklep_elektroniczny`.`wartosc_parametrow` (
  `idwartosc_parametrow` INT(11) NOT NULL AUTO_INCREMENT,
  `id_towar` INT(11) NOT NULL,
  `id_nazwa_parametrow` INT(11) NOT NULL,
  `wartosc_parametrow` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`),
  INDEX `fk_wartosc_parametrow_towar1_idx` (`id_towar` ASC) ,
  INDEX `fk_wartosc_parametrow_nazwa_parametrow1_idx` (`id_nazwa_parametrow` ASC) ,
  CONSTRAINT `fk_wartosc_parametrow_nazwa_parametrow1`
    FOREIGN KEY (`id_nazwa_parametrow`)
    REFERENCES `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wartosc_parametrow_towar1`
    FOREIGN KEY (`id_towar`)
    REFERENCES `sklep_elektroniczny`.`towar` (`idtowar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `sklep_elektroniczny`.`grupy_towarow`
-- -----------------------------------------------------
START TRANSACTION;
USE `sklep_elektroniczny`;
INSERT INTO `sklep_elektroniczny`.`grupy_towarow` (`idgrupy_towarow`, `nazwa_grupy`) VALUES (1, 'Procesor');
INSERT INTO `sklep_elektroniczny`.`grupy_towarow` (`idgrupy_towarow`, `nazwa_grupy`) VALUES (2, 'Karta_Graficzna');
INSERT INTO `sklep_elektroniczny`.`grupy_towarow` (`idgrupy_towarow`, `nazwa_grupy`) VALUES (3, 'Plyta_Glowna');
INSERT INTO `sklep_elektroniczny`.`grupy_towarow` (`idgrupy_towarow`, `nazwa_grupy`) VALUES (4, 'Ram');
INSERT INTO `sklep_elektroniczny`.`grupy_towarow` (`idgrupy_towarow`, `nazwa_grupy`) VALUES (5, 'Dysk_Twardy');
INSERT INTO `sklep_elektroniczny`.`grupy_towarow` (`idgrupy_towarow`, `nazwa_grupy`) VALUES (6, 'Zasilacz');
INSERT INTO `sklep_elektroniczny`.`grupy_towarow` (`idgrupy_towarow`, `nazwa_grupy`) VALUES (7, 'Obudowa');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sklep_elektroniczny`.`nazwa_parametrow`
-- -----------------------------------------------------
START TRANSACTION;
USE `sklep_elektroniczny`;
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (1, 'Taktowanie Procesora');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (2, 'Liczba Rdzeni');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (3, 'Cena');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (4, 'Pojemnosc Pamieci');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (5, 'Predkosc Pamieci');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (6, 'Usb 3.0');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (7, 'Gniazdo M2');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (8, 'DDR');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (9, 'Typ');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (10, 'Moc');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (11, 'Rozmiar');
INSERT INTO `sklep_elektroniczny`.`nazwa_parametrow` (`idnazwa_parametrow`, `nazwa_parametru`) VALUES (12, 'Kolor');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow`
-- -----------------------------------------------------
START TRANSACTION;
USE `sklep_elektroniczny`;
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (1, 1);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (1, 2);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (1, 3);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (2, 3);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (2, 4);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (2, 5);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (3, 3);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (3, 6);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (3, 7);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (4, 3);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (4, 4);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (4, 8);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (5, 3);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (5, 4);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (5, 9);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (6, 3);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (6, 10);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (7, 3);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (7, 11);
INSERT INTO `sklep_elektroniczny`.`grupy_towarow_has_nazwa_parametrow` (`grupy_towarow_idgrupy_towarow`, `nazwa_parametrow_idnazwa_parametrow`) VALUES (7, 12);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sklep_elektroniczny`.`rola`
-- -----------------------------------------------------
START TRANSACTION;
USE `sklep_elektroniczny`;
INSERT INTO `sklep_elektroniczny`.`rola` (`idrola`, `nazwa_roli`) VALUES (1, 'admin');
INSERT INTO `sklep_elektroniczny`.`rola` (`idrola`, `nazwa_roli`) VALUES (2, 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sklep_elektroniczny`.`konto`
-- -----------------------------------------------------
START TRANSACTION;
USE `sklep_elektroniczny`;
INSERT INTO `sklep_elektroniczny`.`konto` (`idkonto`, `login`, `haslo`, `email`, `Rola_idrola`) VALUES (1, 'admin', 'admin', 'admin@admin.com', 1);
INSERT INTO `sklep_elektroniczny`.`konto` (`idkonto`, `login`, `haslo`, `email`, `Rola_idrola`) VALUES (2, 'user', 'user', 'user@user.com', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sklep_elektroniczny`.`towar`
-- -----------------------------------------------------
START TRANSACTION;
USE `sklep_elektroniczny`;
INSERT INTO `sklep_elektroniczny`.`towar` (`idtowar`, `id_grupy_towarow`, `producent`, `model`) VALUES (1, 1, 'Intel', 'i5');
INSERT INTO `sklep_elektroniczny`.`towar` (`idtowar`, `id_grupy_towarow`, `producent`, `model`) VALUES (2, 2, 'Nvidia', 'GTX1060');
INSERT INTO `sklep_elektroniczny`.`towar` (`idtowar`, `id_grupy_towarow`, `producent`, `model`) VALUES (3, 3, 'Asrock', 'B150 HDS');
INSERT INTO `sklep_elektroniczny`.`towar` (`idtowar`, `id_grupy_towarow`, `producent`, `model`) VALUES (4, 4, 'GoodRam', 'Play');
INSERT INTO `sklep_elektroniczny`.`towar` (`idtowar`, `id_grupy_towarow`, `producent`, `model`) VALUES (5, 5, 'WD', 'Blue');
INSERT INTO `sklep_elektroniczny`.`towar` (`idtowar`, `id_grupy_towarow`, `producent`, `model`) VALUES (6, 6, 'Be Quiet!', 'SP8');
INSERT INTO `sklep_elektroniczny`.`towar` (`idtowar`, `id_grupy_towarow`, `producent`, `model`) VALUES (7, 7, 'SilentiumPC', 'Regnum RG1');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sklep_elektroniczny`.`wartosc_parametrow`
-- -----------------------------------------------------
START TRANSACTION;
USE `sklep_elektroniczny`;
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (1, 1, 1, '3500mhz');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (2, 1, 2, '4');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (3, 1, 3, '1700');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (4, 2, 4, '3 GB');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (5, 2, 5, '400mhz');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (6, 2, 3, '980');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (7, 3, 6, 'Nie posiada');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (8, 3, 7, 'Posiada');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (9, 3, 3, '150');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (10, 4, 8, 'DDR4');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (11, 4, 4, '8 GB');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (12, 4, 3, '200');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (13, 5, 9, 'HDD');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (14, 5, 4, '1000 GB');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (15, 5, 3, '150');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (16, 6, 10, '500W');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (17, 6, 3, '350');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (18, 7, 11, 'ATX');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (19, 7, 12, 'Czarny');
INSERT INTO `sklep_elektroniczny`.`wartosc_parametrow` (`idwartosc_parametrow`, `id_towar`, `id_nazwa_parametrow`, `wartosc_parametrow`) VALUES (20, 7, 3, '100');

COMMIT;

