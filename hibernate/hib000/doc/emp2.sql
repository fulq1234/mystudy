/*
MySQL Data Transfer
Source Host: localhost
Source Database: feifei
Target Host: localhost
Target Database: feifei
Date: 2018/5/1 8:26:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for emp2
-- ----------------------------
DROP TABLE IF EXISTS `emp2`;
CREATE TABLE `emp2` (
  `empno` smallint(6) NOT NULL DEFAULT '0',
  `ename` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `job` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `sal` double(12,2) DEFAULT NULL,
  `comm` double(12,2) DEFAULT NULL,
  `deptno` tinyint(4) NOT NULL,
  PRIMARY KEY (`empno`),
  KEY `fk_deptno` (`deptno`),
  CONSTRAINT `fk_deptno` FOREIGN KEY (`deptno`) REFERENCES `dept` (`DEPTNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
