/*
SQLyog Ultimate v8.32 
MySQL - 5.1.33-community : Database - dms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dms`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `aid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `old` varchar(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `bid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

/*Table structure for table `announcement` */

DROP TABLE IF EXISTS `announcement`;

CREATE TABLE `announcement` (
  `bid` varchar(32) NOT NULL,
  `releasetime` varchar(20) DEFAULT NULL,
  `content` longtext,
  `aid` varchar(32) NOT NULL,
  `annid` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `announcement` */

/*Table structure for table `building` */

DROP TABLE IF EXISTS `building`;

CREATE TABLE `building` (
  `bid` varchar(32) NOT NULL,
  `number` int(10) DEFAULT NULL,
  `studentsex` varchar(10) DEFAULT NULL,
  `adminnumber` int(10) DEFAULT '0',
  `nowstudentnumber` int(20) DEFAULT '0',
  `studentnumber` int(20) DEFAULT '0',
  `dornumber` int(20) DEFAULT '0',
  `selectdormitory` int(10) DEFAULT '0',
  `changedormitory` varchar(10) DEFAULT '0',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `building` */

/*Table structure for table `checkdor` */

DROP TABLE IF EXISTS `checkdor`;

CREATE TABLE `checkdor` (
  `cid` varchar(32) NOT NULL,
  `sid` varchar(32) DEFAULT NULL,
  `dorid` varchar(32) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `integral` varchar(10) DEFAULT NULL,
  `starttime` date DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `checkdor` */

/*Table structure for table `dormitory` */

DROP TABLE IF EXISTS `dormitory`;

CREATE TABLE `dormitory` (
  `dorid` varchar(32) NOT NULL,
  `bid` varchar(32) DEFAULT NULL,
  `number` int(10) DEFAULT NULL,
  `nownumber` int(10) DEFAULT NULL,
  `num` varchar(20) DEFAULT NULL,
  `integral` int(10) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dormitory` */

/*Table structure for table `employer` */

DROP TABLE IF EXISTS `employer`;

CREATE TABLE `employer` (
  `eid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `old` varchar(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `state` int(10) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employer` */

/*Table structure for table `position` */

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
  `pid` varchar(32) NOT NULL,
  `sid` varchar(32) DEFAULT NULL,
  `dorid` varchar(32) DEFAULT NULL,
  `bid` varchar(32) DEFAULT NULL,
  `numbering` int(10) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `position` */

/*Table structure for table `repair` */

DROP TABLE IF EXISTS `repair`;

CREATE TABLE `repair` (
  `rid` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `state` int(10) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `overtime` datetime DEFAULT NULL,
  `dorid` varchar(32) DEFAULT NULL,
  `eid` varchar(32) DEFAULT NULL,
  `bid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `repair` */

/*Table structure for table `requestbean` */

DROP TABLE IF EXISTS `requestbean`;

CREATE TABLE `requestbean` (
  `rid` varchar(32) NOT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `rnum` varchar(32) DEFAULT NULL,
  `bernum` varchar(32) DEFAULT NULL,
  `bid` varchar(32) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `overtime` datetime DEFAULT NULL,
  `dorid` varchar(32) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `state` int(10) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `requestbean` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `sid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `leavetime` datetime DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `old` varchar(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `college` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `teacherphone` varchar(20) DEFAULT NULL,
  `dorid` varchar(32) DEFAULT NULL,
  `bid` varchar(32) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `year` int(10) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

/*Table structure for table `superadmin` */

DROP TABLE IF EXISTS `superadmin`;

CREATE TABLE `superadmin` (
  `said` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `old` varchar(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `superadmin` */

insert  into `superadmin`(`said`,`name`,`old`,`sex`) values ('DCF0E994BAE543C8AC234E5952A910F4','超级管理员','19','男');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`password`,`type`) values ('DCF0E994BAE543C8AC234E5952A910F4','admin','fcea920f7412b5da7be0cf42b8c93759','superadmin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
