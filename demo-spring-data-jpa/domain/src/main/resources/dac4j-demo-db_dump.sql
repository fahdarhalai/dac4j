CREATE DATABASE  IF NOT EXISTS `dac4jdemodb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dac4jdemodb`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dac4jdemodb
-- ------------------------------------------------------
-- Server version	5.6.51

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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `created_at` date DEFAULT NULL,
                        `updated_at` date DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `country_id` bigint(20) NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FK61qp87vb3ppupcmq8tc3wes8` (`country_id`),
                        CONSTRAINT `FK61qp87vb3p5upcmqqtc3wes8` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'2022-02-27','2022-02-27','Rabat',1),(2,'2022-02-27','2022-02-27','Mohammedia',1),(3,'2022-02-27','2022-02-27','Kenitra',1),(4,'2022-02-27','2022-02-27','Calais',2),(5,'2022-02-27','2022-02-27','Paris',2),(6,'2022-02-27','2022-02-27','Treviso',3);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `created_at` date DEFAULT NULL,
                           `updated_at` date DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'2022-02-27','2022-02-27','Morocco'),(2,'2022-02-27','2022-02-27','France'),(3,'2022-02-27','2022-02-27','Italy');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_access_control`
--

DROP TABLE IF EXISTS `data_access_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_access_control` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                       `source_column_name` varchar(255) DEFAULT NULL,
                                       `source_table_name` varchar(255) DEFAULT NULL,
                                       `source_value` varchar(255) DEFAULT NULL,
                                       `target_column_name` varchar(255) DEFAULT NULL,
                                       `target_table_name` varchar(255) DEFAULT NULL,
                                       `target_value` varchar(255) DEFAULT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_access_control`
--

LOCK TABLES `data_access_control` WRITE;
/*!40000 ALTER TABLE `data_access_control` DISABLE KEYS */;
INSERT INTO `data_access_control` VALUES (1,'id','user','1','id','country','1'),(2,'id','user','1','id','city','6');
/*!40000 ALTER TABLE `data_access_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistics`
--

DROP TABLE IF EXISTS `statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statistics` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `created_at` date DEFAULT NULL,
                              `updated_at` date DEFAULT NULL,
                              `population` bigint(20) DEFAULT NULL,
                              `city_id` bigint(20) NOT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FK1g7ppmn5h9rpnxuga4gk7w962` (`city_id`),
                              CONSTRAINT `FK1g7ppm45hirpnxuga4gk7w962` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistics`
--

LOCK TABLES `statistics` WRITE;
/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;
INSERT INTO `statistics` VALUES (1,'2022-02-27','2022-02-27',10000,1),(2,'2022-02-27','2022-02-27',10000,2),(3,'2022-02-27','2022-02-27',10000,3),(4,'2022-02-27','2022-02-27',10000,4),(5,'2022-02-27','2022-02-27',10000,5),(6,'2022-02-27','2022-02-27',10000,6);
/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `created_at` date DEFAULT NULL,
                        `updated_at` date DEFAULT NULL,
                        `first_name` varchar(255) DEFAULT NULL,
                        `last_name` varchar(255) DEFAULT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2022-02-27','2022-02-27','Fahd','Arhalai','fahdarhalai'),(2,'2022-02-27','2022-02-27','Faeas','Lirekib','faeaslirekib'),(3,'2022-02-27','2022-02-27','Aizaij','PoaZA','aizaijpoaza');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-17 12:01:43
