create table houseuser(
	id int primary key not null AUTO_INCREMENT ,
	password varchar(32) comment '√‹¬Î',
        telephone varchar(32) comment 'µÁª∞',
	username varchar(32),
	isadmin varchar(32),
	realname varchar(32)
);
create table USERS
(
  ID        NUMBER not null,
  NAME      VARCHAR2(50),
  PASSWORD  VARCHAR2(50),
  TELEPHONE VARCHAR2(15),
  USERNAME  VARCHAR2(50),
  ISADMIN   VARCHAR2(5)
);
create table DISTRICT
(
  ID   NUMBER not null,
  NAME VARCHAR2(50) not null
);
create table HOUSE
(
  ID          NUMBER,
  USER_ID     NUMBER,
  TYPE_ID     NUMBER,
  TITLE       NVARCHAR2(50),
  DESCRIPTION NVARCHAR2(2000),
  PRICE       NUMBER,
  PUBDATE     DATE,
  FLOORAGE    NUMBER,
  CONTACT     VARCHAR2(100),
  STREET_ID   NUMBER
);
create table STREET
(
  ID          NUMBER not null,
  NAME        VARCHAR2(50),
  DISTRICT_ID NUMBER
);
create table TYPE
(
  ID   NUMBER not null,
  NAME VARCHAR2(10) not null
)