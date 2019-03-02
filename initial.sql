-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: presente
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ano_letivo` int(11) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  `bolsista` bit(1) NOT NULL,
  `data_matricula` datetime NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `foto` bit(1) NOT NULL,
  `matricula` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nome` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `responsavel_id` int(11) DEFAULT NULL,
  `turma_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKksnvipsjrd93vwrok5swusprg` (`responsavel_id`),
  KEY `FKehtgr8rih20h4gomh4dd4sbxu` (`turma_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_alteracao_aluno`
--

DROP TABLE IF EXISTS `log_alteracao_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_alteracao_aluno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `alteracao_aluno` int(11) DEFAULT NULL,
  `ano_letivo` int(11) DEFAULT NULL,
  `ativo` bit(1) DEFAULT NULL,
  `bolsista` bit(1) DEFAULT NULL,
  `cpf_responsavel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `data_matricula` datetime DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email_responsavel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email_responsavel2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enviar_email_registro` bit(1) DEFAULT NULL,
  `enviar_mensagem` bit(1) DEFAULT NULL,
  `foto` bit(1) DEFAULT NULL,
  `id_aluno` int(11) DEFAULT NULL,
  `id_responsavel` int(11) DEFAULT NULL,
  `id_turma` int(11) DEFAULT NULL,
  `matricula` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nome_responsavel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sala` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `serie` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `turma` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_alteracao_aluno`
--

LOCK TABLES `log_alteracao_aluno` WRITE;
/*!40000 ALTER TABLE `log_alteracao_aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_alteracao_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_envio_email`
--

DROP TABLE IF EXISTS `log_envio_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_envio_email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `log_error` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `registro_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK92n8qm62t7boo2m77c309p7rb` (`registro_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_envio_email`
--

LOCK TABLES `log_envio_email` WRITE;
/*!40000 ALTER TABLE `log_envio_email` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_envio_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfis`
--

DROP TABLE IF EXISTS `perfis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfis` (
  `responsavel_id` int(11) NOT NULL,
  `perfis` int(11) DEFAULT NULL,
  KEY `FK5scyqqijjt520uh17o1qpn8ts` (`responsavel_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfis`
--

LOCK TABLES `perfis` WRITE;
/*!40000 ALTER TABLE `perfis` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tipo_registro` int(11) DEFAULT NULL,
  `log_alteracao_aluno_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK23eyd9f1jpmaak4wb1cjdpav` (`log_alteracao_aluno_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsavel`
--

DROP TABLE IF EXISTS `responsavel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responsavel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ativo` bit(1) NOT NULL,
  `cpf` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email2` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enviar_email_registro` bit(1) NOT NULL,
  `enviar_mensagem` bit(1) NOT NULL,
  `nome` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `senha` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsavel`
--

LOCK TABLES `responsavel` WRITE;
/*!40000 ALTER TABLE `responsavel` DISABLE KEYS */;
/*!40000 ALTER TABLE `responsavel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone`
--

DROP TABLE IF EXISTS `telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefone` (
  `telefone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `descricao` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `responsavel_id` int(11) NOT NULL,
  PRIMARY KEY (`responsavel_id`,`telefone`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone`
--

LOCK TABLES `telefone` WRITE;
/*!40000 ALTER TABLE `telefone` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turma` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ativo` bit(1) NOT NULL,
  `descricao` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sala` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `serie` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `turno` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-02 15:47:00
