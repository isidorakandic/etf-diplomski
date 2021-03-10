CREATE DATABASE  IF NOT EXISTS `baza_recenzenata` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `baza_recenzenata`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: baza_recenzenata
-- ------------------------------------------------------
-- Server version	5.7.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `korisnik_odgovor`
--

DROP TABLE IF EXISTS `korisnik_odgovor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik_odgovor` (
  `idodgovor` int(11) NOT NULL,
  `idpitanje` int(11) NOT NULL,
  `idkorisnik` int(11) NOT NULL,
  `idprojekat` int(11) NOT NULL,
  PRIMARY KEY (`idodgovor`,`idpitanje`,`idkorisnik`,`idprojekat`),
  KEY `idpitanje_idx` (`idpitanje`),
  KEY `idkorisnik_idx` (`idkorisnik`,`idprojekat`),
  CONSTRAINT `idkorisnik12` FOREIGN KEY (`idkorisnik`, `idprojekat`) REFERENCES `korisnik_projekat` (`idkorisnik`, `idprojekat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idodgovor12` FOREIGN KEY (`idodgovor`) REFERENCES `odgovor` (`idodgovor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idpitanje12` FOREIGN KEY (`idpitanje`) REFERENCES `pitanje` (`idpitanje`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik_odgovor`
--

LOCK TABLES `korisnik_odgovor` WRITE;
/*!40000 ALTER TABLE `korisnik_odgovor` DISABLE KEYS */;
INSERT INTO `korisnik_odgovor` VALUES (12,5,11,7),(14,6,11,7);
/*!40000 ALTER TABLE `korisnik_odgovor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-08 18:28:34
