-- MySQL dump 10.13  Distrib 9.0.1, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ms
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
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (20,'admin','12345678901','$2a$10$PtKYJHzp.S7oQssULXSVTuKX.KN4pxjkqzMVj4BAaUkNYKJH5CXBe','2025-02-19 19:46:45');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
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
  `companyName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '特种设备种类',
  `productNumber` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `commissioningDate` date DEFAULT NULL COMMENT '投入使用日期',
  `inspectionDate` date DEFAULT NULL COMMENT '（上次）检验日期',
  `comment` text COMMENT '备注',
  `nextInspectionDate` date DEFAULT NULL COMMENT '下次检验日期',
  `createdAt` datetime DEFAULT NULL,
  `is_inspected` int DEFAULT NULL,
  `is_overdue` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'电动单梁起重机','41702156120190307','起17辽JG0033(24)','LD5—23.5 A3','车间','xxxx铸造有限公司','起重机','2019.03.07','2019-04-20','2023-04-26','','2025-04-25','2025-02-16 10:48:28',NULL,NULL),(2,'液压机','1234646','1556465','15465456','554656','lixadha','高压设备','4464646','2025-02-17','2025-02-17','','2025-02-17','2025-02-17 12:19:28',NULL,NULL),(3,'equip1','code1','number1','version',NULL,'companyName',NULL,NULL,'2025-02-20','2025-02-20',NULL,'2026-02-20','2025-02-19 21:06:57',NULL,NULL),(4,'equip2','code2','number2',NULL,NULL,'companyName',NULL,NULL,'2025-02-20','2025-02-20',NULL,'2026-02-20','2025-02-20 12:36:03',NULL,NULL),(5,'桥式起重机','41702156120001','起17辽JG0001','LD5—23.5 A3','车间二','companyName','压力容器','2019.03.02','2025-02-19','2025-02-19',NULL,'2026-02-19','2025-02-20 16:24:14',1,0),(6,'塔式起重机','41702156120002','起17辽JG0002','LD5—23.5 A3','车间三','companyName','电梯','2019.03.03','2025-02-19','2025-02-19',NULL,'2026-02-19','2025-02-20 16:24:14',1,0);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `information` (
  `id` int NOT NULL,
  `username_id` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `equip_id` int DEFAULT NULL,
  `equip_name` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `information`
--

LOCK TABLES `information` WRITE;
/*!40000 ALTER TABLE `information` DISABLE KEYS */;
/*!40000 ALTER TABLE `information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register_user`
--

DROP TABLE IF EXISTS `register_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `register_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` datetime NOT NULL,
  `region` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000017 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register_user`
--

LOCK TABLES `register_user` WRITE;
/*!40000 ALTER TABLE `register_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `register_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` datetime NOT NULL,
  `region` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user','companyName','$2a$10$hkCzO15knYu7HwfroH8L/.iv3bTq3YcBKMK9nlwRemG2anrpNGx7.','2025-02-19 20:33:43','region'),(10,'15625632365','小时有限公司','$2a$10$oPLnIwMafHYDHBwU5A2xPuZCUN9OiPy/Mcegqmz0JiLGPgoI9yxR.','2025-02-17 12:07:56',''),(11,'13652412123','力行责任有限公司','$2a$10$57d8RKmjNAFWMGutnwnA2ewYt4UykbHCSLAzbofgPUMPuK7SLffGO','2025-02-17 12:17:47',''),(13,'12345678901','company','$2a$10$hkCzO15knYu7HwfroH8L/.iv3bTq3YcBKMK9nlwRemG2anrpNGx7.','2025-02-19 20:51:53','region');
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

-- Dump completed on 2025-02-20 16:32:05
