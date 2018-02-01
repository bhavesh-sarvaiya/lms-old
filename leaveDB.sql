CREATE DATABASE  IF NOT EXISTS `leavemanagementapp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `leavemanagementapp`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: leavemanagementapp
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','classpath:config/liquibase/changelog/00000000000000_initial_schema.xml','2017-06-02 00:09:28',1,'EXECUTED','7:289534c28c020e6fefc04422562ad4a2','createTable tableName=jhi_user; createIndex indexName=idx_user_login, tableName=jhi_user; createIndex indexName=idx_user_email, tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableN...','',NULL,'3.5.3',NULL,NULL,'6342366500'),('20170601183353-1','jhipster','classpath:config/liquibase/changelog/20170601183353_added_entity_Department.xml','2017-06-02 00:09:29',2,'EXECUTED','7:6453eb416952499c02f65fc08235fd5b','createTable tableName=department','',NULL,'3.5.3',NULL,NULL,'6342366500'),('20170601183354-1','jhipster','classpath:config/liquibase/changelog/20170601183354_added_entity_Employee.xml','2017-06-02 00:09:29',3,'EXECUTED','7:c6c934e83ca8699bf1829ef855aa8d42','createTable tableName=employee','',NULL,'3.5.3',NULL,NULL,'6342366500'),('20170601183355-1','jhipster','classpath:config/liquibase/changelog/20170601183355_added_entity_LeaveType.xml','2017-06-02 00:09:29',4,'EXECUTED','7:16a3f1b52f771979c3e42c1dd4bb8182','createTable tableName=leave_type','',NULL,'3.5.3',NULL,NULL,'6342366500'),('20170601183356-1','jhipster','classpath:config/liquibase/changelog/20170601183356_added_entity_LeaveBalance.xml','2017-06-02 00:09:29',5,'EXECUTED','7:be4536e14e922407a369a48dbcd184d8','createTable tableName=leave_balance','',NULL,'3.5.3',NULL,NULL,'6342366500'),('20170601183357-1','jhipster','classpath:config/liquibase/changelog/20170601183357_added_entity_LeaveApplication.xml','2017-06-02 00:09:29',6,'EXECUTED','7:357c48ee8e8c385b62dff7864d964eb0','createTable tableName=leave_application','',NULL,'3.5.3',NULL,NULL,'6342366500'),('20170601183358-1','jhipster','classpath:config/liquibase/changelog/20170601183358_added_entity_LeaveApplicationHistory.xml','2017-06-02 00:09:29',7,'EXECUTED','7:a119d2cb3da546e776e1204f0ceddc6b','createTable tableName=leave_application_history','',NULL,'3.5.3',NULL,NULL,'6342366500'),('20170601183354-2','jhipster','classpath:config/liquibase/changelog/20170601183354_added_entity_constraints_Employee.xml','2017-06-02 00:09:29',8,'EXECUTED','7:9311fd70b6c1eea70fcf978dcbfd1299','addForeignKeyConstraint baseTableName=employee, constraintName=fk_employee_department_id, referencedTableName=department','',NULL,'3.5.3',NULL,NULL,'6342366500'),('20170601183357-2','jhipster','classpath:config/liquibase/changelog/20170601183357_added_entity_constraints_LeaveApplication.xml','2017-06-02 00:09:29',9,'EXECUTED','7:0c6d4f7cddc86f29582514f8fb791254','addForeignKeyConstraint baseTableName=leave_application, constraintName=fk_leave_application_leave_type_id, referencedTableName=leave_type','',NULL,'3.5.3',NULL,NULL,'6342366500'),('1496376961560-1','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:34',10,'EXECUTED','7:e2f86761237f898abcfc29dd99bdf5ac','createTable tableName=leave_application_leave_application_histories','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-2','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:35',11,'EXECUTED','7:3c5e154c68ae9cea2b9eebc706c7ddca','addColumn tableName=leave_application','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-3','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:35',12,'EXECUTED','7:4abfe92c3fe8cc1dd0e70522444a8478','addColumn tableName=leave_application','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-4','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:36',13,'EXECUTED','7:0f143b08c2a5f083e562707f5f296adf','addColumn tableName=leave_balance','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-5','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:36',14,'EXECUTED','7:2ae177724fae65009f60d13b2e9cff46','addColumn tableName=leave_application_history','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-6','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:36',15,'EXECUTED','7:03d8894c45aca5e5d85d1738463bcd99','addColumn tableName=leave_balance','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-7','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:37',16,'EXECUTED','7:dd034d1256f44cc336f9dbdebd4503bb','addColumn tableName=employee','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-8','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:37',17,'EXECUTED','7:e1f24fd8f29ba7f3567ec0ab3ece5d16','addUniqueConstraint constraintName=UK_mpps3d3r9pdvyjx3iqixi96fi, tableName=employee','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-9','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:37',18,'EXECUTED','7:68e3a3d899f2f03049079eaded899afa','addUniqueConstraint constraintName=UK_mwkeed2ani4ou3ajb1o624u65, tableName=leave_application_leave_application_histories','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-10','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:37',19,'EXECUTED','7:f2097cdc3a4333ae9681ea6e2eddf86c','addForeignKeyConstraint baseTableName=leave_application, constraintName=FK478qtky6708f5vr93wok0v2v9, referencedTableName=employee','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-11','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:38',20,'EXECUTED','7:d555d18b1b28b5c754f0a94cb7dad529','addForeignKeyConstraint baseTableName=leave_application, constraintName=FK5wn6nda4428nqlooiul7swpeq, referencedTableName=employee','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-12','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:38',21,'EXECUTED','7:3dca6352ca5996edcc01f10ec9f7cd7c','addForeignKeyConstraint baseTableName=leave_application_leave_application_histories, constraintName=FK7snid38uk6ru8xlbp15wlekmm, referencedTableName=leave_application_history','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-13','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:38',22,'EXECUTED','7:e6ccf370463276e4f4f6fed4e991d139','addForeignKeyConstraint baseTableName=leave_balance, constraintName=FK9ueylmeksoyp2jvtdiovselp7, referencedTableName=employee','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-14','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 09:49:39',23,'EXECUTED','7:b63f11fe2f8d841ba754bf3ba25a8c70','addForeignKeyConstraint baseTableName=leave_application_leave_application_histories, constraintName=FKgsqk5dnw4iyx8d3a3x6sem5pq, referencedTableName=leave_application','',NULL,'3.5.3',NULL,NULL,'6377173127'),('1496376961560-15','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 13:15:03',24,'EXECUTED','7:86c9ca7d1def7cf191a57500bf776343','addForeignKeyConstraint baseTableName=employee, constraintName=FKiv756awjw8mae3rmfo03d9wb3, referencedTableName=jhi_user','',NULL,'3.5.3',NULL,NULL,'6389501765'),('1496376961560-16','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 13:15:04',25,'EXECUTED','7:7bdaa38f923c59915065106d3b868e3a','addForeignKeyConstraint baseTableName=leave_application_history, constraintName=FKoau30opyryv2tacqs2si5v0wm, referencedTableName=leave_application','',NULL,'3.5.3',NULL,NULL,'6389501765'),('1496376961560-17','Administrator (generated)','classpath:config/liquibase/changelog/20170602094546_changelog.xml','2017-06-02 13:15:05',26,'EXECUTED','7:f466c9193561612a0d347181d7aa77b0','addForeignKeyConstraint baseTableName=leave_balance, constraintName=FKr7fmdsbyl1l02pt10gdvgkkrq, referencedTableName=leave_type','',NULL,'3.5.3',NULL,NULL,'6389501765'),('1496377541085-1','Administrator (generated)','classpath:config/liquibase/changelog/20170602095524_changelog.xml','2017-06-02 13:17:04',27,'EXECUTED','7:86c9ca7d1def7cf191a57500bf776343','addForeignKeyConstraint baseTableName=employee, constraintName=FKiv756awjw8mae3rmfo03d9wb3, referencedTableName=jhi_user','',NULL,'3.5.3',NULL,NULL,'6389624451'),('1496377541085-2','Administrator (generated)','classpath:config/liquibase/changelog/20170602095524_changelog.xml','2017-06-02 21:22:10',28,'EXECUTED','7:7bdaa38f923c59915065106d3b868e3a','addForeignKeyConstraint baseTableName=leave_application_history, constraintName=FKoau30opyryv2tacqs2si5v0wm, referencedTableName=leave_application','',NULL,'3.5.3',NULL,NULL,'6418729500'),('1496726895698-1','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:32',29,'EXECUTED','7:84c306e3ba509c43ade8fb4a621af2d0','createTable tableName=department','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-2','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:32',30,'EXECUTED','7:2ce4ec86e00a9dbcfd94c0ac1587400d','createTable tableName=employee','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-3','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:32',31,'EXECUTED','7:22406030f2ae3be2a02b2696c26a26c9','createTable tableName=leave_allocation','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-4','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:32',32,'EXECUTED','7:fe1f62f455220e787e5bd52052b3e5c8','createTable tableName=leave_application','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-5','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:33',33,'EXECUTED','7:edd269ff4a063e92667b6beba77c7519','createTable tableName=leave_application_history','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-6','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:33',34,'EXECUTED','7:e2f86761237f898abcfc29dd99bdf5ac','createTable tableName=leave_application_leave_application_histories','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-7','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:33',35,'EXECUTED','7:287b8cc57ac2f1614c50f7900f89cce9','createTable tableName=leave_balance','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-8','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:33',36,'EXECUTED','7:0b57d49c055441740bbe8fea80a46531','createTable tableName=leave_type','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-9','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:33',37,'EXECUTED','7:e1f24fd8f29ba7f3567ec0ab3ece5d16','addUniqueConstraint constraintName=UK_mpps3d3r9pdvyjx3iqixi96fi, tableName=employee','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-10','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:33',38,'EXECUTED','7:68e3a3d899f2f03049079eaded899afa','addUniqueConstraint constraintName=UK_mwkeed2ani4ou3ajb1o624u65, tableName=leave_application_leave_application_histories','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-11','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:33',39,'EXECUTED','7:f2097cdc3a4333ae9681ea6e2eddf86c','addForeignKeyConstraint baseTableName=leave_application, constraintName=FK478qtky6708f5vr93wok0v2v9, referencedTableName=employee','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-12','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:34',40,'EXECUTED','7:d555d18b1b28b5c754f0a94cb7dad529','addForeignKeyConstraint baseTableName=leave_application, constraintName=FK5wn6nda4428nqlooiul7swpeq, referencedTableName=employee','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-13','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:34',41,'EXECUTED','7:3dca6352ca5996edcc01f10ec9f7cd7c','addForeignKeyConstraint baseTableName=leave_application_leave_application_histories, constraintName=FK7snid38uk6ru8xlbp15wlekmm, referencedTableName=leave_application_history','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-14','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:34',42,'EXECUTED','7:e6ccf370463276e4f4f6fed4e991d139','addForeignKeyConstraint baseTableName=leave_balance, constraintName=FK9ueylmeksoyp2jvtdiovselp7, referencedTableName=employee','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-15','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:34',43,'EXECUTED','7:83581c41445058e8a39dda06e37f8b81','addForeignKeyConstraint baseTableName=employee, constraintName=FKbejtwvg9bxus2mffsm3swj3u9, referencedTableName=department','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-16','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:35',44,'EXECUTED','7:5581d24fbcc432b0bedc16044811055d','addForeignKeyConstraint baseTableName=leave_application, constraintName=FKfrg7tqa7y2e4vcxuaqg18jxoa, referencedTableName=leave_type','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-17','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:35',45,'EXECUTED','7:b63f11fe2f8d841ba754bf3ba25a8c70','addForeignKeyConstraint baseTableName=leave_application_leave_application_histories, constraintName=FKgsqk5dnw4iyx8d3a3x6sem5pq, referencedTableName=leave_application','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-18','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:36',46,'EXECUTED','7:86c9ca7d1def7cf191a57500bf776343','addForeignKeyConstraint baseTableName=employee, constraintName=FKiv756awjw8mae3rmfo03d9wb3, referencedTableName=jhi_user','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-19','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:37',47,'EXECUTED','7:80734f7e80f91362faef3ef7ef8aadd4','addForeignKeyConstraint baseTableName=leave_allocation, constraintName=FKodx30xvl9bytlu7yw7381vhpe, referencedTableName=leave_type','',NULL,'3.5.3',NULL,NULL,'6727632679'),('1496726895698-20','Administrator (generated)','classpath:config/liquibase/changelog/20170606105753_changelog_all_table_constrain.xml','2017-06-06 11:10:37',48,'EXECUTED','7:f466c9193561612a0d347181d7aa77b0','addForeignKeyConstraint baseTableName=leave_balance, constraintName=FKr7fmdsbyl1l02pt10gdvgkkrq, referencedTableName=leave_type','',NULL,'3.5.3',NULL,NULL,'6727632679');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'This is teaching department','Computer Science'),(2,'','Audio Visual'),(3,NULL,'Economics'),(4,NULL,'gujarati'),(5,'This is a leave management department','Mahekam'),(6,'Media','Jounalism');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `blood_group` varchar(255) NOT NULL,
  `can_have_vacation` bit(1) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `emp_enroll_ment_no` int(11) NOT NULL,
  `father_husband_name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `granted` bit(1) DEFAULT NULL,
  `join_date` date NOT NULL,
  `marital_status` varchar(255) NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `othe_note` varchar(255) DEFAULT NULL,
  `payband` double NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `physical_fit` bit(1) NOT NULL,
  `pincode` varchar(255) NOT NULL,
  `post` varchar(255) NOT NULL,
  `qualification` varchar(255) NOT NULL,
  `retired_date` date NOT NULL,
  `state` varchar(255) NOT NULL,
  `teachingstaff` bit(1) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mpps3d3r9pdvyjx3iqixi96fi` (`user_id`),
  KEY `FKbejtwvg9bxus2mffsm3swj3u9` (`department_id`),
  CONSTRAINT `FKbejtwvg9bxus2mffsm3swj3u9` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKiv756awjw8mae3rmfo03d9wb3` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Ahmedabad','ANEGATIVE','','Ahmedabad','1970-07-20',111,'xyz','FEMALE','','2017-06-15','MARRIED','999999999','None',1500000,'9999999','','364320','HOD','Phd','2017-06-29','Gujarat','',3,18),(2,'Ahmedabad','ABPOSITIVE','\0','Ahmedabad','2017-06-14',222,'xyz','FEMALE','','2017-06-22','MARRIED','9999999999','no',8000000,'9999999999','','364320','HOD','PHd','2017-07-05','Gujarat','',1,19),(3,'Ahmedabad','BNEGATIVE','\0','Ahmedabad','2017-06-14',333,'xyz','MALE','\0','2017-06-29','MARRIED','99898989899','no',15000,'9898695896','','364325','FACULTY','Post Graduate','2017-06-21','Gujarat','',1,20),(4,'Ahmedabad','ONEGATIVE','\0','Ahmedabad','2017-06-13',444,'xyz','MALE','','2017-06-22','MARRIED','9696585847','no',25000,'96584787855','','789858','ASSISTANTREGISTER','Bachelor','2017-06-29','Gujarat','\0',5,21),(5,'Ahmedabad','ANEGATIVE','\0','Ahmedabad','2017-06-19',666,'xyz','FEMALE','\0','2017-06-15','MARRIED','9998878789','no',150000,'9865896895','','969858','LDC','M.Com','2017-06-23','Gujarat','\0',1,22),(6,'Ahmedabad','OPOSITIVE','','Ahmedabad','2017-06-16',555,'XYZ','MALE','\0','2017-06-07','UNMMARIED','9898989696','no',5000,'989859969','','966985','FACULTY','Graduate','2017-06-13','Gujarat','',4,23),(7,'rtreetretret','BPOSITIVE','','rtet','2017-06-14',115331,'xtyz','MALE','','2017-06-22','UNMMARIED','9696969696','no',1500,'969696858589','','364320','LDC','graduate','2017-06-22','ertret','',1,25),(9,'To: Ayavej','OPOSITIVE','\0','Bhavnagar','1995-06-06',999,'Dalsukhbhai','MALE','','2017-05-31','MARRIED','7874637579',NULL,5000,'123456','','364510','HOD','Graduate','2040-06-13','Gujarat','',1,27);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'user','2017-06-02 04:38:03','AUTHENTICATION_FAILURE'),(2,'user','2017-06-02 04:38:05','AUTHENTICATION_FAILURE'),(3,'user','2017-06-02 04:38:06','AUTHENTICATION_FAILURE'),(4,'user','2017-06-02 04:38:06','AUTHENTICATION_FAILURE'),(5,'user','2017-06-02 04:38:11','AUTHENTICATION_SUCCESS'),(6,'admin','2017-06-02 04:39:03','AUTHENTICATION_SUCCESS'),(7,'admin','2017-06-02 10:01:24','AUTHENTICATION_SUCCESS'),(8,'ewrewr@gmail.com','2017-06-02 10:02:18','AUTHENTICATION_FAILURE'),(9,'ewrewr@gmail.com','2017-06-02 10:02:22','AUTHENTICATION_FAILURE'),(10,'admin','2017-06-02 10:03:40','AUTHENTICATION_SUCCESS'),(11,'115331.gvp@gujaratvidyapith.org','2017-06-02 10:09:32','AUTHENTICATION_SUCCESS'),(12,'admin','2017-06-02 10:11:59','AUTHENTICATION_SUCCESS'),(13,'admin','2017-06-02 11:04:08','AUTHENTICATION_SUCCESS'),(14,'admin','2017-06-02 11:38:15','AUTHENTICATION_SUCCESS'),(15,'admin','2017-06-02 12:05:17','AUTHENTICATION_SUCCESS'),(16,'admin','2017-06-02 12:05:18','AUTHENTICATION_SUCCESS'),(17,'admin','2017-06-02 15:57:31','AUTHENTICATION_SUCCESS'),(18,'admin','2017-06-02 16:06:15','AUTHENTICATION_SUCCESS'),(19,'admin','2017-06-02 16:08:59','AUTHENTICATION_SUCCESS'),(20,'admin','2017-06-02 16:49:11','AUTHENTICATION_SUCCESS'),(21,'admin','2017-06-02 16:49:14','AUTHENTICATION_SUCCESS'),(22,'115331.gvp@gujaratvidyapith.org','2017-06-03 03:02:03','AUTHENTICATION_FAILURE'),(23,'115331.gvp@gujaratvidyapith.org','2017-06-03 03:02:05','AUTHENTICATION_FAILURE'),(24,'115331.gvp@gujaratvidyapith.org','2017-06-03 03:02:05','AUTHENTICATION_FAILURE'),(25,'115331.gvp@gujaratvidyapith.org','2017-06-03 03:02:05','AUTHENTICATION_FAILURE'),(26,'115331.gvp@gujaratvidyapith.org','2017-06-03 03:02:05','AUTHENTICATION_FAILURE'),(27,'115331.gvp@gujaratvidyapith.org','2017-06-03 03:02:05','AUTHENTICATION_FAILURE'),(28,'admin','2017-06-03 03:02:42','AUTHENTICATION_SUCCESS'),(29,'admin','2017-06-03 04:01:45','AUTHENTICATION_SUCCESS'),(30,'admin','2017-06-03 04:13:17','AUTHENTICATION_SUCCESS'),(31,'admin','2017-06-03 06:14:27','AUTHENTICATION_SUCCESS'),(32,'admin','2017-06-03 06:14:27','AUTHENTICATION_SUCCESS'),(33,'admin','2017-06-03 06:14:30','AUTHENTICATION_SUCCESS'),(34,'admin','2017-06-03 06:22:05','AUTHENTICATION_SUCCESS'),(35,'admin','2017-06-06 05:41:02','AUTHENTICATION_SUCCESS'),(36,'admin','2017-06-06 05:44:00','AUTHENTICATION_SUCCESS'),(37,'admin','2017-06-06 08:23:32','AUTHENTICATION_SUCCESS'),(38,'admin','2017-06-06 08:25:39','AUTHENTICATION_SUCCESS'),(39,'admin','2017-06-06 08:26:14','AUTHENTICATION_SUCCESS'),(40,'admin','2017-06-06 09:04:12','AUTHENTICATION_SUCCESS'),(41,'admin','2017-06-06 09:04:29','AUTHENTICATION_SUCCESS'),(42,'admin','2017-06-06 09:04:29','AUTHENTICATION_SUCCESS'),(43,'ketan','2017-06-06 09:05:03','AUTHENTICATION_FAILURE'),(44,'admin','2017-06-06 09:05:41','AUTHENTICATION_SUCCESS'),(45,'bhavesh','2017-06-06 09:05:59','AUTHENTICATION_SUCCESS'),(46,'admin','2017-06-06 09:19:22','AUTHENTICATION_SUCCESS'),(47,'bhavesh','2017-06-06 10:43:21','AUTHENTICATION_SUCCESS'),(48,'admin','2017-06-06 10:43:31','AUTHENTICATION_SUCCESS'),(49,'admin','2017-06-06 10:58:16','AUTHENTICATION_SUCCESS'),(50,'ketan','2017-06-06 11:00:17','AUTHENTICATION_SUCCESS'),(51,'neemisha','2017-06-06 11:10:09','AUTHENTICATION_FAILURE'),(52,'neemisha','2017-06-06 11:10:11','AUTHENTICATION_FAILURE'),(53,'neemisha','2017-06-06 11:10:12','AUTHENTICATION_FAILURE'),(54,'neemisha','2017-06-06 11:10:13','AUTHENTICATION_FAILURE'),(55,'neemisha','2017-06-06 11:10:14','AUTHENTICATION_FAILURE'),(56,'neemisha','2017-06-06 11:10:14','AUTHENTICATION_FAILURE'),(57,'neemisha','2017-06-06 11:10:14','AUTHENTICATION_FAILURE'),(58,'neemisha','2017-06-06 11:10:14','AUTHENTICATION_FAILURE'),(59,'neemisha','2017-06-06 11:10:14','AUTHENTICATION_FAILURE'),(60,'admin','2017-06-06 11:10:24','AUTHENTICATION_SUCCESS'),(61,'nimishaben@gmail.com','2017-06-06 11:10:43','AUTHENTICATION_SUCCESS'),(62,'admin','2017-06-06 11:11:27','AUTHENTICATION_SUCCESS'),(63,'admin','2017-06-06 11:27:11','AUTHENTICATION_SUCCESS'),(64,'bhavesh','2017-06-07 06:14:39','AUTHENTICATION_SUCCESS'),(65,'admin','2017-06-07 06:19:07','AUTHENTICATION_SUCCESS'),(66,'admin','2017-06-07 06:59:19','AUTHENTICATION_SUCCESS'),(67,'admin','2017-06-07 07:15:33','AUTHENTICATION_SUCCESS'),(68,'admin','2017-06-07 07:25:43','AUTHENTICATION_SUCCESS'),(69,'bhavesh','2017-06-07 07:27:09','AUTHENTICATION_SUCCESS'),(70,'bhavesh','2017-06-07 07:27:10','AUTHENTICATION_SUCCESS'),(71,'admin','2017-06-07 07:27:37','AUTHENTICATION_SUCCESS'),(72,'admin','2017-06-07 07:27:49','AUTHENTICATION_SUCCESS'),(73,'admin','2017-06-07 07:28:02','AUTHENTICATION_SUCCESS'),(74,'admin','2017-06-07 07:28:11','AUTHENTICATION_SUCCESS'),(75,'admin','2017-06-07 09:08:17','AUTHENTICATION_SUCCESS'),(76,'admin','2017-06-07 09:08:42','AUTHENTICATION_SUCCESS'),(77,'admin','2017-06-07 09:09:06','AUTHENTICATION_SUCCESS'),(78,'admin','2017-06-07 09:11:00','AUTHENTICATION_SUCCESS'),(79,'admin','2017-06-07 09:20:27','AUTHENTICATION_SUCCESS'),(80,'admin','2017-06-07 09:27:58','AUTHENTICATION_SUCCESS'),(81,'admin','2017-06-07 10:49:17','AUTHENTICATION_SUCCESS'),(82,'admin','2017-06-07 10:52:49','AUTHENTICATION_SUCCESS'),(83,'admin','2017-06-07 10:58:48','AUTHENTICATION_SUCCESS'),(84,'admin','2017-06-07 10:59:07','AUTHENTICATION_SUCCESS'),(85,'bhavesh','2017-06-07 10:59:31','AUTHENTICATION_SUCCESS'),(86,'admin','2017-06-07 11:02:46','AUTHENTICATION_SUCCESS'),(87,'admin','2017-06-07 11:03:00','AUTHENTICATION_SUCCESS'),(88,'bhavesh','2017-06-07 11:03:48','AUTHENTICATION_SUCCESS'),(89,'admin','2017-06-07 11:04:36','AUTHENTICATION_SUCCESS'),(90,'bhavesh','2017-06-07 11:05:01','AUTHENTICATION_SUCCESS'),(91,'admin','2017-06-07 11:09:18','AUTHENTICATION_SUCCESS'),(92,'bhavesh','2017-06-07 11:09:38','AUTHENTICATION_SUCCESS'),(93,'admin','2017-06-07 11:10:40','AUTHENTICATION_SUCCESS'),(94,'bhavesh','2017-06-07 11:11:24','AUTHENTICATION_SUCCESS'),(95,'admin','2017-06-07 11:14:18','AUTHENTICATION_SUCCESS'),(96,'bhavesh','2017-06-07 11:26:32','AUTHENTICATION_SUCCESS'),(97,'nimisha','2017-06-07 11:27:08','AUTHENTICATION_FAILURE'),(98,'nimisha@gmail.com','2017-06-07 11:27:24','AUTHENTICATION_FAILURE'),(99,'nimisha@gmail.com','2017-06-07 11:27:25','AUTHENTICATION_FAILURE'),(100,'nimisha@gmail.com','2017-06-07 11:27:27','AUTHENTICATION_FAILURE'),(101,'nimishaben@gmail.com','2017-06-07 11:27:35','AUTHENTICATION_SUCCESS'),(102,'admin','2017-06-07 11:27:57','AUTHENTICATION_SUCCESS'),(103,'nimishaben@gmail.com','2017-06-07 11:29:29','AUTHENTICATION_SUCCESS'),(104,'admin','2017-06-07 11:32:35','AUTHENTICATION_SUCCESS'),(105,'admin','2017-06-07 15:05:26','AUTHENTICATION_SUCCESS'),(106,'admin','2017-06-07 15:05:26','AUTHENTICATION_SUCCESS'),(107,'admin','2017-06-07 15:05:48','AUTHENTICATION_SUCCESS'),(108,'admin','2017-06-07 15:13:00','AUTHENTICATION_SUCCESS'),(109,'bhavesh','2017-06-07 15:33:34','AUTHENTICATION_SUCCESS'),(110,'admin','2017-06-07 15:35:07','AUTHENTICATION_SUCCESS'),(111,'admin','2017-06-07 15:54:22','AUTHENTICATION_SUCCESS'),(112,'admin','2017-06-07 16:31:49','AUTHENTICATION_SUCCESS'),(113,'nimishaben@gmail.com','2017-06-07 17:02:50','AUTHENTICATION_SUCCESS'),(114,'admin','2017-06-07 17:07:11','AUTHENTICATION_SUCCESS'),(115,'admin','2017-06-07 17:42:30','AUTHENTICATION_SUCCESS'),(116,'nimishaben@gmail.com','2017-06-07 17:43:18','AUTHENTICATION_SUCCESS'),(117,'admin','2017-06-07 17:45:12','AUTHENTICATION_SUCCESS'),(118,'nimishaben@gmail.com','2017-06-07 17:45:32','AUTHENTICATION_SUCCESS'),(119,'nimishaben@gmail.com','2017-06-07 17:46:30','AUTHENTICATION_FAILURE'),(120,'nimishaben@gmail.com','2017-06-07 17:46:37','AUTHENTICATION_SUCCESS'),(121,'admin','2017-06-07 17:56:03','AUTHENTICATION_SUCCESS'),(122,'nimishaben@gmail.com','2017-06-07 18:03:03','AUTHENTICATION_FAILURE'),(123,'nimishaben@gmail.com','2017-06-07 18:03:08','AUTHENTICATION_SUCCESS'),(124,'admin','2017-06-08 03:54:50','AUTHENTICATION_SUCCESS'),(125,'admin','2017-06-08 06:30:56','AUTHENTICATION_SUCCESS'),(126,'admin','2017-06-08 06:43:59','AUTHENTICATION_SUCCESS'),(127,'admin','2017-06-09 05:19:09','AUTHENTICATION_SUCCESS'),(128,'nimishaben@gmail.com','2017-06-09 05:20:14','AUTHENTICATION_SUCCESS'),(129,'admin','2017-06-09 06:47:14','AUTHENTICATION_SUCCESS'),(130,'admin','2017-06-09 17:14:30','AUTHENTICATION_SUCCESS'),(131,'nimishaben@gmail.com','2017-06-09 18:03:52','AUTHENTICATION_SUCCESS'),(132,'admin','2017-06-09 18:05:29','AUTHENTICATION_SUCCESS'),(133,'admin','2017-06-09 18:37:23','AUTHENTICATION_SUCCESS'),(134,'admin','2017-06-09 18:37:34','AUTHENTICATION_SUCCESS'),(135,'admin','2017-06-09 18:37:46','AUTHENTICATION_SUCCESS'),(136,'admin','2017-06-09 18:38:11','AUTHENTICATION_SUCCESS'),(137,'admin','2017-06-10 07:06:36','AUTHENTICATION_SUCCESS'),(138,'nimishaben@gmail.com','2017-06-10 13:20:18','AUTHENTICATION_SUCCESS'),(139,'admin','2017-06-10 13:37:42','AUTHENTICATION_SUCCESS'),(140,'nimishaben@gmail.com','2017-06-10 15:08:02','AUTHENTICATION_SUCCESS'),(141,'nimishaben@gmail.com','2017-06-10 15:09:04','AUTHENTICATION_SUCCESS'),(142,'admin','2017-06-10 15:09:21','AUTHENTICATION_SUCCESS'),(143,'nimishaben@gmail.com','2017-06-10 15:10:01','AUTHENTICATION_SUCCESS'),(144,'bhavesh','2017-06-10 15:18:43','AUTHENTICATION_SUCCESS'),(145,'admin','2017-06-10 15:29:42','AUTHENTICATION_SUCCESS'),(146,'nimishaben@gmail.com','2017-06-10 15:40:14','AUTHENTICATION_SUCCESS'),(147,'admin','2017-06-10 15:40:30','AUTHENTICATION_SUCCESS'),(148,'nimishaben@gmail.com','2017-06-10 15:41:03','AUTHENTICATION_SUCCESS'),(149,'nimishaben@gmail.com','2017-06-10 16:35:22','AUTHENTICATION_SUCCESS'),(150,'admin','2017-06-10 16:36:06','AUTHENTICATION_SUCCESS'),(151,'nimishaben@gmail.com','2017-06-10 16:36:40','AUTHENTICATION_SUCCESS'),(152,'admin','2017-06-10 16:40:42','AUTHENTICATION_SUCCESS'),(153,'admin','2017-06-10 16:41:04','AUTHENTICATION_SUCCESS'),(154,'bhavesh@gmail.com','2017-06-10 16:44:28','AUTHENTICATION_FAILURE'),(155,'admin','2017-06-10 16:44:35','AUTHENTICATION_SUCCESS'),(156,'bhavesh@gmail.com','2017-06-10 16:45:11','AUTHENTICATION_FAILURE'),(157,'admin','2017-06-10 16:45:31','AUTHENTICATION_SUCCESS'),(158,'admin','2017-06-10 16:51:15','AUTHENTICATION_SUCCESS'),(159,'bhavesh@gmail.com','2017-06-10 16:54:10','AUTHENTICATION_FAILURE'),(160,'bhavesh@gmail.com','2017-06-10 16:54:13','AUTHENTICATION_FAILURE'),(161,'bhavesh@gmail.com','2017-06-10 16:54:14','AUTHENTICATION_FAILURE'),(162,'bhavesh@gmail.com','2017-06-10 16:54:15','AUTHENTICATION_FAILURE'),(163,'bhavesh@gmail.com','2017-06-10 16:54:15','AUTHENTICATION_FAILURE'),(164,'bhavesh@gmail.com','2017-06-10 16:54:15','AUTHENTICATION_FAILURE'),(165,'bhavesh@gmail.com','2017-06-10 16:54:15','AUTHENTICATION_FAILURE'),(166,'bhavesh@gmail.com','2017-06-10 16:54:15','AUTHENTICATION_FAILURE'),(167,'bhavesh@gmail.com','2017-06-10 16:54:16','AUTHENTICATION_FAILURE'),(168,'bhavesh@gmail.com','2017-06-10 16:54:16','AUTHENTICATION_FAILURE'),(169,'bhavesh@gmail.com','2017-06-10 16:54:16','AUTHENTICATION_FAILURE'),(170,'bhavesh@gmail.com','2017-06-10 16:54:16','AUTHENTICATION_FAILURE'),(171,'bhavesh@gmail.com','2017-06-10 16:54:16','AUTHENTICATION_FAILURE'),(172,'bhavesh@gmail.com','2017-06-10 16:54:16','AUTHENTICATION_FAILURE'),(173,'bhavesh@gmail.com','2017-06-10 16:54:16','AUTHENTICATION_FAILURE'),(174,'bhavesh@gmail.com','2017-06-10 16:54:17','AUTHENTICATION_FAILURE'),(175,'bhavesh@gmail.com','2017-06-10 16:54:17','AUTHENTICATION_FAILURE'),(176,'admin','2017-06-10 17:14:33','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (1,'message','Bad credentials'),(1,'type','org.springframework.security.authentication.BadCredentialsException'),(2,'message','Bad credentials'),(2,'type','org.springframework.security.authentication.BadCredentialsException'),(3,'message','Bad credentials'),(3,'type','org.springframework.security.authentication.BadCredentialsException'),(4,'message','Bad credentials'),(4,'type','org.springframework.security.authentication.BadCredentialsException'),(8,'message','Bad credentials'),(8,'type','org.springframework.security.authentication.BadCredentialsException'),(9,'message','Bad credentials'),(9,'type','org.springframework.security.authentication.BadCredentialsException'),(22,'message','Bad credentials'),(22,'type','org.springframework.security.authentication.BadCredentialsException'),(23,'message','Bad credentials'),(23,'type','org.springframework.security.authentication.BadCredentialsException'),(24,'message','Bad credentials'),(24,'type','org.springframework.security.authentication.BadCredentialsException'),(25,'message','Bad credentials'),(25,'type','org.springframework.security.authentication.BadCredentialsException'),(26,'message','Bad credentials'),(26,'type','org.springframework.security.authentication.BadCredentialsException'),(27,'message','Bad credentials'),(27,'type','org.springframework.security.authentication.BadCredentialsException'),(43,'message','Bad credentials'),(43,'type','org.springframework.security.authentication.BadCredentialsException'),(51,'message','Bad credentials'),(51,'type','org.springframework.security.authentication.BadCredentialsException'),(52,'message','Bad credentials'),(52,'type','org.springframework.security.authentication.BadCredentialsException'),(53,'message','Bad credentials'),(53,'type','org.springframework.security.authentication.BadCredentialsException'),(54,'message','Bad credentials'),(54,'type','org.springframework.security.authentication.BadCredentialsException'),(55,'message','Bad credentials'),(55,'type','org.springframework.security.authentication.BadCredentialsException'),(56,'message','Bad credentials'),(56,'type','org.springframework.security.authentication.BadCredentialsException'),(57,'message','Bad credentials'),(57,'type','org.springframework.security.authentication.BadCredentialsException'),(58,'message','Bad credentials'),(58,'type','org.springframework.security.authentication.BadCredentialsException'),(59,'message','Bad credentials'),(59,'type','org.springframework.security.authentication.BadCredentialsException'),(97,'message','Bad credentials'),(97,'type','org.springframework.security.authentication.BadCredentialsException'),(98,'message','Bad credentials'),(98,'type','org.springframework.security.authentication.BadCredentialsException'),(99,'message','Bad credentials'),(99,'type','org.springframework.security.authentication.BadCredentialsException'),(100,'message','Bad credentials'),(100,'type','org.springframework.security.authentication.BadCredentialsException'),(119,'message','Bad credentials'),(119,'type','org.springframework.security.authentication.BadCredentialsException'),(122,'message','Bad credentials'),(122,'type','org.springframework.security.authentication.BadCredentialsException'),(154,'message','Bad credentials'),(154,'type','org.springframework.security.authentication.BadCredentialsException'),(156,'message','Bad credentials'),(156,'type','org.springframework.security.authentication.BadCredentialsException'),(159,'message','Bad credentials'),(159,'type','org.springframework.security.authentication.BadCredentialsException'),(160,'message','Bad credentials'),(160,'type','org.springframework.security.authentication.BadCredentialsException'),(161,'message','Bad credentials'),(161,'type','org.springframework.security.authentication.BadCredentialsException'),(162,'message','Bad credentials'),(162,'type','org.springframework.security.authentication.BadCredentialsException'),(163,'message','Bad credentials'),(163,'type','org.springframework.security.authentication.BadCredentialsException'),(164,'message','Bad credentials'),(164,'type','org.springframework.security.authentication.BadCredentialsException'),(165,'message','Bad credentials'),(165,'type','org.springframework.security.authentication.BadCredentialsException'),(166,'message','Bad credentials'),(166,'type','org.springframework.security.authentication.BadCredentialsException'),(167,'message','Bad credentials'),(167,'type','org.springframework.security.authentication.BadCredentialsException'),(168,'message','Bad credentials'),(168,'type','org.springframework.security.authentication.BadCredentialsException'),(169,'message','Bad credentials'),(169,'type','org.springframework.security.authentication.BadCredentialsException'),(170,'message','Bad credentials'),(170,'type','org.springframework.security.authentication.BadCredentialsException'),(171,'message','Bad credentials'),(171,'type','org.springframework.security.authentication.BadCredentialsException'),(172,'message','Bad credentials'),(172,'type','org.springframework.security.authentication.BadCredentialsException'),(173,'message','Bad credentials'),(173,'type','org.springframework.security.authentication.BadCredentialsException'),(174,'message','Bad credentials'),(174,'type','org.springframework.security.authentication.BadCredentialsException'),(175,'message','Bad credentials'),(175,'type','org.springframework.security.authentication.BadCredentialsException');
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `idx_user_login` (`login`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','','','en',NULL,NULL,'system','2017-06-01 18:39:28',NULL,'system',NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','','en',NULL,NULL,'system','2017-06-01 18:39:28',NULL,'system',NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','','en',NULL,NULL,'system','2017-06-01 18:39:28',NULL,'system',NULL),(9,'115331.gvp@gujaratvidyapith.org','$2a$10$rQeYfUnCJb9TJoNPx/Igj.LVPS7QofIRFDk15f4gZa.qzCAlW5W22',NULL,NULL,'115331.gvp@gujaratvidyapith.org',NULL,'','en',NULL,NULL,'anonymousUser','2017-06-02 10:07:58',NULL,'admin','2017-06-03 03:01:22'),(17,'ketan','$2a$10$rTH7gT01ex8YnNbKSC3FOOTza7ggI3JjCqgiv9eduIVIKRlNCbkp.',NULL,NULL,'ketanvnavadiay1@gmail.com',NULL,'','en','27330284583579566685',NULL,'anonymousUser','2017-06-03 04:00:56',NULL,'admin','2017-06-06 11:00:04'),(18,'nimishaben@gmail.com','$2a$10$EaR4RvNozAtCBk1nS8lBpu/uK1JLxB1h6.gfD98/zZafVadlVpit6','Nimishaben','Economics','nimishaben@gmail.com',NULL,'','en',NULL,NULL,'admin','2017-06-06 06:23:16',NULL,'anonymousUser','2017-06-06 11:09:58'),(19,'ruchitalen@gmail.com','$2a$10$JSEiDBntWcvpKGylF9nEIeKy47M5ylTwtAf4TSutynJo8goOHRomW','Ruchitaben','Shah','ruchitalen@gmail.com',NULL,'','en',NULL,'36614604730925923689','admin','2017-06-06 06:26:39','2017-06-06 06:26:39','admin','2017-06-06 06:26:39'),(20,'yogeshbhai@gmail.com','$2a$10$0MB8YZ41s23JODqRpRzugOCiL8gBzUlbUK3gLc.gh8/UP.Q8A4aX2','Yogeshbhai','Limbasiya','yogeshbhai@gmail.com',NULL,'','en',NULL,'34563582536579070994','admin','2017-06-06 06:29:05','2017-06-10 15:30:21','anonymousUser','2017-06-10 15:30:21'),(21,'pgparmar@gmail.com','$2a$10$cPrAMcoP7.IEPw54rahqWudgZBCUJqA/KHm29HtLs0PU1KTtXgYtS','P. G','Parmar','pgparmar@gmail.com',NULL,'','en',NULL,'07703591406495921024','admin','2017-06-06 06:34:32','2017-06-06 06:34:32','admin','2017-06-06 06:34:32'),(22,'dixitaben@gmail.com','$2a$10$VcGsshiH/JCCzaGoCt/PHO1SUHTy58E4KPYnQmZkxUttfu6TtzeEe','Dixitaben','Bramhbhaat','dixitaben@gmail.com',NULL,'','en',NULL,'41942212555828424136','admin','2017-06-06 06:37:46','2017-06-06 06:37:46','admin','2017-06-06 06:37:46'),(23,'mahesbhai@gmail.com','$2a$10$Ni//V68ZpPmXERzzDzeo5.mg5aumi835fwKB1FYlbjPOnaPyaLfPa','Maheshbhai','Patel','mahesbhai@gmail.com',NULL,'','en',NULL,'03690231629823747343','admin','2017-06-06 06:40:38','2017-06-06 06:40:38','admin','2017-06-06 06:40:38'),(25,'ketan@gmal.com','$2a$10$ox8jtpNVJaA38FModyX0keRDDn5PgJutAzQWdyX3Okw5Eoww4F/sy','Ketan','Navadoiya','ketan@gmal.com',NULL,'','en',NULL,'45504481744783036626','admin','2017-06-09 17:28:07','2017-06-09 17:28:07','admin','2017-06-09 17:28:07'),(27,'bhavesh@gmail.com','$2a$10$NwCOTwYTPs6fC304KK8NUueWSmcySw2rH8wUeTinTDyGPTE0iRt5y','Bhavesh','Sarvaiya','bhavesh@gmail.com',NULL,'','en',NULL,'25207690576194939572','admin','2017-06-10 16:53:52','2017-06-10 16:53:52','admin','2017-06-10 16:53:52');
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (3,'ROLE_ADMIN'),(3,'ROLE_USER'),(4,'ROLE_USER'),(9,'ROLE_USER'),(17,'ROLE_USER'),(18,'ROLE_USER'),(19,'ROLE_USER'),(20,'ROLE_USER'),(21,'ROLE_USER'),(22,'ROLE_USER'),(23,'ROLE_USER'),(25,'ROLE_USER'),(27,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_allocation`
--

DROP TABLE IF EXISTS `leave_allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_allocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `allocation_date` date DEFAULT NULL,
  `can_have_vacation` bit(1) DEFAULT NULL,
  `granted` bit(1) DEFAULT NULL,
  `no_of_leaves` double DEFAULT NULL,
  `teaching` bit(1) DEFAULT NULL,
  `leave_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKodx30xvl9bytlu7yw7381vhpe` (`leave_type_id`),
  CONSTRAINT `FKodx30xvl9bytlu7yw7381vhpe` FOREIGN KEY (`leave_type_id`) REFERENCES `leave_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_allocation`
--

LOCK TABLES `leave_allocation` WRITE;
/*!40000 ALTER TABLE `leave_allocation` DISABLE KEYS */;
INSERT INTO `leave_allocation` VALUES (1,'2017-06-05','','',25,'',1),(2,'2017-06-06','\0','',20,'',2),(3,'2017-06-06','\0','\0',30,'\0',3),(4,'2017-06-29','\0','',15,'\0',1);
/*!40000 ALTER TABLE `leave_allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_application`
--

DROP TABLE IF EXISTS `leave_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_application` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_from` date NOT NULL,
  `end_date` date NOT NULL,
  `reason` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `employee_applied_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `leave_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK478qtky6708f5vr93wok0v2v9` (`employee_applied_id`),
  KEY `FK5wn6nda4428nqlooiul7swpeq` (`employee_id`),
  KEY `FKfrg7tqa7y2e4vcxuaqg18jxoa` (`leave_type_id`),
  CONSTRAINT `FK478qtky6708f5vr93wok0v2v9` FOREIGN KEY (`employee_applied_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK5wn6nda4428nqlooiul7swpeq` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKfrg7tqa7y2e4vcxuaqg18jxoa` FOREIGN KEY (`leave_type_id`) REFERENCES `leave_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_application`
--

LOCK TABLES `leave_application` WRITE;
/*!40000 ALTER TABLE `leave_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `leave_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_application_history`
--

DROP TABLE IF EXISTS `leave_application_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_application_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_date` date NOT NULL,
  `jhi_comment` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_application_history`
--

LOCK TABLES `leave_application_history` WRITE;
/*!40000 ALTER TABLE `leave_application_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `leave_application_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_application_leave_application_histories`
--

DROP TABLE IF EXISTS `leave_application_leave_application_histories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_application_leave_application_histories` (
  `leave_application_id` bigint(20) NOT NULL,
  `leave_application_histories_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_mwkeed2ani4ou3ajb1o624u65` (`leave_application_histories_id`),
  KEY `FKgsqk5dnw4iyx8d3a3x6sem5pq` (`leave_application_id`),
  CONSTRAINT `FK7snid38uk6ru8xlbp15wlekmm` FOREIGN KEY (`leave_application_histories_id`) REFERENCES `leave_application_history` (`id`),
  CONSTRAINT `FKgsqk5dnw4iyx8d3a3x6sem5pq` FOREIGN KEY (`leave_application_id`) REFERENCES `leave_application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_application_leave_application_histories`
--

LOCK TABLES `leave_application_leave_application_histories` WRITE;
/*!40000 ALTER TABLE `leave_application_leave_application_histories` DISABLE KEYS */;
/*!40000 ALTER TABLE `leave_application_leave_application_histories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_balance`
--

DROP TABLE IF EXISTS `leave_balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_balance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no_of_leave` double NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `leave_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9ueylmeksoyp2jvtdiovselp7` (`employee_id`),
  KEY `FKr7fmdsbyl1l02pt10gdvgkkrq` (`leave_type_id`),
  CONSTRAINT `FK9ueylmeksoyp2jvtdiovselp7` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKr7fmdsbyl1l02pt10gdvgkkrq` FOREIGN KEY (`leave_type_id`) REFERENCES `leave_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_balance`
--

LOCK TABLES `leave_balance` WRITE;
/*!40000 ALTER TABLE `leave_balance` DISABLE KEYS */;
INSERT INTO `leave_balance` VALUES (1,25,1,1),(2,20,2,2),(3,30,5,3),(4,15,4,1);
/*!40000 ALTER TABLE `leave_balance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_type`
--

DROP TABLE IF EXISTS `leave_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_type`
--

LOCK TABLES `leave_type` WRITE;
/*!40000 ALTER TABLE `leave_type` DISABLE KEYS */;
INSERT INTO `leave_type` VALUES (1,'CL',NULL,'Casual leave'),(2,'PL',NULL,'Privilege leave'),(3,'SL',NULL,'Sick leave');
/*!40000 ALTER TABLE `leave_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_type_days_mapping`
--

DROP TABLE IF EXISTS `leave_type_days_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leave_type_days_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LEAVE_TYPE_CODE` char(5) DEFAULT NULL,
  `teaching` tinyint(4) DEFAULT NULL,
  `granted` tinyint(4) DEFAULT NULL,
  `CAN_HAVE_VACATION` tinyint(4) DEFAULT NULL,
  `MAX_NO_OF_DAYS` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_type_days_mapping`
--

LOCK TABLES `leave_type_days_mapping` WRITE;
/*!40000 ALTER TABLE `leave_type_days_mapping` DISABLE KEYS */;
INSERT INTO `leave_type_days_mapping` VALUES (1,'CL',1,1,1,6);
/*!40000 ALTER TABLE `leave_type_days_mapping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-10 22:51:41
