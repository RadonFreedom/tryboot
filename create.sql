show variables like 'character%';
set character_set_database = utf8mb4;
set character_set_server = utf8mb4;
show variables like '%time_zone%';

create database if not exists auth_service;
use auth_service;

drop table if exists user;
create table user
(
    id           bigint unsigned NOT NULL AUTO_INCREMENT,
    username     varchar(50)     NOT NULL unique,
    password     varchar(60)     NOT NULL,
    email        varchar(50)     NOT NULL unique,
    create_time  char(19)        not null,
#     gmt_create   datetime default CURRENT_TIMESTAMP COMMENT '更新时间',
#     gmt_modified datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB;

create database if not exists good_service;
use good_service;

DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    `good_name`   varchar(16)     not null COMMENT '商品名称',
    `good_title`  varchar(64)     not null COMMENT '商品标题',
    `good_img`    varchar(64)    DEFAULT NULL COMMENT '商品的图片',
    `good_detail` longtext COMMENT '商品的详情介绍',
    `good_price`  decimal(10, 2) DEFAULT '0.00' COMMENT '商品单价',
    `good_stock`  int(11)        DEFAULT '0' COMMENT '商品库存，-1表示没有限制',
    gmt_create    datetime       default CURRENT_TIMESTAMP COMMENT '更新时间',
    gmt_modified  datetime       default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

INSERT INTO `good` (good_name, good_title, good_img, good_detail, good_price, good_stock)
VALUES ('iphoneX', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', '/img/iphonex.png',
        'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', '8765.00', '10000'),
       ('华为Meta9', '华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '/img/meta10.png',
        '华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '3212.00', '10000'),
       ('iphone8', 'Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机', '/img/iphone8.png',
        'Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机', '5589.00', '10000'),
       ('小米6', '小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '/img/mi6.png', '小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待', '3212.00',
        '10000');


DROP TABLE IF EXISTS `seckill_good`;
CREATE TABLE `seckill_good`
(
    `id`            bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '秒杀的商品表',
    `good_id`       bigint(20)     DEFAULT NULL COMMENT '商品Id',
    `seckill_price` decimal(10, 2) DEFAULT '0.00' COMMENT '秒杀价',
    `stock_count`   int(11)        DEFAULT NULL COMMENT '库存数量',
    `start_date`    datetime       default CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
    `end_date`      datetime       default null COMMENT '秒杀结束时间',
    gmt_create      datetime       default CURRENT_TIMESTAMP COMMENT '更新时间',
    gmt_modified    datetime       default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `seckill_good`
    (good_id, seckill_price, stock_count, end_date)
VALUES ('1', '0.01', '9', '2019-12-31 21:51:27'),
       ('2', '0.01', '9', '2019-12-31 14:00:24'),
       ('3', '0.01', '9', '2019-12-31 14:00:24'),
       ('4', '0.01', '9', '2019-12-31 14:00:24');

DROP TABLE IF EXISTS `seckill_order`;
CREATE TABLE `seckill_order`
(
    `id`               bigint unsigned NOT NULL AUTO_INCREMENT,
    username           varchar(20)     NOT NULL,
    seckill_good_id    bigint(20)     DEFAULT NULL COMMENT '商品ID',
    good_id            bigint(20)     DEFAULT NULL COMMENT '商品ID',
    `delivery_info_id` bigint(20)     DEFAULT NULL COMMENT '收获地址ID',
    `good_name`        varchar(16)    DEFAULT NULL COMMENT '冗余过来的商品名称',
    `good_count`       int(11)        DEFAULT '0' COMMENT '商品数量',
    `good_price`       decimal(10, 2) DEFAULT '0.00' COMMENT '商品单价',
    seckill_price      decimal(10, 2) DEFAULT '0.00' COMMENT '秒杀价',
    `order_channel`    tinyint(4)     DEFAULT '0' COMMENT '1pc，2android，3ios',
    `status`           tinyint(4)     DEFAULT '0' COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
    gmt_create         datetime       default CURRENT_TIMESTAMP COMMENT '订单的创建时间',
    `pay_date`         datetime       DEFAULT NULL COMMENT '支付时间',
    gmt_modified       datetime       default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY pk_id (`id`),
    UNIQUE KEY `uk_good_id_username` (seckill_good_id, `username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;



# DROP TABLE IF EXISTS `seckill_message`;
# CREATE TABLE `seckill_message`
# (
#     `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '消息主键',
#     `message_id`   bigint(20)      NOT NULL COMMENT '分布式id',
#     `content`      text COMMENT '消息内容',
#     `create_time`  date           DEFAULT NULL COMMENT '创建时间',
#     `status`       int(1)          NOT NULL COMMENT '1 有效 2 失效 ',
#     `over_time`    datetime       DEFAULT NULL COMMENT '结束时间',
#     `message_type` int(1)         DEFAULT '3' COMMENT '0 秒杀消息 1 购买消息 2 推送消息',
#     `send_type`    int(1)         DEFAULT '3' COMMENT '发送类型 0 app 1 pc 2 ios',
#     `good_name`    varchar(50)    DEFAULT '' COMMENT '商品名称',
#     `price`        decimal(10, 2) DEFAULT '0.00' COMMENT '商品价格',
#     gmt_create     datetime       default CURRENT_TIMESTAMP COMMENT '更新时间',
#     gmt_modified   datetime       default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8;
#
# INSERT INTO `seckill_message`
# VALUES ('1', '533324506110885888', '尊敬的用户你好，你已经成功注册！', null, '0', null, null, '0', null, null);
# INSERT INTO `seckill_message`
# VALUES ('2', '533324506110885888', '尊敬的用户你好，你已经成功注册！', null, '0', null, null, '0', null, null);
# INSERT INTO `seckill_message`
# VALUES ('3', '533324506110885888', '尊敬的用户你好，你已经成功注册！', '2019-01-11', '0', null, null, '0', null, null);
# INSERT INTO `seckill_message`
# VALUES ('4', '533324506110885888', '尊敬的用户你好，你已经成功注册！', '2019-01-11', '0', null, null, '0', null, null);
#
# -- ----------------------------
# -- Table structure for seckill_message_user
# -- ----------------------------
# DROP TABLE IF EXISTS `seckill_message_user`;
# CREATE TABLE `seckill_message_user`
# (
#     `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '消息主键',
#     `user_id`    bigint(20)      NOT NULL,
#     `message_id` bigint(50)      NOT NULL,
#     `good_id`    int(20) DEFAULT NULL,
#     `order_id`   int(20) DEFAULT NULL,
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   AUTO_INCREMENT = 15
#   DEFAULT CHARSET = utf8;
#
# -- ----------------------------
# -- Records of seckill_message_user
# -- ----------------------------
# INSERT INTO `seckill_message_user`
# VALUES ('1', '1', '222', '22', '2');
# INSERT INTO `seckill_message_user`
# VALUES ('11', '22', '533324506110885888', null, null);
# INSERT INTO `seckill_message_user`
# VALUES ('12', '22', '533324506110885888', null, null);
# INSERT INTO `seckill_message_user`
# VALUES ('13', '22', '533324506110885888', null, null);
# INSERT INTO `seckill_message_user`
# VALUES ('14', '22', '533324506110885888', null, null);
