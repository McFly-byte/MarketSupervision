/*
Navicat MySQL Data Transfer

Source Server         : hm
Source Server Version : 80033
Source Host           : localhost:3306
Source Database       : ms

Target Server Type    : MYSQL
Target Server Version : 80033
File Encoding         : 65001

Date: 2025-02-19 15:01:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('10', 'test', '110', '$2a$10$Y2ifFi2SMc3HW0A8w6905uNoVzqF9FgX0MUl9lyHFplD4jtNrDdz6', '2025-02-15 15:08:39');
INSERT INTO `admin` VALUES ('12', 'test22', '18168035472', '$2a$10$sLkC.0N1SDOymlU7jAnLIe0A9a.NcX1LzAwP628gX8GFfRvRXGiB6', '2025-02-15 15:31:30');
INSERT INTO `admin` VALUES ('16', 'wdddd', '13652121201', '$2a$10$qlM/MB9Qgj7GqHPQ4LFlzeNOaI2Cay6XBfCMXwXHpM.j6XLMj8vSa', '2025-02-17 11:46:13');
INSERT INTO `admin` VALUES ('17', 'wss', '13521203265', '$2a$10$r6Fl7DQFDGeqmqbpg9Go3eh.YpyeKDL4tc7U2SWMY5/UAMLD13mVK', '2025-02-17 11:50:03');
INSERT INTO `admin` VALUES ('18', 'WDDD', '13652412122', '$2a$10$yv9KFKT/tsaw11RnpdMafuMyKQxuKcI.WH4UWNopmsxphos7EKTLe', '2025-02-17 11:53:39');
INSERT INTO `admin` VALUES ('19', 'liukun', '13562120213', '$2a$10$sL1Jh7oUIwhR7AH8uu6s1O5cVi3spt7eOohH.iw1yf6mTAtJuKNHC', '2025-02-17 11:56:21');

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES ('1', '电动单梁起重机', '41702156120190307', '起17辽JG0033(24)', 'LD5—23.5 A3', '车间', 'xxxx铸造有限公司', '起重机', '2019.03.07', '2019-04-20', '2023-04-26', '', '2025-04-25', '2025-02-16 10:48:28', null);
INSERT INTO `equipment` VALUES ('2', '液压机', '1234646', '1556465', '15465456', '554656', 'lixadha', '高压设备', '4464646', '2025-02-17', '2025-02-17', '', '2025-02-17', '2025-02-17 12:19:28', null);

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `id` int NOT NULL,
  `username_id` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `equip_id` int DEFAULT NULL,
  `equip_name` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of information
-- ----------------------------

-- ----------------------------
-- Table structure for register_user
-- ----------------------------
DROP TABLE IF EXISTS `register_user`;
CREATE TABLE `register_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` datetime NOT NULL,
  `region` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000016 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of register_user
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` datetime NOT NULL,
  `region` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10', '15625632365', '小时有限公司', '$2a$10$oPLnIwMafHYDHBwU5A2xPuZCUN9OiPy/Mcegqmz0JiLGPgoI9yxR.', '2025-02-17 12:07:56', '');
INSERT INTO `user` VALUES ('11', '13652412123', '力行责任有限公司', '$2a$10$57d8RKmjNAFWMGutnwnA2ewYt4UykbHCSLAzbofgPUMPuK7SLffGO', '2025-02-17 12:17:47', '');
