/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.16 : Database - toutiao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`toutiao` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `toutiao`;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `entity_id` int(11) NOT NULL,
  `entity_type` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `entity_index` (`entity_id`,`entity_type`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id`,`content`,`user_id`,`entity_id`,`entity_type`,`created_date`,`status`) values (1,'Comment 0',1,1,1,'2022-02-24 16:23:29',0),(2,'Comment 1',1,1,1,'2022-02-24 16:23:29',0),(3,'Comment 2',1,1,1,'2022-02-24 16:23:29',0),(4,'Comment 0',2,2,1,'2022-02-24 16:23:29',0),(5,'Comment 1',2,2,1,'2022-02-24 16:23:29',0),(6,'Comment 2',2,2,1,'2022-02-24 16:23:29',0),(7,'Comment 0',3,3,1,'2022-02-24 16:23:29',0),(8,'Comment 1',3,3,1,'2022-02-24 16:23:29',0),(9,'Comment 2',3,3,1,'2022-02-24 16:23:29',0),(10,'Comment 0',4,4,1,'2022-02-24 16:23:29',0),(11,'Comment 1',4,4,1,'2022-02-24 16:23:29',0),(12,'Comment 2',4,4,1,'2022-02-24 16:23:29',0),(13,'Comment 0',5,5,1,'2022-02-24 16:23:30',0),(14,'Comment 1',5,5,1,'2022-02-24 16:23:30',0),(15,'Comment 2',5,5,1,'2022-02-24 16:23:30',0),(16,'Comment 0',6,6,1,'2022-02-24 16:23:30',0),(17,'Comment 1',6,6,1,'2022-02-24 16:23:30',0),(18,'Comment 2',6,6,1,'2022-02-24 16:23:30',0),(19,'Comment 0',7,7,1,'2022-02-24 16:23:30',0),(20,'Comment 1',7,7,1,'2022-02-24 16:23:30',0),(21,'Comment 2',7,7,1,'2022-02-24 16:23:30',0),(22,'Comment 0',8,8,1,'2022-02-24 16:23:30',0),(23,'Comment 1',8,8,1,'2022-02-24 16:23:30',0),(24,'Comment 2',8,8,1,'2022-02-24 16:23:30',0),(25,'Comment 0',9,9,1,'2022-02-24 16:23:30',0),(26,'Comment 1',9,9,1,'2022-02-24 16:23:30',0),(27,'Comment 2',9,9,1,'2022-02-24 16:23:30',0),(28,'Comment 0',10,10,1,'2022-02-24 16:23:30',0),(29,'Comment 1',10,10,1,'2022-02-24 16:23:30',0),(30,'Comment 2',10,10,1,'2022-02-24 16:23:30',0);

/*Table structure for table `login_ticket` */

DROP TABLE IF EXISTS `login_ticket`;

CREATE TABLE `login_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `ticket` varchar(45) NOT NULL,
  `expired` datetime NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticket_UNIQUE` (`ticket`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `login_ticket` */

insert  into `login_ticket`(`id`,`user_id`,`ticket`,`expired`,`status`) values (1,1,'TICKET1','2022-02-24 16:23:29',2),(2,2,'TICKET2','2022-02-24 16:23:29',2),(3,3,'TICKET3','2022-02-24 16:23:29',2),(4,4,'TICKET4','2022-02-24 16:23:29',2),(5,5,'TICKET5','2022-02-24 16:23:30',2),(6,6,'TICKET6','2022-02-24 16:23:30',2),(7,7,'TICKET7','2022-02-24 16:23:30',2),(8,8,'TICKET8','2022-02-24 16:23:30',2),(9,9,'TICKET9','2022-02-24 16:23:30',2),(10,10,'TICKET10','2022-02-24 16:23:30',2),(11,11,'72ae58aa8f6f4d238f5f0bc797aceb53','2022-02-26 14:45:50',0);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) DEFAULT NULL,
  `to_id` int(11) DEFAULT NULL,
  `content` text,
  `created_date` datetime DEFAULT NULL,
  `has_read` int(11) DEFAULT NULL,
  `conversation_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conversation_index` (`conversation_id`),
  KEY `created_date` (`created_date`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`from_id`,`to_id`,`content`,`created_date`,`has_read`,`conversation_id`) values (1,3,11,'用户qq赞了你的资讯,http://127.0.0.1:8080/news/10','2022-02-25 14:47:10',0,'3_11');

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL DEFAULT '',
  `link` varchar(256) NOT NULL DEFAULT '',
  `image` varchar(256) NOT NULL DEFAULT '',
  `like_count` int(11) NOT NULL,
  `comment_count` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `news` */

insert  into `news`(`id`,`title`,`link`,`image`,`like_count`,`comment_count`,`created_date`,`user_id`) values (1,'TITLE{0}','http://www.nowcoder.com/0.html','http://images.nowcoder.com/head/485m.png',1,0,'2022-02-24 16:23:29',1),(2,'TITLE{1}','http://www.nowcoder.com/1.html','http://images.nowcoder.com/head/546m.png',2,0,'2022-02-24 21:23:29',2),(3,'TITLE{2}','http://www.nowcoder.com/2.html','http://images.nowcoder.com/head/863m.png',3,0,'2022-02-25 02:23:29',3),(4,'TITLE{3}','http://www.nowcoder.com/3.html','http://images.nowcoder.com/head/687m.png',4,0,'2022-02-25 07:23:29',4),(5,'TITLE{4}','http://www.nowcoder.com/4.html','http://images.nowcoder.com/head/288m.png',5,0,'2022-02-25 12:23:30',5),(6,'TITLE{5}','http://www.nowcoder.com/5.html','http://images.nowcoder.com/head/751m.png',6,0,'2022-02-25 17:23:30',6),(7,'TITLE{6}','http://www.nowcoder.com/6.html','http://images.nowcoder.com/head/221m.png',7,0,'2022-02-25 22:23:30',7),(8,'TITLE{7}','http://www.nowcoder.com/7.html','http://images.nowcoder.com/head/352m.png',8,0,'2022-02-26 03:23:30',8),(9,'TITLE{8}','http://www.nowcoder.com/8.html','http://images.nowcoder.com/head/938m.png',9,0,'2022-02-26 08:23:30',9),(10,'TITLE{9}','http://www.nowcoder.com/9.html','http://images.nowcoder.com/head/295m.png',2,0,'2022-02-26 13:23:30',10);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(128) NOT NULL DEFAULT '',
  `salt` varchar(32) NOT NULL DEFAULT '',
  `head_url` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`salt`,`head_url`) values (2,'user1','newpassword','aa','http://images.nowcoder.com/head/975t.png'),(3,'user2','newpassword','aa','http://images.nowcoder.com/head/434t.png'),(4,'user3','newpassword','aa','http://images.nowcoder.com/head/130t.png'),(5,'user4','newpassword','aa','http://images.nowcoder.com/head/587t.png'),(6,'user5','newpassword','aa','http://images.nowcoder.com/head/827t.png'),(7,'user6','newpassword','aa','http://images.nowcoder.com/head/697t.png'),(8,'user7','newpassword','aa','http://images.nowcoder.com/head/431t.png'),(9,'user8','newpassword','aa','http://images.nowcoder.com/head/561t.png'),(10,'user9','newpassword','aa','http://images.nowcoder.com/head/675t.png'),(11,'qq','2C40C0E39E988A9584A651CD79F96908','ed4e2','http://images.nowcoder.com/head/191t.png');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
