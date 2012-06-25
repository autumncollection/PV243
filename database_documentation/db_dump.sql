-- MySQL dump 10.13  Distrib 5.1.54, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: PV243
-- ------------------------------------------------------
-- Server version	5.1.54-1ubuntu4

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
-- Table structure for table `Actor`
--

DROP TABLE IF EXISTS `Actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Actor` (
  `idActor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPerson` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idActor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Actor`
--

LOCK TABLES `Actor` WRITE;
/*!40000 ALTER TABLE `Actor` DISABLE KEYS */;
INSERT INTO `Actor` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,7),(7,8);
/*!40000 ALTER TABLE `Actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ActorAtMovie`
--

DROP TABLE IF EXISTS `ActorAtMovie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ActorAtMovie` (
  `idActorAtMovie` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idMovie` int(10) unsigned NOT NULL,
  `idActor` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idActorAtMovie`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActorAtMovie`
--

LOCK TABLES `ActorAtMovie` WRITE;
/*!40000 ALTER TABLE `ActorAtMovie` DISABLE KEYS */;
INSERT INTO `ActorAtMovie` VALUES (1,1,2),(2,1,3),(3,2,4),(4,2,5),(5,3,4),(6,3,5),(7,4,6),(8,4,7);
/*!40000 ALTER TABLE `ActorAtMovie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Director`
--

DROP TABLE IF EXISTS `Director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Director` (
  `idDector` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPerson` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idDector`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Director`
--

LOCK TABLES `Director` WRITE;
/*!40000 ALTER TABLE `Director` DISABLE KEYS */;
INSERT INTO `Director` VALUES (1,1),(2,6);
/*!40000 ALTER TABLE `Director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DirectorAtMovie`
--

DROP TABLE IF EXISTS `DirectorAtMovie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DirectorAtMovie` (
  `idActorAtMovie` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idMovie` int(10) unsigned NOT NULL,
  `idDirector` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idActorAtMovie`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DirectorAtMovie`
--

LOCK TABLES `DirectorAtMovie` WRITE;
/*!40000 ALTER TABLE `DirectorAtMovie` DISABLE KEYS */;
INSERT INTO `DirectorAtMovie` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,4,2);
/*!40000 ALTER TABLE `DirectorAtMovie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movie`
--

DROP TABLE IF EXISTS `Movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Movie` (
  `idMovie` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `movieName` varchar(255) NOT NULL,
  `movieDate` datetime DEFAULT NULL,
  `length` int(10) unsigned DEFAULT NULL,
  `description` text,
  `image` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`idMovie`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movie`
--

LOCK TABLES `Movie` WRITE;
/*!40000 ALTER TABLE `Movie` DISABLE KEYS */;
INSERT INTO `Movie` VALUES (1,'Hanebný pancharti',NULL,153,'Bylo nebylo v nacisty okupované Francii… Vítejte ve Druhé sv?tové válce podle Quentina Tarantina. Vítejte ve sv?t?, kde historická realita ustupuje lehce brutálnímu pohádkovému vypráv?ní jednoho z nejkontroverzn?jších režisér? sou?asnosti. ','http://screencrave.com/wp-content/uploads/2009/06/basterds09-6-16-header.jpg'),(2,'Kill Bill',NULL,111,'Bývalá ?lenka špi?kového zabijáckého komanda (Uma Thurman) se rozhodne navždy skon?it s minulostí a vdát se. Její svatební den se však zm?ní v krvavá jatka v okamžiku, kdy na ni zaúto?í její bývalý šéf Bill (David Carradine). ','http://killbill.vstup.net/galerie/kb1/9/large/1kb_9_17_large.jpg'),(3,'Kill Bill 2',NULL,136,'Svatební ob?ad bývalé ?lenky Komanda Zmijí Zabiják?, ?erné Mamby, zm?nil její pomstychtivý ex-milenec Bill s bývalými kumpány v krvavý masakr. T?hotná Nev?sta však kulku vpálenou do hlavy p?ežila.','http://www.impawards.com/2004/posters/kill_bill_vol_two_ver11.jpg'),(4,'Sin City',NULL,125,'in City - M?sto h?íchu. John Hartigan (Bruce Willis), jeden z mála nezkorumpovaných policist? v tomto m?st?, se poslední noc své služby snaží zachránit Nancy Callahanovou, v?k 11 let, z rukou Juniora (Nick Stahl).','http://images2.fanpop.com/images/photos/7000000/Sin-City-benicio-del-toro-7052785-640-480.jpg');
/*!40000 ALTER TABLE `Movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `idPerson` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `birthdate` datetime DEFAULT NULL,
  `image` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`idPerson`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES (1,'Quentin','Tarantino','1963-06-25 23:21:51','http://www.studentpoint.cz/files/film/quentin3.jpg'),(2,'Pitt','Brad','1963-06-25 23:22:12','http://images5.fanpop.com/image/photos/25200000/Brad-brad-pitt-25214444-620-430.jpg'),(3,'Mélanie','Laurent','1983-06-25 23:22:31','http://www.collider.com/wp-content/uploads/Melanie-Laurent-2.jpg'),(4,'Uma','Thurman','1970-06-25 23:22:39','http://gallery.celebritypro.com/data/media/17/uma-thurman-virgin-media-2.jpg'),(5,'Lucy','Liu','1968-06-25 23:23:12','http://www.tvequals.com/wp-content/uploads/2010/11/MARRY-ME-Lifetime-10-550x366.jpg'),(6,'Robert','Rodriguez','1968-06-25 23:23:20','http://welivefilm.com/wp-content/uploads/2012/04/Robert_Rodriguez_poses_for_the_camera_jpg_800x1000_q100.jpg'),(7,'Jessica','Alba','1981-06-25 23:23:42','http://nd01.jxs.cz/817/746/b69be714ff_42042155_o2.jpg'),(8,'Bruce','Willis','1955-06-25 23:24:01','http://www.chartmovieinfo.com/wp-content/uploads/2010/11/bruce-willis-red1-580x386.jpg');
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `idRole` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'admin'),(2,'moderator'),(3,'user');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `idUser` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `idRole` tinyint(3) unsigned NOT NULL,
  `salt` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'sezimatomas@gmail.com','d6917737a086955aecbb041ddaf56dd7',3,'rOYNP#mcBJnCKGo');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-25 23:32:07
