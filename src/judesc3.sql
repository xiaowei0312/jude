DROP TABLE IF EXISTS `t_online_commodity_type`;
-- 线上商品分类表
CREATE TABLE `t_online_commodity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `typeName` varchar(30) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `t_online_commodity`;
-- 线上商品表
create table t_online_commodity (
   `id` int(11) NOT NULL AUTO_INCREMENT,
    commodityName varchar(1023) Not NULL,  -- 名称
    commodityMainPic varchar(255),
    commodityDetailFileName varchar(255), -- 商品详情页文件名
   	commoditySequence int(11) DEFAULT 0,
   	
   	-- added on 2017.03.05
   	onlineCommodityFlag    int(11)     default 0,   --  商品状态   0 上架   1 下架  2 删除
   	onLineCommodityPutawayTime  datetime,		-- 上架时间
    onLineCommoditySoldoutTime  datetime,		-- 下架时间
    
   	commodityTypeId   int(11),
    PRIMARY KEY (`id`),
    FOREIGN KEY (commodityTypeId)  REFERENCES  t_online_commodity_type (id)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;
-- 线上商品型号表
DROP TABLE IF EXISTS `t_online_commodity_model`;
create table t_online_commodity_model (
		`id` int(11) NOT NULL AUTO_INCREMENT,
    commodityModel  varchar(255),         -- 型号    
    commodityPrice  double(12,2),	-- 价格
    commodityDesc  varchar(1023),      -- 简介
    commodityService  varchar(1023),    -- 服务
    commodityRepertory   int(11)  default 0,    -- 库存
    commodityFlag    int(11)     default 0,   --  商品状态   0 上架   1 下架  2 删除
    commoditySalesVolume   int(11)  default 0,  -- 销量
    commodityPutawayTime  datetime,	-- 上架时间
    commoditySoldoutTime  datetime,		-- 下架时间
    isDefaultModel int(1) default 0, -- 是否默认型号，0不是，1默认
 
    commoditySmallPic1 varchar(32),			--	小图片1
    commoditySmallPic2 varchar(32),			--	小图片2
    commoditySmallPic3 varchar(32),			--	小图片3
    commoditySmallPic4 varchar(32),			--	小图片4
    commoditySmallPic5 varchar(32),			--	小图片5
    
    commodityId int(11),		-- 商品ID
    PRIMARY KEY (`id`),	
    FOREIGN KEY (commodityId)  REFERENCES  t_online_commodity (id)
)ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

insert into t_online_commodity_type(id,parentId,typeName,sequence) values(1,NULL,'家用电器',1);
insert into t_online_commodity_type(id,parentId,typeName,sequence) values(2,NULL,'手机数码',2);
insert into t_online_commodity_type(id,parentId,typeName,sequence) values(3,NULL,'电脑、办公',3);
insert into t_online_commodity_type(id,parentId,typeName,sequence) values(4,NULL,'家居、家具',4);
insert into t_online_commodity_type(id,parentId,typeName,sequence) values(5,NULL,'食品、酒类、特产',5);
insert into t_online_commodity_type(id,parentId,typeName,sequence) values(6,NULL,'营养保健',6);
insert into t_online_commodity_type(id,parentId,typeName,sequence) values(7,NULL,'汽车、房产',7);
insert into t_online_commodity_type(id,parentId,typeName,sequence) values(8,NULL,'珠宝、首饰',8);

insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(1,'iphone 6s','main.jpg','12345.html',1,2);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(2,'Mac Book Pro 2016','main.jpg','12345.html',2,3);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(3,'Ipad Air 2','main.jpg','12345.html',3,3);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(4,'iphone 7s','main.jpg','12345.html',1,2);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(5,'Mac Book Pro 2017','main.jpg','12345.html',2,3);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(6,'Ipad Air 3','main.jpg','12345.html',3,3);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(7,'iphone 8s','main.jpg','12345.html',1,2);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(8,'Mac Book Pro 2018','main.jpg','12345.html',2,3);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(9,'Ipad Air 4','main.jpg','12345.html',3,3);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(10,'iphone 9s','main.jpg','12345.html',1,2);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(11,'Mac Book Pro 2019','main.jpg','12345.html',2,3);
insert into t_online_commodity(id,commodityName,commodityMainPic,commodityDetailFileName,commoditySequence,commodityTypeId)
	values(12,'Ipad Air 5','main.jpg','12345.html',3,3);

insert into t_online_commodity_model values(1,'金色',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',1);
insert into t_online_commodity_model values(2,'银色',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',1);
insert into t_online_commodity_model values(3,'深空灰',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',1);
insert into t_online_commodity_model values(4,'玫瑰金',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',1);

insert into t_online_commodity_model values(5,'16G',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',3);
insert into t_online_commodity_model values(6,'32G',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',3);
insert into t_online_commodity_model values(7,'64G',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',3);
insert into t_online_commodity_model values(8,'128G',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',3);

insert into t_online_commodity_model values(9,'i7',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',2);
insert into t_online_commodity_model values(10,'i5',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',2);
insert into t_online_commodity_model values(11,'i3',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',2);
insert into t_online_commodity_model values(12,'Dual Core',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',2);

insert into t_online_commodity_model values(13,'金色',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',4);
insert into t_online_commodity_model values(14,'银色',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',4);
insert into t_online_commodity_model values(15,'深空灰',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',4);
insert into t_online_commodity_model values(16,'玫瑰金',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',4);

insert into t_online_commodity_model values(17,'16G',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',6);
insert into t_online_commodity_model values(18,'32G',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',6);
insert into t_online_commodity_model values(19,'64G',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',6);
insert into t_online_commodity_model values(20,'128G',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',6);

insert into t_online_commodity_model values(21,'i7',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',5);
insert into t_online_commodity_model values(22,'i5',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',5);
insert into t_online_commodity_model values(23,'i3',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',5);
insert into t_online_commodity_model values(24,'Dual Core',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',5);

insert into t_online_commodity_model values(25,'金色',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',7);
insert into t_online_commodity_model values(26,'银色',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',7);
insert into t_online_commodity_model values(27,'深空灰',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',7);
insert into t_online_commodity_model values(28,'玫瑰金',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',7);

insert into t_online_commodity_model values(29,'16G',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',9);
insert into t_online_commodity_model values(30,'32G',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',9);
insert into t_online_commodity_model values(31,'64G',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',9);
insert into t_online_commodity_model values(32,'128G',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',9);

insert into t_online_commodity_model values(33,'i7',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',8);
insert into t_online_commodity_model values(34,'i5',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',8);
insert into t_online_commodity_model values(35,'i3',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',8);
insert into t_online_commodity_model values(36,'Dual Core',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',8);

insert into t_online_commodity_model values(37,'金色',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',10);
insert into t_online_commodity_model values(38,'银色',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',10);
insert into t_online_commodity_model values(39,'深空灰',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',10);
insert into t_online_commodity_model values(40,'玫瑰金',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',10);

insert into t_online_commodity_model values(41,'16G',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',12);
insert into t_online_commodity_model values(42,'32G',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',12);
insert into t_online_commodity_model values(43,'64G',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',12);
insert into t_online_commodity_model values(44,'128G',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',12);

insert into t_online_commodity_model values(45,'i7',6288.00,null,null,100,0,2000,now(),null,1,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',11);
insert into t_online_commodity_model values(46,'i5',6188.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',11);
insert into t_online_commodity_model values(47,'i3',6388.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',11);
insert into t_online_commodity_model values(48,'Dual Core',6488.00,null,null,100,0,2000,now(),null,0,'s1.jpg','s2,jpg','s3.jpg','s4.jpg','s5.jpg',11);


-- 通知类型表
DROP TABLE IF EXISTS `t_sc_notice_type`;
CREATE TABLE `t_sc_notice_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `typeName` varchar(30) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;
-- 通知表
DROP TABLE IF EXISTS `t_sc_notice`;
CREATE TABLE `t_sc_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noticeTitle` varchar(100) DEFAULT NULL,	-- 通知标题
  `noticeFileName` varchar(33) DEFAULT NULL,	-- 通知文件名(制作通告时生成.html文件)
  `noticeAddTime` datetime DEFAULT NULL,
  `noticeFlag` int DEFAULT 0,		-- 通知状态（0可用 1 禁用）
  `noticeTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`noticeTypeId`) references t_sc_notice_type(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

insert into t_sc_notice_type(id,parentId,typeName,sequence) values(1,NULL,'商城公告',1);
insert into t_sc_notice_type(id,parentId,typeName,sequence) values(2,NULL,'养老咨询',2);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(1,'公告1','1.html',now(),0,1);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(2,'公告2','2.html',now(),0,1);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(3,'公告3','3.html',now(),0,1);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(4,'公告4','4.html',now(),0,1);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(5,'公告5','5.html',now(),0,1);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(6,'公告1','6.html',now(),0,1);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(7,'公告2','7.html',now(),0,2);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(8,'公告3','8.html',now(),0,2);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(9,'公告4','9.html',now(),0,2);
insert into t_sc_notice(id,noticeTitle,noticeFileName,noticeAddTime,noticeFlag,noticeTypeId) values(10,'公告5','10.html',now(),0,2);


-- 聚得币账户信息表
DROP TABLE IF EXISTS `t_jdb_account`;
-- 聚德币账户表
CREATE TABLE `t_jdb_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountNo` varchar(30) DEFAULT NULL,		--	帐号
  `accountBalance` double(9,2) DEFAULT NULL,	--	余额
  `totalPlatformIncomings` double(9,2) DEFAULT NULL,	--	平台累计收入（奖励）
  `totalPlatformOutgoings` double(9,2) DEFAULT NULL,	--	平台累计支出（消费）
  `totalUserIncomings` double(9,2) DEFAULT NULL,	--	用户累计转入（用户转入）
  `totalUserOutgoings` double(9,2) DEFAULT NULL,	--  用户累计转出（用户转出->提现）
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_jdb_account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=310 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `t_jdb_record`;
-- 聚德币记录表
CREATE TABLE `t_jdb_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` int(11) DEFAULT NULL,		-- 操作类型（0转入,1转出）
  `operateTime` datetime DEFAULT NULL,		-- 操作时间
  `operateNum` double DEFAULT NULL,		 -- 数量
  `operateDesc` varchar(30) DEFAULT NULL,	-- 描述信息
  `accountId` int(11) DEFAULT NULL,		-- 对应Account
  `operateObjId` int(11) DEFAULT NULL,		-- 操作对象
  PRIMARY KEY (`id`),	
  KEY `accountId` (`accountId`),
  KEY `operateObjId` (`operateObjId`),
  CONSTRAINT `t_jdb_record_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `t_jf_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=678 DEFAULT CHARSET=utf8;

-- 线上订单表


-- 线上购物订单表
drop table if exists t_online_journal;
create table  t_online_journal(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	-- 订单编号，添加于添加于2017/2/13 07:29
	orderNo varchar(37),
	userId   int(11),	
	-- recvCommodityAddressId 添加于2017/2/13 07:27
	recvCommodityAddressId int(11), -- 收货地址
	totalAmount  double(12,2),	-- 总金额
	receiveType   int,		--  收货方式   0 自提   1 邮寄
	postage	    double(10,2),	-- 邮费
	postAddress  varchar(255),	-- 邮寄地址
	postReceiveName  varchar(64),    -- 邮寄接收人名
	postReceivePhone  varchar(32),   -- 邮寄接受人电话
	takeTheirStore  varchar(64),	-- 自提门店  
	status   int,  		--  状态 -1已退货  0已付款   1 已发货    2 确认收货
	journalTime   datetime,
	PRIMARY KEY (`id`),
  FOREIGN KEY (userId)  REFERENCES  t_user (id)
)ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

-- 订单详情表
drop table if exists t_indent_details;
create table t_indent_details(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	journalId  int,		-- 订单id
	commodityModelId  int,	-- 商品模型id
	num  int,		  -- 数量
	amount   double(10,2),     --  单价
	PRIMARY KEY (`id`),
    	FOREIGN KEY (journalId)  REFERENCES  t_online_journal (id),
	FOREIGN KEY (commodityModelId)  REFERENCES  t_online_commodity_model (id)
)ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

drop table if exists t_recv_commodity_address;
create table t_recv_commodity_address(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`province` varchar(32) DEFAULT NULL,
  `city` varchar(32) DEFAULT NULL,
  `area` varchar(32) DEFAULT NULL,
  `extra` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `contacts` varchar(30) DEFAULT NULL,
	`isDefault` int DEFAULT 1,
  `flag` int DEFAULT 0, -- 0正常,1禁用
  `lastModifyTime` TIMESTAMP,
  `userId` int DEFAULT NULL,
	PRIMARY KEY (`id`),
  FOREIGN KEY (userId)  REFERENCES  t_user(id)
)ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

insert into t_recv_commodity_address(id,province,city,area,extra,phone,contacts,userId) values(1,'山西省','大同市','大同县','xxx街道xxx小区','13131313131','MrsC',3);
insert into t_recv_commodity_address(id,province,city,area,extra,phone,contacts,userId) values(2,'山西省','大同市','大同县','xxx街道xxx小区','13131313132','MrsC',3);
insert into t_recv_commodity_address(id,province,city,area,extra,phone,contacts,userId) values(3,'山西省','大同市','大同县','xxx街道xxx小区','13131313133','MrsC',3);