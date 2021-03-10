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
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `idkorisnik` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) CHARACTER SET latin7 NOT NULL,
  `prezime` varchar(45) CHARACTER SET latin7 NOT NULL,
  `korisnicko_ime` varchar(45) CHARACTER SET latin7 NOT NULL,
  `lozinka` varchar(100) CHARACTER SET latin7 NOT NULL,
  `nacionalnost` varchar(45) CHARACTER SET latin7 DEFAULT NULL,
  `zemlja_zaposlenja` varchar(45) CHARACTER SET latin7 DEFAULT NULL,
  `nio_zaposlenja` varchar(45) CHARACTER SET latin7 DEFAULT NULL,
  `naucno_zvanje` varchar(45) CHARACTER SET latin7 DEFAULT NULL,
  `angazovanje` varchar(45) CHARACTER SET latin7 DEFAULT NULL,
  `strucne_oblasti` varchar(200) CHARACTER SET latin7 DEFAULT NULL,
  `mobilni` varchar(45) CHARACTER SET latin7 DEFAULT NULL,
  `email` varchar(100) CHARACTER SET latin7 NOT NULL,
  `adresa` varchar(100) CHARACTER SET latin7 DEFAULT NULL,
  `veb_stranica` varchar(256) CHARACTER SET latin7 DEFAULT NULL,
  `naziv_biografije` varchar(256) CHARACTER SET latin7 DEFAULT NULL,
  `privremena_lozinka_postavljena` bigint(20) DEFAULT '0',
  `stanje` varchar(5) CHARACTER SET latin7 NOT NULL,
  PRIMARY KEY (`idkorisnik`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (3,'Marko','Markovic','marko','8c5faf36ce0dae48351f5e09c5133fdaddcf52d9baf4369db027766a12c1742f','','','','','','','','marko@gmail.com','','','null-4811751161690370256.null',NULL,'I'),(4,'Sofija','Kandic','sofija','18f5de6ff01071e1550d19745c07f037bb3ac8915ba8a0cd71ca383751ba97de','','','','','','','','sofija@gmail.com','','','null-4089818801180343998.null',NULL,'O'),(11,'Isidora','Kandic','isidora','0062295337385b3d0ce9e950484a4ac4e94b95e04f241579948f2a7a29ff41e8','','','','','','','064444444444','isidora.kandic@gmail.com','','','null-635175282534057943.null',NULL,'P'),(12,'admin','admin','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','','','','','','','','admin@admin.com','','','null-2625564519913338933.null',NULL,'P');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
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
