CREATE DATABASE  IF NOT EXISTS `solicitud_tramites_v2` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `solicitud_tramites_v2`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: solicitud_tramites_v2
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `argumentos`
--

DROP TABLE IF EXISTS `argumentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `argumentos` (
  `args_id` int NOT NULL AUTO_INCREMENT,
  `args_descripcion` varchar(255) NOT NULL,
  `args_precotizacion_elegida` int DEFAULT NULL,
  PRIMARY KEY (`args_id`),
  KEY `FK88agjaqwh2rfwjrry8ut88yhh` (`args_precotizacion_elegida`),
  CONSTRAINT `FK88agjaqwh2rfwjrry8ut88yhh` FOREIGN KEY (`args_precotizacion_elegida`) REFERENCES `precotizaciones` (`pre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `argumentos`
--

LOCK TABLES `argumentos` WRITE;
/*!40000 ALTER TABLE `argumentos` DISABLE KEYS */;
INSERT INTO `argumentos` VALUES (19,'barato',18),(20,'barato',19),(23,'barato',22),(25,'barato',24),(29,'mejor precio',28),(31,'mejor precio',30),(34,'caro',33),(35,'barato',34),(36,'caro',35),(37,'barato',35);
/*!40000 ALTER TABLE `argumentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultas`
--

DROP TABLE IF EXISTS `consultas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultas` (
  `con_sol_id` int NOT NULL,
  `con_parametro` varchar(255) DEFAULT NULL,
  `con_est_id` int NOT NULL,
  PRIMARY KEY (`con_sol_id`),
  KEY `FK1ia6fdoitjete9qlahddwjjqw` (`con_est_id`),
  CONSTRAINT `FK1ia6fdoitjete9qlahddwjjqw` FOREIGN KEY (`con_est_id`) REFERENCES `ma_estados` (`es_id`),
  CONSTRAINT `FK42qnf36vbav6ufhd7if05pkqs` FOREIGN KEY (`con_sol_id`) REFERENCES `solicitudes` (`sol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultas`
--

LOCK TABLES `consultas` WRITE;
/*!40000 ALTER TABLE `consultas` DISABLE KEYS */;
INSERT INTO `consultas` VALUES (17,'consultas front',2),(18,'consultas front',2),(19,'otro',2),(20,'otro',2),(21,'updated',1),(23,'updated',2),(24,'updated',1),(25,'updated',2),(26,'updated',1);
/*!40000 ALTER TABLE `consultas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles_tramites`
--

DROP TABLE IF EXISTS `detalles_tramites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_tramites` (
  `det_id` int NOT NULL AUTO_INCREMENT,
  `det_cantidad` int NOT NULL,
  `det_descripcion` varchar(255) NOT NULL,
  `det_especifica_id` int DEFAULT NULL,
  `det_general_id` int DEFAULT NULL,
  `det_solicitud_id` int DEFAULT NULL,
  PRIMARY KEY (`det_id`),
  KEY `FK5eepy4gtoa4yqv34xhl0l891p` (`det_especifica_id`),
  KEY `FKk3gqmk3nike60un9tpfy8a8of` (`det_general_id`),
  KEY `FKm7eu9qh6srhj0rwuw05851rp7` (`det_solicitud_id`),
  CONSTRAINT `FK5eepy4gtoa4yqv34xhl0l891p` FOREIGN KEY (`det_especifica_id`) REFERENCES `ma_lineas_especificas` (`espec_id`),
  CONSTRAINT `FKk3gqmk3nike60un9tpfy8a8of` FOREIGN KEY (`det_general_id`) REFERENCES `ma_lineas_generales` (`general_id`),
  CONSTRAINT `FKm7eu9qh6srhj0rwuw05851rp7` FOREIGN KEY (`det_solicitud_id`) REFERENCES `solicitudes` (`sol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_tramites`
--

LOCK TABLES `detalles_tramites` WRITE;
/*!40000 ALTER TABLE `detalles_tramites` DISABLE KEYS */;
INSERT INTO `detalles_tramites` VALUES (20,12,'sadfsf',3,1,17),(21,324,'dfsgdfsg',5,2,18),(24,314,'asdfasdfasdf',4,2,19),(26,10,'necesitamos muchos',2,1,20),(30,30,'objetos para investigar',4,2,23),(32,30,'objetos para investigar',4,2,21),(35,62,'56',5,2,25),(36,345,'jkhgk',10,3,24),(37,123,'fgdfg',2,1,26),(38,34,'gsfdgsfdg',4,2,26);
/*!40000 ALTER TABLE `detalles_tramites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudios`
--

DROP TABLE IF EXISTS `estudios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudios` (
  `est_id` int NOT NULL,
  `est_acuerdo` varchar(255) NOT NULL,
  `est_firma_usuario` varchar(255) DEFAULT NULL,
  `est_estado_id` int NOT NULL,
  `est_unidad_academica_id` int DEFAULT NULL,
  `est_firma_director` varchar(255) DEFAULT NULL,
  `est_usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`est_id`),
  KEY `FK3j8sjt79p4aq3542wc47fyg6s` (`est_estado_id`),
  KEY `FKm3sqr7leufit8o3b0t0shjvin` (`est_unidad_academica_id`),
  KEY `FKqt3ksm38026cdruiylxphmub7` (`est_usuario_id`),
  CONSTRAINT `FK2ft523bvbp57gquoii2uw9o5j` FOREIGN KEY (`est_id`) REFERENCES `solicitudes` (`sol_id`),
  CONSTRAINT `FK3j8sjt79p4aq3542wc47fyg6s` FOREIGN KEY (`est_estado_id`) REFERENCES `ma_estados` (`es_id`),
  CONSTRAINT `FKm3sqr7leufit8o3b0t0shjvin` FOREIGN KEY (`est_unidad_academica_id`) REFERENCES `ma_unidad_academicas` (`uni_id`),
  CONSTRAINT `FKqt3ksm38026cdruiylxphmub7` FOREIGN KEY (`est_usuario_id`) REFERENCES `usuario` (`usr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudios`
--

LOCK TABLES `estudios` WRITE;
/*!40000 ALTER TABLE `estudios` DISABLE KEYS */;
INSERT INTO `estudios` VALUES (17,'tercer save, updated, updated controlador estudio','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634174784882-468585.jpg',5,1,'C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',17),(18,'desde el front','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634174784882-468585.jpg',5,1,'C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',17),(19,'desde el front','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634178999651-468585.jpg',5,1,'C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',17),(20,'desde el front','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634178999651-468585.jpg',5,1,'C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',17),(21,'parametros','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',5,1,'C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',17),(23,'parametros','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',5,1,'C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',17),(24,'parametros','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',7,1,NULL,17),(25,'parametros',NULL,8,1,'C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg',17),(26,'parametros',NULL,1,NULL,NULL,17);
/*!40000 ALTER TABLE `estudios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_investigadores`
--

DROP TABLE IF EXISTS `grupo_investigadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_investigadores` (
  `gi_id` int NOT NULL AUTO_INCREMENT,
  `gi_cargo` varchar(255) NOT NULL,
  `gi_nombre_contacto` varchar(255) NOT NULL,
  `gi_telefono_contacto` varchar(255) NOT NULL,
  `gi_grupo_id` int NOT NULL,
  `gi_investigador_id` int NOT NULL,
  `gi_proyecto_id` int NOT NULL,
  PRIMARY KEY (`gi_id`),
  KEY `FKcw30t96j60d8rk66tcegiar51` (`gi_grupo_id`),
  KEY `FKag7yxge6ey6yi64mfkiwf0pf8` (`gi_investigador_id`),
  KEY `FK58g5o5u1v4t6sf2wvrmbll2va` (`gi_proyecto_id`),
  CONSTRAINT `FK58g5o5u1v4t6sf2wvrmbll2va` FOREIGN KEY (`gi_proyecto_id`) REFERENCES `proyectos` (`pr_id`),
  CONSTRAINT `FKag7yxge6ey6yi64mfkiwf0pf8` FOREIGN KEY (`gi_investigador_id`) REFERENCES `investigadores` (`inv_id`),
  CONSTRAINT `FKcw30t96j60d8rk66tcegiar51` FOREIGN KEY (`gi_grupo_id`) REFERENCES `grupos` (`gr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_investigadores`
--

LOCK TABLES `grupo_investigadores` WRITE;
/*!40000 ALTER TABLE `grupo_investigadores` DISABLE KEYS */;
INSERT INTO `grupo_investigadores` VALUES (14,'jefe','juan jose','1323',1,2,1),(15,'jefe','juan jose','2127758',2,1,3),(17,'43534','agregado desde un usuario normal','3545',2,1,3),(18,'oep','prueba12','3142345435',1,2,1),(19,'jefe','juan jose','13132',2,1,3),(20,'2455','sdfgdfsg','45424',2,1,3),(23,'dsafasdf','juan jose2','3128959561',2,1,3),(25,'jefe','jorge','12454',1,2,1),(29,'Jefe','juan','122144',1,2,1),(31,'Jefe','juana','122144',1,2,1),(34,'oep','juan jose','+573155897589',2,8,5),(35,'jefeff','juan jose','16042127758',1,1,1),(36,'jefe','gsfdgfsdg','3434',2,1,2);
/*!40000 ALTER TABLE `grupo_investigadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `gr_id` int NOT NULL AUTO_INCREMENT,
  `gr_cod_colciencia` varchar(255) NOT NULL,
  `gr_nombre` varchar(255) NOT NULL,
  `gr_codigo` varchar(255) NOT NULL,
  PRIMARY KEY (`gr_id`),
  UNIQUE KEY `gr_codigo_unique` (`gr_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (1,'a1','grupo1','534532'),(2,'a1','updated 1','123452'),(8,'a1','nueva fgsd','31654'),(9,'a1','prueba','123');
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investigadores`
--

DROP TABLE IF EXISTS `investigadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investigadores` (
  `inv_id` int NOT NULL AUTO_INCREMENT,
  `inv_email` varchar(255) NOT NULL,
  `inv_identificacion` varchar(255) NOT NULL,
  `inv_nombre` varchar(255) NOT NULL,
  `inv_telefono` varchar(255) NOT NULL,
  PRIMARY KEY (`inv_id`),
  UNIQUE KEY `inv_identificacion_unique` (`inv_identificacion`),
  UNIQUE KEY `inv_email_unique` (`inv_email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investigadores`
--

LOCK TABLES `investigadores` WRITE;
/*!40000 ALTER TABLE `investigadores` DISABLE KEYS */;
INSERT INTO `investigadores` VALUES (1,'juan-urrego@live.com','123','juan','123124'),(2,'jorge.correaj@udea.edu.co','54346','jorge','445435'),(7,'juan-urrego4@live.com','123445','juan','123124'),(8,'danielurregorios@gmail.com','12435','prueba','3445325');
/*!40000 ALTER TABLE `investigadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_estados`
--

DROP TABLE IF EXISTS `ma_estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_estados` (
  `es_id` int NOT NULL AUTO_INCREMENT,
  `es_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`es_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_estados`
--

LOCK TABLES `ma_estados` WRITE;
/*!40000 ALTER TABLE `ma_estados` DISABLE KEYS */;
INSERT INTO `ma_estados` VALUES (1,'CREADA'),(2,'VERIFICADA'),(3,'DOCUMENTADA'),(4,'DILIGENCIADO'),(5,'FIRMADO'),(6,'FINALIZADO'),(7,'ADMIN_FIRMADO'),(8,'DIRECTOR_FIRMADO'),(9,'POR_VERIFICAR');
/*!40000 ALTER TABLE `ma_estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_lineas_especificas`
--

DROP TABLE IF EXISTS `ma_lineas_especificas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_lineas_especificas` (
  `espec_id` int NOT NULL AUTO_INCREMENT,
  `espec_nombre` varchar(255) DEFAULT NULL,
  `espec_general_id` int DEFAULT NULL,
  PRIMARY KEY (`espec_id`),
  KEY `FK3pvrdplg8xmlta72f8sg2pk1s` (`espec_general_id`),
  CONSTRAINT `FK3pvrdplg8xmlta72f8sg2pk1s` FOREIGN KEY (`espec_general_id`) REFERENCES `ma_lineas_generales` (`general_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_lineas_especificas`
--

LOCK TABLES `ma_lineas_especificas` WRITE;
/*!40000 ALTER TABLE `ma_lineas_especificas` DISABLE KEYS */;
INSERT INTO `ma_lineas_especificas` VALUES (1,'mouses',1),(2,'teclados',1),(3,'impresoras',1),(4,'gatos',2),(5,'tubos',2),(10,'computadores',3);
/*!40000 ALTER TABLE `ma_lineas_especificas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_lineas_generales`
--

DROP TABLE IF EXISTS `ma_lineas_generales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_lineas_generales` (
  `general_id` int NOT NULL AUTO_INCREMENT,
  `general_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`general_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_lineas_generales`
--

LOCK TABLES `ma_lineas_generales` WRITE;
/*!40000 ALTER TABLE `ma_lineas_generales` DISABLE KEYS */;
INSERT INTO `ma_lineas_generales` VALUES (1,'tecnologia'),(2,'laboratorio'),(3,'tecnologia2');
/*!40000 ALTER TABLE `ma_lineas_generales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_parametros_acuerdo`
--

DROP TABLE IF EXISTS `ma_parametros_acuerdo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_parametros_acuerdo` (
  `par_acu_id` int NOT NULL AUTO_INCREMENT,
  `par_acu_descipcion` varchar(255) NOT NULL,
  `par_acu_parametro` tinyint NOT NULL,
  PRIMARY KEY (`par_acu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_parametros_acuerdo`
--

LOCK TABLES `ma_parametros_acuerdo` WRITE;
/*!40000 ALTER TABLE `ma_parametros_acuerdo` DISABLE KEYS */;
INSERT INTO `ma_parametros_acuerdo` VALUES (2,'desde el front',0),(4,'parametros',1),(5,'updated',0);
/*!40000 ALTER TABLE `ma_parametros_acuerdo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_parametros_argumento`
--

DROP TABLE IF EXISTS `ma_parametros_argumento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_parametros_argumento` (
  `par_args_id` int NOT NULL AUTO_INCREMENT,
  `par_args_descipcion` varchar(255) NOT NULL,
  PRIMARY KEY (`par_args_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_parametros_argumento`
--

LOCK TABLES `ma_parametros_argumento` WRITE;
/*!40000 ALTER TABLE `ma_parametros_argumento` DISABLE KEYS */;
INSERT INTO `ma_parametros_argumento` VALUES (1,'barato'),(2,'caro'),(4,', updated');
/*!40000 ALTER TABLE `ma_parametros_argumento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_parametros_consulta`
--

DROP TABLE IF EXISTS `ma_parametros_consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_parametros_consulta` (
  `par_con_id` int NOT NULL AUTO_INCREMENT,
  `par_con_descipcion` varchar(255) NOT NULL,
  `par_con_parametro` tinyint NOT NULL,
  PRIMARY KEY (`par_con_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_parametros_consulta`
--

LOCK TABLES `ma_parametros_consulta` WRITE;
/*!40000 ALTER TABLE `ma_parametros_consulta` DISABLE KEYS */;
INSERT INTO `ma_parametros_consulta` VALUES (1,'consultas front',0),(2,'otro',0),(4,'asdf',0),(6,'updated',1);
/*!40000 ALTER TABLE `ma_parametros_consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_parametros_necesidad`
--

DROP TABLE IF EXISTS `ma_parametros_necesidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_parametros_necesidad` (
  `parn_id` int NOT NULL AUTO_INCREMENT,
  `parn_descipcion` varchar(255) NOT NULL,
  `parn_parametro` tinyint NOT NULL,
  PRIMARY KEY (`parn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_parametros_necesidad`
--

LOCK TABLES `ma_parametros_necesidad` WRITE;
/*!40000 ALTER TABLE `ma_parametros_necesidad` DISABLE KEYS */;
INSERT INTO `ma_parametros_necesidad` VALUES (1,'necesidad dsde el front',0),(3,'tercer save',0),(4,'cuarto save',0),(5,' updated',1);
/*!40000 ALTER TABLE `ma_parametros_necesidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_parametros_obeservacion`
--

DROP TABLE IF EXISTS `ma_parametros_obeservacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_parametros_obeservacion` (
  `par_obs_id` int NOT NULL AUTO_INCREMENT,
  `par_obs_descipcion` varchar(255) NOT NULL,
  `par_obs_parametro` tinyint NOT NULL,
  PRIMARY KEY (`par_obs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_parametros_obeservacion`
--

LOCK TABLES `ma_parametros_obeservacion` WRITE;
/*!40000 ALTER TABLE `ma_parametros_obeservacion` DISABLE KEYS */;
INSERT INTO `ma_parametros_obeservacion` VALUES (1,'observaciones front',0),(3,'tercer save:',0),(4,'updated',0),(5,'tercer save:',1),(6,'tercer save:',0);
/*!40000 ALTER TABLE `ma_parametros_obeservacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_rol`
--

DROP TABLE IF EXISTS `ma_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_rol` (
  `rol_id` int NOT NULL AUTO_INCREMENT,
  `rol_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_rol`
--

LOCK TABLES `ma_rol` WRITE;
/*!40000 ALTER TABLE `ma_rol` DISABLE KEYS */;
INSERT INTO `ma_rol` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_DIRECTOR');
/*!40000 ALTER TABLE `ma_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ma_unidad_academicas`
--

DROP TABLE IF EXISTS `ma_unidad_academicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ma_unidad_academicas` (
  `uni_id` int NOT NULL AUTO_INCREMENT,
  `uni_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`uni_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ma_unidad_academicas`
--

LOCK TABLES `ma_unidad_academicas` WRITE;
/*!40000 ALTER TABLE `ma_unidad_academicas` DISABLE KEYS */;
INSERT INTO `ma_unidad_academicas` VALUES (1,'SIU'),(3,'Enfermeria'),(4,'Ingenieria'),(5,'ingenieria , updated');
/*!40000 ALTER TABLE `ma_unidad_academicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precotizaciones`
--

DROP TABLE IF EXISTS `precotizaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precotizaciones` (
  `pre_id` int NOT NULL AUTO_INCREMENT,
  `pre_valor_iva` int NOT NULL,
  `pre_valor_total` int NOT NULL,
  `pre_pro_id` int DEFAULT NULL,
  `pre_sol_id` int DEFAULT NULL,
  PRIMARY KEY (`pre_id`),
  KEY `FK6vcoyjtf3bhux0e13v4j6ph8w` (`pre_pro_id`),
  KEY `FKfptgbqhfn7x85n09qltegj2fw` (`pre_sol_id`),
  CONSTRAINT `FK6vcoyjtf3bhux0e13v4j6ph8w` FOREIGN KEY (`pre_pro_id`) REFERENCES `proveedores` (`pro_id`),
  CONSTRAINT `FKfptgbqhfn7x85n09qltegj2fw` FOREIGN KEY (`pre_sol_id`) REFERENCES `solicitudes` (`sol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precotizaciones`
--

LOCK TABLES `precotizaciones` WRITE;
/*!40000 ALTER TABLE `precotizaciones` DISABLE KEYS */;
INSERT INTO `precotizaciones` VALUES (18,542545,32425,3,17),(19,324,324,3,18),(22,52345234,45432,2,19),(24,3518,60000,1,20),(28,19,5000,3,23),(30,19,5000,3,21),(33,654,65685,1,25),(34,235445,3424,3,24),(35,4324,434,5,26),(36,54,5656,3,26),(37,234,342234,2,26),(38,3242,4324,2,26),(39,4324,432,1,26);
/*!40000 ALTER TABLE `precotizaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor_detalle`
--

DROP TABLE IF EXISTS `proveedor_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor_detalle` (
  `pd_id` int NOT NULL AUTO_INCREMENT,
  `pd_detalle_id` int NOT NULL,
  `gi_proveedor_id` int NOT NULL,
  PRIMARY KEY (`pd_id`),
  KEY `FKhw8dkk43knkcrb1tgyqtqmydo` (`pd_detalle_id`),
  KEY `FKcvew5klsa3b8to3xleqpnrhry` (`gi_proveedor_id`),
  CONSTRAINT `FKcvew5klsa3b8to3xleqpnrhry` FOREIGN KEY (`gi_proveedor_id`) REFERENCES `proveedores` (`pro_id`),
  CONSTRAINT `FKhw8dkk43knkcrb1tgyqtqmydo` FOREIGN KEY (`pd_detalle_id`) REFERENCES `detalles_tramites` (`det_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor_detalle`
--

LOCK TABLES `proveedor_detalle` WRITE;
/*!40000 ALTER TABLE `proveedor_detalle` DISABLE KEYS */;
INSERT INTO `proveedor_detalle` VALUES (19,20,3),(20,21,3),(23,24,2),(25,26,1),(29,30,3),(31,32,3),(34,35,1),(35,36,3),(36,37,3),(37,37,5),(38,37,2),(39,37,1),(40,37,2),(41,38,2),(42,38,5),(43,38,1),(44,38,3),(45,38,2);
/*!40000 ALTER TABLE `proveedor_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `pro_id` int NOT NULL AUTO_INCREMENT,
  `pro_ciudad` varchar(255) NOT NULL,
  `pro_identificacion` varchar(255) NOT NULL,
  `pro_nombre` varchar(255) NOT NULL,
  `pro_telefono` varchar(255) NOT NULL,
  `pro_tipo` varchar(255) NOT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (1,'medellin','43145','juan','21234','naturall'),(2,'bello','143542','jose','1234','natural'),(3,'medellin','5435','Prueba desde la solicitud','5464','empresa'),(4,'Medellin','5312','agregadro desde configuracion front','2313214','Persona natural'),(5,'Medellin','99999','comida','676767676','Persona natural'),(6,'medellin','1234','prueba','34214','empresa'),(7,'sadf','134215','juan jose','1233','Persona natural');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyectos`
--

DROP TABLE IF EXISTS `proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyectos` (
  `pr_id` int NOT NULL AUTO_INCREMENT,
  `pr_centro_costos` varchar(255) NOT NULL,
  `pr_codigo_proyecto` varchar(255) NOT NULL,
  `pr_nombre` varchar(255) NOT NULL,
  `pr_grupo_id` int DEFAULT NULL,
  PRIMARY KEY (`pr_id`),
  UNIQUE KEY `pr_codigo_proyecto_unique` (`pr_codigo_proyecto`),
  KEY `FKhwy9p2apnf2kei6yiqe9es08k` (`pr_grupo_id`),
  CONSTRAINT `FKhwy9p2apnf2kei6yiqe9es08k` FOREIGN KEY (`pr_grupo_id`) REFERENCES `grupos` (`gr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyectos`
--

LOCK TABLES `proyectos` WRITE;
/*!40000 ALTER TABLE `proyectos` DISABLE KEYS */;
INSERT INTO `proyectos` VALUES (1,'123432','12342134','covid',1),(2,'453245','2345','atila',2),(3,'3245235','35445','otro nuevo',2),(5,'34214','123','segundo save, updated',2);
/*!40000 ALTER TABLE `proyectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitudes`
--

DROP TABLE IF EXISTS `solicitudes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitudes` (
  `sol_id` int NOT NULL AUTO_INCREMENT,
  `sol_fecha` varchar(255) NOT NULL,
  `sol_necesidad` varchar(255) NOT NULL,
  `sol_observacion` varchar(255) NOT NULL,
  `sol_tipo_tramite` varchar(255) NOT NULL,
  `sol_valor` double NOT NULL,
  `sol_verificacion` varchar(255) NOT NULL,
  `sol_estado_id` int NOT NULL,
  `sol_grupo_investigador_id` int NOT NULL,
  `sol_precotizacion_elegida_id` int DEFAULT NULL,
  `sol_usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`sol_id`),
  KEY `FKq3rg4ww8fm5mmhuri17yue7ao` (`sol_estado_id`),
  KEY `FKfdtx56i72rqn5ieufojcbnxam` (`sol_grupo_investigador_id`),
  KEY `FK104arvclsfguyp9tii455f3y3` (`sol_precotizacion_elegida_id`),
  KEY `FKngjcug0s731ix301el0kfqrys` (`sol_usuario_id`),
  CONSTRAINT `FK104arvclsfguyp9tii455f3y3` FOREIGN KEY (`sol_precotizacion_elegida_id`) REFERENCES `precotizaciones` (`pre_id`),
  CONSTRAINT `FKfdtx56i72rqn5ieufojcbnxam` FOREIGN KEY (`sol_grupo_investigador_id`) REFERENCES `grupo_investigadores` (`gi_id`),
  CONSTRAINT `FKngjcug0s731ix301el0kfqrys` FOREIGN KEY (`sol_usuario_id`) REFERENCES `usuario` (`usr_id`),
  CONSTRAINT `FKq3rg4ww8fm5mmhuri17yue7ao` FOREIGN KEY (`sol_estado_id`) REFERENCES `ma_estados` (`es_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitudes`
--

LOCK TABLES `solicitudes` WRITE;
/*!40000 ALTER TABLE `solicitudes` DISABLE KEYS */;
INSERT INTO `solicitudes` VALUES (17,'2021-10-14T04:26:44.857Z','','observaciones front','Nacional',658919841,'si',3,19,18,14),(18,'2021-10-14T19:25:21.720Z','','observaciones front','Internacional',234,'si',3,20,19,14),(19,'2021-10-20T02:30:23.148Z','','observaciones frontfgdfsgasdf','Internacional',23424,'si',3,23,22,16),(20,'2021-10-21T20:23:03.091Z','','observaciones front','Nacional',51000,'si',3,25,24,16),(21,'2021-01-16T05:00:00.000Z','asdfsadf','asdfasdf','segundo save',21000,'si',3,31,30,14),(23,'1/16/2021','asdfsadf','asdfasdf','segundo save',21000,'si',3,29,28,16),(24,'2021-11-24T20:03:36.578Z',' updated','tercer save:','Nacional',25435,'si',3,35,34,14),(25,'2021-11-24T20:16:45.638Z',' updated','tercer save:','Internacional',6595,'si',3,34,33,16),(26,'2022-02-07T02:08:41.448Z',' updated','tercer save:','Internacional',2133213,'si',3,36,35,14);
/*!40000 ALTER TABLE `solicitudes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usr_id` int NOT NULL AUTO_INCREMENT,
  `usr_email` varchar(255) NOT NULL,
  `usr_firma` varchar(255) NOT NULL,
  `usr_nombre` varchar(255) NOT NULL,
  `usr_password` varchar(255) NOT NULL,
  `usr_cargo` varchar(255) NOT NULL,
  `usr_activo` bit(1) NOT NULL,
  PRIMARY KEY (`usr_id`),
  UNIQUE KEY `usr_email_UNIQUE` (`usr_email`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (14,'juan-urrego@live.com','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634174784882-468585.jpg','admin1','$2a$10$UuIu2btSOVqovBas.3nFZ.i7iR3lDI0xEYY2vU8cAnrvlwsaXImXi','jefe cargo',_binary ''),(16,'juan-urrego2@live.com','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1637431043612-marte 2.jpg','usuario rios','$2a$10$/s9b80dMOIva727kefucDOkmTYFutP4syFQqzHja7hNfxeVrcX6Hi','auxiliar',_binary ''),(17,'juan-urrego@gmail.com','C:\\Users\\juan-\\Documents\\SIU-solicitud\\solicitud-backend\\src\\main\\resources\\1634249456045-468585.jpg','giorgio2','$2a$10$AA3ByGCC9CilrVnnlnCKOO9/W2G9XXPdFACsWVNnrs5ISuqmrJx7y','boss updated',_binary '');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `usuario_id` int NOT NULL,
  `rol_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `FKssjovcxv0tl7yotywuqku9jvj` (`rol_id`),
  CONSTRAINT `FKbyfgloj439r9wr9smrms9u33r` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usr_id`) ON DELETE CASCADE,
  CONSTRAINT `FKssjovcxv0tl7yotywuqku9jvj` FOREIGN KEY (`rol_id`) REFERENCES `ma_rol` (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (14,1),(16,2),(17,3);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-01  0:24:56
