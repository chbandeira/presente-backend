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
  `id_aluno` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_ativo` tinyint(1) DEFAULT '1',
  `dat_nascimento` date DEFAULT NULL,
  `foto` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_aluno`)
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
-- Table structure for table `aluno_disciplina`
--

DROP TABLE IF EXISTS `aluno_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno_disciplina` (
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `vlr_nota_b1` double DEFAULT NULL,
  `vlr_nota_b2` double DEFAULT NULL,
  `vlr_nota_b3` double DEFAULT NULL,
  `vlr_nota_b4` double DEFAULT NULL,
  `vlr_nota_final` double DEFAULT NULL,
  `num_falta_b1` int(11) DEFAULT NULL,
  `num_falta_b2` int(11) DEFAULT NULL,
  `num_falta_b3` int(11) DEFAULT NULL,
  `num_falta_b4` int(11) DEFAULT NULL,
  `num_falta_final` int(11) DEFAULT NULL,
  `id_matricula` bigint(20) NOT NULL,
  `id_disciplina` bigint(20) NOT NULL,
  PRIMARY KEY (`id_disciplina`,`id_matricula`),
  KEY `FKp30xnjs15u95nj5x0n4q2trko` (`id_matricula`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno_disciplina`
--

LOCK TABLES `aluno_disciplina` WRITE;
/*!40000 ALTER TABLE `aluno_disciplina` DISABLE KEYS */;
/*!40000 ALTER TABLE `aluno_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendario_escolar`
--

DROP TABLE IF EXISTS `calendario_escolar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendario_escolar` (
  `id_calendario_escolar` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_automatico` bit(1) NOT NULL,
  `dat_data` date NOT NULL,
  `str_descricao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bol_recorrente` bit(1) NOT NULL,
  `num_status` bigint(20) NOT NULL,
  PRIMARY KEY (`id_calendario_escolar`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendario_escolar`
--

LOCK TABLES `calendario_escolar` WRITE;
/*!40000 ALTER TABLE `calendario_escolar` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendario_escolar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracao_escola`
--

DROP TABLE IF EXISTS `configuracao_escola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracao_escola` (
  `id_configuracao_escola` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `str_codigo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dat_fim_ano_letivo` date DEFAULT NULL,
  `dat_inicio_ano_letivo` date DEFAULT NULL,
  `dat_inicio_segundo_semestre` date DEFAULT NULL,
  `str_nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `str_ua` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_configuracao_escola`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracao_escola`
--

LOCK TABLES `configuracao_escola` WRITE;
/*!40000 ALTER TABLE `configuracao_escola` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuracao_escola` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disciplina` (
  `id_disciplina` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_ativo` tinyint(1) DEFAULT '1',
  `num_carga_horaria` int(11) DEFAULT NULL,
  `str_nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_disciplina`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina`
--

LOCK TABLES `disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico_alteracao`
--

DROP TABLE IF EXISTS `historico_alteracao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico_alteracao` (
  `id_historico_alteracao` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_aluno_ativo` bit(1) DEFAULT NULL,
  `dat_aluno_nascimento` date DEFAULT NULL,
  `str_aluno_nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_entidade_origem` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_aluno` bigint(20) DEFAULT NULL,
  `id_matricula` bigint(20) DEFAULT NULL,
  `id_responsavel` bigint(20) DEFAULT NULL,
  `id_sala` bigint(20) DEFAULT NULL,
  `id_serie` bigint(20) DEFAULT NULL,
  `id_turma` bigint(20) DEFAULT NULL,
  `str_matricula` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `int_matricula_ano_letivo` int(11) DEFAULT NULL,
  `bol_matricula_ativo` bit(1) DEFAULT NULL,
  `bol_matricula_bolsista` bit(1) DEFAULT NULL,
  `dat_matricula_data_matricula` datetime DEFAULT NULL,
  `bol_matricula_enviar_email_registro` bit(1) DEFAULT NULL,
  `str_matricula_turno` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_responsavel_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_responsavel_nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_responsavel_telefone_celular` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_responsavel_telefone_fixo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bol_sala_ativo` bit(1) DEFAULT NULL,
  `str_sala_descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bol_serie_ativo` bit(1) DEFAULT NULL,
  `str_serie_descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_tipo_alteracao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bol_turma_ativo` bit(1) DEFAULT NULL,
  `str_turma_descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dat_ultima_alteracao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_historico_alteracao`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico_alteracao`
--

LOCK TABLES `historico_alteracao` WRITE;
/*!40000 ALTER TABLE `historico_alteracao` DISABLE KEYS */;
/*!40000 ALTER TABLE `historico_alteracao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matricula` (
  `id_matricula` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `int_ano_letivo` int(11) DEFAULT NULL,
  `bol_ativo` tinyint(1) NOT NULL DEFAULT '1',
  `bol_bolsista` tinyint(1) NOT NULL DEFAULT '0',
  `dat_data_matricula` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bol_enviar_email_registro` tinyint(1) NOT NULL DEFAULT '1',
  `bol_enviar_sms_registro` tinyint(1) NOT NULL DEFAULT '1',
  `str_matricula` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_turno` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_aluno` bigint(20) NOT NULL,
  `id_responsavel` bigint(20) DEFAULT NULL,
  `id_sala` bigint(20) DEFAULT NULL,
  `id_serie` bigint(20) DEFAULT NULL,
  `id_turma` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_matricula`),
  KEY `FK447ng01ewew6knrjl7gl4g65i` (`id_aluno`),
  KEY `FKgkthm4swe5sd9lp31tmjj7e6p` (`id_responsavel`),
  KEY `FKhgh1wicf0v4wj8jho1pgyv89p` (`id_sala`),
  KEY `FKa26lr29cw0tis4n6fxqmw64kh` (`id_serie`),
  KEY `FK8nr8cfa9b7pl0p1p4y6nig1og` (`id_turma`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matricula`
--

LOCK TABLES `matricula` WRITE;
/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocorrencia`
--

DROP TABLE IF EXISTS `ocorrencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocorrencia` (
  `id_ocorrencia` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_ativo` tinyint(1) DEFAULT '1',
  `tmp_data` datetime DEFAULT NULL,
  `tmp_geracao` datetime DEFAULT NULL,
  `str_descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `num_dias` int(11) DEFAULT NULL,
  `id_matricula` bigint(20) NOT NULL,
  `id_tipo_ocorrencia` bigint(20) NOT NULL,
  `str_login` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_ocorrencia`),
  KEY `FKh6mjiti33c9slcyoahfntqfce` (`id_matricula`),
  KEY `FK9uwbf2bieppfv5upakt25a196` (`id_tipo_ocorrencia`),
  KEY `FKn5p0h4l8x582y8vyi8d086q1o` (`str_login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocorrencia`
--

LOCK TABLES `ocorrencia` WRITE;
/*!40000 ALTER TABLE `ocorrencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `ocorrencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocorrencia_importacao`
--

DROP TABLE IF EXISTS `ocorrencia_importacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocorrencia_importacao` (
  `id_ocorrencia_importacao` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `int_ano_letivo` int(11) DEFAULT NULL,
  `bol_bolsista` bit(1) DEFAULT NULL,
  `dat_nascimento` date DEFAULT NULL,
  `str_matricula` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_responsavel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_responsavel_celular` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_responsavel_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_responsavel_telefone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_sala` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_serie` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_turma` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_turno` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_ocorrencia_importacao`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocorrencia_importacao`
--

LOCK TABLES `ocorrencia_importacao` WRITE;
/*!40000 ALTER TABLE `ocorrencia_importacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `ocorrencia_importacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametro_geral`
--

DROP TABLE IF EXISTS `parametro_geral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametro_geral` (
  `str_chave` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `str_valor` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`str_chave`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro_geral`
--

LOCK TABLES `parametro_geral` WRITE;
/*!40000 ALTER TABLE `parametro_geral` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametro_geral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `str_perfil` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `str_nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`str_perfil`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro` (
  `id_registro` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_aluno_ativo` bit(1) DEFAULT NULL,
  `str_aluno_nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dat_data` datetime NOT NULL,
  `bol_email_registro_enviado` bit(1) DEFAULT NULL,
  `id_aluno` bigint(20) DEFAULT NULL,
  `id_sala` bigint(20) DEFAULT NULL,
  `id_serie` bigint(20) DEFAULT NULL,
  `id_turma` bigint(20) DEFAULT NULL,
  `int_matricula_ano_letivo` int(11) DEFAULT NULL,
  `bol_matricula_ativo` bit(1) DEFAULT NULL,
  `dat_matricula_data_matricula` datetime DEFAULT NULL,
  `str_matricula` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bol_matricula_enviar_email_registro` bit(1) DEFAULT NULL,
  `bol_matricula_enviar_sms_registro` bit(1) DEFAULT NULL,
  `str_matricula_turno` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_telefone_celular` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bol_sala_ativo` bit(1) DEFAULT NULL,
  `str_sala_descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bol_serie_ativo` bit(1) DEFAULT NULL,
  `str_serie_descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bol_sms_registro_enviado` bit(1) DEFAULT NULL,
  `str_tipo_registro` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bol_turma_ativo` bit(1) DEFAULT NULL,
  `str_turma_descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_matricula` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_registro`),
  KEY `FKlgo5n7yyqj8ugfyn0nok548ig` (`id_matricula`)
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
-- Table structure for table `relatorio`
--

DROP TABLE IF EXISTS `relatorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relatorio` (
  `id_relatorio` bigint(20) NOT NULL,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `str_nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_relatorio`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relatorio`
--

LOCK TABLES `relatorio` WRITE;
/*!40000 ALTER TABLE `relatorio` DISABLE KEYS */;
/*!40000 ALTER TABLE `relatorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsavel`
--

DROP TABLE IF EXISTS `responsavel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responsavel` (
  `id_responsavel` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `str_cpf` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_telefone_celular` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `str_telefone_fixo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_usuario` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_responsavel`),
  KEY `FKmvif9xru4yyjxc3v123cdjyal` (`id_usuario`)
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
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sala` (
  `id_sala` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_ativo` tinyint(1) DEFAULT '1',
  `str_descricao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_sala`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_aluno`
--

DROP TABLE IF EXISTS `seq_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seq_aluno` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_aluno`
--

LOCK TABLES `seq_aluno` WRITE;
/*!40000 ALTER TABLE `seq_aluno` DISABLE KEYS */;
INSERT INTO `seq_aluno` VALUES (1);
/*!40000 ALTER TABLE `seq_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serie`
--

DROP TABLE IF EXISTS `serie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serie` (
  `id_serie` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_ativo` tinyint(1) DEFAULT '1',
  `str_descricao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_serie`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serie`
--

LOCK TABLES `serie` WRITE;
/*!40000 ALTER TABLE `serie` DISABLE KEYS */;
/*!40000 ALTER TABLE `serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_ocorrencia`
--

DROP TABLE IF EXISTS `tipo_ocorrencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_ocorrencia` (
  `id_tipo_ocorrencia` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_ativo` tinyint(1) DEFAULT '1',
  `str_descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_tipo_ocorrencia`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_ocorrencia`
--

LOCK TABLES `tipo_ocorrencia` WRITE;
/*!40000 ALTER TABLE `tipo_ocorrencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_ocorrencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turma` (
  `id_turma` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_ativo` tinyint(1) DEFAULT '1',
  `str_descricao` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_turma`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `str_login` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `bol_ativo` tinyint(1) NOT NULL DEFAULT '1',
  `str_nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `str_senha` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`str_login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_perfil`
--

DROP TABLE IF EXISTS `usuario_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_perfil` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_ultima_atualizacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_local` bigint(20) DEFAULT NULL,
  `identificador` bigint(20) NOT NULL DEFAULT '1234',
  `str_login` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `str_perfil` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_10uuast8rqvbjim6e5q82e1ot` (`str_perfil`),
  KEY `FKqid0x1xfs4b3204k9k0muvc4l` (`str_login`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_perfil`
--

LOCK TABLES `usuario_perfil` WRITE;
/*!40000 ALTER TABLE `usuario_perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_perfil` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-12 15:05:20
