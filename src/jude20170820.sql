/*
Navicat MySQL Data Transfer

Source Server         : jude
Source Server Version : 50634
Source Host           : 122.114.81.92:3306
Source Database       : jude

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-08-20 19:31:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(5) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `fullId` varchar(20) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `underlingFlag` int(11) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3520 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_certification
-- ----------------------------
DROP TABLE IF EXISTS `t_certification`;
CREATE TABLE `t_certification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT '平台用户',
  `idcard` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1719 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_commodity_type
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity_type`;
CREATE TABLE `t_commodity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `typeName` varchar(30) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_dlb_account
-- ----------------------------
DROP TABLE IF EXISTS `t_dlb_account`;
CREATE TABLE `t_dlb_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountNo` varchar(30) DEFAULT NULL,
  `accountBalance` double(9,2) DEFAULT NULL,
  `totalPlatformIncomings` double(9,2) DEFAULT NULL,
  `totalPlatformOutgoings` double(9,2) DEFAULT NULL,
  `totalUserIncomings` double(9,2) DEFAULT NULL,
  `totalUserOutgoings` double(9,2) DEFAULT NULL,
  `totalReturnCash` double(9,2) DEFAULT '0.00',
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_dlb_account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1682 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_dlb_record
-- ----------------------------
DROP TABLE IF EXISTS `t_dlb_record`;
CREATE TABLE `t_dlb_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` int(11) DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `operateNum` double(9,2) DEFAULT NULL,
  `operateDesc` varchar(30) DEFAULT NULL,
  `accountId` int(11) DEFAULT NULL,
  `operateObjId` int(11) DEFAULT NULL,
  `balance` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `operateObjId` (`operateObjId`),
  CONSTRAINT `t_dlb_record_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `t_dlb_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3025 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_indent
-- ----------------------------
DROP TABLE IF EXISTS `t_indent`;
CREATE TABLE `t_indent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indentNo` varchar(20) DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_indent_details
-- ----------------------------
DROP TABLE IF EXISTS `t_indent_details`;
CREATE TABLE `t_indent_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `journalId` int(11) DEFAULT NULL,
  `commodityModelId` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `amount` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `journalId` (`journalId`),
  KEY `commodityModelId` (`commodityModelId`),
  CONSTRAINT `t_indent_details_ibfk_1` FOREIGN KEY (`journalId`) REFERENCES `t_online_journal` (`id`),
  CONSTRAINT `t_indent_details_ibfk_2` FOREIGN KEY (`commodityModelId`) REFERENCES `t_online_commodity_model` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_jdb_account
-- ----------------------------
DROP TABLE IF EXISTS `t_jdb_account`;
CREATE TABLE `t_jdb_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountNo` varchar(30) DEFAULT NULL,
  `accountBalance` double(9,2) DEFAULT NULL,
  `totalPlatformIncomings` double(9,2) DEFAULT NULL,
  `totalPlatformOutgoings` double(9,2) DEFAULT NULL,
  `totalUserIncomings` double(9,2) DEFAULT NULL,
  `totalUserOutgoings` double(9,2) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_jdb_account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1673 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_jdb_record
-- ----------------------------
DROP TABLE IF EXISTS `t_jdb_record`;
CREATE TABLE `t_jdb_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` int(11) DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `operateNum` double(9,2) DEFAULT NULL,
  `operateDesc` varchar(30) DEFAULT NULL,
  `accountId` int(11) DEFAULT NULL,
  `operateObjId` int(11) DEFAULT NULL,
  `balance` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `operateObjId` (`operateObjId`),
  CONSTRAINT `t_jdb_record_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `t_jdb_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34055 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_jf_account
-- ----------------------------
DROP TABLE IF EXISTS `t_jf_account`;
CREATE TABLE `t_jf_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountNo` varchar(30) DEFAULT NULL,
  `accountBalance` double(9,2) DEFAULT NULL,
  `totalPlatformIncomings` double(9,2) DEFAULT NULL,
  `totalPlatformOutgoings` double(9,2) DEFAULT NULL,
  `totalUserIncomings` double(9,2) DEFAULT NULL,
  `totalUserOutgoings` double(9,2) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_jf_account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1681 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_jf_record
-- ----------------------------
DROP TABLE IF EXISTS `t_jf_record`;
CREATE TABLE `t_jf_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` int(11) DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `operateNum` double(9,2) DEFAULT NULL,
  `operateDesc` varchar(30) DEFAULT NULL,
  `accountId` int(11) DEFAULT NULL,
  `operateObjId` int(11) DEFAULT NULL,
  `balance` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `operateObjId` (`operateObjId`),
  CONSTRAINT `t_jf_record_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `t_jf_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14069 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_journal_book
-- ----------------------------
DROP TABLE IF EXISTS `t_journal_book`;
CREATE TABLE `t_journal_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indentId` int(11) DEFAULT NULL,
  `businessId` int(11) DEFAULT NULL,
  `clientId` int(11) DEFAULT NULL,
  `rewardId` int(11) DEFAULT NULL,
  `commodityTypeId` int(11) DEFAULT NULL,
  `commodityName` varchar(50) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `premiumRates` float(4,2) DEFAULT NULL,
  `giftJf` float(9,1) DEFAULT NULL,
  `rewardJf` float(9,1) DEFAULT NULL,
  `journalTime` datetime DEFAULT NULL,
  `flag` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `commodityTypeId` (`commodityTypeId`),
  KEY `rewardId` (`rewardId`),
  KEY `businessId` (`businessId`),
  KEY `clientId` (`clientId`),
  KEY `indentId` (`indentId`),
  CONSTRAINT `t_journal_book_ibfk_1` FOREIGN KEY (`commodityTypeId`) REFERENCES `t_commodity_type` (`id`),
  CONSTRAINT `t_journal_book_ibfk_2` FOREIGN KEY (`rewardId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_journal_book_ibfk_3` FOREIGN KEY (`businessId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_journal_book_ibfk_4` FOREIGN KEY (`clientId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_journal_book_ibfk_5` FOREIGN KEY (`indentId`) REFERENCES `t_indent` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `requestIp` varchar(50) DEFAULT NULL,
  `exceptionCode` varchar(100) DEFAULT NULL,
  `exceptionDetail` varchar(200) DEFAULT NULL,
  `params` varchar(200) DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `method` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_money_account
-- ----------------------------
DROP TABLE IF EXISTS `t_money_account`;
CREATE TABLE `t_money_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountNo` varchar(30) DEFAULT NULL,
  `accountBalance` double(9,2) DEFAULT NULL,
  `totalPlatformIncomings` double(9,2) DEFAULT NULL,
  `totalPlatformOutgoings` double(9,2) DEFAULT NULL,
  `totalUserIncomings` double(9,2) DEFAULT NULL,
  `totalUserOutgoings` double(9,2) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_money_account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1681 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_money_record
-- ----------------------------
DROP TABLE IF EXISTS `t_money_record`;
CREATE TABLE `t_money_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` int(11) DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `operateNum` double(9,2) DEFAULT NULL,
  `operateDesc` varchar(30) DEFAULT NULL,
  `accountId` int(11) DEFAULT NULL,
  `operateObjId` int(11) DEFAULT NULL,
  `balance` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `operateObjId` (`operateObjId`),
  CONSTRAINT `t_money_record_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `t_money_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94666 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_online_commodity
-- ----------------------------
DROP TABLE IF EXISTS `t_online_commodity`;
CREATE TABLE `t_online_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodityName` varchar(100) NOT NULL,
  `commodityMainPic` varchar(100) DEFAULT NULL,
  `commodityDetailFileName` text,
  `commoditySequence` int(11) DEFAULT '0',
  `commodityTypeId` int(11) DEFAULT NULL,
  `onlineCommodityFlag` int(11) DEFAULT NULL,
  `onLineCommodityPutawayTime` datetime DEFAULT NULL,
  `onLineCommoditySoldoutTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `commodityTypeId` (`commodityTypeId`),
  CONSTRAINT `t_online_commodity_ibfk_1` FOREIGN KEY (`commodityTypeId`) REFERENCES `t_online_commodity_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_online_commodity_model
-- ----------------------------
DROP TABLE IF EXISTS `t_online_commodity_model`;
CREATE TABLE `t_online_commodity_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodityModel` varchar(255) DEFAULT NULL,
  `commodityPrice` double(12,2) DEFAULT NULL,
  `commodityDesc` varchar(1023) DEFAULT NULL,
  `commodityService` varchar(1023) DEFAULT NULL,
  `commodityRepertory` int(11) DEFAULT '0',
  `commodityFlag` int(11) DEFAULT '0',
  `commoditySalesVolume` int(11) DEFAULT '0',
  `commodityPutawayTime` datetime DEFAULT NULL,
  `commoditySoldoutTime` datetime DEFAULT NULL,
  `isDefaultModel` int(1) DEFAULT '0',
  `commoditySmallPic1` varchar(256) DEFAULT NULL,
  `commoditySmallPic2` varchar(256) DEFAULT NULL,
  `commoditySmallPic3` varchar(256) DEFAULT NULL,
  `commoditySmallPic4` varchar(256) DEFAULT NULL,
  `commoditySmallPic5` varchar(256) DEFAULT NULL,
  `commodityId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `commodityId` (`commodityId`),
  CONSTRAINT `t_online_commodity_model_ibfk_1` FOREIGN KEY (`commodityId`) REFERENCES `t_online_commodity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_online_commodity_type
-- ----------------------------
DROP TABLE IF EXISTS `t_online_commodity_type`;
CREATE TABLE `t_online_commodity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `typeName` varchar(30) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_online_journal
-- ----------------------------
DROP TABLE IF EXISTS `t_online_journal`;
CREATE TABLE `t_online_journal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(37) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `recvCommodityAddressId` int(11) DEFAULT NULL,
  `totalAmount` double(12,2) DEFAULT NULL,
  `receiveType` int(11) DEFAULT NULL,
  `postage` double(10,2) DEFAULT NULL,
  `postAddress` varchar(255) DEFAULT NULL,
  `postReceiveName` varchar(64) DEFAULT NULL,
  `postReceivePhone` varchar(32) DEFAULT NULL,
  `takeTheirStore` varchar(64) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `journalTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_online_journal_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_operation`;
CREATE TABLE `t_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(33) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `imgUrl` varchar(33) DEFAULT NULL,
  `style` varchar(33) DEFAULT NULL,
  `seqnum` int(11) DEFAULT NULL,
  `isMenu` int(11) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `t_operation_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `t_operation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_para_config
-- ----------------------------
DROP TABLE IF EXISTS `t_para_config`;
CREATE TABLE `t_para_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cashRatio` float(5,2) DEFAULT NULL,
  `yljRatio` float(5,2) DEFAULT NULL,
  `totalReCash` float(9,2) DEFAULT NULL,
  `cashByOneDlb` float(5,2) DEFAULT NULL,
  `vipJfRatio` float(5,2) DEFAULT NULL,
  `busJfRatio` float(5,2) DEFAULT NULL,
  `oneDlbTotalIncom` float(9,2) DEFAULT NULL,
  `checkLimitAmount` float(9,2) DEFAULT NULL,
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  `withdrawFlag` int(11) DEFAULT NULL,
  `giveFlag` int(11) DEFAULT NULL,
  `withdrawUpLimit` int(11) DEFAULT NULL,
  `inviterSuccessGiftJf` int(11) DEFAULT NULL,
  `beInviterSuccessGiftJf` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(33) NOT NULL,
  `operationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `operationId` (`operationId`),
  CONSTRAINT `t_permission_ibfk_1` FOREIGN KEY (`operationId`) REFERENCES `t_operation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_platform_dlb_distributed_record
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_dlb_distributed_record`;
CREATE TABLE `t_platform_dlb_distributed_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_platform_sy_distributed_record
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_sy_distributed_record`;
CREATE TABLE `t_platform_sy_distributed_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operationTime` datetime DEFAULT NULL,
  `dlbNum` int(11) DEFAULT NULL,
  `distributedMoney` double(9,2) DEFAULT NULL,
  `distributedYlj` double(9,2) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_platform_sy_distributed_record_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79631 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_platform_ylj_saved_record
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_ylj_saved_record`;
CREATE TABLE `t_platform_ylj_saved_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `savedDate` date DEFAULT NULL,
  `prevMonthBalance` double(9,2) DEFAULT NULL,
  `currMonthDistributed` double(9,2) DEFAULT NULL,
  `currMonthSaved` double(9,2) DEFAULT NULL,
  `currMonthBalance` double(9,2) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_platform_ylj_saved_record_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_recharge_withdraw_record
-- ----------------------------
DROP TABLE IF EXISTS `t_recharge_withdraw_record`;
CREATE TABLE `t_recharge_withdraw_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operateType` int(11) DEFAULT NULL,
  `orderNo` varchar(33) DEFAULT NULL,
  `operateNum` double DEFAULT NULL,
  `serviceNum` double DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `operateState` int(11) DEFAULT NULL,
  `operateUserIp` varchar(33) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNo` (`orderNo`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_recharge_withdraw_record_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3108 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_recv_commodity_address
-- ----------------------------
DROP TABLE IF EXISTS `t_recv_commodity_address`;
CREATE TABLE `t_recv_commodity_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(32) DEFAULT NULL,
  `city` varchar(32) DEFAULT NULL,
  `area` varchar(32) DEFAULT NULL,
  `extra` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `contacts` varchar(30) DEFAULT NULL,
  `isDefault` int(11) DEFAULT '1',
  `flag` int(11) DEFAULT '0',
  `lastModifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_recv_commodity_address_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(30) DEFAULT NULL,
  `roleGrade` int(11) DEFAULT NULL,
  `seqNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `t_role_perm`;
CREATE TABLE `t_role_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `permId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  KEY `permId` (`permId`),
  CONSTRAINT `t_role_perm_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_role_perm_ibfk_2` FOREIGN KEY (`permId`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sc_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_sc_notice`;
CREATE TABLE `t_sc_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noticeFileName` varchar(33) DEFAULT NULL,
  `noticeAddTime` datetime DEFAULT NULL,
  `noticeFlag` int(11) DEFAULT '0',
  `noticeTypeId` int(11) DEFAULT NULL,
  `noticeTitle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `noticeTypeId` (`noticeTypeId`),
  CONSTRAINT `t_sc_notice_ibfk_1` FOREIGN KEY (`noticeTypeId`) REFERENCES `t_sc_notice_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sc_notice_type
-- ----------------------------
DROP TABLE IF EXISTS `t_sc_notice_type`;
CREATE TABLE `t_sc_notice_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `typeName` varchar(30) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_shopping_mall
-- ----------------------------
DROP TABLE IF EXISTS `t_shopping_mall`;
CREATE TABLE `t_shopping_mall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mallPos_lat` double DEFAULT NULL,
  `mallPos_lnt` double DEFAULT NULL,
  `mallName` varchar(30) DEFAULT NULL,
  `mallDesc` varchar(255) DEFAULT '暂无简介',
  `mallMainPicUrl` varchar(255) DEFAULT NULL,
  `mallPicUrl1` varchar(255) DEFAULT NULL,
  `mallPicUrl2` varchar(255) DEFAULT NULL,
  `mallPicUrl3` varchar(255) DEFAULT NULL,
  `mallPicUrl4` varchar(255) DEFAULT NULL,
  `mallPicUrl5` varchar(255) DEFAULT NULL,
  `mallLinkMan` varchar(30) DEFAULT NULL,
  `mallPhone` varchar(20) DEFAULT NULL,
  `mallAddress` varchar(255) DEFAULT NULL,
  `locked` int(11) DEFAULT '0',
  `user_id` int(11) DEFAULT NULL,
  `proxy_user_id` int(11) DEFAULT NULL,
  `mallTypeId` int(11) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `proxy_user_id` (`proxy_user_id`),
  CONSTRAINT `t_shopping_mall_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_shopping_mall_ibfk_2` FOREIGN KEY (`proxy_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_shopping_mall_type
-- ----------------------------
DROP TABLE IF EXISTS `t_shopping_mall_type`;
CREATE TABLE `t_shopping_mall_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `typeName` varchar(30) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=255 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openId` varchar(32) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `province` varchar(32) DEFAULT NULL,
  `city` varchar(32) DEFAULT NULL,
  `area` varchar(32) DEFAULT NULL,
  `phone` varchar(22) DEFAULT NULL,
  `headImgUrl` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `certificationId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT '2',
  `jfAccountId` int(11) DEFAULT NULL,
  `dlbAccountId` int(11) DEFAULT NULL,
  `moneyAccountId` int(11) DEFAULT NULL,
  `yljAccountId` int(11) DEFAULT NULL,
  `jdbAccountId` int(11) DEFAULT NULL,
  `proxy_user_id` int(11) DEFAULT NULL,
  `getCashFlag` int(11) DEFAULT '0',
  `incomFlag` int(11) DEFAULT '0',
  `addTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `openId` (`openId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1684 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_account
-- ----------------------------
DROP TABLE IF EXISTS `t_user_account`;
CREATE TABLE `t_user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accoutType` int(11) DEFAULT NULL,
  `accountNo` varchar(30) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `accountNo` (`accountNo`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_user_account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_extra
-- ----------------------------
DROP TABLE IF EXISTS `t_user_extra`;
CREATE TABLE `t_user_extra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `inviterUserId` int(11) DEFAULT NULL,
  `inviterRewardFlag` int(11) DEFAULT NULL,
  `beInviterRewardFlag` int(11) DEFAULT NULL,
  `sendPushMsgFlag` int(11) DEFAULT NULL,
  `giveMoneyFlag` int(11) DEFAULT NULL,
  `withdrawLimit` int(11) DEFAULT '100',
  `delFlag` int(11) DEFAULT NULL,
  `extra` varchar(50) DEFAULT NULL,
  `addTime` datetime DEFAULT NULL,
  `lmtTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `inviterUserId` (`inviterUserId`),
  CONSTRAINT `t_user_extra_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_user_extra_ibfk_2` FOREIGN KEY (`inviterUserId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1666 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ylj_account
-- ----------------------------
DROP TABLE IF EXISTS `t_ylj_account`;
CREATE TABLE `t_ylj_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountNo` varchar(30) DEFAULT NULL,
  `accountBalance` double(9,2) DEFAULT NULL,
  `totalPlatformIncomings` double(9,2) DEFAULT NULL,
  `totalPlatformOutgoings` double(9,2) DEFAULT NULL,
  `totalUserIncomings` double(9,2) DEFAULT NULL,
  `totalUserOutgoings` double(9,2) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_ylj_account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1680 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ylj_record
-- ----------------------------
DROP TABLE IF EXISTS `t_ylj_record`;
CREATE TABLE `t_ylj_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` int(11) DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `operateNum` double(9,2) DEFAULT NULL,
  `operateDesc` varchar(30) DEFAULT NULL,
  `accountId` int(11) DEFAULT NULL,
  `operateObjId` int(11) DEFAULT NULL,
  `balance` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `operateObjId` (`operateObjId`),
  CONSTRAINT `t_ylj_record_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `t_ylj_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79651 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wdzlog
-- ----------------------------
DROP TABLE IF EXISTS `wdzlog`;
CREATE TABLE `wdzlog` (
  `WDZLOGID` int(11) NOT NULL AUTO_INCREMENT,
  `LogName` varchar(255) DEFAULT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `Class` varchar(255) DEFAULT NULL,
  `Mothod` varchar(255) DEFAULT NULL,
  `CreateTime` varchar(255) DEFAULT NULL,
  `LogLevel` varchar(20) DEFAULT NULL,
  `MSG` varchar(555) DEFAULT NULL,
  PRIMARY KEY (`WDZLOGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
