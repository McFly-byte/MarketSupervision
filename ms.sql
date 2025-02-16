-- MySQL dump 10.13  Distrib 9.0.1, for Win64 (x86_64)
--
-- Host: localhost    Database: ms
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `pwd` varchar(255) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (10,'test','110','$2a$10$Y2ifFi2SMc3HW0A8w6905uNoVzqF9FgX0MUl9lyHFplD4jtNrDdz6','2025-02-15 15:08:39'),(12,'test22','18168035472','$2a$10$sLkC.0N1SDOymlU7jAnLIe0A9a.NcX1LzAwP628gX8GFfRvRXGiB6','2025-02-15 15:31:30');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (3,'test2','$2a$10$49T7KBsbEE.P5lbgumax/.c/1Hf2B6E.hOuGImxIEJ9ev66Cftxc2','110','2025-02-15 21:36:13'),(4,'test','$2a$10$Vcu90O1cR1cgUraNu9JUCeii5CJxgzBtSpxkFJ5NYGfHuZ6lD9cUG','18168035472','2025-02-15 21:36:59'),(8,'test3','$2a$10$Ih2vf.wNpoNKYLouU1miP.88ZG/2D0lI.RdbcY9LenDROYr9S4gle','18168035472','2025-02-16 10:47:59'),(9,'test4','$2a$10$P.xBK7PqFrVjnixe8t0re.TsGS4C0daQkb/Y7EeVsymrSeOku.NIK','18168035472','2025-02-16 10:48:02');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ename` varchar(255) DEFAULT NULL COMMENT '设备名称',
  `ecode` varchar(255) DEFAULT NULL COMMENT '设备代码',
  `registrationNumber` varchar(255) DEFAULT NULL COMMENT '使用登记证编号',
  `version` varchar(255) DEFAULT NULL COMMENT '设备型号规格',
  `usingPlace` varchar(255) DEFAULT NULL COMMENT '使用地点',
  `companyId` int NOT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '特种设备种类',
  `productNumber` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `commissioningDate` date DEFAULT NULL COMMENT '投入使用日期',
  `inspectionDate` date DEFAULT NULL COMMENT '（上次）检验日期',
  `comment` text COMMENT '备注',
  `nextInspectionDate` date DEFAULT NULL COMMENT '下次检验日期',
  `createdAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'电动单梁起重机','41702156120190307','起17辽JG0033(24)','LD5—23.5 A3','车间',1,'xxxx铸造有限公司','起重机','2019.03.07','2019-04-20','2023-04-26','','2025-04-25','2025-02-16 10:48:28');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unverifiedcom`
--

DROP TABLE IF EXISTS `unverifiedcom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unverifiedcom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `comment` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000014 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unverifiedcom`
--

LOCK TABLES `unverifiedcom` WRITE;
/*!40000 ALTER TABLE `unverifiedcom` DISABLE KEYS */;
INSERT INTO `unverifiedcom` VALUES (1000010,'test5','$2a$10$oaZqCJnRmp/xFClHNz6qiOTemKLU9s84g3abyd20slDYkjruN9kdu','18168035472','2025-02-16 10:34:25',NULL),(1000011,'test6','$2a$10$iXc.sHxMI.xk19laFDJAP.hTF8Q0tp0QnHQNaji5SFKzVE.FE0IHa','18168035472','2025-02-16 10:34:30',NULL),(1000012,'test7','$2a$10$ujliaLeCdpya8dyH1fhIce4sdeFrIltBb3gawC.CVgqEAnR1yyQ4q','18168035472','2025-02-16 10:35:03',NULL),(1000013,'test8','$2a$10$odb2Dz87buDuPuqDR0kTfO/bmyrfTMA7NBzuQZji5yLAzSTU6Ozo2','18168035472','2025-02-16 10:47:20',NULL);
/*!40000 ALTER TABLE `unverifiedcom` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-16 10:54:48
