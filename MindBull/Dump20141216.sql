CREATE DATABASE  IF NOT EXISTS `firefalcon` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `firefalcon`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: firefalcon
-- ------------------------------------------------------
-- Server version	5.6.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignmentresult`
--

DROP TABLE IF EXISTS `assignmentresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignmentresult` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avgTime` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `maxSpeed` int(11) DEFAULT NULL,
  `maxTime` int(11) DEFAULT NULL,
  `minTime` int(11) DEFAULT NULL,
  `repetitions` int(11) DEFAULT NULL,
  `totalTime` int(11) DEFAULT NULL,
  `exercise_id` int(11) DEFAULT NULL,
  `patient_bsn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAFC3716A9B5D1019` (`exercise_id`),
  KEY `FKAFC3716A8C91C3C9` (`patient_bsn`),
  CONSTRAINT `FKAFC3716A8C91C3C9` FOREIGN KEY (`patient_bsn`) REFERENCES `patient` (`bsn`),
  CONSTRAINT `FKAFC3716A9B5D1019` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignmentresult`
--

LOCK TABLES `assignmentresult` WRITE;
/*!40000 ALTER TABLE `assignmentresult` DISABLE KEYS */;
INSERT INTO `assignmentresult` VALUES (1,7,'2014-11-14',10,8,6,10,140,1,'zpNMwDk3D6Q='),(2,6,'2014-11-16',11,7,5,13,150,2,'zpNMwDk3D6Q='),(3,7,'2014-11-17',12,9,6,12,130,1,'zpNMwDk3D6Q='),(4,6,'2014-11-18',10,8,5,9,100,2,'zpNMwDk3D6Q='),(5,5,'2014-11-19',9,6,4,15,120,1,'zpNMwDk3D6Q='),(6,9,'2014-12-01',11,12,6,12,140,1,'zpNMwDk3D6Q='),(7,9,'2014-12-03',12,12,6,14,130,2,'zpNMwDk3D6Q='),(8,8,'2014-12-04',12,11,5,15,120,1,'zpNMwDk3D6Q='),(9,8,'2014-12-06',11,11,5,14,120,1,'zpNMwDk3D6Q='),(10,7,'2014-12-07',12,10,4,15,110,1,'zpNMwDk3D6Q='),(11,7,'2014-11-06',10,8,6,10,140,1,'zpNMwDk3D6Q='),(12,7,'2014-10-16',10,8,6,12,150,1,'zpNMwDk3D6Q='),(13,7,'2014-08-14',10,8,6,13,130,1,'zpNMwDk3D6Q='),(14,7,'2014-07-30',10,8,6,14,100,1,'zpNMwDk3D6Q=');
/*!40000 ALTER TABLE `assignmentresult` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-16 11:41:38
