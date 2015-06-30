-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: db-cms
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

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
-- Table structure for table `app`
--

DROP TABLE IF EXISTS `app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app` (
  `APP_ID` int(22) NOT NULL AUTO_INCREMENT COMMENT '站点ID，关联基础表BasicId',
  `APP_NAME` varchar(60) DEFAULT NULL,
  `APP_URL` varchar(200) DEFAULT NULL COMMENT '站点域名',
  `APP_LOGO` varchar(120) DEFAULT NULL,
  `APP_KEYWORD` varchar(1000) DEFAULT NULL COMMENT '站点关键字',
  `APP_COPYRIGHT` varchar(1000) DEFAULT NULL COMMENT '站点版权信息',
  `APP_STYLE` varchar(50) DEFAULT NULL COMMENT '站点风格',
  `APP_MANAGERID` int(11) DEFAULT NULL,
  `APP_DESCRIPTION` varchar(1000) DEFAULT NULL,
  `APP_DATETIME` datetime DEFAULT NULL,
  `APP_MOBILE_STYLE` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`APP_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1545 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app`
--

LOCK TABLES `app` WRITE;
/*!40000 ALTER TABLE `app` DISABLE KEYS */;
INSERT INTO `app` VALUES (1,'MCMS','http://localhost:8080',NULL,NULL,NULL,NULL,45,NULL,NULL,NULL);
/*!40000 ALTER TABLE `app` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basic`
--

DROP TABLE IF EXISTS `basic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basic` (
  `BASIC_ID` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `BASIC_TITLE` varchar(300) DEFAULT NULL COMMENT '标题',
  `BASIC_DESCRIPTION` text COMMENT '描述',
  `BASIC_THUMBNAILS` varchar(1000) DEFAULT NULL COMMENT '缩略图',
  `BASIC_HIT` bigint(22) DEFAULT NULL COMMENT '点击次数',
  `BASIC_SORT` int(11) DEFAULT NULL,
  `BASIC_DATETIME` datetime DEFAULT NULL COMMENT ' 发布时间',
  `BASIC_UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `BASIC_PEOPLEID` int(22) DEFAULT NULL COMMENT '用户编号',
  `BASIC_CATEGORYID` int(22) DEFAULT NULL COMMENT '所属分类编号',
  `BASIC_APPID` int(11) DEFAULT NULL,
  `BASIC_MODELID` int(11) DEFAULT NULL,
  PRIMARY KEY (`BASIC_ID`),
  UNIQUE KEY `SYS_C009068` (`BASIC_ID`),
  KEY `BASIC_TITLE` (`BASIC_TITLE`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basic`
--

LOCK TABLES `basic` WRITE;
/*!40000 ALTER TABLE `basic` DISABLE KEYS */;
/*!40000 ALTER TABLE `basic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basic_attention`
--

DROP TABLE IF EXISTS `basic_attention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basic_attention` (
  `ba_id` int(11) NOT NULL AUTO_INCREMENT,
  `ba_peopleID` int(11) DEFAULT NULL,
  `ba_appID` int(11) DEFAULT NULL,
  `ba_basicID` int(11) DEFAULT NULL,
  `ba_type` int(11) DEFAULT NULL,
  `ba_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`ba_id`),
  KEY `BA_PEOPLEID` (`ba_peopleID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basic_attention`
--

LOCK TABLES `basic_attention` WRITE;
/*!40000 ALTER TABLE `basic_attention` DISABLE KEYS */;
/*!40000 ALTER TABLE `basic_attention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `CATEGORY_ID` int(22) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `CATEGORY_TITLE` varchar(50) DEFAULT NULL COMMENT '类别标题',
  `CATEGORY_SORT` int(10) DEFAULT NULL COMMENT '类别排序',
  `CATEGORY_DATETIME` datetime DEFAULT NULL COMMENT '类别发布时间',
  `CATEGORY_MANAGERID` int(22) DEFAULT NULL COMMENT '发布用户ID',
  `CATEGORY_MODELID` int(11) DEFAULT NULL COMMENT '所属模块ID',
  `CATEGORY_CATEGORYID` int(22) DEFAULT NULL COMMENT '父类别编号',
  `CATEGORY_SMALLIMG` varchar(120) DEFAULT NULL COMMENT '略缩图',
  `CATEGORY_APPID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`),
  KEY `CATEGORY_APPID` (`CATEGORY_APPID`),
  KEY `CATEGORY_MANAGERID` (`CATEGORY_MANAGERID`),
  KEY `CATEGORY_MODELID` (`CATEGORY_MODELID`),
  KEY `CATEGORY_CATEGORYID` (`CATEGORY_CATEGORYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_article`
--

DROP TABLE IF EXISTS `cms_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_article` (
  `ARTICLE_BASICID` int(20) DEFAULT NULL COMMENT '文章ID',
  `ARTICLE_AUTHOR` varchar(20) DEFAULT NULL COMMENT '文章作者',
  `ARTICLE_CONTENT` longtext COMMENT '文章内容',
  `ARTICLE_TYPE` varchar(100) DEFAULT NULL COMMENT '文章类型',
  `ARTICLE_SOURCE` varchar(300) DEFAULT NULL COMMENT '文章来源',
  `ARTICLE_URL` varchar(200) DEFAULT NULL COMMENT '文章跳转链接地址',
  `ARTICLE_KEYWORD` varchar(300) DEFAULT NULL COMMENT '文章关键字',
  `ARTICLE_FREEORDER` int(255) DEFAULT NULL COMMENT '文章自定义显示顺序',
  `ARTICLE_WEBID` int(11) DEFAULT NULL,
  UNIQUE KEY `ARTICLE_BASICID` (`ARTICLE_BASICID`) USING BTREE,
  KEY `ARTICLE_WEBID` (`ARTICLE_WEBID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_article`
--

LOCK TABLES `cms_article` WRITE;
/*!40000 ALTER TABLE `cms_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_column`
--

DROP TABLE IF EXISTS `cms_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_column` (
  `COLUMN_CATEGORYID` int(22) NOT NULL DEFAULT '0' COMMENT '关联category表（类别表ID）',
  `COLUMN_KEYWORD` varchar(300) DEFAULT NULL COMMENT '栏目简介',
  `COLUMN_DESCRIP` varchar(500) DEFAULT NULL COMMENT '栏目关键字的扩展',
  `COLUMN_TYPE` int(2) DEFAULT NULL COMMENT '1,代表最终列表栏目。2，代表频道封面。3，带表外部链接',
  `COLUMN_URL` varchar(50) DEFAULT NULL COMMENT '如果是外部链接，则保持外部链接地址。如果为最终列表栏目，就保存文章显示列表',
  `COLUMN_LISTURL` varchar(50) DEFAULT NULL COMMENT '最终列表栏目的列表模板地址',
  `COLUMN_TENTMODELID` int(22) DEFAULT NULL COMMENT '栏目类型,直接影响栏目发布的表单样式',
  `COLUMN_WEBSITEID` int(22) DEFAULT NULL COMMENT '栏目所属站点ID',
  `COLUMN_PATH` varchar(150) DEFAULT NULL,
  `COLUMN_CONTENTMODELID` int(11) DEFAULT '0',
  PRIMARY KEY (`COLUMN_CATEGORYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_column`
--

LOCK TABLES `cms_column` WRITE;
/*!40000 ALTER TABLE `cms_column` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_content_mode_field`
--

DROP TABLE IF EXISTS `cms_content_mode_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_content_mode_field` (
  `FIELD_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '字段自增长id',
  `FIELD_TIPSNAME` varchar(30) DEFAULT NULL COMMENT '字段提示文字',
  `FIELD_FIELDNAME` varchar(20) DEFAULT NULL COMMENT '字段名称',
  `FIELD_TYPE` int(11) DEFAULT NULL COMMENT '字段类型（如1.单行，2.多行，3.图片，等）',
  `FIELD_DEFAULT` varchar(250) DEFAULT NULL,
  `FIELD_ISNULL` int(11) DEFAULT NULL,
  `FIELD_CMID` int(11) DEFAULT NULL COMMENT '关联内容模型表id',
  `FIELD_SORT` int(11) DEFAULT NULL,
  PRIMARY KEY (`FIELD_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_content_mode_field`
--

LOCK TABLES `cms_content_mode_field` WRITE;
/*!40000 ALTER TABLE `cms_content_mode_field` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_content_mode_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_content_model`
--

DROP TABLE IF EXISTS `cms_content_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_content_model` (
  `CM_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `CM_TIPSNAME` varchar(30) NOT NULL COMMENT '表单提示文字',
  `CM_TABLENAME` varchar(20) NOT NULL COMMENT '表单名称',
  `CM_MANAGERID` int(11) NOT NULL,
  PRIMARY KEY (`CM_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_content_model`
--

LOCK TABLES `cms_content_model` WRITE;
/*!40000 ALTER TABLE `cms_content_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_content_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_search`
--

DROP TABLE IF EXISTS `cms_search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_search` (
  `SEARCH_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `SEARCH_NAME` varchar(20) NOT NULL COMMENT '搜索名称',
  `SEARCH_TEMPLETS` varchar(50) NOT NULL COMMENT '搜索结果模版',
  `SEARCH_WEBSITEID` int(11) NOT NULL COMMENT '点站ID',
  PRIMARY KEY (`SEARCH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_search`
--

LOCK TABLES `cms_search` WRITE;
/*!40000 ALTER TABLE `cms_search` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_search` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `MANAGER_ID` bigint(22) NOT NULL AUTO_INCREMENT,
  `MANAGER_NAME` varchar(15) DEFAULT NULL,
  `MANAGER_NICKNAME` varchar(15) DEFAULT NULL,
  `MANAGER_PASSWORD` varchar(45) DEFAULT NULL,
  `MANAGER_ROLEID` bigint(22) DEFAULT NULL COMMENT '色角编号',
  `MANAGER_PEOPLEID` bigint(22) DEFAULT '0' COMMENT '户用编号即商家编号',
  `MANAGER_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`MANAGER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (45,'admin','cms','570d99e4c85914470d914170d1e95144',44,0,'2015-03-17 22:07:05');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager_model_page`
--

DROP TABLE IF EXISTS `manager_model_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager_model_page` (
  `mmp_managerID` int(11) DEFAULT NULL,
  `mmp_modelID` int(11) DEFAULT NULL COMMENT '模块编号',
  `mmp_url` varchar(255) DEFAULT NULL COMMENT '默认后台显示的主界面'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager_model_page`
--

LOCK TABLES `manager_model_page` WRITE;
/*!40000 ALTER TABLE `manager_model_page` DISABLE KEYS */;
INSERT INTO `manager_model_page` VALUES (30,0,'/manager/staging/index.do');
/*!40000 ALTER TABLE `manager_model_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `model` (
  `MODEL_ID` int(22) NOT NULL AUTO_INCREMENT,
  `MODEL_TITLE` varchar(150) DEFAULT NULL,
  `MODEL_CODE` varchar(255) DEFAULT NULL COMMENT '模块编号',
  `MODEL_MODELID` int(22) DEFAULT NULL,
  `MODEL_URL` varchar(255) DEFAULT NULL,
  `MODEL_DATETIME` datetime DEFAULT NULL,
  `MODEL_ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `MODEL_MODELMANAGERID` int(11) DEFAULT NULL,
  `MODEL_SORT` int(11) DEFAULT NULL,
  PRIMARY KEY (`MODEL_ID`),
  UNIQUE KEY `SYS_C009201` (`MODEL_ID`),
  KEY `MODEL_MODELID` (`MODEL_MODELID`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,'内容管理','02000000',0,'','2014-08-01 16:19:50','/upload/model/content.png',0,NULL),(2,'应用管理','00000002',22,'/manager/app/list.do','2014-08-01 16:27:56','',0,NULL),(3,'模块管理','00000001',22,'/manager/model/list.do','2014-08-01 18:35:00','',0,NULL),(4,'管理员管理','01020000',23,'/manager/manager/queryList.do','2014-08-03 09:15:02','',0,NULL),(5,'角色管理','01010000',23,'/manager/role/queryList.do','2014-08-03 09:15:14','',0,NULL),(7,'栏目管理','02990000',1,'/manager/cms/column/list.do','2014-08-03 09:16:29','',0,NULL),(8,'文章管理','02980000',1,'/manager/cms/article/index.do','2014-08-03 09:17:10','',0,NULL),(22,'系统管理','00000000',0,'','2014-09-08 08:11:28','/upload/model/1418984009120.png',0,NULL),(23,'权限管理','01000000',0,'','2014-09-08 08:12:22','/upload/model/1418984027679.png',0,NULL),(33,'数据导入','02090000',0,'','2014-10-07 11:32:07','/upload/model/shuj.png',0,NULL),(34,'织梦数据导入','02090100',33,'/manager/cms/dede/dede.do','2014-10-07 11:32:47','',0,NULL),(35,'自定义模型','02060000',1,'/manager/cms/contentModel/list.do','2014-10-13 18:27:23','',0,NULL),(36,'自定义搜索','02050000',1,'/manager/cms/search/list.do','2014-10-13 18:28:34','',0,NULL),(69,'自定义页面','12030100',84,'/manager/modeltemplate/list.do','2014-11-21 22:04:31','',0,NULL),(79,'生成器','11000000',0,'','2014-12-18 11:36:16','/upload/model/1418960617708.png',0,NULL),(80,'生成主页','11010000',79,'/manager/cms/generate/index.do','2014-12-18 11:37:15','',0,NULL),(81,'生成栏目','11020000',79,'/manager/cms/generate/column.do','2014-12-18 11:37:53','',0,NULL),(82,'生成文章','11030000',79,'/manager/cms/generate/article.do','2014-12-18 11:38:38','',0,NULL),(84,'系统设置','12000000',0,'','2014-12-18 18:30:24','/upload/model/1418984110144.png',0,NULL),(86,'应用设置','12010000',84,'/manager/app/-1/edit.do','2014-12-18 18:31:59','',0,NULL),(87,'模版管理','12020000',84,'/manager/cms/templet/queryTempletSkin.do','2014-12-18 18:32:50','',0,NULL),(94,'自定义表单','12050000',84,'/manager/diy/form/list.do','2015-01-01 20:23:20','',0,NULL);
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_template`
--

DROP TABLE IF EXISTS `model_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `model_template` (
  `mt_id` int(11) NOT NULL AUTO_INCREMENT,
  `mt_modelID` int(11) DEFAULT NULL,
  `mt_appID` int(11) DEFAULT NULL,
  `mt_path` varchar(255) DEFAULT NULL,
  `mt_title` varchar(255) DEFAULT NULL,
  `mt_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mt_id`),
  KEY `mt_key` (`mt_key`) USING BTREE,
  KEY `mt_appID` (`mt_appID`),
  KEY `mt_modelID` (`mt_modelID`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_template`
--

LOCK TABLES `model_template` WRITE;
/*!40000 ALTER TABLE `model_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `model_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `PEOPLE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '增长自ID',
  `PEOPLE_PHONE` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `PEOPLE_NAME` varchar(30) DEFAULT NULL COMMENT '陆登账号',
  `PEOPLE_PASSWORD` varchar(50) DEFAULT NULL COMMENT '陆登密码',
  `PEOPLE_DATETIME` datetime NOT NULL COMMENT '注册时间',
  `PEOPLE_APP_ID` int(11) NOT NULL COMMENT '用户所属用户ID',
  `PEOPLE_MAIL` varchar(120) DEFAULT NULL COMMENT '用户邮箱',
  `PEOPLE_STATE` int(2) DEFAULT '0' COMMENT '用户状态',
  `PEOPLE_CODE` varchar(15) DEFAULT NULL COMMENT '随机验证码',
  `PEOPLE_CODESENDDATE` datetime DEFAULT NULL,
  `PEOPLE_PHONECHECK` int(1) DEFAULT NULL,
  `PEOPLE_MAILLCHECK` int(1) DEFAULT NULL,
  PRIMARY KEY (`PEOPLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1449 DEFAULT CHARSET=utf8 COMMENT='户用基础表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people_user`
--

DROP TABLE IF EXISTS `people_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people_user` (
  `PU_PEOPLE_ID` int(11) NOT NULL COMMENT '用户ID关联people表的（people_id）',
  `PU_REAL_NAME` varchar(50) DEFAULT NULL COMMENT '用户真实名称',
  `PU_ADDRESS` varchar(200) DEFAULT NULL COMMENT '用户地址',
  `PU_ICON` varchar(120) DEFAULT NULL COMMENT '用户头像图标地址',
  `PU_NICKNAME` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `PU_SEX` int(2) DEFAULT NULL COMMENT '用户性别(0.未知、1.男、2.女)',
  `PU_BIRTHDAY` date DEFAULT NULL COMMENT '用户出生年月日',
  `PU_CARD` varchar(255) DEFAULT NULL COMMENT '身份证',
  `PU_APP_ID` int(11) NOT NULL COMMENT '用户所属应用ID',
  PRIMARY KEY (`PU_PEOPLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people_user`
--

LOCK TABLES `people_user` WRITE;
/*!40000 ALTER TABLE `people_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `people_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLE_ID` bigint(22) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(30) DEFAULT NULL,
  `ROLE_MANAGERID` bigint(22) DEFAULT '0',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'系统管理员',1),(44,'cms',45);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_model`
--

DROP TABLE IF EXISTS `role_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_model` (
  `RM_MODELID` int(22) DEFAULT NULL,
  `RM_ROLEID` int(22) DEFAULT NULL,
  KEY `RM_MODELID` (`RM_MODELID`),
  CONSTRAINT `role_model_ibfk_1` FOREIGN KEY (`RM_MODELID`) REFERENCES `model` (`MODEL_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_model`
--

LOCK TABLES `role_model` WRITE;
/*!40000 ALTER TABLE `role_model` DISABLE KEYS */;
INSERT INTO `role_model` VALUES (22,1),(3,1),(2,1),(1,44),(7,44),(8,44),(35,44),(36,44),(23,44),(4,44),(5,44),(33,44),(34,44),(79,44),(80,44),(81,44),(82,44),(84,44),(69,44),(86,44),(87,44),(94,44);
/*!40000 ALTER TABLE `role_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_main_page`
--

DROP TABLE IF EXISTS `system_main_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_main_page` (
  `smp_appID` int(11) DEFAULT NULL,
  `smp_url` varchar(255) DEFAULT NULL COMMENT '默认后台显示的主界面',
  `smp_modelID` int(11) DEFAULT NULL COMMENT '模块编号',
  `smp_managerID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_main_page`
--

LOCK TABLES `system_main_page` WRITE;
/*!40000 ALTER TABLE `system_main_page` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_main_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_skin`
--

DROP TABLE IF EXISTS `system_skin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_skin` (
  `ss_id` int(11) NOT NULL AUTO_INCREMENT,
  `ss_loginpage` varchar(255) DEFAULT NULL COMMENT '登陆页面',
  `ss_backgroundimg` varchar(255) DEFAULT NULL,
  `ss_color` varchar(255) DEFAULT NULL,
  `ss_css` varchar(255) DEFAULT NULL,
  `ss_datetime` datetime DEFAULT NULL,
  `ss_appID` int(11) DEFAULT NULL COMMENT '0后台发布大于０表示是应用自定义',
  `ss_categoryID` int(11) DEFAULT NULL COMMENT '主题分类',
  PRIMARY KEY (`ss_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_skin`
--

LOCK TABLES `system_skin` WRITE;
/*!40000 ALTER TABLE `system_skin` DISABLE KEYS */;
INSERT INTO `system_skin` VALUES (1,NULL,'http://7.su.bdimg.com/skin/117.jpg?2',NULL,NULL,'2015-01-10 11:07:33',NULL,NULL),(2,NULL,'http://2.su.bdimg.com/skin/208.jpg?2',NULL,NULL,NULL,NULL,NULL),(3,NULL,'http://3.su.bdimg.com/skin/249.jpg?2',NULL,NULL,NULL,NULL,NULL),(4,'lefen.do','',NULL,'css/css.css',NULL,NULL,NULL),(5,'','http://8.su.bdimg.com/skin/142.jpg?2',NULL,'',NULL,1528,NULL),(6,NULL,'http://8.su.bdimg.com/skin/142.jpg?2',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `system_skin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_skin_manager`
--

DROP TABLE IF EXISTS `system_skin_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_skin_manager` (
  `ssm_managerID` int(11) NOT NULL DEFAULT '0',
  `ssm_system_skin_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ssm_managerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_skin_manager`
--

LOCK TABLES `system_skin_manager` WRITE;
/*!40000 ALTER TABLE `system_skin_manager` DISABLE KEYS */;
INSERT INTO `system_skin_manager` VALUES (1,1),(9,1),(10,2),(11,1),(12,1),(13,1),(19,1),(21,1),(26,1),(27,1),(30,4),(33,1),(35,1),(36,1),(37,1),(39,1),(40,1),(41,1),(42,1),(45,1);
/*!40000 ALTER TABLE `system_skin_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_article`
--

DROP TABLE IF EXISTS `v_article`;
/*!50001 DROP VIEW IF EXISTS `v_article`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_article` (
  `ARTICLE_BASICID` tinyint NOT NULL,
  `BASIC_ID` tinyint NOT NULL,
  `BASIC_CATEGORYID` tinyint NOT NULL,
  `BASIC_TITLE` tinyint NOT NULL,
  `BASIC_DESCRIPTION` tinyint NOT NULL,
  `BASIC_THUMBNAILS` tinyint NOT NULL,
  `BASIC_HIT` tinyint NOT NULL,
  `BASIC_DATETIME` tinyint NOT NULL,
  `BASIC_UPDATETIME` tinyint NOT NULL,
  `BASIC_PEOPLEID` tinyint NOT NULL,
  `ARTICLE_AUTHOR` tinyint NOT NULL,
  `ARTICLE_CONTENT` tinyint NOT NULL,
  `ARTICLE_TYPE` tinyint NOT NULL,
  `ARTICLE_SOURCE` tinyint NOT NULL,
  `ARTICLE_URL` tinyint NOT NULL,
  `ARTICLE_KEYWORD` tinyint NOT NULL,
  `ARTICLE_FREEORDER` tinyint NOT NULL,
  `ARTICLE_WEBID` tinyint NOT NULL,
  `COLUMN_KEYWORD` tinyint NOT NULL,
  `COLUMN_DESCRIP` tinyint NOT NULL,
  `COLUMN_TYPE` tinyint NOT NULL,
  `COLUMN_URL` tinyint NOT NULL,
  `COLUMN_LISTURL` tinyint NOT NULL,
  `COLUMN_TENTMODELID` tinyint NOT NULL,
  `COLUMN_WEBSITEID` tinyint NOT NULL,
  `column_path` tinyint NOT NULL,
  `COLUMN_CONTENTMODELID` tinyint NOT NULL,
  `CATEGORY_TITLE` tinyint NOT NULL,
  `CATEGORY_APPID` tinyint NOT NULL,
  `COLUMN_CATEGORYID` tinyint NOT NULL,
  `CATEGORY_ID` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_attention_article`
--

DROP TABLE IF EXISTS `v_attention_article`;
/*!50001 DROP VIEW IF EXISTS `v_attention_article`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_attention_article` (
  `BASIC_ID` tinyint NOT NULL,
  `BASIC_TITLE` tinyint NOT NULL,
  `BASIC_DESCRIPTION` tinyint NOT NULL,
  `BASIC_THUMBNAILS` tinyint NOT NULL,
  `BASIC_HIT` tinyint NOT NULL,
  `BASIC_SORT` tinyint NOT NULL,
  `BASIC_DATETIME` tinyint NOT NULL,
  `BASIC_UPDATETIME` tinyint NOT NULL,
  `BASIC_PEOPLEID` tinyint NOT NULL,
  `BASIC_CATEGORYID` tinyint NOT NULL,
  `BASIC_APPID` tinyint NOT NULL,
  `BASIC_MODELID` tinyint NOT NULL,
  `ba_id` tinyint NOT NULL,
  `ba_peopleID` tinyint NOT NULL,
  `ba_appID` tinyint NOT NULL,
  `ba_basicID` tinyint NOT NULL,
  `ba_type` tinyint NOT NULL,
  `ba_datetime` tinyint NOT NULL,
  `ARTICLE_BASICID` tinyint NOT NULL,
  `ARTICLE_AUTHOR` tinyint NOT NULL,
  `ARTICLE_CONTENT` tinyint NOT NULL,
  `ARTICLE_TYPE` tinyint NOT NULL,
  `ARTICLE_SOURCE` tinyint NOT NULL,
  `ARTICLE_URL` tinyint NOT NULL,
  `ARTICLE_KEYWORD` tinyint NOT NULL,
  `ARTICLE_FREEORDER` tinyint NOT NULL,
  `ARTICLE_WEBID` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_people_user`
--

DROP TABLE IF EXISTS `v_people_user`;
/*!50001 DROP VIEW IF EXISTS `v_people_user`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_people_user` (
  `PEOPLE_ID` tinyint NOT NULL,
  `PEOPLE_PHONE` tinyint NOT NULL,
  `PEOPLE_NAME` tinyint NOT NULL,
  `PEOPLE_PASSWORD` tinyint NOT NULL,
  `PEOPLE_DATETIME` tinyint NOT NULL,
  `PEOPLE_APP_ID` tinyint NOT NULL,
  `PEOPLE_MAIL` tinyint NOT NULL,
  `PEOPLE_STATE` tinyint NOT NULL,
  `PEOPLE_CODE` tinyint NOT NULL,
  `PEOPLE_CODESENDDATE` tinyint NOT NULL,
  `PEOPLE_PHONECHECK` tinyint NOT NULL,
  `PEOPLE_MAILLCHECK` tinyint NOT NULL,
  `PU_PEOPLE_ID` tinyint NOT NULL,
  `PU_REAL_NAME` tinyint NOT NULL,
  `PU_ADDRESS` tinyint NOT NULL,
  `PU_ICON` tinyint NOT NULL,
  `PU_NICKNAME` tinyint NOT NULL,
  `PU_SEX` tinyint NOT NULL,
  `PU_BIRTHDAY` tinyint NOT NULL,
  `PU_CARD` tinyint NOT NULL,
  `PU_APP_ID` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `version`
--

DROP TABLE IF EXISTS `version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `version` (
  `version_id` int(11) NOT NULL AUTO_INCREMENT,
  `version_serial` varchar(200) DEFAULT NULL,
  `version_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`version_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `version`
--

LOCK TABLES `version` WRITE;
/*!40000 ALTER TABLE `version` DISABLE KEYS */;
INSERT INTO `version` VALUES (1,'f2e246c745a7dd63f7518770014b7b35998df7dd35a42697ac687ef322361dcb','2015-03-20 13:20:07');
/*!40000 ALTER TABLE `version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db-cms'
--
/*!50003 DROP PROCEDURE IF EXISTS `p_getAllChildren` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_getAllChildren`(`pId` int)
BEGIN
	
   declare lev int;
   set lev=1;
   drop table if exists tmp_category;    
   CREATE TABLE tmp_category(category_id int(40),category_title varchar(50),category_categoryid varchar(40) ,lev INT);    
   INSERT tmp_category SELECT category_id,category_title,category_categoryid,1 FROM `category` WHERE category_categoryid=pid;    
  while ROW_COUNT()>0 
    do     set lev=lev+1; 
     INSERT tmp_category SELECT c.category_id,c.category_title,c.category_categoryid,lev  from category c  join tmp_category a on c.category_categoryid = a.category_id AND a.lev=lev-1;
  end while ;    
  INSERT tmp_category SELECT c.category_id,c.category_title,c.category_categoryid,0 FROM category  c WHERE c.category_id=pid;   
  SELECT * FROM tmp_category;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `v_article`
--

/*!50001 DROP TABLE IF EXISTS `v_article`*/;
/*!50001 DROP VIEW IF EXISTS `v_article`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_article` AS select `cms_article`.`ARTICLE_BASICID` AS `ARTICLE_BASICID`,`basic`.`BASIC_ID` AS `BASIC_ID`,`basic`.`BASIC_CATEGORYID` AS `BASIC_CATEGORYID`,`basic`.`BASIC_TITLE` AS `BASIC_TITLE`,`basic`.`BASIC_DESCRIPTION` AS `BASIC_DESCRIPTION`,`basic`.`BASIC_THUMBNAILS` AS `BASIC_THUMBNAILS`,`basic`.`BASIC_HIT` AS `BASIC_HIT`,`basic`.`BASIC_DATETIME` AS `BASIC_DATETIME`,`basic`.`BASIC_UPDATETIME` AS `BASIC_UPDATETIME`,`basic`.`BASIC_PEOPLEID` AS `BASIC_PEOPLEID`,`cms_article`.`ARTICLE_AUTHOR` AS `ARTICLE_AUTHOR`,`cms_article`.`ARTICLE_CONTENT` AS `ARTICLE_CONTENT`,`cms_article`.`ARTICLE_TYPE` AS `ARTICLE_TYPE`,`cms_article`.`ARTICLE_SOURCE` AS `ARTICLE_SOURCE`,`cms_article`.`ARTICLE_URL` AS `ARTICLE_URL`,`cms_article`.`ARTICLE_KEYWORD` AS `ARTICLE_KEYWORD`,`cms_article`.`ARTICLE_FREEORDER` AS `ARTICLE_FREEORDER`,`cms_article`.`ARTICLE_WEBID` AS `ARTICLE_WEBID`,`cms_column`.`COLUMN_KEYWORD` AS `COLUMN_KEYWORD`,`cms_column`.`COLUMN_DESCRIP` AS `COLUMN_DESCRIP`,`cms_column`.`COLUMN_TYPE` AS `COLUMN_TYPE`,`cms_column`.`COLUMN_URL` AS `COLUMN_URL`,`cms_column`.`COLUMN_LISTURL` AS `COLUMN_LISTURL`,`cms_column`.`COLUMN_TENTMODELID` AS `COLUMN_TENTMODELID`,`cms_column`.`COLUMN_WEBSITEID` AS `COLUMN_WEBSITEID`,`cms_column`.`COLUMN_PATH` AS `column_path`,`cms_column`.`COLUMN_CONTENTMODELID` AS `COLUMN_CONTENTMODELID`,`category`.`CATEGORY_TITLE` AS `CATEGORY_TITLE`,`category`.`CATEGORY_APPID` AS `CATEGORY_APPID`,`cms_column`.`COLUMN_CATEGORYID` AS `COLUMN_CATEGORYID`,`category`.`CATEGORY_ID` AS `CATEGORY_ID` from (((`basic` join `cms_article` on((`basic`.`BASIC_ID` = `cms_article`.`ARTICLE_BASICID`))) join `cms_column` on((`basic`.`BASIC_CATEGORYID` = `cms_column`.`COLUMN_CATEGORYID`))) join `category` on((`cms_column`.`COLUMN_CATEGORYID` = `category`.`CATEGORY_ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_attention_article`
--

/*!50001 DROP TABLE IF EXISTS `v_attention_article`*/;
/*!50001 DROP VIEW IF EXISTS `v_attention_article`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_attention_article` AS select `basic`.`BASIC_ID` AS `BASIC_ID`,`basic`.`BASIC_TITLE` AS `BASIC_TITLE`,`basic`.`BASIC_DESCRIPTION` AS `BASIC_DESCRIPTION`,`basic`.`BASIC_THUMBNAILS` AS `BASIC_THUMBNAILS`,`basic`.`BASIC_HIT` AS `BASIC_HIT`,`basic`.`BASIC_SORT` AS `BASIC_SORT`,`basic`.`BASIC_DATETIME` AS `BASIC_DATETIME`,`basic`.`BASIC_UPDATETIME` AS `BASIC_UPDATETIME`,`basic`.`BASIC_PEOPLEID` AS `BASIC_PEOPLEID`,`basic`.`BASIC_CATEGORYID` AS `BASIC_CATEGORYID`,`basic`.`BASIC_APPID` AS `BASIC_APPID`,`basic`.`BASIC_MODELID` AS `BASIC_MODELID`,`basic_attention`.`ba_id` AS `ba_id`,`basic_attention`.`ba_peopleID` AS `ba_peopleID`,`basic_attention`.`ba_appID` AS `ba_appID`,`basic_attention`.`ba_basicID` AS `ba_basicID`,`basic_attention`.`ba_type` AS `ba_type`,`basic_attention`.`ba_datetime` AS `ba_datetime`,`cms_article`.`ARTICLE_BASICID` AS `ARTICLE_BASICID`,`cms_article`.`ARTICLE_AUTHOR` AS `ARTICLE_AUTHOR`,`cms_article`.`ARTICLE_CONTENT` AS `ARTICLE_CONTENT`,`cms_article`.`ARTICLE_TYPE` AS `ARTICLE_TYPE`,`cms_article`.`ARTICLE_SOURCE` AS `ARTICLE_SOURCE`,`cms_article`.`ARTICLE_URL` AS `ARTICLE_URL`,`cms_article`.`ARTICLE_KEYWORD` AS `ARTICLE_KEYWORD`,`cms_article`.`ARTICLE_FREEORDER` AS `ARTICLE_FREEORDER`,`cms_article`.`ARTICLE_WEBID` AS `ARTICLE_WEBID` from ((`basic` join `basic_attention` on((`basic_attention`.`ba_basicID` = `basic`.`BASIC_ID`))) join `cms_article` on((`cms_article`.`ARTICLE_BASICID` = `basic`.`BASIC_ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_people_user`
--

/*!50001 DROP TABLE IF EXISTS `v_people_user`*/;
/*!50001 DROP VIEW IF EXISTS `v_people_user`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_people_user` AS select `people`.`PEOPLE_ID` AS `PEOPLE_ID`,`people`.`PEOPLE_PHONE` AS `PEOPLE_PHONE`,`people`.`PEOPLE_NAME` AS `PEOPLE_NAME`,`people`.`PEOPLE_PASSWORD` AS `PEOPLE_PASSWORD`,`people`.`PEOPLE_DATETIME` AS `PEOPLE_DATETIME`,`people`.`PEOPLE_APP_ID` AS `PEOPLE_APP_ID`,`people`.`PEOPLE_MAIL` AS `PEOPLE_MAIL`,`people`.`PEOPLE_STATE` AS `PEOPLE_STATE`,`people`.`PEOPLE_CODE` AS `PEOPLE_CODE`,`people`.`PEOPLE_CODESENDDATE` AS `PEOPLE_CODESENDDATE`,`people`.`PEOPLE_PHONECHECK` AS `PEOPLE_PHONECHECK`,`people`.`PEOPLE_MAILLCHECK` AS `PEOPLE_MAILLCHECK`,`people_user`.`PU_PEOPLE_ID` AS `PU_PEOPLE_ID`,`people_user`.`PU_REAL_NAME` AS `PU_REAL_NAME`,`people_user`.`PU_ADDRESS` AS `PU_ADDRESS`,`people_user`.`PU_ICON` AS `PU_ICON`,`people_user`.`PU_NICKNAME` AS `PU_NICKNAME`,`people_user`.`PU_SEX` AS `PU_SEX`,`people_user`.`PU_BIRTHDAY` AS `PU_BIRTHDAY`,`people_user`.`PU_CARD` AS `PU_CARD`,`people_user`.`PU_APP_ID` AS `PU_APP_ID` from (`people` join `people_user` on((`people`.`PEOPLE_ID` = `people_user`.`PU_PEOPLE_ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-20 21:32:26
