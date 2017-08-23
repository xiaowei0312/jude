drop database if exists jude;
create database jude DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use jude;

create table t_operation(
	id int auto_increment,
	name varchar(33),
	url varchar(256),
	imgUrl varchar(33),	--	 菜单图标
	style varchar(33), -- 显示样式
	seqnum int, --  序号（用于显示排号）
	isMenu int,	--	是否作为左栏菜单显示
	parentId int,	--	父菜单ID
	primary key(id),
	foreign key(parentId) references t_operation(id)
);

create table t_permission(
	id int auto_increment,
	name varchar(33) not null,
	operationId int,
	primary key(id),
	foreign key(operationId) references t_operation(id)
);

create table t_role(
	id int auto_increment,
	roleName varchar(30),
	roleGrade int,
	seqNum int,
	primary key(id)
);

create table t_role_perm(
	id int auto_increment,
	roleId int,
	permId int,
	primary key(id),
	foreign key(roleId) references t_role(id),
	foreign key(permId) references t_permission(id)
);

create table t_certification(
	id int auto_increment,
	name varchar(30) default '平台用户',
	idcard varchar(22),
	primary key(id)
);

drop table if exists t_user;
create table t_user(
	id int auto_increment,
	openId varchar(32) unique,	-- Wechat openid
	username varchar(32) unique, -- email or phone
	password varchar(128), 		-- password
	province varchar(32),
	city varchar(32),
	area varchar(32),
	phone varchar(22),
	headImgUrl varchar(255),
	status int default 0,	--	0正常 1禁用
	addTime timestamp,   --  创建时间
	
	certificationId int,  		--	实名认证信息
	roleId int default 2,		-- 角色	
	jfAccountId int,				-- 积分账户
	dlbAccountId int,				-- 得利宝账户
	moneyAccountId int,				-- 现金账户
	yljAccountId int,				-- 养老金账户
	-- shoppingMallId int default -1,	-- 商铺ID
	primary key(id)
) ;

-- 商户类型表
DROP TABLE IF EXISTS `t_shopping_mall_type`;
-- 线上商品分类表
CREATE TABLE `t_shopping_mall_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `typeName` varchar(30) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

drop table if exists t_shopping_mall;
create table t_shopping_mall(
	id int auto_increment,
	mallPos_lat double,		--	纬度
	mallPos_lnt double,		--	经度
	mallName varchar(30),	--	商铺名称
	mallDesc varchar(255) default "暂无简介",	--	商铺描述
	mallMainPicUrl varchar(255), -- 商铺封面图片
	mallPicUrl1 varchar(255), -- 商铺图片1，最多支持五张图片
	mallPicUrl2 varchar(255), -- 商铺图片2
	mallPicUrl3 varchar(255), -- 商铺图片3
	mallPicUrl4 varchar(255), -- 商铺图片4
	mallPicUrl5 varchar(255), -- 商铺图片5
	mallLinkMan varchar(30), -- 联系人
	mallPhone varchar(20),  --  联系电话
	mallAddress varchar(20), -- 地理位置描述
	locked int default 0,	--  锁定状态
	user_id int,		--  卖家ID
	proxy_user_id int,	--	代理商ID
	mallTypeId int,			--	商户类型ID
	createTime timeStamp,	--  添加时间/最后修改时间
	primary key(id),
	foreign key(user_id) references t_user(id),
	foreign key(proxy_user_id) references t_user(id),
	foreign key(mallTypeId) references t_shopping_mall_type(id)
);

create table t_user_account(
	id int primary key auto_increment,
	accoutType int, -- 账户类型：银行卡账户，养老金账户，微信账户
	accountNo varchar(30) unique, -- 账号
	userId int, -- 对应用户
	foreign key(userId) references t_user(id)
);

create table t_jf_account(
	id int auto_increment,
	accountNo varchar(30),		--	帐号
	accountBalance double,				--	余额
	totalPlatformIncomings double,		--	平台累计收入（奖励）
	totalPlatformOutgoings double,		--	平台累计支出（消费）
	totalUserIncomings double,			--	用户累计转入（用户转入）
	totalUserOutgoings double,			--  用户累计转出（用户转出->提现）
	userId int,
	primary key(id),
	foreign key(userId) references t_user(id)
);

create table t_dlb_account(
	id int auto_increment,
	accountNo varchar(30),
	accountBalance double,
	totalPlatformIncomings double,		--	累计收入（奖励）
	totalPlatformOutgoings double,		--	累计支出（消费）
	totalUserIncomings double,			--	累计转入（用户转入）
	totalUserOutgoings double,			--  累计转出（用户转出->提现）
	totalReturnCash double default 0.0,
	userId int,
	primary key(id),
	foreign key(userId) references t_user(id)
);

create table t_money_account(
	id int auto_increment,
	accountNo varchar(30),
	accountBalance double,
	totalPlatformIncomings double,		--	累计收入（奖励）
	totalPlatformOutgoings double,		--	累计支出（消费）
	totalUserIncomings double,			--	累计转入（用户转入）
	totalUserOutgoings double,			--  累计转出（用户转出->提现）
	userId int,
	primary key(id),
	foreign key(userId) references t_user(id)
);

create table t_ylj_account(
	id int auto_increment,
	accountNo varchar(30),
	accountBalance double,
	totalPlatformIncomings double,		--	累计收入（奖励）
	totalPlatformOutgoings double,		--	累计支出（消费）
	totalUserIncomings double,			--	累计转入（用户转入）
	totalUserOutgoings double,			--  累计转出（用户转出->提现）
	userId int,
	primary key(id),
	foreign key(userId) references t_user(id)
);

create table t_jf_record(
	id int auto_increment,
	operation int,	-- 操作类型（0转入,1转出）
	operateTime datetime, -- 操作时间
	operateNum double, -- 数量
	operateDesc varchar(30), -- 描述信息
	accountId int, -- 对应Account
	operateObjId int, -- 操作对象
	primary key(id),
	foreign key(accountId) references t_jf_account(id),
	foreign key(operateObjId)  references t_user_account(id)
);

create table t_dlb_record(
	id int auto_increment,
	operation int,	-- 操作类型（0转入,1转出）
	operateTime datetime, -- 操作时间
	operateNum double, -- 数量
	operateDesc varchar(30), -- 描述信息
	accountId int, -- 对应Account
	operateObjId int, -- 操作对象
	primary key(id),
	foreign key(accountId) references t_dlb_account(id),
	foreign key(operateObjId)  references t_user_account(id)
);

create table t_money_record(
	id int auto_increment,
	operation int,	-- 操作类型（0转入,1转出）
	operateTime datetime, -- 操作时间
	operateNum double, -- 数量
	operateDesc varchar(30), -- 描述信息
	accountId int, -- 对应Account
	operateObjId int, -- 操作对象
	primary key(id),
	foreign key(accountId) references t_money_account(id),
	foreign key(operateObjId)  references t_user_account(id)
);

create table t_ylj_record(
	id int auto_increment,
	operation int,	-- 操作类型（0转入,1转出）
	operateTime datetime, -- 操作时间
	operateNum double, -- 数量
	operateDesc varchar(30), -- 描述信息
	accountId int, -- 对应Account
	operateObjId int, -- 操作对象
	primary key(id),
	foreign key(accountId) references t_ylj_account(id),
	foreign key(operateObjId)  references t_user_account(id)
);

-- 平台养老金转存记录
drop table if exists t_platform_ylj_saved_record;
create table t_platform_ylj_saved_record(
	id int auto_increment,
	savedDate Date, 		-- 操作年月
	prevMonthBalance double,	-- 上月转入
	currMonthDistributed double,	-- 当月累计
	currMonthSaved double,	-- 当月转存
	currMonthBalance double,	-- 当月结余
	userId int, -- 对应Account
	primary key(id),
	foreign key(userId) references t_user(id)
);

-- 平台收益下发记录
drop table if exists t_platform_sy_distributed_record;
create table t_platform_sy_distributed_record(
	id int auto_increment,
	operationTime datetime, -- 操作时间
	dlbNum int,				-- 得利宝数量
	distributedMoney double,	--	分发余额
	distributedYlj double,		--	分发养老金
	userId int,					--	用户ID
	primary key(id),
	foreign key(userId) references t_user(id)
);

-- 平台得利宝下发记录，暂时还没想好字段
drop table if exists t_platform_dlb_distributed_record;
create table t_platform_dlb_distributed_record(
	id int auto_increment,
	primary key(id)
);

drop table if exists t_address;
create table t_address(
	id int auto_increment,
	code varchar(5),
	parentId int,
	name varchar(20),
	fullId varchar(20),
	grade int,
	underlingFlag int,
	sequence int,
	primary key(id)
);

drop table if exists t_indent;
create table t_indent(
	id int primary key auto_increment,
	indentNo   varchar(20),
	flag    int default 0     --  是否使用  0未使用  1已使用
);


-- 商品分类表
drop table if exists t_commodity_type;
create table t_commodity_type(
	id int auto_increment,
	parentId int,
	typeName varchar(30),
	sequence int,
	primary key(id)
);

--  线下报单流水表
drop table if exists t_journal_book;
create table  t_journal_book(
	id int primary key auto_increment,
	indentId  int,    -- 订单号  由平台下发单据生成
	businessId int,	   -- 商户id
	clientId int,		   -- 客户id
	rewardId int,			-- 奖励用户id
	commodityTypeId  int,			--	商品类型
	commodityName  varchar(50),   -- 商品名称
	amount   double,	      -- 交易金额
	premiumRates  float(4,2),   --  优惠率
	giftJf int,					--  获赠积分(用户)
	rewardJf int,				--	奖励积分(商家)
	journalTime  datetime,		-- 报单时间
	flag  int default 1,	-- 是否处理  0未审核  1通过审核但未处理 2通过审核且已处理  3未通过审核
	foreign key(commodityTypeId) references t_commodity_type(id),
	foreign key(rewardId) references t_user(id),
	foreign key(businessId) references t_user(id),
	foreign key(clientId) references  t_user(id),
	foreign key(indentId) references  t_indent(id)  		
);

-- 提现充值表
drop table if exists t_recharge_withdraw_record;
create table t_recharge_withdraw_record(
	id int auto_increment,
	operateType int,	-- 0 recharge, 1 withdraw
	orderNo varchar(33) unique,	-- Order No
	operateNum double, -- num yuan
	serviceNum double, -- 提现手续费
	operateTime datetime, 	-- 操作时间
	operateState int, -- 0 已报单待支付  1支付失败  2支付成功
	operateUserIp varchar(33), -- 用户ip地址
	userId int,
	primary key(id),
	foreign key(userId) references t_user(id)
);

-- 参数信息表
CREATE TABLE `t_para_config` (
`id`  int NOT NULL AUTO_INCREMENT ,
`cashRatio`  float(5,2) NULL ,     -- 现金比例
`yljRatio`  float(5,2) NULL ,	   -- 养老金比例
`totalReCash`  float(9,2) NULL ,   -- 每日返还总额
`cashByOneDlb`  float(5,2) NULL ,  -- 每日每个聚财宝返还金额
`vipJfRatio`  float(5,2) NULL ,	   --  会员积分获得比例	
`busJfRatio`  float(5,2) NULL ,    --  商家积分获得比例
`oneDlbTotalIncom`  float(9,2) NULL ,  -- 得利宝收入上限
`beginTime`  datetime NULL ,          -- 启用时间
`endTime`  datetime NULL ,          -- 停用时间
`flag`  int NULL DEFAULT 0 ,           -- 启用标志  0为启用，1为停用 只能有一条记录为0
PRIMARY KEY (`id`)
);