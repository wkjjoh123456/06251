/*
SQLyog Ultimate v8.32 
MySQL - 5.6.24 : Database - zjtblog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zjtblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zjtblog`;

/*Table structure for table `djxt_base_bg` */

DROP TABLE IF EXISTS `djxt_base_bg`;

CREATE TABLE `djxt_base_bg` (
  `base_LBG` decimal(10,1) NOT NULL COMMENT '低血糖',
  `base_FBG` decimal(10,1) NOT NULL COMMENT '空腹/餐前/睡前高血糖',
  `base_PBG` decimal(10,1) NOT NULL COMMENT '餐后高血糖'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='血糖标准';

/*Data for the table `djxt_base_bg` */

insert  into `djxt_base_bg`(`base_LBG`,`base_FBG`,`base_PBG`) values ('4.4','7.0','10.0');

/*Table structure for table `djxt_food_list` */

DROP TABLE IF EXISTS `djxt_food_list`;

CREATE TABLE `djxt_food_list` (
  `id` int(11) NOT NULL COMMENT '食物id主键',
  `f_Isquick` int(11) DEFAULT NULL COMMENT '1:快速记录,2:不是快速记录',
  `f_ParentId` int(11) DEFAULT NULL COMMENT '父级id（自定义为0）',
  `f_Pro` decimal(10,0) DEFAULT NULL COMMENT '食用100g蛋白质',
  `f_Car` decimal(10,0) DEFAULT NULL COMMENT '食用100g碳水化合物',
  `f_Fat` decimal(10,0) DEFAULT NULL COMMENT '食用100g脂肪',
  `f_Ene` decimal(10,0) DEFAULT NULL COMMENT '食用100g能量',
  `f_Name` varchar(10) DEFAULT NULL COMMENT '食物名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='食物信息列表';

/*Data for the table `djxt_food_list` */

insert  into `djxt_food_list`(`id`,`f_Isquick`,`f_ParentId`,`f_Pro`,`f_Car`,`f_Fat`,`f_Ene`,`f_Name`) values (2,NULL,NULL,NULL,NULL,NULL,NULL,'肉'),(4,2,2,'56','324','34','43','牛肉'),(5,2,1,'10','10','10','10','小米饭');

/*Table structure for table `djxt_food_parent` */

DROP TABLE IF EXISTS `djxt_food_parent`;

CREATE TABLE `djxt_food_parent` (
  `fp_id` int(11) NOT NULL,
  `fp_name` varchar(20) DEFAULT NULL COMMENT '类别0自定义',
  PRIMARY KEY (`fp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='食物分类表';

/*Data for the table `djxt_food_parent` */

insert  into `djxt_food_parent`(`fp_id`,`fp_name`) values (0,'自定义'),(1,'米饭'),(2,'肉');

/*Table structure for table `djxt_sport_list` */

DROP TABLE IF EXISTS `djxt_sport_list`;

CREATE TABLE `djxt_sport_list` (
  `s_Id` int(11) NOT NULL COMMENT '运动编号主键',
  `s_Name` varchar(20) DEFAULT NULL COMMENT '运动名称',
  `s_Unit` varchar(4) DEFAULT NULL COMMENT '运动单位（次，遍）',
  `s_Base` int(11) DEFAULT NULL COMMENT '基础数值',
  `s_Energy` int(11) DEFAULT NULL COMMENT '基础数值消耗能量',
  PRIMARY KEY (`s_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运动列表';

/*Data for the table `djxt_sport_list` */

insert  into `djxt_sport_list`(`s_Id`,`s_Name`,`s_Unit`,`s_Base`,`s_Energy`) values (1,'八段锦','遍',1,NULL),(2,'点筋操','遍',1,NULL);

/*Table structure for table `djxt_user` */

DROP TABLE IF EXISTS `djxt_user`;

CREATE TABLE `djxt_user` (
  `u_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user主键',
  `u_Name` varchar(20) NOT NULL COMMENT '用户姓名',
  `u_Password` varchar(30) NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`u_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `djxt_user` */

/*Table structure for table `djxt_user_bg` */

DROP TABLE IF EXISTS `djxt_user_bg`;

CREATE TABLE `djxt_user_bg` (
  `ug_Date` datetime NOT NULL COMMENT '血糖录入日期',
  `ug_Type` int(11) DEFAULT NULL COMMENT '1:空腹,2:早餐后,3:午餐前,4:午餐后,5:晚餐前,6:晚餐后,7:睡前,8:凌晨',
  `ug_Record` decimal(10,0) DEFAULT NULL COMMENT '血糖值',
  `u_Id` int(11) DEFAULT NULL COMMENT '用户id',
  `ug_Id` int(11) NOT NULL,
  PRIMARY KEY (`ug_Id`),
  KEY `FK_djxt_user_bg` (`u_Id`),
  CONSTRAINT `FK_djxt_user_bg` FOREIGN KEY (`u_Id`) REFERENCES `djxt_user` (`u_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户血糖';

/*Data for the table `djxt_user_bg` */

/*Table structure for table `djxt_user_bp` */

DROP TABLE IF EXISTS `djxt_user_bp`;

CREATE TABLE `djxt_user_bp` (
  `up_Type` int(11) DEFAULT NULL COMMENT '用户血压类型1:上午,2:下午',
  `up_Date` datetime NOT NULL COMMENT '用户血压录入时间',
  `up_Record` varchar(15) DEFAULT NULL COMMENT '用户血压值',
  `u_Id` int(11) NOT NULL,
  `up_Id` int(11) NOT NULL,
  PRIMARY KEY (`up_Id`),
  KEY `FK_djxt_user_bp` (`u_Id`),
  CONSTRAINT `FK_djxt_user_bp` FOREIGN KEY (`u_Id`) REFERENCES `djxt_user` (`u_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户血压';

/*Data for the table `djxt_user_bp` */

/*Table structure for table `djxt_user_contacts` */

DROP TABLE IF EXISTS `djxt_user_contacts`;

CREATE TABLE `djxt_user_contacts` (
  `u_Id` int(11) DEFAULT NULL COMMENT '用户id',
  `uc_Auth` varchar(20) DEFAULT NULL COMMENT '设置我授权的人',
  `uc_Authw` varchar(20) DEFAULT NULL COMMENT '我授权填写的人',
  `uc_Follow` varchar(20) DEFAULT NULL COMMENT '我关注的人',
  `uc_Helpw` varchar(20) DEFAULT NULL COMMENT '我帮助填写的人',
  `uc_Rec` varchar(20) DEFAULT NULL COMMENT '推荐我的人',
  `uc_Id` int(11) NOT NULL,
  PRIMARY KEY (`uc_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户联系人表';

/*Data for the table `djxt_user_contacts` */

/*Table structure for table `djxt_user_diet` */

DROP TABLE IF EXISTS `djxt_user_diet`;

CREATE TABLE `djxt_user_diet` (
  `ud_Id` int(11) NOT NULL COMMENT '用户饮食记录id主键',
  `ud_Date` datetime DEFAULT NULL COMMENT '日期',
  `ud_Meal` int(11) DEFAULT NULL COMMENT '1:早餐,2:早餐加餐,3:午餐,4:午餐加餐,5:晚餐,6:晚餐加餐',
  `f_Id` int(11) DEFAULT NULL COMMENT '食物id',
  `ud_Famount` int(11) DEFAULT NULL COMMENT '吃的数量',
  `ud_Ene` decimal(10,0) DEFAULT NULL COMMENT '总能量',
  `ud_Fat` decimal(10,0) DEFAULT NULL COMMENT '总脂肪',
  `ud_Car` decimal(10,0) DEFAULT NULL COMMENT '总碳水化合物',
  `ud_Pro` decimal(10,0) DEFAULT NULL COMMENT '总蛋白质',
  PRIMARY KEY (`ud_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户饮食记录表';

/*Data for the table `djxt_user_diet` */

/*Table structure for table `djxt_user_diet_total` */

DROP TABLE IF EXISTS `djxt_user_diet_total`;

CREATE TABLE `djxt_user_diet_total` (
  `udt_Id` int(11) NOT NULL COMMENT '用户饮食汇总',
  `udt_Date` datetime DEFAULT NULL COMMENT '日期',
  `u_Id` int(11) DEFAULT NULL COMMENT '用户id',
  `udt_Ene` decimal(10,0) DEFAULT NULL COMMENT '能量汇总',
  `udt_Fat` decimal(10,0) DEFAULT NULL COMMENT '脂肪汇总',
  `udt_Pro` decimal(10,0) DEFAULT NULL COMMENT '蛋白质汇总',
  `udt_Car` decimal(10,0) DEFAULT NULL COMMENT '碳水化合物汇总',
  PRIMARY KEY (`udt_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户饮食汇总表';

/*Data for the table `djxt_user_diet_total` */

/*Table structure for table `djxt_user_ghb` */

DROP TABLE IF EXISTS `djxt_user_ghb`;

CREATE TABLE `djxt_user_ghb` (
  `u_Id` int(11) DEFAULT NULL COMMENT '用户id',
  `ugh_date` datetime DEFAULT NULL COMMENT '日期',
  `ugh_id` int(11) NOT NULL,
  `ugh_num` decimal(10,0) DEFAULT NULL COMMENT '糖化血蛋白',
  PRIMARY KEY (`ugh_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='糖化血蛋白记录表';

/*Data for the table `djxt_user_ghb` */

/*Table structure for table `djxt_user_health` */

DROP TABLE IF EXISTS `djxt_user_health`;

CREATE TABLE `djxt_user_health` (
  `u_Id` int(11) DEFAULT NULL COMMENT '用户id',
  `uh_Type` varchar(40) DEFAULT NULL COMMENT '糖尿病类型',
  `uh_FL` decimal(10,0) DEFAULT NULL COMMENT '腹围',
  `uh_WL` decimal(10,0) DEFAULT NULL COMMENT '腰围',
  `uh_BMI` decimal(10,0) DEFAULT NULL COMMENT 'BMI',
  `uh_BP` decimal(10,0) DEFAULT NULL COMMENT '血压',
  `uh_BF` decimal(10,0) DEFAULT NULL COMMENT '血脂',
  `uh_GHb` decimal(10,0) DEFAULT NULL COMMENT '糖化血红蛋白',
  `uh_Pic` varchar(100) DEFAULT NULL COMMENT '图片地址',
  `uh_Cur` varchar(100) DEFAULT NULL COMMENT '目前症状',
  `uh_Comp` varchar(100) DEFAULT NULL COMMENT '并发症',
  `uh_Comor` varchar(100) DEFAULT NULL COMMENT '合并疾病',
  `uh_Fam` varchar(100) DEFAULT NULL COMMENT '糖尿病家族史',
  `uh_Smoke` int(11) DEFAULT NULL COMMENT '是否吸烟',
  `uh_Id` int(11) NOT NULL,
  PRIMARY KEY (`uh_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员健康信息彪啊';

/*Data for the table `djxt_user_health` */

/*Table structure for table `djxt_user_info` */

DROP TABLE IF EXISTS `djxt_user_info`;

CREATE TABLE `djxt_user_info` (
  `u_Id` int(11) DEFAULT NULL COMMENT '用户id',
  `ui_Birth` datetime DEFAULT NULL COMMENT '用户出生日期',
  `ui_Type` int(11) DEFAULT NULL COMMENT '用户类型',
  `ui_Tall` double DEFAULT NULL COMMENT '身高',
  `ui_Weight` decimal(10,0) DEFAULT NULL COMMENT '体重',
  `ui_WorkTime` decimal(10,0) DEFAULT NULL COMMENT '体力劳动时间',
  `ui_DiaTime` datetime DEFAULT NULL COMMENT '确诊时间',
  `ui_Treat` int(11) DEFAULT NULL COMMENT '治疗方式',
  `ui_Id` int(11) NOT NULL,
  PRIMARY KEY (`ui_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户个人信息表';

/*Data for the table `djxt_user_info` */

/*Table structure for table `djxt_user_sport` */

DROP TABLE IF EXISTS `djxt_user_sport`;

CREATE TABLE `djxt_user_sport` (
  `us_Id` int(11) NOT NULL COMMENT '用户运动记录',
  `us_Date` datetime DEFAULT NULL COMMENT '运动日期',
  `s_Id` int(11) DEFAULT NULL COMMENT '运动项目id',
  `us_Time` int(11) DEFAULT NULL COMMENT '运动时间',
  `u_Id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`us_Id`),
  KEY `FK_djxt_user_sport` (`u_Id`),
  CONSTRAINT `FK_djxt_user_sport` FOREIGN KEY (`u_Id`) REFERENCES `djxt_user` (`u_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户运动记录表';

/*Data for the table `djxt_user_sport` */

/*Table structure for table `djxt_user_weight` */

DROP TABLE IF EXISTS `djxt_user_weight`;

CREATE TABLE `djxt_user_weight` (
  `uw_Id` int(11) NOT NULL COMMENT '用户体重id主键',
  `uw_Weight` decimal(10,0) DEFAULT NULL COMMENT '用户体重',
  `uw_BMI` decimal(10,0) DEFAULT NULL COMMENT 'bmi',
  `uw_Change` decimal(10,0) DEFAULT NULL COMMENT '变化',
  PRIMARY KEY (`uw_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户体重表';

/*Data for the table `djxt_user_weight` */

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `p_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200001 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`icon`,`name`,`state`,`url`,`p_id`) values (1,'menu-plugin','系统菜单',1,NULL,-1),(10,'&#xe68e;','食品管理',1,NULL,1),(60,'&#xe631;','系统管理',1,NULL,1),(61,'&#xe705;','会员管理',1,'admin/xuser/toxusermanage',1),(62,'&#xe631;','数据字典',1,'admin/dic',1),(1000,'icon-text','体质指数',2,'admin/food/tofoodBMI',10),(1010,'icon-text','食品录入',2,'admin/food/tofoodmanage',10),(6000,'&#xe631;','菜单管理',2,'admin/menu/tomunemanage',60),(6010,'icon-icon10','角色管理',2,'admin/role/torolemanage',60),(6020,'&#xe612;','用户管理',2,'admin/user/tousermanage',60),(6030,'&#xe631;','sql监控',2,'druid/index.html',60),(6040,'icon-ziliao','修改密码',2,'admin/user/toUpdatePassword',60),(6050,'icon-tuichu','安全退出',2,'user/logout',60),(6100,'&#xe60e;','会员列表',2,'admin/xuser/toxusermanage',61),(200000,'44','44',3,'44',2000);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bz` varchar(1000) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remarks` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`bz`,`name`,`remarks`) values (1,'系统管理员 最高权限','管理员',NULL),(2,'主管','主管',NULL),(4,'采购员','采购员',NULL),(5,'销售经理','销售经理','22'),(7,'仓库管理员','仓库管理员',NULL),(9,'总经理','总经理',NULL),(15,'111111','111111',NULL),(16,'222','222222',NULL),(17,'33','333333',NULL),(18,'444444','444444',NULL),(19,'555','555555',NULL),(20,'666666','666666',NULL);

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8;

/*Data for the table `t_role_menu` */

insert  into `t_role_menu`(`id`,`menu_id`,`role_id`) values (45,1,4),(48,1,5),(55,1,9),(65,1,7),(66,10,7),(126,60,15),(127,6010,15),(128,6020,15),(129,6030,15),(130,6040,15),(131,6050,15),(248,2000,1),(259,100000,1),(292,10,2),(293,60,2),(294,6000,2),(295,6010,2),(296,6020,2),(310,10,1),(311,1000,1),(312,1010,1),(313,60,1),(314,6000,1),(315,6010,1),(316,6020,1),(317,6030,1),(318,6040,1),(319,6050,1),(320,61,1),(321,6100,1),(322,62,1);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bz` varchar(1000) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `true_name` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `remarks` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`bz`,`password`,`true_name`,`user_name`,`remarks`) values (1,'管理员','1','点筋血糖','admin','1'),(2,'主管','123','王大锤','jack','2'),(3,'销售经理','123','玛丽','marry','33'),(5,'1','111111','1','111111',NULL),(6,'2','222222','2','222222',NULL),(7,'3','333333','3','333333',NULL),(8,'','444444','44','444444',NULL),(9,'5','555555','5','555555',NULL),(11,'7','777777','7','777777',NULL),(12,'88','888888','88','888888',NULL),(13,'9','999999','9','999999',NULL),(14,'121212','121212','121212','121212',NULL);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`id`,`role_id`,`user_id`) values (1,1,1),(19,2,2),(20,4,2),(21,5,2),(49,15,5),(50,1,5),(51,2,3),(52,4,3),(53,5,3),(54,7,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
