/*
MySQL Data Transfer
Source Host: localhost
Source Database: feifei
Target Host: localhost
Target Database: feifei
Date: 2018/4/25 21:43:47
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `DEPTNO` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `DNAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `LOC` varchar(32) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `dept` VALUES ('10', '???', '??????');
INSERT INTO `dept` VALUES ('1', '中文', null);
INSERT INTO `dept` VALUES ('10', '研发部', '北京市海淀区');
