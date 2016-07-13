-- MySQL dump 10.13  Distrib 5.5.19, for Win32 (x86)
--
-- Host: localhost    Database: aj
-- ------------------------------------------------------
-- Server version	5.5.19

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
-- Table structure for table `tblbackup`
--

DROP TABLE IF EXISTS `tblbackup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblbackup` (
  `email_backup_date` date DEFAULT NULL,
  `drive_backup_date` date DEFAULT NULL,
  `email_backup_time` time DEFAULT NULL,
  `drive_backup_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbackup`
--

LOCK TABLES `tblbackup` WRITE;
/*!40000 ALTER TABLE `tblbackup` DISABLE KEYS */;
INSERT INTO `tblbackup` VALUES ('2012-11-09','2012-11-09','17:01:12','17:01:59'),('2012-11-09','2012-11-09','17:01:12','17:01:59');
/*!40000 ALTER TABLE `tblbackup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbackupsetting`
--

DROP TABLE IF EXISTS `tblbackupsetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblbackupsetting` (
  `email_backup` int(5) DEFAULT NULL,
  `drive_backup` int(5) DEFAULT NULL,
  `backup_on_exit` int(5) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `drive_backup_path` varchar(400) DEFAULT NULL,
  KEY `username` (`username`),
  CONSTRAINT `tblbackupsetting_ibfk_1` FOREIGN KEY (`username`) REFERENCES `tbllogin` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbackupsetting`
--

LOCK TABLES `tblbackupsetting` WRITE;
/*!40000 ALTER TABLE `tblbackupsetting` DISABLE KEYS */;
INSERT INTO `tblbackupsetting` VALUES (0,0,2,'admin','C:/Users/ABC/Documents/OneNote Notebooks');
/*!40000 ALTER TABLE `tblbackupsetting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcurrentlogin`
--

DROP TABLE IF EXISTS `tblcurrentlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcurrentlogin` (
  `username` varchar(40) DEFAULT NULL,
  KEY `username` (`username`),
  CONSTRAINT `tblcurrentlogin_ibfk_1` FOREIGN KEY (`username`) REFERENCES `tbllogin` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcurrentlogin`
--

LOCK TABLES `tblcurrentlogin` WRITE;
/*!40000 ALTER TABLE `tblcurrentlogin` DISABLE KEYS */;
INSERT INTO `tblcurrentlogin` VALUES ('admin');
/*!40000 ALTER TABLE `tblcurrentlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblfinishitem`
--

DROP TABLE IF EXISTS `tblfinishitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblfinishitem` (
  `stock_id` int(10) NOT NULL,
  `id` int(10) NOT NULL,
  KEY `stock_id` (`stock_id`),
  KEY `id` (`id`),
  CONSTRAINT `tblfinishitem_ibfk_1` FOREIGN KEY (`stock_id`) REFERENCES `tblstockitem` (`si_id`),
  CONSTRAINT `tblfinishitem_ibfk_2` FOREIGN KEY (`id`) REFERENCES `tblfinishtype` (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblfinishitem`
--

LOCK TABLES `tblfinishitem` WRITE;
/*!40000 ALTER TABLE `tblfinishitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblfinishitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblfinishtype`
--

DROP TABLE IF EXISTS `tblfinishtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblfinishtype` (
  `f_name` varchar(30) DEFAULT NULL,
  `f_id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblfinishtype`
--

LOCK TABLES `tblfinishtype` WRITE;
/*!40000 ALTER TABLE `tblfinishtype` DISABLE KEYS */;
INSERT INTO `tblfinishtype` VALUES ('OSL',1),('BSL',2);
/*!40000 ALTER TABLE `tblfinishtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblgroup`
--

DROP TABLE IF EXISTS `tblgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblgroup` (
  `group_id` int(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) DEFAULT NULL,
  `group_under` int(20) DEFAULT NULL,
  `group_alias` varchar(20) DEFAULT NULL,
  `group_isDeletable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblgroup`
--

LOCK TABLES `tblgroup` WRITE;
/*!40000 ALTER TABLE `tblgroup` DISABLE KEYS */;
INSERT INTO `tblgroup` VALUES (50,'Primary',NULL,NULL,NULL),(54,'Current Liability',50,'Main Group',NULL),(55,'Current Assets',50,'Main Group',NULL),(56,'Suspense Account',50,'Primary Group',NULL),(57,'Sundry Debtors',54,'SUN D',NULL),(58,'Sundry Creditors',54,'SC',NULL),(59,'Stock-In-Hand',55,'SIH',NULL),(60,'Sales Account',50,'SA',NULL),(61,'Purchase Account',50,'PA',NULL),(62,'Loan Liability',50,'LL',NULL),(63,'Loans and Advances',55,'LAA',NULL),(64,'Indirect Expenses',50,'IE',NULL),(65,'Indirect Income',50,'II',NULL),(66,'Income (Direct)',50,'DI',NULL),(67,'Income (Indirect)',50,'II',NULL),(68,'Expense (Direct)',50,'ED',NULL),(69,'Expense (Indirect)',50,'EI',NULL),(70,'Bank Account',55,'BA',NULL),(71,'Cash In Hand',55,'CIH',NULL);
/*!40000 ALTER TABLE `tblgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblinventorytransaction`
--

DROP TABLE IF EXISTS `tblinventorytransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblinventorytransaction` (
  `invtrans_id` int(11) NOT NULL AUTO_INCREMENT,
  `trans_id` int(11) DEFAULT NULL,
  `invtrans_type` int(11) DEFAULT NULL,
  `dat` date DEFAULT NULL,
  PRIMARY KEY (`invtrans_id`),
  KEY `trans_id` (`trans_id`),
  CONSTRAINT `tblinventorytransaction_ibfk_1` FOREIGN KEY (`trans_id`) REFERENCES `tbltransactionmain` (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblinventorytransaction`
--

LOCK TABLES `tblinventorytransaction` WRITE;
/*!40000 ALTER TABLE `tblinventorytransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblinventorytransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblinventorytransactionitems`
--

DROP TABLE IF EXISTS `tblinventorytransactionitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblinventorytransactionitems` (
  `invtrans_id` int(11) NOT NULL,
  `invtrans_qty` int(11) DEFAULT NULL,
  `invtrans_rate` int(11) DEFAULT NULL,
  `invtrans_amount` int(11) DEFAULT NULL,
  `invtrans_totalSize` int(11) DEFAULT NULL,
  `invtrans_finishType` varchar(30) DEFAULT NULL,
  `invtrans_index` int(11) DEFAULT NULL,
  `invtrans_itemId` int(11) DEFAULT NULL,
  KEY `invtrans_id` (`invtrans_id`),
  KEY `invtrans_itemId` (`invtrans_itemId`),
  CONSTRAINT `tblinventorytransactionitems_ibfk_1` FOREIGN KEY (`invtrans_id`) REFERENCES `tblinventorytransaction` (`invtrans_id`),
  CONSTRAINT `tblinventorytransactionitems_ibfk_2` FOREIGN KEY (`invtrans_itemId`) REFERENCES `tblstockitem` (`si_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblinventorytransactionitems`
--

LOCK TABLES `tblinventorytransactionitems` WRITE;
/*!40000 ALTER TABLE `tblinventorytransactionitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblinventorytransactionitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblledger`
--

DROP TABLE IF EXISTS `tblledger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblledger` (
  `ledger_id` int(20) NOT NULL AUTO_INCREMENT,
  `ledger_name` varchar(30) DEFAULT NULL,
  `ledger_under` int(20) DEFAULT NULL,
  `ledger_address` varchar(100) DEFAULT NULL,
  `ledger_contactno` varchar(20) DEFAULT NULL,
  `ledger_emailId` varchar(50) DEFAULT NULL,
  `ledger_openingBalance` int(20) DEFAULT NULL,
  `ledger_alias` varchar(20) DEFAULT NULL,
  `ledger_inTaxo` int(20) DEFAULT NULL,
  `ledger_saleTaxNo` int(20) DEFAULT NULL,
  `opening_type` int(5) DEFAULT NULL,
  `dat` date DEFAULT NULL,
  PRIMARY KEY (`ledger_id`),
  KEY `ledger_under` (`ledger_under`),
  CONSTRAINT `tblledger_ibfk_1` FOREIGN KEY (`ledger_under`) REFERENCES `tblgroup` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblledger`
--

LOCK TABLES `tblledger` WRITE;
/*!40000 ALTER TABLE `tblledger` DISABLE KEYS */;
INSERT INTO `tblledger` VALUES (20,'Ajinkya',71,'Pune','7276303576','sdf@ds.dfg',1000,'ert',1,2,2,'2012-11-09');
/*!40000 ALTER TABLE `tblledger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblledgercreditlimit`
--

DROP TABLE IF EXISTS `tblledgercreditlimit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblledgercreditlimit` (
  `ledger_id` int(20) DEFAULT NULL,
  `ledger_limit` int(20) DEFAULT NULL,
  KEY `ledger_id` (`ledger_id`),
  CONSTRAINT `tblledgercreditlimit_ibfk_1` FOREIGN KEY (`ledger_id`) REFERENCES `tblledger` (`ledger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblledgercreditlimit`
--

LOCK TABLES `tblledgercreditlimit` WRITE;
/*!40000 ALTER TABLE `tblledgercreditlimit` DISABLE KEYS */;
INSERT INTO `tblledgercreditlimit` VALUES (20,10000);
/*!40000 ALTER TABLE `tblledgercreditlimit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblledgercurrentbalance`
--

DROP TABLE IF EXISTS `tblledgercurrentbalance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblledgercurrentbalance` (
  `ledger_id` int(20) DEFAULT NULL,
  `ledger_currentBalance` int(20) DEFAULT NULL,
  `ledger_DebitOrCredit` int(5) DEFAULT NULL,
  KEY `ledger_id` (`ledger_id`),
  CONSTRAINT `tblledgercurrentbalance_ibfk_1` FOREIGN KEY (`ledger_id`) REFERENCES `tblledger` (`ledger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblledgercurrentbalance`
--

LOCK TABLES `tblledgercurrentbalance` WRITE;
/*!40000 ALTER TABLE `tblledgercurrentbalance` DISABLE KEYS */;
INSERT INTO `tblledgercurrentbalance` VALUES (20,1000,2);
/*!40000 ALTER TABLE `tblledgercurrentbalance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbllogin`
--

DROP TABLE IF EXISTS `tbllogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbllogin` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `new_entry` int(5) DEFAULT NULL,
  `acc_vouchers` int(5) DEFAULT NULL,
  `report` int(5) DEFAULT NULL,
  `production` int(5) DEFAULT NULL,
  `user_type` int(5) DEFAULT NULL,
  `email_id` varchar(40) DEFAULT NULL,
  `email_pass` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbllogin`
--

LOCK TABLES `tbllogin` WRITE;
/*!40000 ALTER TABLE `tbllogin` DISABLE KEYS */;
INSERT INTO `tbllogin` VALUES ('admin','admin',1,1,1,1,1,'pmcandroidproject@gmail.com','androidproject'),('aj','aj',1,1,1,1,2,'pmcandroidproject@gmail.com','androidproject');
/*!40000 ALTER TABLE `tbllogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblproductiondetails`
--

DROP TABLE IF EXISTS `tblproductiondetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblproductiondetails` (
  `Prod_id` int(20) NOT NULL AUTO_INCREMENT,
  `dat` date DEFAULT NULL,
  PRIMARY KEY (`Prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblproductiondetails`
--

LOCK TABLES `tblproductiondetails` WRITE;
/*!40000 ALTER TABLE `tblproductiondetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblproductiondetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblproductionitems`
--

DROP TABLE IF EXISTS `tblproductionitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblproductionitems` (
  `Prod_id` int(20) DEFAULT NULL,
  `item` int(20) DEFAULT NULL,
  `qty` int(10) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  KEY `Prod_id` (`Prod_id`),
  CONSTRAINT `tblproductionitems_ibfk_1` FOREIGN KEY (`Prod_id`) REFERENCES `tblproductiondetails` (`Prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblproductionitems`
--

LOCK TABLES `tblproductionitems` WRITE;
/*!40000 ALTER TABLE `tblproductionitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblproductionitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblproductionrawmaterial`
--

DROP TABLE IF EXISTS `tblproductionrawmaterial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblproductionrawmaterial` (
  `FinishedItem` int(20) DEFAULT NULL,
  `RawItem` int(20) DEFAULT NULL,
  `qty` int(10) DEFAULT NULL,
  `ind` int(10) DEFAULT NULL,
  KEY `FinishedItem` (`FinishedItem`),
  KEY `RawItem` (`RawItem`),
  CONSTRAINT `tblproductionrawmaterial_ibfk_1` FOREIGN KEY (`FinishedItem`) REFERENCES `tblstockitem` (`si_id`),
  CONSTRAINT `tblproductionrawmaterial_ibfk_2` FOREIGN KEY (`RawItem`) REFERENCES `tblstockitem` (`si_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblproductionrawmaterial`
--

LOCK TABLES `tblproductionrawmaterial` WRITE;
/*!40000 ALTER TABLE `tblproductionrawmaterial` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblproductionrawmaterial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblstockgroup`
--

DROP TABLE IF EXISTS `tblstockgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblstockgroup` (
  `sg_id` int(20) NOT NULL AUTO_INCREMENT,
  `sg_name` varchar(30) DEFAULT NULL,
  `sg_alias` varchar(20) DEFAULT NULL,
  `sg_under` int(20) DEFAULT NULL,
  PRIMARY KEY (`sg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblstockgroup`
--

LOCK TABLES `tblstockgroup` WRITE;
/*!40000 ALTER TABLE `tblstockgroup` DISABLE KEYS */;
INSERT INTO `tblstockgroup` VALUES (26,'Primary','P',26),(27,'Chemical','Chemical',26),(28,'Paper','Paper',26),(29,'Raw Material','Raw Material',26),(30,'Finished Board','Finished Board',26),(31,'ert','dsrg',27);
/*!40000 ALTER TABLE `tblstockgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblstockitem`
--

DROP TABLE IF EXISTS `tblstockitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblstockitem` (
  `si_id` int(20) NOT NULL AUTO_INCREMENT,
  `si_name` varchar(20) DEFAULT NULL,
  `si_alias` varchar(30) DEFAULT NULL,
  `si_under` int(20) DEFAULT NULL,
  `si_length` int(10) DEFAULT NULL,
  `si_width` int(10) DEFAULT NULL,
  `si_thickness` int(10) DEFAULT NULL,
  `si_qty` int(10) DEFAULT NULL,
  `si_rate` int(10) DEFAULT NULL,
  `si_unitOfMeasure` int(10) DEFAULT NULL,
  `si_openingBalance` int(10) DEFAULT NULL,
  `si_color` varchar(30) DEFAULT NULL,
  `si_unit` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`si_id`),
  KEY `si_under` (`si_under`),
  KEY `si_unitOfMeasure` (`si_unitOfMeasure`),
  CONSTRAINT `tblstockitem_ibfk_1` FOREIGN KEY (`si_under`) REFERENCES `tblstockgroup` (`sg_id`),
  CONSTRAINT `tblstockitem_ibfk_2` FOREIGN KEY (`si_unitOfMeasure`) REFERENCES `tblunitofmeasure` (`uomType_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblstockitem`
--

LOCK TABLES `tblstockitem` WRITE;
/*!40000 ALTER TABLE `tblstockitem` DISABLE KEYS */;
INSERT INTO `tblstockitem` VALUES (1,'A','a',28,1,2,3,NULL,100,NULL,1000,'red','mtr'),(2,'B','bb',28,100,100,NULL,NULL,1000,NULL,1000,'White','Mtr'),(3,'C','c',27,NULL,NULL,NULL,NULL,200,NULL,2000,NULL,'Kg'),(4,'D','d',30,200,200,200,NULL,20000,NULL,100,'Red','No');
/*!40000 ALTER TABLE `tblstockitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblstockitemcurrentbalance`
--

DROP TABLE IF EXISTS `tblstockitemcurrentbalance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblstockitemcurrentbalance` (
  `si_id` int(20) DEFAULT NULL,
  `si_currentBalance` int(20) DEFAULT NULL,
  `si_DebitOrCredit` int(5) DEFAULT NULL,
  KEY `si_id` (`si_id`),
  CONSTRAINT `tblstockitemcurrentbalance_ibfk_1` FOREIGN KEY (`si_id`) REFERENCES `tblstockitem` (`si_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblstockitemcurrentbalance`
--

LOCK TABLES `tblstockitemcurrentbalance` WRITE;
/*!40000 ALTER TABLE `tblstockitemcurrentbalance` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblstockitemcurrentbalance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltransactionledger`
--

DROP TABLE IF EXISTS `tbltransactionledger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltransactionledger` (
  `trans_id` int(11) NOT NULL,
  `trans_ledgerId` int(11) DEFAULT NULL,
  `trans_type` int(11) DEFAULT NULL,
  `trans_checkNo` varchar(20) DEFAULT NULL,
  `trans_index` int(11) DEFAULT NULL,
  `trans_narration` varchar(500) DEFAULT NULL,
  `trans_amt` int(20) DEFAULT NULL,
  KEY `trans_ledgerId` (`trans_ledgerId`),
  KEY `trans_id` (`trans_id`),
  CONSTRAINT `tbltransactionledger_ibfk_1` FOREIGN KEY (`trans_ledgerId`) REFERENCES `tblledger` (`ledger_id`),
  CONSTRAINT `tbltransactionledger_ibfk_2` FOREIGN KEY (`trans_id`) REFERENCES `tbltransactionmain` (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltransactionledger`
--

LOCK TABLES `tbltransactionledger` WRITE;
/*!40000 ALTER TABLE `tbltransactionledger` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbltransactionledger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltransactionmain`
--

DROP TABLE IF EXISTS `tbltransactionmain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltransactionmain` (
  `trans_id` int(10) NOT NULL AUTO_INCREMENT,
  `trans_receiptNo` int(10) DEFAULT NULL,
  `trans_date` date DEFAULT NULL,
  `trans_typeIndex` int(11) DEFAULT NULL,
  `trans_narration` varchar(200) DEFAULT NULL,
  `trans_grandtotal` float DEFAULT NULL,
  `trans_transport` int(20) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltransactionmain`
--

LOCK TABLES `tbltransactionmain` WRITE;
/*!40000 ALTER TABLE `tbltransactionmain` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbltransactionmain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltransactionotherdetails`
--

DROP TABLE IF EXISTS `tbltransactionotherdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltransactionotherdetails` (
  `trans_id` int(11) NOT NULL,
  `trans_reference` varchar(50) DEFAULT NULL,
  `trans_buyerOrderNo` varchar(20) DEFAULT NULL,
  `trans_dispatchDocThrough` varchar(20) DEFAULT NULL,
  `trans_dispatchDocNo` varchar(20) DEFAULT NULL,
  `trans_amt` float DEFAULT NULL,
  KEY `trans_id` (`trans_id`),
  CONSTRAINT `tbltransactionotherdetails_ibfk_1` FOREIGN KEY (`trans_id`) REFERENCES `tbltransactionmain` (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltransactionotherdetails`
--

LOCK TABLES `tbltransactionotherdetails` WRITE;
/*!40000 ALTER TABLE `tbltransactionotherdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbltransactionotherdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltransactiontype`
--

DROP TABLE IF EXISTS `tbltransactiontype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltransactiontype` (
  `transType_id` int(10) NOT NULL AUTO_INCREMENT,
  `transType_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`transType_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltransactiontype`
--

LOCK TABLES `tbltransactiontype` WRITE;
/*!40000 ALTER TABLE `tbltransactiontype` DISABLE KEYS */;
INSERT INTO `tbltransactiontype` VALUES (1,'Sales'),(2,'Purchase'),(3,'Payment'),(4,'Contra'),(5,'Journal'),(6,'Receipt'),(7,'Chalan');
/*!40000 ALTER TABLE `tbltransactiontype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbltransactionvat`
--

DROP TABLE IF EXISTS `tbltransactionvat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbltransactionvat` (
  `trans_id` int(20) DEFAULT NULL,
  `vat_rate` float DEFAULT NULL,
  `vat_amt` float DEFAULT NULL,
  KEY `trans_id` (`trans_id`),
  CONSTRAINT `tbltransactionvat_ibfk_1` FOREIGN KEY (`trans_id`) REFERENCES `tbltransactionmain` (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbltransactionvat`
--

LOCK TABLES `tbltransactionvat` WRITE;
/*!40000 ALTER TABLE `tbltransactionvat` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbltransactionvat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblunitofmeasure`
--

DROP TABLE IF EXISTS `tblunitofmeasure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblunitofmeasure` (
  `uomType_id` int(20) NOT NULL,
  `uom_type` varchar(20) DEFAULT NULL,
  `uom_symbol` varchar(20) DEFAULT NULL,
  `uom_formalName` varchar(20) DEFAULT NULL,
  `uom_noOfDecimalPts` int(20) DEFAULT NULL,
  `uom_id` int(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`uom_id`),
  KEY `uomType_id` (`uomType_id`),
  CONSTRAINT `tblunitofmeasure_ibfk_1` FOREIGN KEY (`uomType_id`) REFERENCES `tbluomtype` (`uomType_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblunitofmeasure`
--

LOCK TABLES `tblunitofmeasure` WRITE;
/*!40000 ALTER TABLE `tblunitofmeasure` DISABLE KEYS */;
INSERT INTO `tblunitofmeasure` VALUES (1,'Simple','mtr','mtr',2,1);
/*!40000 ALTER TABLE `tblunitofmeasure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbluomtype`
--

DROP TABLE IF EXISTS `tbluomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbluomtype` (
  `uomType_id` int(20) NOT NULL AUTO_INCREMENT,
  `uomType_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uomType_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluomtype`
--

LOCK TABLES `tbluomtype` WRITE;
/*!40000 ALTER TABLE `tbluomtype` DISABLE KEYS */;
INSERT INTO `tbluomtype` VALUES (1,'Simple'),(2,'Complex');
/*!40000 ALTER TABLE `tbluomtype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-09 17:49:48
