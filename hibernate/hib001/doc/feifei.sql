/*
MySQL Data Transfer
Source Host: localhost
Source Database: feifei
Target Host: localhost
Target Database: feifei
Date: 2018/5/1 21:16:22
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `number` int(11) DEFAULT NULL COMMENT '人数',
  `allcomputer` int(11) DEFAULT NULL COMMENT '是否都有笔记本 0没有 1 都有',
  `status` int(11) DEFAULT NULL COMMENT '状态-1不在 0在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `DEPTNO` tinyint(32) NOT NULL AUTO_INCREMENT,
  `DNAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `LOC` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`DEPTNO`),
  KEY `DEPTNO` (`DEPTNO`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `empname` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `empno` int(11) NOT NULL DEFAULT '0',
  `hireDate` date DEFAULT NULL,
  `job` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `salary` double(12,1) DEFAULT NULL,
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
  `deptno` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`empno`),
  KEY `fk_deptno` (`deptno`),
  CONSTRAINT `fk_deptno` FOREIGN KEY (`deptno`) REFERENCES `dept` (`DEPTNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `empid` smallint(6) NOT NULL DEFAULT '0',
  `empname` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='雇员表';

-- ----------------------------
-- Table structure for keci
-- ----------------------------
DROP TABLE IF EXISTS `keci`;
CREATE TABLE `keci` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '时间段 8：30-10：10',
  `order` int(11) DEFAULT NULL COMMENT '第几节课 1',
  `keshi` int(11) DEFAULT '2' COMMENT '属于多少课时 默认2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for proemp
-- ----------------------------
DROP TABLE IF EXISTS `proemp`;
CREATE TABLE `proemp` (
  `rproid` smallint(6) DEFAULT NULL COMMENT '项目表(PROGECT)的主键',
  `rempid` smallint(6) DEFAULT NULL COMMENT '雇员表(EMPLOTEE)的主键值',
  KEY `fk_proid` (`rproid`),
  KEY `fk_empid` (`rempid`),
  CONSTRAINT `fk_proid` FOREIGN KEY (`rproid`) REFERENCES `project` (`proid`),
  CONSTRAINT `fk_empid` FOREIGN KEY (`rempid`) REFERENCES `employee` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='一个项目可以有多个雇员。\r\n一个雇员也可以参加多个项目。项目和雇员就是多对多关系\r\n他们不能直接产生关系，需要一个关系表';

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `proid` smallint(6) NOT NULL DEFAULT '0',
  `proname` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`proid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目表';

-- ----------------------------
-- Table structure for ssmds_role
-- ----------------------------
DROP TABLE IF EXISTS `ssmds_role`;
CREATE TABLE `ssmds_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `roleCode` varchar(15) DEFAULT NULL COMMENT '角色编码',
  `roleName` varchar(15) DEFAULT NULL COMMENT '角色名称',
  `createdBy` int(20) DEFAULT NULL COMMENT '创建者',
  `creationDate` date DEFAULT NULL COMMENT '创建时间',
  `modifyBy` int(20) DEFAULT NULL COMMENT '修改者',
  `modifyDate` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ssmds_user
-- ----------------------------
DROP TABLE IF EXISTS `ssmds_user`;
CREATE TABLE `ssmds_user` (
  `userId` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userCode` varchar(15) DEFAULT NULL COMMENT '用户编码',
  `userName` varchar(15) DEFAULT NULL COMMENT '用户名称',
  `userPwd` varchar(15) DEFAULT NULL COMMENT '用户密码',
  `gender` int(10) DEFAULT NULL COMMENT '性别（1:女、 2:男）',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机',
  `address` varchar(30) DEFAULT NULL COMMENT '地址',
  `userRole` int(10) DEFAULT NULL COMMENT '用户角色（取自角色表-角色id）',
  `createdBy` int(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` date DEFAULT NULL COMMENT '创建时间',
  `modifyBy` int(20) DEFAULT NULL COMMENT '更新者（userId）',
  `modifyDate` date DEFAULT NULL COMMENT '更新时间',
  `idPicPath` varchar(200) DEFAULT NULL COMMENT '证件照路径（头像）',
  `workPicPath` varchar(200) DEFAULT NULL COMMENT '工作证照片路径',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_course
-- ----------------------------
DROP TABLE IF EXISTS `tbl_course`;
CREATE TABLE `tbl_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `time` int(11) DEFAULT NULL COMMENT '课时',
  `order` int(11) DEFAULT NULL COMMENT '顺序',
  `status` int(11) DEFAULT NULL COMMENT '属性 -1 课程不上了 0专业课讲课 1 专业课上机 2 符合零和一 3 其它',
  `tcher_sts` int(11) DEFAULT NULL COMMENT '老师属性 0教员 1导员 2公共课 3招办 4其它',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for tbl_plan
-- ----------------------------
DROP TABLE IF EXISTS `tbl_plan`;
CREATE TABLE `tbl_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseid` int(11) DEFAULT NULL COMMENT '课程id',
  `coursenname` int(11) NOT NULL COMMENT '课程名称',
  `teacherid` int(11) DEFAULT NULL COMMENT '教员id',
  `classid` int(11) DEFAULT NULL COMMENT '班级id',
  `roomid` int(11) NOT NULL COMMENT '教室id',
  `fixed_week` int(11) DEFAULT NULL COMMENT '指定第几周',
  `fixed_keci` int(11) DEFAULT NULL COMMENT '指定课次',
  `ispaike` int(11) NOT NULL DEFAULT '0' COMMENT '是否已经排课 默认0 0否 1是',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 默认1 1启用 0禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for tbl_rel_class_course
-- ----------------------------
DROP TABLE IF EXISTS `tbl_rel_class_course`;
CREATE TABLE `tbl_rel_class_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classid` int(11) DEFAULT NULL COMMENT '班级id 指向班级表 id',
  `courseid` int(11) DEFAULT NULL COMMENT '课程id 指向课程表 id',
  `status` int(11) DEFAULT NULL COMMENT '状态 0没开始 1正在进行 2 已结束',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for tbl_relastion_class_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tbl_relastion_class_teacher`;
CREATE TABLE `tbl_relastion_class_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classid` int(11) DEFAULT NULL COMMENT '班级id 指向班级表id',
  `teacherid` int(11) DEFAULT NULL COMMENT '老师 id  指向老师表id',
  `status` int(11) DEFAULT '1' COMMENT '状态1 还在教课 0没有教课； 默认为1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for tbl_relation_core
-- ----------------------------
DROP TABLE IF EXISTS `tbl_relation_core`;
CREATE TABLE `tbl_relation_core` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `week` int(11) DEFAULT NULL COMMENT '周几',
  `date` varchar(255) DEFAULT NULL COMMENT '日期',
  `roomid` int(11) DEFAULT NULL COMMENT '教室id',
  `keciid` int(11) DEFAULT NULL COMMENT '课次id',
  `courseid` int(11) DEFAULT NULL COMMENT '课程id',
  `teacherid` int(11) DEFAULT NULL COMMENT '教员id',
  `classid` int(11) DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for tbl_room
-- ----------------------------
DROP TABLE IF EXISTS `tbl_room`;
CREATE TABLE `tbl_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `capacity` int(50) DEFAULT NULL COMMENT '人数上限  这个教室可以容纳多少人 -1没有限制',
  `status` int(11) DEFAULT NULL COMMENT '属性 -1不能使用 0 专业课讲课 1 专业课上机 2符合零和一 有机器 3符合零和一没机器 10其它',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `status` int(11) DEFAULT NULL COMMENT '属性 -1 不在 0教员 1导员 2公共课 3 招办 4 其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `class` VALUES ('1', '1701', '22', '1', '0');
INSERT INTO `class` VALUES ('2', '1702', '30', '1', '0');
INSERT INTO `class` VALUES ('3', '1703', '30', '0', '0');
INSERT INTO `class` VALUES ('4', '1704', '30', '0', '0');
INSERT INTO `class` VALUES ('5', '1705', '30', '0', '0');
INSERT INTO `class` VALUES ('6', '1706', '30', '0', '0');
INSERT INTO `class` VALUES ('7', '1707', '30', '0', '0');
INSERT INTO `class` VALUES ('8', '1708', '30', '0', '0');
INSERT INTO `class` VALUES ('9', '1801', '30', '0', '0');
INSERT INTO `dept` VALUES ('1', '研发部', '石家庄裕华区');
INSERT INTO `dept` VALUES ('80', 'dept001', '北京');
INSERT INTO `dept` VALUES ('82', 'dept003', '宁波');
INSERT INTO `dept` VALUES ('83', 'dept004', '宁波');
INSERT INTO `emp` VALUES ('new Emp2', '1', null, null, null);
INSERT INTO `emp` VALUES ('new Emp444', '2', null, null, null);
INSERT INTO `emp` VALUES ('周小二', '3', '1982-09-01', '1', '3333.0');
INSERT INTO `emp2` VALUES ('0', '1222', null, null, null, null, null, '1');
INSERT INTO `emp2` VALUES ('1', 'dp1 e2', null, null, null, null, null, null);
INSERT INTO `emp2` VALUES ('2', 'dp1 e1', null, null, null, null, null, null);
INSERT INTO `emp2` VALUES ('3', 'dp1 e2', null, null, null, null, null, '82');
INSERT INTO `emp2` VALUES ('4', 'dp1 e1', null, null, null, null, null, '82');
INSERT INTO `emp2` VALUES ('5', 'dp1 e2', null, null, null, null, null, '83');
INSERT INTO `emp2` VALUES ('6', 'dp1 e1', null, null, null, null, null, '83');
INSERT INTO `employee` VALUES ('3', '王五');
INSERT INTO `employee` VALUES ('4', '赵六');
INSERT INTO `keci` VALUES ('1', '8:30-10:10', '1', '2');
INSERT INTO `keci` VALUES ('2', '10:20-120', '2', '2');
INSERT INTO `keci` VALUES ('3', '14:00-15:40', '3', '2');
INSERT INTO `keci` VALUES ('4', '16:00-17:40', '4', '2');
INSERT INTO `keci` VALUES ('5', '19:00-12:40', '5', '2');
INSERT INTO `proemp` VALUES ('3', '3');
INSERT INTO `proemp` VALUES ('3', '4');
INSERT INTO `proemp` VALUES ('4', '3');
INSERT INTO `project` VALUES ('3', '3号项目');
INSERT INTO `project` VALUES ('4', '4号项目');
INSERT INTO `ssmds_role` VALUES ('1', 'SMBMS_ADMIN', '系统管理员', '1', '2016-04-13', null, null);
INSERT INTO `ssmds_user` VALUES ('1', 'admin', 'admin', 'admin', '1', '1983-10-10', '13688889999', '北京市海淀区成府路207号', '1', '1', '2013-03-21', null, null, null, null);
INSERT INTO `ssmds_user` VALUES ('7', '123456', '123456', '123456789', '1', '2018-04-16', '15932116119', '是打发士大夫', '1', '1', '2018-04-21', null, null, null, null);
INSERT INTO `tbl_course` VALUES ('1', 'STB-1', '4', '2', '1', '0');
INSERT INTO `tbl_course` VALUES ('2', 'STB-2', '4', '2', '2', '0');
INSERT INTO `tbl_course` VALUES ('3', 'STB-3', '4', '2', '3', '0');
INSERT INTO `tbl_course` VALUES ('4', 'STB-4', '4', '2', '4', '0');
INSERT INTO `tbl_course` VALUES ('5', 'STB-5', '4', '2', '5', '0');
INSERT INTO `tbl_course` VALUES ('6', '毛概', null, '3', null, '2');
INSERT INTO `tbl_course` VALUES ('7', '体育', null, '3', null, '2');
INSERT INTO `tbl_course` VALUES ('8', '自习课', null, '3', null, null);
INSERT INTO `tbl_course` VALUES ('9', '知识获取有方法', null, '0', null, '1');
INSERT INTO `tbl_course` VALUES ('10', 'jQuery-1', '4', '2', '1', '0');
INSERT INTO `tbl_course` VALUES ('11', 'jQuery-2', '4', '2', '2', '0');
INSERT INTO `tbl_course` VALUES ('12', 'jQuery-3', '4', '2', '3', '0');
INSERT INTO `tbl_course` VALUES ('13', 'jQuery-4', '4', '2', '4', '0');
INSERT INTO `tbl_course` VALUES ('14', 'jQuery-5', '4', '2', '5', '0');
INSERT INTO `tbl_rel_class_course` VALUES ('50', '1', '1', null);
INSERT INTO `tbl_rel_class_course` VALUES ('51', '2', '1', null);
INSERT INTO `tbl_rel_class_course` VALUES ('52', '3', '1', null);
INSERT INTO `tbl_rel_class_course` VALUES ('53', '4', '1', null);
INSERT INTO `tbl_rel_class_course` VALUES ('54', '5', '1', null);
INSERT INTO `tbl_rel_class_course` VALUES ('55', '6', '1', null);
INSERT INTO `tbl_rel_class_course` VALUES ('56', '7', '1', null);
INSERT INTO `tbl_rel_class_course` VALUES ('57', '8', '1', null);
INSERT INTO `tbl_rel_class_course` VALUES ('58', '9', '1', null);
INSERT INTO `tbl_rel_class_course` VALUES ('59', '1', '2', null);
INSERT INTO `tbl_rel_class_course` VALUES ('60', '2', '2', null);
INSERT INTO `tbl_rel_class_course` VALUES ('61', '3', '2', null);
INSERT INTO `tbl_rel_class_course` VALUES ('62', '4', '2', null);
INSERT INTO `tbl_rel_class_course` VALUES ('63', '5', '2', null);
INSERT INTO `tbl_rel_class_course` VALUES ('64', '6', '2', null);
INSERT INTO `tbl_rel_class_course` VALUES ('65', '7', '2', null);
INSERT INTO `tbl_rel_class_course` VALUES ('66', '8', '2', null);
INSERT INTO `tbl_rel_class_course` VALUES ('67', '9', '2', null);
INSERT INTO `tbl_rel_class_course` VALUES ('68', '1', '3', null);
INSERT INTO `tbl_rel_class_course` VALUES ('69', '2', '3', null);
INSERT INTO `tbl_rel_class_course` VALUES ('70', '3', '3', null);
INSERT INTO `tbl_rel_class_course` VALUES ('71', '4', '3', null);
INSERT INTO `tbl_rel_class_course` VALUES ('72', '5', '3', null);
INSERT INTO `tbl_rel_class_course` VALUES ('73', '6', '3', null);
INSERT INTO `tbl_rel_class_course` VALUES ('74', '7', '3', null);
INSERT INTO `tbl_rel_class_course` VALUES ('75', '8', '3', null);
INSERT INTO `tbl_rel_class_course` VALUES ('76', '9', '3', null);
INSERT INTO `tbl_rel_class_course` VALUES ('77', '1', '4', null);
INSERT INTO `tbl_rel_class_course` VALUES ('78', '2', '4', null);
INSERT INTO `tbl_rel_class_course` VALUES ('79', '3', '4', null);
INSERT INTO `tbl_rel_class_course` VALUES ('80', '4', '4', null);
INSERT INTO `tbl_rel_class_course` VALUES ('81', '5', '4', null);
INSERT INTO `tbl_rel_class_course` VALUES ('82', '6', '4', null);
INSERT INTO `tbl_rel_class_course` VALUES ('83', '7', '4', null);
INSERT INTO `tbl_rel_class_course` VALUES ('84', '8', '4', null);
INSERT INTO `tbl_rel_class_course` VALUES ('85', '9', '4', null);
INSERT INTO `tbl_rel_class_course` VALUES ('86', '1', '5', null);
INSERT INTO `tbl_rel_class_course` VALUES ('87', '2', '5', null);
INSERT INTO `tbl_rel_class_course` VALUES ('88', '3', '5', null);
INSERT INTO `tbl_rel_class_course` VALUES ('89', '4', '5', null);
INSERT INTO `tbl_rel_class_course` VALUES ('90', '5', '5', null);
INSERT INTO `tbl_rel_class_course` VALUES ('91', '6', '5', null);
INSERT INTO `tbl_rel_class_course` VALUES ('92', '7', '5', null);
INSERT INTO `tbl_rel_class_course` VALUES ('93', '8', '5', null);
INSERT INTO `tbl_rel_class_course` VALUES ('94', '9', '5', null);
INSERT INTO `tbl_rel_class_course` VALUES ('95', '1', '6', null);
INSERT INTO `tbl_rel_class_course` VALUES ('96', '2', '6', null);
INSERT INTO `tbl_rel_class_course` VALUES ('97', '3', '6', null);
INSERT INTO `tbl_rel_class_course` VALUES ('98', '4', '6', null);
INSERT INTO `tbl_rel_class_course` VALUES ('99', '5', '6', null);
INSERT INTO `tbl_rel_class_course` VALUES ('100', '6', '6', null);
INSERT INTO `tbl_rel_class_course` VALUES ('101', '7', '6', null);
INSERT INTO `tbl_rel_class_course` VALUES ('102', '8', '6', null);
INSERT INTO `tbl_rel_class_course` VALUES ('103', '9', '6', null);
INSERT INTO `tbl_rel_class_course` VALUES ('104', '1', '7', null);
INSERT INTO `tbl_rel_class_course` VALUES ('105', '2', '7', null);
INSERT INTO `tbl_rel_class_course` VALUES ('106', '3', '7', null);
INSERT INTO `tbl_rel_class_course` VALUES ('107', '4', '7', null);
INSERT INTO `tbl_rel_class_course` VALUES ('108', '5', '7', null);
INSERT INTO `tbl_rel_class_course` VALUES ('109', '6', '7', null);
INSERT INTO `tbl_rel_class_course` VALUES ('110', '7', '7', null);
INSERT INTO `tbl_rel_class_course` VALUES ('111', '8', '7', null);
INSERT INTO `tbl_rel_class_course` VALUES ('112', '9', '7', null);
INSERT INTO `tbl_rel_class_course` VALUES ('113', '1', '8', null);
INSERT INTO `tbl_rel_class_course` VALUES ('114', '2', '8', null);
INSERT INTO `tbl_rel_class_course` VALUES ('115', '3', '8', null);
INSERT INTO `tbl_rel_class_course` VALUES ('116', '4', '8', null);
INSERT INTO `tbl_rel_class_course` VALUES ('117', '5', '8', null);
INSERT INTO `tbl_rel_class_course` VALUES ('118', '6', '8', null);
INSERT INTO `tbl_rel_class_course` VALUES ('119', '7', '8', null);
INSERT INTO `tbl_rel_class_course` VALUES ('120', '8', '8', null);
INSERT INTO `tbl_rel_class_course` VALUES ('121', '9', '8', null);
INSERT INTO `tbl_rel_class_course` VALUES ('122', '1', '9', null);
INSERT INTO `tbl_rel_class_course` VALUES ('123', '2', '9', null);
INSERT INTO `tbl_rel_class_course` VALUES ('124', '3', '9', null);
INSERT INTO `tbl_rel_class_course` VALUES ('125', '4', '9', null);
INSERT INTO `tbl_rel_class_course` VALUES ('126', '5', '9', null);
INSERT INTO `tbl_rel_class_course` VALUES ('127', '6', '9', null);
INSERT INTO `tbl_rel_class_course` VALUES ('128', '7', '9', null);
INSERT INTO `tbl_rel_class_course` VALUES ('129', '8', '9', null);
INSERT INTO `tbl_rel_class_course` VALUES ('130', '9', '9', null);
INSERT INTO `tbl_rel_class_course` VALUES ('131', '1', '10', null);
INSERT INTO `tbl_rel_class_course` VALUES ('132', '2', '10', null);
INSERT INTO `tbl_rel_class_course` VALUES ('133', '3', '10', null);
INSERT INTO `tbl_rel_class_course` VALUES ('134', '4', '10', null);
INSERT INTO `tbl_rel_class_course` VALUES ('135', '5', '10', null);
INSERT INTO `tbl_rel_class_course` VALUES ('136', '6', '10', null);
INSERT INTO `tbl_rel_class_course` VALUES ('137', '7', '10', null);
INSERT INTO `tbl_rel_class_course` VALUES ('138', '8', '10', null);
INSERT INTO `tbl_rel_class_course` VALUES ('139', '9', '10', null);
INSERT INTO `tbl_rel_class_course` VALUES ('140', '1', '11', null);
INSERT INTO `tbl_rel_class_course` VALUES ('141', '2', '11', null);
INSERT INTO `tbl_rel_class_course` VALUES ('142', '3', '11', null);
INSERT INTO `tbl_rel_class_course` VALUES ('143', '4', '11', null);
INSERT INTO `tbl_rel_class_course` VALUES ('144', '5', '11', null);
INSERT INTO `tbl_rel_class_course` VALUES ('145', '6', '11', null);
INSERT INTO `tbl_rel_class_course` VALUES ('146', '7', '11', null);
INSERT INTO `tbl_rel_class_course` VALUES ('147', '8', '11', null);
INSERT INTO `tbl_rel_class_course` VALUES ('148', '9', '11', null);
INSERT INTO `tbl_rel_class_course` VALUES ('149', '1', '12', null);
INSERT INTO `tbl_rel_class_course` VALUES ('150', '2', '12', null);
INSERT INTO `tbl_rel_class_course` VALUES ('151', '3', '12', null);
INSERT INTO `tbl_rel_class_course` VALUES ('152', '4', '12', null);
INSERT INTO `tbl_rel_class_course` VALUES ('153', '5', '12', null);
INSERT INTO `tbl_rel_class_course` VALUES ('154', '6', '12', null);
INSERT INTO `tbl_rel_class_course` VALUES ('155', '7', '12', null);
INSERT INTO `tbl_rel_class_course` VALUES ('156', '8', '12', null);
INSERT INTO `tbl_rel_class_course` VALUES ('157', '9', '12', null);
INSERT INTO `tbl_rel_class_course` VALUES ('158', '1', '13', null);
INSERT INTO `tbl_rel_class_course` VALUES ('159', '2', '13', null);
INSERT INTO `tbl_rel_class_course` VALUES ('160', '3', '13', null);
INSERT INTO `tbl_rel_class_course` VALUES ('161', '4', '13', null);
INSERT INTO `tbl_rel_class_course` VALUES ('162', '5', '13', null);
INSERT INTO `tbl_rel_class_course` VALUES ('163', '6', '13', null);
INSERT INTO `tbl_rel_class_course` VALUES ('164', '7', '13', null);
INSERT INTO `tbl_rel_class_course` VALUES ('165', '8', '13', null);
INSERT INTO `tbl_rel_class_course` VALUES ('166', '9', '13', null);
INSERT INTO `tbl_rel_class_course` VALUES ('167', '1', '14', null);
INSERT INTO `tbl_rel_class_course` VALUES ('168', '2', '14', null);
INSERT INTO `tbl_rel_class_course` VALUES ('169', '3', '14', null);
INSERT INTO `tbl_rel_class_course` VALUES ('170', '4', '14', null);
INSERT INTO `tbl_rel_class_course` VALUES ('171', '5', '14', null);
INSERT INTO `tbl_rel_class_course` VALUES ('172', '6', '14', null);
INSERT INTO `tbl_rel_class_course` VALUES ('173', '7', '14', null);
INSERT INTO `tbl_rel_class_course` VALUES ('174', '8', '14', null);
INSERT INTO `tbl_rel_class_course` VALUES ('175', '9', '14', null);
INSERT INTO `tbl_relastion_class_teacher` VALUES ('32', '1', '6', '1');
INSERT INTO `tbl_relastion_class_teacher` VALUES ('33', '1', '8', '1');
INSERT INTO `tbl_relastion_class_teacher` VALUES ('34', '1', '1', '1');
INSERT INTO `tbl_relastion_class_teacher` VALUES ('35', '2', '6', '1');
INSERT INTO `tbl_relastion_class_teacher` VALUES ('36', '2', '2', '1');
INSERT INTO `tbl_room` VALUES ('1', '一教室', '40', '0');
INSERT INTO `tbl_room` VALUES ('2', '二教室', '40', '0');
INSERT INTO `tbl_room` VALUES ('3', '一机房', '40', '1');
INSERT INTO `tbl_room` VALUES ('4', '二机房', '40', '1');
INSERT INTO `tbl_room` VALUES ('5', '三机房', null, '2');
INSERT INTO `tbl_room` VALUES ('6', '四机房', '40', '2');
INSERT INTO `tbl_room` VALUES ('7', '五机房', '40', '2');
INSERT INTO `tbl_room` VALUES ('8', '四教室', '40', '3');
INSERT INTO `tbl_room` VALUES ('9', '操场', '-1', '10');
INSERT INTO `tbl_room` VALUES ('10', '主教414', '-1', '10');
INSERT INTO `tbl_room` VALUES ('11', '主教417/419', '-1', '10');
INSERT INTO `teacher` VALUES ('1', '付巧玲', '0');
INSERT INTO `teacher` VALUES ('2', '苗老师', '-1');
INSERT INTO `teacher` VALUES ('3', '穆贵军', '0');
INSERT INTO `teacher` VALUES ('4', '杨涛', '0');
INSERT INTO `teacher` VALUES ('5', '李树江', '0');
INSERT INTO `teacher` VALUES ('6', '催雅鑫', '0');
INSERT INTO `teacher` VALUES ('7', '姚凯', '0');
INSERT INTO `teacher` VALUES ('8', '尹兴兴', '0');
INSERT INTO `teacher` VALUES ('9', '赵东明', '3');
INSERT INTO `teacher` VALUES ('10', '刘海燕', '2');
