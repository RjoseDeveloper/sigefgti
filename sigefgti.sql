/*
Navicat MySQL Data Transfer

Source Server         : data_base
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : sigefgti

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-05-23 09:07:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `idactivity` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idactivity`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of activity
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `iddepartment` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `dateadded` date DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iddepartment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for `event`
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `idevent` int(11) NOT NULL AUTO_INCREMENT,
  `details` varchar(255) DEFAULT NULL,
  `starttime` varchar(100) DEFAULT NULL,
  `endtime` varchar(100) DEFAULT NULL,
  `dateadded` date DEFAULT NULL,
  `idactivity` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `relevance` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idevent`),
  KEY `idactivity` (`idactivity`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`idactivity`) REFERENCES `activity` (`idactivity`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of event
-- ----------------------------

-- ----------------------------
-- Table structure for `participant`
-- ----------------------------
DROP TABLE IF EXISTS `participant`;
CREATE TABLE `participant` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `idevents` int(11) NOT NULL,
  `datetaken` date DEFAULT NULL,
  `researchfields` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iduser`,`idevents`),
  KEY `idevents` (`idevents`),
  CONSTRAINT `participant_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  CONSTRAINT `participant_ibfk_2` FOREIGN KEY (`idevents`) REFERENCES `event` (`idevent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of participant
-- ----------------------------

-- ----------------------------
-- Table structure for `responsible`
-- ----------------------------
DROP TABLE IF EXISTS `responsible`;
CREATE TABLE `responsible` (
  `iduser` int(11) NOT NULL,
  `idevent` int(11) NOT NULL,
  `datetaken` date DEFAULT NULL,
  `biograph` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iduser`,`idevent`),
  KEY `idevent` (`idevent`),
  CONSTRAINT `responsible_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  CONSTRAINT `responsible_ibfk_2` FOREIGN KEY (`idevent`) REFERENCES `event` (`idevent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of responsible
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `idrole` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` char(255) DEFAULT NULL,
  `iddepartment` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact1` varchar(255) DEFAULT NULL,
  `contact2` varchar(255) DEFAULT NULL,
  `idrole` int(11) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  KEY `iddepartment` (`iddepartment`),
  KEY `idrole` (`idrole`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`iddepartment`) REFERENCES `department` (`iddepartment`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`idrole`) REFERENCES `role` (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
