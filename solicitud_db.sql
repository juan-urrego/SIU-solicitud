CREATE DATABASE  IF NOT EXISTS `solicitud_tramites` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `solicitud_tramites`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: solicitud_tramites
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `consultas`
--

DROP TABLE IF EXISTS `consultas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultas` (
  `id` int NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `porque` varchar(255) DEFAULT NULL,
  `precotizacion_id_precotizacion` int DEFAULT NULL,
  `solicitud_id_solicitud` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4238k1mj41wn3ykqobq3mplpk` (`precotizacion_id_precotizacion`),
  KEY `FKaplonawg8n4vv66f6uo0lpg7u` (`solicitud_id_solicitud`),
  CONSTRAINT `FK4238k1mj41wn3ykqobq3mplpk` FOREIGN KEY (`precotizacion_id_precotizacion`) REFERENCES `precotizaciones` (`id_precotizacion`),
  CONSTRAINT `FKaplonawg8n4vv66f6uo0lpg7u` FOREIGN KEY (`solicitud_id_solicitud`) REFERENCES `sol_tramites` (`id_solicitud`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultas`
--

LOCK TABLES `consultas` WRITE;
/*!40000 ALTER TABLE `consultas` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directores`
--

DROP TABLE IF EXISTS `directores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `directores` (
  `id` int NOT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `firma` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directores`
--

LOCK TABLES `directores` WRITE;
/*!40000 ALTER TABLE `directores` DISABLE KEYS */;
/*!40000 ALTER TABLE `directores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudios`
--

DROP TABLE IF EXISTS `estudios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudios` (
  `id` int NOT NULL,
  `acuerdo` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `firma` varchar(255) DEFAULT NULL,
  `director_id` int DEFAULT NULL,
  `solicitud_id_solicitud` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5h70vifq5juq8vvha4pjv7m43` (`director_id`),
  KEY `FKgpwx7bmgkbny5ha43c78rtk2d` (`solicitud_id_solicitud`),
  CONSTRAINT `FK5h70vifq5juq8vvha4pjv7m43` FOREIGN KEY (`director_id`) REFERENCES `directores` (`id`),
  CONSTRAINT `FKgpwx7bmgkbny5ha43c78rtk2d` FOREIGN KEY (`solicitud_id_solicitud`) REFERENCES `sol_tramites` (`id_solicitud`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudios`
--

LOCK TABLES `estudios` WRITE;
/*!40000 ALTER TABLE `estudios` DISABLE KEYS */;
/*!40000 ALTER TABLE `estudios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `id_grupo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `codigo_col` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_grupo`)
) ENGINE=InnoDB AUTO_INCREMENT=155628 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (33,'Covid','A1'),(34,'Ecosis','B1');
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (136);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inv_grupos`
--

DROP TABLE IF EXISTS `inv_grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inv_grupos` (
  `id_grupos` int DEFAULT NULL,
  `id_investigadores` int DEFAULT NULL,
  KEY `grupos_idx` (`id_grupos`),
  KEY `invest_idx` (`id_investigadores`),
  CONSTRAINT `grupos` FOREIGN KEY (`id_grupos`) REFERENCES `grupos` (`id_grupo`) ON DELETE CASCADE,
  CONSTRAINT `invest` FOREIGN KEY (`id_investigadores`) REFERENCES `investigadores` (`id_investigador`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inv_grupos`
--

LOCK TABLES `inv_grupos` WRITE;
/*!40000 ALTER TABLE `inv_grupos` DISABLE KEYS */;
INSERT INTO `inv_grupos` VALUES (33,30),(33,31),(33,32),(34,31),(34,32),(34,63);
/*!40000 ALTER TABLE `inv_grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investigadores`
--

DROP TABLE IF EXISTS `investigadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investigadores` (
  `id_investigador` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `telefono` bigint DEFAULT NULL,
  `identificacion` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_investigador`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investigadores`
--

LOCK TABLES `investigadores` WRITE;
/*!40000 ALTER TABLE `investigadores` DISABLE KEYS */;
INSERT INTO `investigadores` VALUES (30,'Juan',2127758,'1017018',NULL),(31,'Jose',123456,'3365432',NULL),(32,'Carlos',987654,'435408',NULL),(63,'Daniel',31285959,'11281648',NULL);
/*!40000 ALTER TABLE `investigadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precotizaciones`
--

DROP TABLE IF EXISTS `precotizaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precotizaciones` (
  `id_precotizacion` int NOT NULL AUTO_INCREMENT,
  `id_prov` int DEFAULT NULL,
  `valor` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id_precotizacion`),
  KEY `proveedores_idx` (`id_prov`),
  CONSTRAINT `proveedores` FOREIGN KEY (`id_prov`) REFERENCES `proveedores` (`id_proveedor`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precotizaciones`
--

LOCK TABLES `precotizaciones` WRITE;
/*!40000 ALTER TABLE `precotizaciones` DISABLE KEYS */;
INSERT INTO `precotizaciones` VALUES (1,NULL,5000),(14,2,5000),(44,NULL,5000),(47,NULL,5000),(53,2,5000),(55,2,5000),(56,5,120000),(58,NULL,5000),(59,NULL,120000),(61,2,5000),(62,5,120000),(67,6,2154148844),(68,5,651681),(69,NULL,0),(70,NULL,0),(71,NULL,0),(73,2,1234),(74,12,4321),(75,5,654676),(76,NULL,0),(77,NULL,0),(78,NULL,0),(79,NULL,0),(80,NULL,0),(81,NULL,0),(82,NULL,0),(83,2,123456),(84,5,321654),(85,2,1234),(86,12,4321),(87,5,654676),(88,10,1000000),(94,2,5000),(97,2,5000),(105,5,10000),(106,6,50000),(107,10,90000),(108,5,10000),(109,6,50000),(110,10,90000),(114,2,43241),(116,2,32134),(117,2,32134),(118,2,32134),(119,2,32134),(120,2,32134),(121,2,32134),(122,2,32134),(123,2,43241),(124,2,5000),(126,5,2345),(127,5,2345),(128,5,2345),(129,5,2345),(130,5,2345),(131,5,2345),(132,5,2345),(133,5,2345),(135,5,321564);
/*!40000 ALTER TABLE `precotizaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `id_proveedor` int NOT NULL AUTO_INCREMENT,
  `nit` int DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (2,98765421,'esto es un put desde front',54831),(5,1236546,'JuanJoseUrregoRios',2127758),(6,123456,'Carlote nuevamente',2127758),(7,123456,'Carlote nuevamente',2127758),(9,38438,'esto es un post desde el front',123456),(10,34881,'leonuisa',6884646),(11,123456,'Carlote nuevamente',2127758),(12,324234,'sadfouhsaf',45352345);
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sol_tramites`
--

DROP TABLE IF EXISTS `sol_tramites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sol_tramites` (
  `id_grup` int DEFAULT NULL,
  `id_inv` int DEFAULT NULL,
  `necesidad` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `valor` decimal(10,0) DEFAULT NULL,
  `verificacion` varchar(45) DEFAULT NULL,
  `observacion` varchar(45) DEFAULT NULL,
  `id_solicitud` int NOT NULL AUTO_INCREMENT,
  `cargo` varchar(45) DEFAULT NULL,
  `nombre_proyecto` varchar(45) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `rubro` varchar(45) DEFAULT NULL,
  `subrubro` varchar(45) DEFAULT NULL,
  `financiador` varchar(45) DEFAULT NULL,
  `centro_costos` varchar(45) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_solicitud`),
  KEY `id_grup_idx` (`id_grup`),
  KEY `id_inv_idx` (`id_inv`),
  CONSTRAINT `id_grup` FOREIGN KEY (`id_grup`) REFERENCES `grupos` (`id_grupo`) ON DELETE SET NULL,
  CONSTRAINT `id_inv` FOREIGN KEY (`id_inv`) REFERENCES `investigadores` (`id_investigador`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sol_tramites`
--

LOCK TABLES `sol_tramites` WRITE;
/*!40000 ALTER TABLE `sol_tramites` DISABLE KEYS */;
INSERT INTO `sol_tramites` VALUES (33,30,'pruebas postman','postman',5555,'si','se requiere urgente',52,'prueba','prueba1','2020-06-29','afg','agf','gfdg',NULL,'Verificada Proyectos'),(34,63,'asdfasf','afsfsd',342341,'true','afgafgfdagsdfg',72,'perro','front2','2020-06-25',NULL,NULL,NULL,NULL,'Creada'),(34,31,'dfsdfsdf','sdfsdfdf',1000,'si','gfgfdg',104,'fdf','prueba con el ing.','2020-07-03','equipos','computadores','dfggf','20000','Verificada Proyectos'),(34,32,'sadfasdf','asdfsadfsadf',34324,'si','sadfasdfsae',113,'aguatero','Estos esdafs una prueba','2020-07-11','sadf','32','5235','fsdg','Verificada Proyectos'),(34,31,'fgdsgsfhg','hgfhfdhdfh',3321,'si','dfsafv',115,'sdafsadf','Estos es una prueasdfba','2020-07-11','actualiza1','fgdfh','rtgf','1234','Verificada Proyectos'),(34,31,'gdfsgsdf','gsdfgsdfg',3245,'no','fgfg',125,'4t5t','Estos rges una prueba','2020-07-11','affgg','gfsdg','fsdag','','Creada'),(33,31,'sdhgfn','fgnfghfgh',455,'si','jhvj',134,'bnvnbvn','Estos es una pruebavbnvbn','2020-07-13T22:09:25.310Z','','','','','Creada');
/*!40000 ALTER TABLE `sol_tramites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_precotizacion`
--

DROP TABLE IF EXISTS `solicitud_precotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud_precotizacion` (
  `id_solicitudes` int NOT NULL,
  `id_precotizaciones` int DEFAULT NULL,
  UNIQUE KEY `UK_82rsj31r2hqi34v33vmjgr7n3` (`id_precotizaciones`),
  KEY `FKeg5ne79436tpatlorjx0xn8em` (`id_solicitudes`),
  CONSTRAINT `FKeg5ne79436tpatlorjx0xn8em` FOREIGN KEY (`id_solicitudes`) REFERENCES `sol_tramites` (`id_solicitud`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKer07gf69v6t45hoy8wbya8k4x` FOREIGN KEY (`id_precotizaciones`) REFERENCES `precotizaciones` (`id_precotizacion`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_precotizacion`
--

LOCK TABLES `solicitud_precotizacion` WRITE;
/*!40000 ALTER TABLE `solicitud_precotizacion` DISABLE KEYS */;
INSERT INTO `solicitud_precotizacion` VALUES (72,85),(72,86),(72,87),(72,88),(104,108),(104,109),(104,110),(115,122),(113,123),(52,124),(125,133),(134,135);
/*!40000 ALTER TABLE `solicitud_precotizacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-20 15:41:55
