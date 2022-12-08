-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: medicalsystem
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `ID` int NOT NULL,
  `AppointmentNumber` int NOT NULL,
  `AppointmentType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`,`AppointmentNumber`),
  UNIQUE KEY `AppointmentNumber` (`AppointmentNumber`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `patient` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (462380,3,'checkup');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic`
--

DROP TABLE IF EXISTS `clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic` (
  `Address` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic`
--

LOCK TABLES `clinic` WRITE;
/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` VALUES ('327 Road Rd.','Oasis@gmail.com','(403)-999-1234','Oasis Clinic'),('999 Road','XClin@gmail.com','789-332-9987','XClinic');
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contains`
--

DROP TABLE IF EXISTS `contains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contains` (
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`Name`),
  CONSTRAINT `contains_ibfk_1` FOREIGN KEY (`Name`) REFERENCES `drugs` (`Name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contains`
--

LOCK TABLES `contains` WRITE;
/*!40000 ALTER TABLE `contains` DISABLE KEYS */;
/*!40000 ALTER TABLE `contains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `ID` int NOT NULL,
  `Fname` varchar(255) DEFAULT NULL,
  `Lname` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Specialty` varchar(255) DEFAULT NULL,
  `ClinicName` varchar(255) DEFAULT NULL,
  `Username` varchar(255) NOT NULL,
  `Spe_Flag` int DEFAULT NULL,
  `Fam_Flag` int DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`,`Username`),
  UNIQUE KEY `Username` (`Username`),
  KEY `ClinicName` (`ClinicName`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`ClinicName`) REFERENCES `clinic` (`Name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (12,'joe','joe','joe','joe','Oasis Clinic','koe',1,0,'no','nope'),(32,'Fir','Nam','jr4@yahoo.com','Specialist','Oasis Clinic','fna@',1,0,'9086676','12345tyu'),(123,'a','a','a','Family Doctor','Oasis Clinic','a',1,0,'a','a'),(123,'FName','LName','emailin','specialty','Oasis Clinic','userin',1,0,'phonein','pswdin'),(453,'Ros','Ln','tl@tip.com','Family Doctor','Oasis Clinic','r_ln',0,1,'908-667-3218','pass'),(89765,'Michael','Rose','dr_mrose@gmail.com','Specialist','Oasis Clinic','mrose4567',1,0,'403-999-9999','iloveroses'),(215403,'Johnny','Appleseed','JohnApple@gmail.com','None','Oasis Clinic','Lakers248',0,0,'(403) 555-9898','Calgary403'),(34871452,'doctor','example','myemail@email.com','Specialist','Oasis Clinic','exuser',1,0,'403 555 7826','expass');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_address`
--

DROP TABLE IF EXISTS `doctor_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_address` (
  `ID` int NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  `DoctorUser` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`,`ADDRESS`,`DoctorUser`),
  KEY `DoctorUser` (`DoctorUser`),
  CONSTRAINT `doctor_address_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `doctor` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `doctor_address_ibfk_2` FOREIGN KEY (`DoctorUser`) REFERENCES `doctor` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_address`
--

LOCK TABLES `doctor_address` WRITE;
/*!40000 ALTER TABLE `doctor_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctorschedule`
--

DROP TABLE IF EXISTS `doctorschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctorschedule` (
  `ID` int NOT NULL,
  `Hours` int DEFAULT NULL,
  `VacatonDays` varchar(255) DEFAULT NULL,
  `Days` varchar(255) DEFAULT NULL,
  `Username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Username` (`Username`),
  CONSTRAINT `doctorschedule_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `doctor` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorschedule`
--

LOCK TABLES `doctorschedule` WRITE;
/*!40000 ALTER TABLE `doctorschedule` DISABLE KEYS */;
INSERT INTO `doctorschedule` VALUES (12,8,'Mondays','T-F','koe'),(123,18,'july 22','Monday-Tuesday','userin'),(215403,7,'every last month\'s friday','Monday-Friday','Lakers248');
/*!40000 ALTER TABLE `doctorschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drugs`
--

DROP TABLE IF EXISTS `drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drugs` (
  `Company` varchar(255) DEFAULT NULL,
  `SideEffects` varchar(255) DEFAULT NULL,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugs`
--

LOCK TABLES `drugs` WRITE;
/*!40000 ALTER TABLE `drugs` DISABLE KEYS */;
INSERT INTO `drugs` VALUES ('123','123','123'),('123','xyz','abc'),('BigPharma','Confusion, night sweats','Adderall'),('Balmoral','None','Ativan'),('BigPharma','Cough and runny nose','Clenbuterol'),(NULL,NULL,'Clorazepam'),('BigPharma','Cough and runny nose','Hydroxyzine'),('BigPharma','Cough and runny nose','Ibuprofen'),('BigPharma','Cough and runny nose','Klonopin'),('naturalPharm','none','menthol'),('PharmMD','Dizziness, diarrhea, confusion, etc.','Metranolol'),('LundyCo.','blurred vision, low bp','minoxidil'),('BigPharma','Cough and runny nose','Pepto'),('Drug Inc.','Drowsiness, Irritability','Propranolol'),('BigPharma','Cough and runny nose','Tylenol'),('BigPharma','Cough and runny nose','Xanax');
/*!40000 ALTER TABLE `drugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `has`
--

DROP TABLE IF EXISTS `has`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `has` (
  `DoctorID` int NOT NULL,
  `NurseID` int NOT NULL,
  `PatientID` int NOT NULL,
  `AppointmentNumber` int NOT NULL,
  `PatientUser` varchar(255) DEFAULT NULL,
  `DocUser` varchar(255) DEFAULT NULL,
  `NurseUser` varchar(255) DEFAULT NULL,
  `TimeDate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DoctorID`,`NurseID`,`PatientID`,`AppointmentNumber`),
  UNIQUE KEY `PatientUser` (`PatientUser`,`DocUser`,`NurseUser`),
  KEY `NurseID` (`NurseID`),
  KEY `PatientID` (`PatientID`),
  KEY `AppointmentNumber` (`AppointmentNumber`),
  KEY `DocUser` (`DocUser`),
  KEY `NurseUser` (`NurseUser`),
  CONSTRAINT `has_ibfk_1` FOREIGN KEY (`DoctorID`) REFERENCES `doctor` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `has_ibfk_2` FOREIGN KEY (`NurseID`) REFERENCES `nurse` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `has_ibfk_3` FOREIGN KEY (`PatientID`) REFERENCES `patient` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `has_ibfk_4` FOREIGN KEY (`AppointmentNumber`) REFERENCES `appointment` (`AppointmentNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `has_ibfk_5` FOREIGN KEY (`DocUser`) REFERENCES `doctor` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `has_ibfk_6` FOREIGN KEY (`NurseUser`) REFERENCES `nurse` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `has_ibfk_7` FOREIGN KEY (`PatientUser`) REFERENCES `patient` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `has`
--

LOCK TABLES `has` WRITE;
/*!40000 ALTER TABLE `has` DISABLE KEYS */;
INSERT INTO `has` VALUES (215403,123,462380,3,'Chris_Breaux','Lakers248','userty','12:45,5/15/2022');
/*!40000 ALTER TABLE `has` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurse`
--

DROP TABLE IF EXISTS `nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse` (
  `ID` int NOT NULL,
  `Fname` varchar(255) DEFAULT NULL,
  `Lname` varchar(255) DEFAULT NULL,
  `ClinicName` varchar(255) DEFAULT NULL,
  `Specialty` varchar(255) DEFAULT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`,`Username`),
  UNIQUE KEY `Username` (`Username`),
  KEY `ClinicName` (`ClinicName`),
  CONSTRAINT `nurse_ibfk_1` FOREIGN KEY (`ClinicName`) REFERENCES `clinic` (`Name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse`
--

LOCK TABLES `nurse` WRITE;
/*!40000 ALTER TABLE `nurse` DISABLE KEYS */;
INSERT INTO `nurse` VALUES (3,'Ri','Lu','XClinic','Many of them','pas','word'),(123,'Jaa','Daniels','Oasis Clinic','Special Nurse','userty','passwordty'),(875587857,'User','Manual','XClinic','Emergency medicine','Example','password');
/*!40000 ALTER TABLE `nurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurseschedule`
--

DROP TABLE IF EXISTS `nurseschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurseschedule` (
  `ID` int NOT NULL,
  `Hours` int DEFAULT NULL,
  `VacatonDays` varchar(255) DEFAULT NULL,
  `Days` varchar(255) DEFAULT NULL,
  `Username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Username` (`Username`),
  CONSTRAINT `nurseschedule_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `nurse` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurseschedule`
--

LOCK TABLES `nurseschedule` WRITE;
/*!40000 ALTER TABLE `nurseschedule` DISABLE KEYS */;
INSERT INTO `nurseschedule` VALUES (3,10,'may 12','Tuesday-Saturday','pas'),(875587857,4,'none','Monday-Thursday','Example');
/*!40000 ALTER TABLE `nurseschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `ID` int NOT NULL,
  `Fname` varchar(255) DEFAULT NULL,
  `Lname` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `DoB` varchar(255) DEFAULT NULL,
  `Gender` varchar(255) DEFAULT NULL,
  `Adm_Flag` int DEFAULT NULL,
  `Out_Flag` int DEFAULT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`,`Username`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (11111,'Pat','Zero','Jar@33e','04/23/1999','Non-Binary',0,1,'user','pass'),(462380,'Frank','Ocean','jjjjjjjjj','04/13/5555','Male',0,1,'Chris_Breaux','ChannelOrange');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_address`
--

DROP TABLE IF EXISTS `patient_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_address` (
  `ID` int NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`,`ADDRESS`),
  CONSTRAINT `patient_address_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `patient` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_address`
--

LOCK TABLES `patient_address` WRITE;
/*!40000 ALTER TABLE `patient_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_conditions`
--

DROP TABLE IF EXISTS `patient_conditions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_conditions` (
  `ID` int NOT NULL,
  `Conditions` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`,`Conditions`),
  CONSTRAINT `patient_conditions_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `patient` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_conditions`
--

LOCK TABLES `patient_conditions` WRITE;
/*!40000 ALTER TABLE `patient_conditions` DISABLE KEYS */;
INSERT INTO `patient_conditions` VALUES (11111,'Routine Spinal Tap (2009) , Aertrial Bypass Surgery(08/2008)'),(462380,'Clubfoot , Major surgery');
/*!40000 ALTER TABLE `patient_conditions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_medicalhis`
--

DROP TABLE IF EXISTS `patient_medicalhis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_medicalhis` (
  `ID` int NOT NULL,
  `MedicalHistory` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`,`MedicalHistory`),
  CONSTRAINT `patient_medicalhis_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `patient` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_medicalhis`
--

LOCK TABLES `patient_medicalhis` WRITE;
/*!40000 ALTER TABLE `patient_medicalhis` DISABLE KEYS */;
INSERT INTO `patient_medicalhis` VALUES (11111,'Heart AttackBad Cough'),(462380,'Wheezing and dry mouth , Vertigo and cough');
/*!40000 ALTER TABLE `patient_medicalhis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacist`
--

DROP TABLE IF EXISTS `pharmacist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmacist` (
  `ID` int NOT NULL,
  `Fname` varchar(255) DEFAULT NULL,
  `Lname` varchar(255) DEFAULT NULL,
  `Supply` varchar(255) DEFAULT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`,`Username`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacist`
--

LOCK TABLES `pharmacist` WRITE;
/*!40000 ALTER TABLE `pharmacist` DISABLE KEYS */;
INSERT INTO `pharmacist` VALUES (56,'Jordan','Swiss','none','cat_nyr','eric'),(1234,'John','Doe','SupplyOne','JohnDoe123','mypass'),(12345,'jar','lan','SupplyO i','jr_l','123ref'),(9853107,'Pharmacist','Pharm','none','pharm_md','pharmacy');
/*!40000 ALTER TABLE `pharmacist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescribes`
--

DROP TABLE IF EXISTS `prescribes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescribes` (
  `ID` int NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Prescribed` varchar(255) DEFAULT NULL,
  `PatientID` varchar(255) DEFAULT NULL,
  `DoctorNotes` varchar(255) DEFAULT NULL,
  `Dosage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`,`Name`,`Username`),
  KEY `Name` (`Name`),
  KEY `Username` (`Username`),
  CONSTRAINT `prescribes_ibfk_1` FOREIGN KEY (`Name`) REFERENCES `drugs` (`Name`),
  CONSTRAINT `prescribes_ibfk_2` FOREIGN KEY (`Username`) REFERENCES `doctor` (`Username`),
  CONSTRAINT `prescribes_ibfk_3` FOREIGN KEY (`ID`) REFERENCES `doctor` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescribes`
--

LOCK TABLES `prescribes` WRITE;
/*!40000 ALTER TABLE `prescribes` DISABLE KEYS */;
INSERT INTO `prescribes` VALUES (123,'Klonopin','a','09/06/2017','11111','Take at night','890mg'),(123,'menthol','a','1/1/2000','11111','none','as needed'),(123,'Propranolol','a','6/12/2018','11111','Avoid Driving','100mg 2x daily'),(123,'Xanax','a','11/07/2018','11111','Take at night','400mg'),(215403,'Ativan','Lakers248','12/4/2022','11111','Call 911 if signs of stroke','100mg biweekly'),(215403,'Pepto','Lakers248','6/17/2022','11111','Take in morning','400mg daily');
/*!40000 ALTER TABLE `prescribes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receives`
--

DROP TABLE IF EXISTS `receives`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receives` (
  `ID` int NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Dosage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`,`Name`,`Username`),
  KEY `Name` (`Name`),
  KEY `Username` (`Username`),
  CONSTRAINT `receives_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `patient` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `receives_ibfk_2` FOREIGN KEY (`Name`) REFERENCES `drugs` (`Name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `receives_ibfk_3` FOREIGN KEY (`Username`) REFERENCES `patient` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receives`
--

LOCK TABLES `receives` WRITE;
/*!40000 ALTER TABLE `receives` DISABLE KEYS */;
/*!40000 ALTER TABLE `receives` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-07 21:29:51
