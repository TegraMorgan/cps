-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: softengproject.cspvcqknb3vj.eu-central-1.rds.amazonaws.com    Database: kiwi_schema
-- ------------------------------------------------------
-- Server version	5.6.37-log

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
-- Table structure for table `car_transportation`
--

DROP TABLE IF EXISTS `car_transportation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car_transportation` (
  `customer_id` int(10) NOT NULL,
  `car_id` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `auth_type` int(10) NOT NULL,
  `auth_id` int(10) NOT NULL,
  `lot_id` int(10) NOT NULL,
  `inserted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `removed_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`,`car_id`,`inserted_at`)
) ENGINE=Memory DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaint` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customer_id` int(10) NOT NULL,
  `employee_id` int(10) DEFAULT NULL,
  `status` int(10) NOT NULL DEFAULT '0',
  `description` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=Memory DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `debit` float NOT NULL DEFAULT '0',
  `credit` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=Memory DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `daily_statistics`
--

DROP TABLE IF EXISTS `daily_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_statistics` (
  `day` date NOT NULL,
  `lot_id` int(10) NOT NULL,
  `realized_orders` int(10) DEFAULT '0',
  `canceled_orders` int(10) DEFAULT '0',
  `late_arrivals` int(10) DEFAULT '0',
  `inactive_slots` int(10) DEFAULT '0',
  PRIMARY KEY (`day`,`lot_id`)
) ENGINE=Memory DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(10) NOT NULL,
  `username` varchar(45) CHARACTER SET latin1 NOT NULL,
  `password` varchar(45) CHARACTER SET latin1 NOT NULL,
  `dept_type` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `lot_id` int(10) DEFAULT NULL,
  `permissions` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=Memory DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `onetime_service`
--

DROP TABLE IF EXISTS `onetime_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `onetime_service` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parking_type` int(10) NOT NULL,
  `customer_id` int(10) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `car_id` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lot_id` int(10) NOT NULL,
  `planned_start_time` datetime NOT NULL,
  `planned_end_time` datetime NOT NULL,
  `canceled` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=Memory AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parking_lot`
--

DROP TABLE IF EXISTS `parking_lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_lot` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `street_address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `size` int(10) NOT NULL,
  `content` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price1` float NOT NULL DEFAULT '0',
  `price2` float NOT NULL DEFAULT '0',
  `alternative_lots` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `robot_ip` varchar(48) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=Memory AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci DELAY_KEY_WRITE=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subscription_service`
--

DROP TABLE IF EXISTS `subscription_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription_service` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `subs_type` int(10) NOT NULL,
  `customer_id` int(10) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `car_id` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lot_id` int(10) DEFAULT '0',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `daily_exit_time` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=Memory DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `weekly_statistics`
--

DROP TABLE IF EXISTS `weekly_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weekly_statistics` (
  `start` date NOT NULL,
  `lot_id` int(10) NOT NULL,
  `realized_orders_mean` double NOT NULL DEFAULT '0',
  `canceled_orders_mean` double NOT NULL DEFAULT '0',
  `late_arrivals_mean` double NOT NULL DEFAULT '0',
  `realized_orders_median` double NOT NULL DEFAULT '0',
  `canceled_orders_median` double NOT NULL DEFAULT '0',
  `late_arrivals_median` double NOT NULL DEFAULT '0',
  `realized_orders_dist` varchar(300) NOT NULL,
  `canceled_orders_dist` varchar(300) NOT NULL,
  `late_arrivals_dist` varchar(300) NOT NULL,
  PRIMARY KEY (`start`,`lot_id`)
) ENGINE=Memory DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-29 20:36:43