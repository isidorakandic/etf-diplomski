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
-- Table structure for table `projekat`
--

DROP TABLE IF EXISTS `projekat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projekat` (
  `idprojekat` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) CHARACTER SET latin1 NOT NULL,
  `rukovodilac` varchar(100) CHARACTER SET latin1 NOT NULL,
  `nio_rukovodioca` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `zvanje_rukovodioca` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `angazovanje_rukovodioca` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `lokacija_dokumenata` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `oblast` varchar(45) CHARACTER SET latin1 NOT NULL,
  `datum` date NOT NULL,
  `idprogramski_poziv` int(11) NOT NULL,
  `konacna_ocena` int(11) DEFAULT NULL,
  PRIMARY KEY (`idprojekat`),
  KEY `programski_poziv_projekat_idx` (`idprogramski_poziv`),
  CONSTRAINT `prog_poziv_projekat` FOREIGN KEY (`idprogramski_poziv`) REFERENCES `programski_poziv` (`idprogramski_poziv`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projekat`
--

LOCK TABLES `projekat` WRITE;
/*!40000 ALTER TABLE `projekat` DISABLE KEYS */;
INSERT INTO `projekat` VALUES (7,'diplomski','bosko',NULL,NULL,NULL,NULL,'skola','2019-12-06',5,64),(8,'novi','neko',NULL,NULL,NULL,NULL,'zivot','2019-07-04',5,88),(9,'Moj projekat','Isidora','','','','D:/diplomski/uploads/Moj projekatPlXNjIQkUL','','2019-12-07',7,NULL),(10,'Moj projekat 2','neko','','još ništa','','D:/diplomski/uploads/Moj projekat 2SrEJSBUNeN','','2019-12-07',5,NULL);
/*!40000 ALTER TABLE `projekat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-08 18:28:33
