/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.5-10.4.10-MariaDB : Database - veterinarska_ambulanta
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`veterinarska_ambulanta` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `veterinarska_ambulanta`;

/*Table structure for table `karton` */

DROP TABLE IF EXISTS `karton`;

CREATE TABLE `karton` (
  `KartonID` int(11) NOT NULL AUTO_INCREMENT,
  `DatumKreiranja` date DEFAULT NULL,
  `Napomena` varchar(50) DEFAULT NULL,
  `RadnikID` int(11) DEFAULT NULL,
  `ZivotinjaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`KartonID`),
  KEY `RadnikID` (`RadnikID`),
  KEY `karton_ibfk_2` (`ZivotinjaID`),
  CONSTRAINT `karton_ibfk_1` FOREIGN KEY (`RadnikID`) REFERENCES `radnik` (`RadnikID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `karton_ibfk_2` FOREIGN KEY (`ZivotinjaID`) REFERENCES `zivotinja` (`ZivotinjaID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `karton` */

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `KlijentID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) DEFAULT NULL,
  `Prezime` varchar(50) DEFAULT NULL,
  `DatumRodjenja` date DEFAULT NULL,
  `Adresa` varchar(50) DEFAULT NULL,
  `Telefon` varchar(50) DEFAULT NULL,
  `Mesto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`KlijentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `klijent` */

/*Table structure for table `lek` */

DROP TABLE IF EXISTS `lek`;

CREATE TABLE `lek` (
  `PredmetProdajeID` int(11) NOT NULL,
  `Oznaka` varchar(50) DEFAULT NULL,
  `Naziv` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PredmetProdajeID`),
  CONSTRAINT `lek_ibfk_1` FOREIGN KEY (`PredmetProdajeID`) REFERENCES `predmet_prodaje` (`PredmetProdajeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `lek` */

/*Table structure for table `poreska_stopa` */

DROP TABLE IF EXISTS `poreska_stopa`;

CREATE TABLE `poreska_stopa` (
  `PoreskaStopaID` int(11) NOT NULL AUTO_INCREMENT,
  `Oznaka` varchar(50) DEFAULT NULL,
  `Vrednost` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`PoreskaStopaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `poreska_stopa` */

/*Table structure for table `predmet_prodaje` */

DROP TABLE IF EXISTS `predmet_prodaje`;

CREATE TABLE `predmet_prodaje` (
  `PredmetProdajeID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(50) DEFAULT NULL,
  `CenaBezPoreza` decimal(10,0) DEFAULT NULL,
  `CenaSaPorezom` decimal(10,0) DEFAULT NULL,
  `PoreskaStopaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PredmetProdajeID`),
  KEY `PoreskaStopaID` (`PoreskaStopaID`),
  CONSTRAINT `predmet_prodaje_ibfk_1` FOREIGN KEY (`PoreskaStopaID`) REFERENCES `poreska_stopa` (`PoreskaStopaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `predmet_prodaje` */

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `RacunID` int(11) NOT NULL AUTO_INCREMENT,
  `UkupnaCenaSaPorezom` decimal(10,0) DEFAULT NULL,
  `UkupnaCenaBezPoreza` decimal(10,0) DEFAULT NULL,
  `Porez` decimal(10,0) DEFAULT NULL,
  `Obradjen` tinyint(1) DEFAULT NULL,
  `Storniran` tinyint(1) DEFAULT NULL,
  `RadnikID` int(11) DEFAULT NULL,
  `KartonID` int(11) DEFAULT NULL,
  `DatumKreiranja` date DEFAULT NULL,
  PRIMARY KEY (`RacunID`),
  KEY `RadnikID` (`RadnikID`),
  KEY `KartonID` (`KartonID`),
  CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`RadnikID`) REFERENCES `radnik` (`RadnikID`),
  CONSTRAINT `racun_ibfk_2` FOREIGN KEY (`KartonID`) REFERENCES `karton` (`KartonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `racun` */

/*Table structure for table `radnik` */

DROP TABLE IF EXISTS `radnik`;

CREATE TABLE `radnik` (
  `RadnikID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) DEFAULT NULL,
  `Prezime` varchar(50) DEFAULT NULL,
  `DatumRodjenja` date DEFAULT NULL,
  `Adresa` varchar(50) DEFAULT NULL,
  `Telefon` varchar(20) DEFAULT NULL,
  `Administrator` tinyint(1) DEFAULT NULL,
  `KorisnickoIme` varchar(50) DEFAULT NULL,
  `Lozinka` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`RadnikID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `radnik` */

/*Table structure for table `stavka_racuna` */

DROP TABLE IF EXISTS `stavka_racuna`;

CREATE TABLE `stavka_racuna` (
  `RacunID` int(11) NOT NULL,
  `StavkaRacunaID` int(11) NOT NULL,
  `Kolicina` int(11) DEFAULT NULL,
  `CenaPoJediniciSaPorezom` decimal(10,0) DEFAULT NULL,
  `UkupnaCenaSaPorezom` decimal(10,0) DEFAULT NULL,
  `PredmetProdajeID` int(11) DEFAULT NULL,
  `CenaPoJediniciBezPoreza` decimal(10,0) DEFAULT NULL,
  `UkupnaCenaBezPoreza` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`RacunID`,`StavkaRacunaID`),
  KEY `stavka_racuna_ibfk_1` (`PredmetProdajeID`),
  CONSTRAINT `stavka_racuna_ibfk_1` FOREIGN KEY (`PredmetProdajeID`) REFERENCES `predmet_prodaje` (`PredmetProdajeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `stavka_racuna` */

/*Table structure for table `usluga` */

DROP TABLE IF EXISTS `usluga`;

CREATE TABLE `usluga` (
  `PredmetProdajeID` int(11) NOT NULL,
  `Naziv` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PredmetProdajeID`),
  CONSTRAINT `usluga_ibfk_1` FOREIGN KEY (`PredmetProdajeID`) REFERENCES `predmet_prodaje` (`PredmetProdajeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `usluga` */

/*Table structure for table `zivotinja` */

DROP TABLE IF EXISTS `zivotinja`;

CREATE TABLE `zivotinja` (
  `ZivotinjaID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) DEFAULT NULL,
  `Rasa` varchar(50) DEFAULT NULL,
  `Boja` varchar(50) DEFAULT NULL,
  `Pol` varchar(50) DEFAULT NULL,
  `DatumRodjenja` date DEFAULT NULL,
  `KlijentID` int(11) DEFAULT NULL,
  `Vrsta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ZivotinjaID`),
  KEY `KlijentID` (`KlijentID`),
  CONSTRAINT `zivotinja_ibfk_1` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `zivotinja` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
