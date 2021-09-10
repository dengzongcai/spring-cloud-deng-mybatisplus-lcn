/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : deng-account

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 07/09/2021 11:44:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_profit_type
-- ----------------------------
DROP TABLE IF EXISTS `account_profit_type`;
CREATE TABLE `account_profit_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `profit_type_desc` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收益/支出类型描述',
  `status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '状态 0.启用   1.未启用',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号，乐观锁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `profit_type_desc`(`profit_type_desc`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户收益/支出类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account_profit_type
-- ----------------------------
INSERT INTO `account_profit_type` VALUES (1, '服务收益', 0, NULL, '2021-08-12 17:25:50', NULL, NULL, 1);

-- ----------------------------
-- Table structure for account_system_role
-- ----------------------------
DROP TABLE IF EXISTS `account_system_role`;
CREATE TABLE `account_system_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_type_desc` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户类型描述',
  `status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '状态 0.启用   1.未启用',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号，乐观锁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_type_desc`(`role_type_desc`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户系统角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account_system_role
-- ----------------------------
INSERT INTO `account_system_role` VALUES (1, '营运公司', 0, NULL, NULL, '2021-08-04 16:26:44', NULL, NULL, 1);

-- ----------------------------
-- Table structure for account_system_role_and_account_type_relation
-- ----------------------------
DROP TABLE IF EXISTS `account_system_role_and_account_type_relation`;
CREATE TABLE `account_system_role_and_account_type_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `system_role_id` bigint NOT NULL COMMENT '账户系统角色id',
  `account_type_id` bigint NOT NULL COMMENT '账户类型id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号，乐观锁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `system_role_id`(`system_role_id`, `account_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户系统角色与账户关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account_system_role_and_account_type_relation
-- ----------------------------
INSERT INTO `account_system_role_and_account_type_relation` VALUES (1, 1, 1, '营运公司结算账户', NULL, '2021-08-04 16:30:33', NULL, NULL, 1);

-- ----------------------------
-- Table structure for account_type
-- ----------------------------
DROP TABLE IF EXISTS `account_type`;
CREATE TABLE `account_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_type_desc` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户类型描述',
  `status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '状态 0.启用   1.未启用',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号，乐观锁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_type_desc`(`account_type_desc`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account_type
-- ----------------------------
INSERT INTO `account_type` VALUES (1, '结算账户', 0, NULL, NULL, '2021-08-04 16:19:09', NULL, NULL, 1);

-- ----------------------------
-- Table structure for account_type_and_profit_type_relation
-- ----------------------------
DROP TABLE IF EXISTS `account_type_and_profit_type_relation`;
CREATE TABLE `account_type_and_profit_type_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_system_role_id` bigint NOT NULL COMMENT '账户系统角色id',
  `account_type_id` bigint NOT NULL COMMENT '账户类型id',
  `account_profit_type_id` bigint NOT NULL COMMENT '账户收益类型id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号，乐观锁用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `account_system_role_id`(`account_system_role_id`, `account_type_id`, `account_profit_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户类型与收益类型关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account_type_and_profit_type_relation
-- ----------------------------
INSERT INTO `account_type_and_profit_type_relation` VALUES (1, 1, 1, 1, '营运公司结算账户-结算账户', NULL, '2021-08-12 17:54:59', NULL, NULL, 1);

-- ----------------------------
-- Table structure for bus_brand_company_account
-- ----------------------------
DROP TABLE IF EXISTS `bus_brand_company_account`;
CREATE TABLE `bus_brand_company_account`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand_company_id` bigint NOT NULL COMMENT '品牌公司id',
  `account_type_id` bigint NOT NULL COMMENT '账户类型id',
  `account_balance` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '账户余额',
  `status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '状态 0.启用   1.未启用',
  `last_operation_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次操作对象',
  `last_trans_balance` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '上次交易余额',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号，乐观锁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `brand_company_id`(`brand_company_id`, `account_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司账户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bus_brand_company_account
-- ----------------------------
INSERT INTO `bus_brand_company_account` VALUES (1, 1, 1, 0.0000, 0, NULL, 0.0000, '营运公司结算账户', NULL, '2021-08-11 14:18:11', NULL, NULL, 1);

-- ----------------------------
-- Table structure for bus_brand_company_account_detail
-- ----------------------------
DROP TABLE IF EXISTS `bus_brand_company_account_detail`;
CREATE TABLE `bus_brand_company_account_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand_company_id` bigint NOT NULL COMMENT '品牌公司id',
  `account_type_id` bigint NOT NULL COMMENT '账户类型id',
  `income_pay_type` int NOT NULL COMMENT '收支类型 1.收入  2.支出',
  `income_pay_quota` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '收支额度',
  `income_pay_direction_account_id` bigint NULL DEFAULT NULL COMMENT '收支方向账户id',
  `income_pay_direction_detail_id` bigint NULL DEFAULT NULL COMMENT '收支方向明细id',
  `account_profit_type_id` bigint NOT NULL COMMENT '账户收益/支出类型id',
  `this_trans_balance` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '本地交易余额',
  `last_trans_balance` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '上次交易余额',
  `this_operation_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本次操作对象',
  `belong_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '来源id',
  `belong_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '来源类型,0.其他 1.城主订单号 2.VIP订单号 3.分红单号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号，乐观锁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司账户明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bus_brand_company_account_detail
-- ----------------------------

-- ----------------------------
-- Table structure for mb_user_account
-- ----------------------------
DROP TABLE IF EXISTS `mb_user_account`;
CREATE TABLE `mb_user_account`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '实体用户id',
  `account_type_id` bigint NOT NULL COMMENT '账户类型id',
  `account_balance` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '账户余额',
  `status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '状态 0.启用   1.未启用',
  `last_operation_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次操作对象',
  `last_trans_balance` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '上次交易余额',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号，乐观锁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`, `account_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户(实体)账户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mb_user_account
-- ----------------------------

-- ----------------------------
-- Table structure for mb_user_account_detail
-- ----------------------------
DROP TABLE IF EXISTS `mb_user_account_detail`;
CREATE TABLE `mb_user_account_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '实体用户id',
  `account_type_id` bigint NOT NULL COMMENT '账户类型id',
  `income_pay_type` int NOT NULL COMMENT '收支类型 1.收入  2.支出',
  `income_pay_quota` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '收支额度',
  `income_pay_direction_account_id` bigint NULL DEFAULT NULL COMMENT '收支方向账户id',
  `income_pay_direction_detail_id` bigint NULL DEFAULT NULL COMMENT '收支方向明细id',
  `account_profit_type_id` bigint NOT NULL COMMENT '账户收益/支出类型id',
  `this_trans_balance` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '本地交易余额',
  `last_trans_balance` decimal(20, 4) NOT NULL DEFAULT 0.0000 COMMENT '上次交易余额',
  `this_operation_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本次操作对象',
  `belong_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '来源id',
  `belong_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '来源类型,0.其他 1.城主订单号 2.VIP订单号 3.分红单号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号，乐观锁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户(实体)账户明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mb_user_account_detail
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
