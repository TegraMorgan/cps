-- MySQL dump 10.16  Distrib 10.1.30-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: cps_test
-- ------------------------------------------------------
-- Server version	10.1.30-MariaDB

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_transportation`
--

LOCK TABLES `car_transportation` WRITE;
/*!40000 ALTER TABLE `car_transportation` DISABLE KEYS */;
/*!40000 ALTER TABLE `car_transportation` ENABLE KEYS */;
UNLOCK TABLES;

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
  `lot_id` int(10) DEFAULT NULL,
  `status` int(10) NOT NULL DEFAULT '1',
  `description` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `resolved_at` timestamp NULL DEFAULT NULL,
  `refund_amount` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_statistics`
--

LOCK TABLES `daily_statistics` WRITE;
/*!40000 ALTER TABLE `daily_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `daily_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disabled_slots_table`
--

DROP TABLE IF EXISTS `disabled_slots_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disabled_slots_table` (
  `lotid` int(11) NOT NULL,
  `date_disabled` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `depth` int(11) NOT NULL,
  `date_enabled` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`lotid`,`depth`,`height`,`width`,`date_disabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disabled_slots_table`
--

LOCK TABLES `disabled_slots_table` WRITE;
/*!40000 ALTER TABLE `disabled_slots_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `disabled_slots_table` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_report`
--

DROP TABLE IF EXISTS `monthly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_report` (
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `lot_id` int(11) NOT NULL,
  `ordered_reserved` int(11) DEFAULT '0',
  `ordered_incidental` int(11) DEFAULT '0',
  `ordered_regular` int(11) DEFAULT '0',
  `ordered_full` int(11) DEFAULT '0',
  `complaints_count` int(11) DEFAULT '0',
  `complaints_closed_count` int(11) DEFAULT '0',
  `complaints_refunded_count` int(11) DEFAULT '0',
  `disabled_slots` int(11) DEFAULT '0',
  PRIMARY KEY (`year`,`month`,`lot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_report`
--

LOCK TABLES `monthly_report` WRITE;
/*!40000 ALTER TABLE `monthly_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `monthly_report` ENABLE KEYS */;
UNLOCK TABLES;

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
  `parked` bit(1) NOT NULL DEFAULT b'0',
  `completed` bit(1) NOT NULL DEFAULT b'0',
  `canceled` bit(1) NOT NULL DEFAULT b'0',
  `warned` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `onetime_service`
--

LOCK TABLES `onetime_service` WRITE;
/*!40000 ALTER TABLE `onetime_service` DISABLE KEYS */;
INSERT INTO `onetime_service` VALUES (1,2,0,'sjnuglg@gmail.com','LA-401099',1,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(2,2,0,'ffugjfu@gmail.com','LA-393609',1,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(3,2,0,'jfsjfjf@gmail.com','AA-057008',1,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(4,2,0,'jufurjl@gmail.com','LA-486208',1,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(5,2,0,'agfanun@gmail.com','II-008583',1,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(6,2,0,'gfjnfjf@gmail.com','BB-312252',1,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(7,2,0,'usnrrus@gmail.com','AI-804155',1,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(8,2,0,'sfjfujr@gmail.com','LB-629355',1,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(9,2,0,'jujflfl@gmail.com','IB-741010',1,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(10,2,0,'sfuujug@gmail.com','AI-436740',1,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(11,2,0,'affnfrn@gmail.com','AB-447318',1,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(12,2,0,'jullurf@gmail.com','LI-209065',1,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(13,2,0,'ujsuujr@gmail.com','TL-646058',1,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(14,2,0,'jrjsfrj@gmail.com','IL-069325',1,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(15,2,0,'usgsfjg@gmail.com','TT-591689',1,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(16,2,0,'ggjjfuu@gmail.com','AL-181590',1,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(17,2,0,'njjrnnr@gmail.com','TL-815353',1,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(18,2,0,'flfnarj@gmail.com','IT-034739',1,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(19,2,0,'flsasgn@gmail.com','IL-106312',1,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(20,2,0,'afujujn@gmail.com','AT-571187',1,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(21,2,0,'jlgnffj@gmail.com','BL-795989',1,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(22,2,0,'jfnfrrj@gmail.com','LT-911767',1,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(23,2,0,'rrsfjfa@gmail.com','IT-938826',1,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(24,2,0,'ruunrus@gmail.com','TA-951591',1,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(25,2,0,'jarjjnf@gmail.com','II-224210',1,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(26,2,0,'jrnjfla@gmail.com','AL-981051',1,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(27,2,0,'jjrfnuj@gmail.com','LA-719923',1,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(28,2,0,'rljluaf@gmail.com','IL-432532',1,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(29,2,0,'fgfufuf@gmail.com','BT-771985',1,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(30,2,0,'jsfnfnj@gmail.com','TT-058949',1,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(31,2,0,'jgusauj@gmail.com','AT-812659',1,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(32,2,0,'ljjfjnu@gmail.com','TI-284214',1,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(33,2,0,'nfjufrf@gmail.com','AA-044818',1,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(34,2,0,'suugjjs@gmail.com','BB-268679',1,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(35,2,0,'sjffrng@gmail.com','IL-675974',1,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(36,2,0,'fjjnnjj@gmail.com','IA-512882',1,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(37,2,0,'sujgfnf@gmail.com','TA-418031',2,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(38,2,0,'faujnnj@gmail.com','AL-784648',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(39,2,0,'sufuujj@gmail.com','AT-255077',2,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(40,2,0,'ssjlsug@gmail.com','LB-809659',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(41,2,0,'uujjnfg@gmail.com','IT-044275',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(42,2,0,'asrjjgu@gmail.com','BT-600753',2,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(43,2,0,'njjjgus@gmail.com','BA-604696',2,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(44,2,0,'sjsufju@gmail.com','TL-619924',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(45,2,0,'jurlunu@gmail.com','BB-564242',2,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(46,2,0,'jjnufag@gmail.com','AT-646443',2,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(47,2,0,'lfujusj@gmail.com','TL-110697',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(48,2,0,'jjuusjj@gmail.com','LL-697515',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(49,2,0,'jujgjaf@gmail.com','LL-388277',2,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(50,2,0,'ujnfjfg@gmail.com','IB-528150',2,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(51,2,0,'jsjlsaa@gmail.com','IT-953115',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(52,2,0,'nuujnjg@gmail.com','IL-831761',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(53,2,0,'jjruauu@gmail.com','TI-056596',2,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(54,2,0,'nfnuuls@gmail.com','AT-142083',2,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(55,2,0,'ruaurrn@gmail.com','AA-580372',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(56,2,0,'gfnffjn@gmail.com','TT-647762',2,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(57,2,0,'jrfjurg@gmail.com','LI-065965',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(58,2,0,'jfasgsg@gmail.com','LT-503730',2,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(59,2,0,'ffnjjuf@gmail.com','IA-670990',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(60,2,0,'fjsurll@gmail.com','TL-785459',2,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(61,2,0,'gsuruja@gmail.com','IT-889721',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(62,2,0,'lnjuafj@gmail.com','AA-854854',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(63,2,0,'uffsful@gmail.com','II-820867',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(64,2,0,'jfajjaf@gmail.com','II-377658',2,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(65,2,0,'flgrjaa@gmail.com','BB-241741',2,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(66,2,0,'nfjjjju@gmail.com','LT-805785',2,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(67,2,0,'fuunual@gmail.com','AA-294508',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(68,2,0,'sjgjffj@gmail.com','AA-036965',2,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(69,2,0,'njgjsug@gmail.com','BB-404800',2,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(70,2,0,'njjjjrn@gmail.com','IT-100700',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(71,2,0,'jjfjfju@gmail.com','IA-788370',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(72,2,0,'gfgflfa@gmail.com','IB-605410',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(73,2,0,'lsgasfj@gmail.com','BI-175354',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(74,2,0,'sjrjfjr@gmail.com','BL-992057',2,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(75,2,0,'ljlnjja@gmail.com','AL-089534',2,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(76,2,0,'afjalnu@gmail.com','IL-471492',2,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(77,2,0,'fjslunr@gmail.com','LB-102936',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(78,2,0,'fffgusu@gmail.com','TB-076426',2,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(79,2,0,'ujarljj@gmail.com','LI-284179',2,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(80,2,0,'flsjaul@gmail.com','IB-495621',2,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(81,2,0,'fauusgr@gmail.com','LI-247582',2,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(82,2,0,'jjrunlf@gmail.com','BA-022368',2,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(83,2,0,'gljjfsu@gmail.com','LI-500760',2,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(84,2,0,'fajlnjr@gmail.com','IB-918388',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(85,2,0,'jarsfjg@gmail.com','IL-648229',2,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(86,2,0,'jusjjaf@gmail.com','AL-651700',2,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(87,2,0,'ljrujjf@gmail.com','TI-193082',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(88,2,0,'agnrjlr@gmail.com','BL-770994',2,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(89,2,0,'ufsufrf@gmail.com','TT-777142',2,'2018-02-02 01:06:47','2018-02-02 19:01:47','\0','\0','\0','\0'),(90,2,0,'jfjujrs@gmail.com','AT-874657',2,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(91,2,0,'unjgjfu@gmail.com','BB-294408',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(92,2,0,'ggujjsa@gmail.com','LA-644868',2,'2018-02-02 01:06:47','2018-02-03 17:01:47','\0','\0','\0','\0'),(93,2,0,'ffsjngn@gmail.com','AL-969850',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(94,2,0,'ajfsfsn@gmail.com','LL-655774',2,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(95,2,0,'gfufjjf@gmail.com','BI-714758',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(96,2,0,'ljfjjug@gmail.com','LB-237029',2,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(97,2,0,'ujafgjj@gmail.com','LI-483241',2,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(98,2,0,'uajgrgg@gmail.com','AL-383557',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0'),(99,2,0,'fjurjsa@gmail.com','LT-046945',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(100,2,0,'rljjjuj@gmail.com','TB-066983',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(101,2,0,'jruaurn@gmail.com','TB-736503',2,'2018-02-02 01:06:47','2018-02-02 03:01:47','\0','\0','\0','\0'),(102,2,0,'juunujn@gmail.com','LA-585082',2,'2018-02-02 01:06:47','2018-02-02 14:01:47','\0','\0','\0','\0'),(103,2,0,'fsufsun@gmail.com','BL-324497',2,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(104,2,0,'sufauja@gmail.com','TA-623895',2,'2018-02-02 01:06:47','2018-02-03 03:01:47','\0','\0','\0','\0'),(105,2,0,'fjjlluu@gmail.com','AA-925994',2,'2018-02-02 01:06:47','2018-02-03 07:01:47','\0','\0','\0','\0'),(106,2,0,'jglajjr@gmail.com','AT-737161',2,'2018-02-02 01:06:47','2018-02-03 01:01:47','\0','\0','\0','\0'),(107,2,0,'jggrgru@gmail.com','LI-876320',2,'2018-02-02 01:06:47','2018-02-02 08:01:47','\0','\0','\0','\0');
/*!40000 ALTER TABLE `onetime_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_cell`
--

DROP TABLE IF EXISTS `parking_cell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_cell` (
  `lot_id` int(10) NOT NULL,
  `i` int(10) NOT NULL,
  `j` int(10) NOT NULL,
  `k` int(10) NOT NULL,
  `car_id` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `planned_end_time` datetime DEFAULT NULL,
  `reserved` bit(1) DEFAULT b'0',
  `disabled` bit(1) DEFAULT b'0',
  PRIMARY KEY (`lot_id`,`i`,`j`,`k`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_cell`
--

LOCK TABLES `parking_cell` WRITE;
/*!40000 ALTER TABLE `parking_cell` DISABLE KEYS */;
INSERT INTO `parking_cell` VALUES (1,0,0,0,'IA-512882','2018-02-03 17:01:47','\0','\0'),(1,0,0,1,'LT-911767','2018-02-02 03:01:47','\0','\0'),(1,0,0,2,'BB-312252','2018-02-02 08:01:47','\0','\0'),(1,0,1,0,'TI-284214','2018-02-02 08:01:47','\0','\0'),(1,0,1,1,'AL-181590','2018-02-02 03:01:47','\0','\0'),(1,0,1,2,'II-008583','2018-02-02 03:01:47','\0','\0'),(1,0,2,0,'IL-432532','2018-02-03 17:01:47','\0','\0'),(1,0,2,1,'IL-069325','2018-02-02 03:01:47','\0','\0'),(1,0,2,2,'LA-401099','2018-02-02 08:01:47','\0','\0'),(1,1,0,0,'IL-675974','2018-02-02 08:01:47','\0','\0'),(1,1,0,1,'IL-106312','2018-02-02 19:01:47','\0','\0'),(1,1,0,2,'AI-436740','2018-02-02 19:01:47','\0','\0'),(1,1,1,0,'AT-812659','2018-02-03 01:01:47','\0','\0'),(1,1,1,1,'TL-815353','2018-02-02 14:01:47','\0','\0'),(1,1,1,2,'LA-486208','2018-02-02 14:01:47','\0','\0'),(1,1,2,0,'LA-719923','2018-02-02 08:01:47','\0','\0'),(1,1,2,1,'TT-591689','2018-02-03 01:01:47','\0','\0'),(1,1,2,2,'AA-057008','2018-02-03 01:01:47','\0','\0'),(1,2,0,0,'BB-268679','2018-02-02 19:01:47','\0','\0'),(1,2,0,1,'LI-209065','2018-02-03 07:01:47','\0','\0'),(1,2,0,2,'LB-629355','2018-02-03 03:01:47','\0','\0'),(1,2,1,0,'TT-058949','2018-02-03 07:01:47','\0','\0'),(1,2,1,1,'AB-447318','2018-02-03 07:01:47','\0','\0'),(1,2,1,2,'AI-804155','2018-02-03 07:01:47','\0','\0'),(1,2,2,0,'AL-981051','2018-02-03 07:01:47','\0','\0'),(1,2,2,1,'LA-393609','2018-02-03 03:01:47','\0','\0'),(1,2,2,2,'BL-795989','2018-02-02 14:01:47','\0','\0'),(1,3,0,0,'AA-044818','2018-02-02 19:01:47','\0','\0'),(1,3,0,1,'TA-951591','2018-02-03 17:01:47','\0','\0'),(1,3,0,2,'AT-571187','2018-02-03 07:01:47','\0','\0'),(1,3,1,0,'BT-771985','2018-02-02 14:01:47','\0','\0'),(1,3,1,1,'IT-938826','2018-02-03 17:01:47','\0','\0'),(1,3,1,2,'IT-034739','2018-02-03 07:01:47','\0','\0'),(1,3,2,0,'II-224210','2018-02-03 07:01:47','\0','\0'),(1,3,2,1,'IB-741010','2018-02-03 03:01:47','\0','\0'),(1,3,2,2,'TL-646058','2018-02-03 03:01:47','\0','\0'),(2,0,0,0,NULL,NULL,'\0','\0'),(2,0,0,1,'IT-100700','2018-02-02 03:01:47','\0','\0'),(2,0,0,2,'IT-889721','2018-02-02 03:01:47','\0','\0'),(2,0,1,0,'TB-066983','2018-02-03 07:01:47','\0','\0'),(2,0,1,1,'AA-294508','2018-02-02 03:01:47','\0','\0'),(2,0,1,2,'IT-953115','2018-02-02 03:01:47','\0','\0'),(2,0,2,0,'LA-644868','2018-02-03 17:01:47','\0','\0'),(2,0,2,1,'II-820867','2018-02-02 03:01:47','\0','\0'),(2,0,2,2,'TL-110697','2018-02-02 03:01:47','\0','\0'),(2,1,0,0,'LI-876320','2018-02-02 08:01:47','\0','\0'),(2,1,0,1,'IA-788370','2018-02-02 03:01:47','\0','\0'),(2,1,0,2,'LI-065965','2018-02-02 08:01:47','\0','\0'),(2,1,1,0,'LT-046945','2018-02-02 03:01:47','\0','\0'),(2,1,1,1,'AA-854854','2018-02-02 08:01:47','\0','\0'),(2,1,1,2,'TL-619924','2018-02-02 08:01:47','\0','\0'),(2,1,2,0,'BB-294408','2018-02-02 08:01:47','\0','\0'),(2,1,2,1,'LB-809659','2018-02-02 08:01:47','\0','\0'),(2,1,2,2,'IB-605410','2018-02-02 03:01:47','\0','\0'),(2,2,0,0,'AT-737161','2018-02-03 01:01:47','\0','\0'),(2,2,0,1,'TI-056596','2018-02-02 19:01:47','\0','\0'),(2,2,0,2,'AT-646443','2018-02-02 19:01:47','\0','\0'),(2,2,1,0,'AL-383557','2018-02-02 08:01:47','\0','\0'),(2,2,1,1,'BB-564242','2018-02-02 19:01:47','\0','\0'),(2,2,1,2,'IB-918388','2018-02-02 08:01:47','\0','\0'),(2,2,2,0,'AT-874657','2018-02-03 17:01:47','\0','\0'),(2,2,2,1,'TA-418031','2018-02-02 19:01:47','\0','\0'),(2,2,2,2,'LB-102936','2018-02-02 08:01:47','\0','\0'),(2,3,0,0,'AA-925994','2018-02-03 07:01:47','\0','\0'),(2,3,0,1,'LI-284179','2018-02-02 19:01:47','\0','\0'),(2,3,0,2,'LL-388277','2018-02-02 14:01:47','\0','\0'),(2,3,1,0,'LI-483241','2018-02-03 01:01:47','\0','\0'),(2,3,1,1,'IA-670990','2018-02-02 08:01:47','\0','\0'),(2,3,1,2,'II-377658','2018-02-02 14:01:47','\0','\0'),(2,3,2,0,'TT-777142','2018-02-02 19:01:47','\0','\0'),(2,3,2,1,'IB-528150','2018-02-02 14:01:47','\0','\0'),(2,3,2,2,'LI-500760','2018-02-02 19:01:47','\0','\0'),(2,4,0,0,'TA-623895','2018-02-03 03:01:47','\0','\0'),(2,4,0,1,'IT-044275','2018-02-03 07:01:47','\0','\0'),(2,4,0,2,'LT-805785','2018-02-03 01:01:47','\0','\0'),(2,4,1,0,'LB-237029','2018-02-03 01:01:47','\0','\0'),(2,4,1,1,'TB-076426','2018-02-03 01:01:47','\0','\0'),(2,4,1,2,'AT-142083','2018-02-02 19:01:47','\0','\0'),(2,4,2,0,'BL-770994','2018-02-03 03:01:47','\0','\0'),(2,4,2,1,'LI-247582','2018-02-02 14:01:47','\0','\0'),(2,4,2,2,'IL-471492','2018-02-03 01:01:47','\0','\0'),(2,5,0,0,'BL-324497','2018-02-03 03:01:47','\0','\0'),(2,5,0,1,'BI-175354','2018-02-03 07:01:47','\0','\0'),(2,5,0,2,'LT-503730','2018-02-03 03:01:47','\0','\0'),(2,5,1,0,'BI-714758','2018-02-02 03:01:47','\0','\0'),(2,5,1,1,'BB-404800','2018-02-03 03:01:47','\0','\0'),(2,5,1,2,'TT-647762','2018-02-03 03:01:47','\0','\0'),(2,5,2,0,'TI-193082','2018-02-03 07:01:47','\0','\0'),(2,5,2,1,'AA-580372','2018-02-03 07:01:47','\0','\0'),(2,5,2,2,'AA-036965','2018-02-02 14:01:47','\0','\0'),(2,6,0,0,'LA-585082','2018-02-02 14:01:47','\0','\0'),(2,6,0,1,'BL-992057','2018-02-03 17:01:47','\0','\0'),(2,6,0,2,'AT-255077','2018-02-03 03:01:47','\0','\0'),(2,6,1,0,'LL-655774','2018-02-02 14:01:47','\0','\0'),(2,6,1,1,'BB-241741','2018-02-03 17:01:47','\0','\0'),(2,6,1,2,'AL-784648','2018-02-03 07:01:47','\0','\0'),(2,6,2,0,'AL-651700','2018-02-03 17:01:47','\0','\0'),(2,6,2,1,'TL-785459','2018-02-03 03:01:47','\0','\0'),(2,6,2,2,'IL-831761','2018-02-03 07:01:47','\0','\0'),(2,7,0,0,'TB-736503','2018-02-02 03:01:47','\0','\0'),(2,7,0,1,'BT-600753','2018-02-03 17:01:47','\0','\0'),(2,7,0,2,'BA-604696','2018-02-03 03:01:47','\0','\0'),(2,7,1,0,'AL-969850','2018-02-03 07:01:47','\0','\0'),(2,7,1,1,'LL-697515','2018-02-03 07:01:47','\0','\0'),(2,7,1,2,'IB-495621','2018-02-03 17:01:47','\0','\0'),(2,7,2,0,'IL-648229','2018-02-03 01:01:47','\0','\0'),(2,7,2,1,'BA-022368','2018-02-03 17:01:47','\0','\0'),(2,7,2,2,'AL-089534','2018-02-03 17:01:47','\0','\0'),(3,0,0,0,NULL,NULL,'\0','\0'),(3,0,0,1,NULL,NULL,'\0','\0'),(3,0,0,2,NULL,NULL,'\0','\0'),(3,0,1,0,NULL,NULL,'\0','\0'),(3,0,1,1,NULL,NULL,'\0','\0'),(3,0,1,2,NULL,NULL,'\0','\0'),(3,0,2,0,NULL,NULL,'\0','\0'),(3,0,2,1,NULL,NULL,'\0','\0'),(3,0,2,2,NULL,NULL,'\0','\0'),(3,1,0,0,NULL,NULL,'\0','\0'),(3,1,0,1,NULL,NULL,'\0','\0'),(3,1,0,2,NULL,NULL,'\0','\0'),(3,1,1,0,NULL,NULL,'\0','\0'),(3,1,1,1,NULL,NULL,'\0','\0'),(3,1,1,2,NULL,NULL,'\0','\0'),(3,1,2,0,NULL,NULL,'\0','\0'),(3,1,2,1,NULL,NULL,'\0','\0'),(3,1,2,2,NULL,NULL,'\0','\0'),(3,2,0,0,NULL,NULL,'\0','\0'),(3,2,0,1,NULL,NULL,'\0','\0'),(3,2,0,2,NULL,NULL,'\0','\0'),(3,2,1,0,NULL,NULL,'\0','\0'),(3,2,1,1,NULL,NULL,'\0','\0'),(3,2,1,2,NULL,NULL,'\0','\0'),(3,2,2,0,NULL,NULL,'\0','\0'),(3,2,2,1,NULL,NULL,'\0','\0'),(3,2,2,2,NULL,NULL,'\0','\0'),(3,3,0,0,NULL,NULL,'\0','\0'),(3,3,0,1,NULL,NULL,'\0','\0'),(3,3,0,2,NULL,NULL,'\0','\0'),(3,3,1,0,NULL,NULL,'\0','\0'),(3,3,1,1,NULL,NULL,'\0','\0'),(3,3,1,2,NULL,NULL,'\0','\0'),(3,3,2,0,NULL,NULL,'\0','\0'),(3,3,2,1,NULL,NULL,'\0','\0'),(3,3,2,2,NULL,NULL,'\0','\0'),(3,4,0,0,NULL,NULL,'\0','\0'),(3,4,0,1,NULL,NULL,'\0','\0'),(3,4,0,2,NULL,NULL,'\0','\0'),(3,4,1,0,NULL,NULL,'\0','\0'),(3,4,1,1,NULL,NULL,'\0','\0'),(3,4,1,2,NULL,NULL,'\0','\0'),(3,4,2,0,NULL,NULL,'\0','\0'),(3,4,2,1,NULL,NULL,'\0','\0'),(3,4,2,2,NULL,NULL,'\0','\0'),(3,5,0,0,NULL,NULL,'\0','\0'),(3,5,0,1,NULL,NULL,'\0','\0'),(3,5,0,2,NULL,NULL,'\0','\0'),(3,5,1,0,NULL,NULL,'\0','\0'),(3,5,1,1,NULL,NULL,'\0','\0'),(3,5,1,2,NULL,NULL,'\0','\0'),(3,5,2,0,NULL,NULL,'\0','\0'),(3,5,2,1,NULL,NULL,'\0','\0'),(3,5,2,2,NULL,NULL,'\0','\0'),(3,6,0,0,NULL,NULL,'\0','\0'),(3,6,0,1,NULL,NULL,'\0','\0'),(3,6,0,2,NULL,NULL,'\0','\0'),(3,6,1,0,NULL,NULL,'\0','\0'),(3,6,1,1,NULL,NULL,'\0','\0'),(3,6,1,2,NULL,NULL,'\0','\0'),(3,6,2,0,NULL,NULL,'\0','\0'),(3,6,2,1,NULL,NULL,'\0','\0'),(3,6,2,2,NULL,NULL,'\0','\0'),(3,7,0,0,NULL,NULL,'\0','\0'),(3,7,0,1,NULL,NULL,'\0','\0'),(3,7,0,2,NULL,NULL,'\0','\0'),(3,7,1,0,NULL,NULL,'\0','\0'),(3,7,1,1,NULL,NULL,'\0','\0'),(3,7,1,2,NULL,NULL,'\0','\0'),(3,7,2,0,NULL,NULL,'\0','\0'),(3,7,2,1,NULL,NULL,'\0','\0'),(3,7,2,2,NULL,NULL,'\0','\0'),(3,8,0,0,NULL,NULL,'\0','\0'),(3,8,0,1,NULL,NULL,'\0','\0'),(3,8,0,2,NULL,NULL,'\0','\0'),(3,8,1,0,NULL,NULL,'\0','\0'),(3,8,1,1,NULL,NULL,'\0','\0'),(3,8,1,2,NULL,NULL,'\0','\0'),(3,8,2,0,NULL,NULL,'\0','\0'),(3,8,2,1,NULL,NULL,'\0','\0'),(3,8,2,2,NULL,NULL,'\0','\0'),(3,9,0,0,NULL,NULL,'\0','\0'),(3,9,0,1,NULL,NULL,'\0','\0'),(3,9,0,2,NULL,NULL,'\0','\0'),(3,9,1,0,NULL,NULL,'\0','\0'),(3,9,1,1,NULL,NULL,'\0','\0'),(3,9,1,2,NULL,NULL,'\0','\0'),(3,9,2,0,NULL,NULL,'\0','\0'),(3,9,2,1,NULL,NULL,'\0','\0'),(3,9,2,2,NULL,NULL,'\0','\0'),(3,10,0,0,NULL,NULL,'\0','\0'),(3,10,0,1,NULL,NULL,'\0','\0'),(3,10,0,2,NULL,NULL,'\0','\0'),(3,10,1,0,NULL,NULL,'\0','\0'),(3,10,1,1,NULL,NULL,'\0','\0'),(3,10,1,2,NULL,NULL,'\0','\0'),(3,10,2,0,NULL,NULL,'\0','\0'),(3,10,2,1,NULL,NULL,'\0','\0'),(3,10,2,2,NULL,NULL,'\0','\0'),(3,11,0,0,NULL,NULL,'\0','\0'),(3,11,0,1,NULL,NULL,'\0','\0'),(3,11,0,2,NULL,NULL,'\0','\0'),(3,11,1,0,NULL,NULL,'\0','\0'),(3,11,1,1,NULL,NULL,'\0','\0'),(3,11,1,2,NULL,NULL,'\0','\0'),(3,11,2,0,NULL,NULL,'\0','\0'),(3,11,2,1,NULL,NULL,'\0','\0'),(3,11,2,2,NULL,NULL,'\0','\0');
/*!40000 ALTER TABLE `parking_cell` ENABLE KEYS */;
UNLOCK TABLES;

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
  `price1` float NOT NULL DEFAULT '0',
  `price2` float NOT NULL DEFAULT '0',
  `alternative_lots` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `robot_ip` varchar(48) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lot_full` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_lot`
--

LOCK TABLES `parking_lot` WRITE;
/*!40000 ALTER TABLE `parking_lot` DISABLE KEYS */;
INSERT INTO `parking_lot` VALUES (1,'Sesam 2',4,5,3,NULL,'12.f.t43','\0'),(2,'Rabin 14',8,5,3,NULL,'13.f.t43','\0'),(3,'Big 16',12,5,3,NULL,'14.f.t43','\0');
/*!40000 ALTER TABLE `parking_lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quarterly_report`
--

DROP TABLE IF EXISTS `quarterly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quarterly_report` (
  `year` int(11) NOT NULL,
  `quarter` int(11) NOT NULL,
  `lot_id` int(11) NOT NULL,
  `ordered_onetimes` int(11) DEFAULT '0',
  `ordered_incidental` int(11) DEFAULT '0',
  `ordered_regular` int(11) DEFAULT '0',
  `ordered_full` int(11) DEFAULT '0',
  `complaints_count` int(11) DEFAULT '0',
  `disabled_slots` int(11) DEFAULT '0',
  PRIMARY KEY (`year`,`quarter`,`lot_id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quarterly_report`
--

LOCK TABLES `quarterly_report` WRITE;
/*!40000 ALTER TABLE `quarterly_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `quarterly_report` ENABLE KEYS */;
UNLOCK TABLES;

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
  `parked` bit(1) NOT NULL DEFAULT b'0',
  `completed` bit(1) NOT NULL DEFAULT b'0',
  `canceled` bit(1) NOT NULL DEFAULT b'0',
  `warned` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_service`
--

LOCK TABLES `subscription_service` WRITE;
/*!40000 ALTER TABLE `subscription_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscription_service` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weekly_statistics`
--

LOCK TABLES `weekly_statistics` WRITE;
/*!40000 ALTER TABLE `weekly_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `weekly_statistics` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-02  1:12:15
