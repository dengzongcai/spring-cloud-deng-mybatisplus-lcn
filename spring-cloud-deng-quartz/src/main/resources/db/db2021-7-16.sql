/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : test_quartz

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 16/07/2021 17:50:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for scheduled
-- ----------------------------
DROP TABLE IF EXISTS `scheduled`;
CREATE TABLE `scheduled`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `task_key` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务key值（使用bean名称）',
  `task_name` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `task_cron` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务表达式',
  `task_status` int NULL DEFAULT 0 COMMENT '状态：0.禁用; 1.启用',
  `gmt_created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_created_on` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编辑人',
  `gmt_updated_on` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_version` int NULL DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniqu_task_key`(`task_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务配置表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
